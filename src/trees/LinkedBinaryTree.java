package trees;

import util.Position;

/**
 * Binary Tree ADT using a Linked structure
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{

	//------------------Nested Node class-----------------
	protected static class Node<E> implements Position<E>{
		
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		
		// Constructor
		public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
			element = e;
			parent = p;
			left = l;
			right = r;
		}
		
		// Accessor methods
		public E getElement() {
			return element;
		}
		public Node<E> getParent(){
			return parent;
		}
		public Node<E> getLeft(){
			return left;
		}
		public Node<E> getRight(){
			return right;
		}
		
		// update methods
		public void setElement(E e) {
			element = e;
		}
		public void setParent(Node<E> n) {
			parent = n;
		}
		public void setLeft(Node<E> n) {
			left = n;
		}
		public void setRight(Node<E> n) {
			right = n;
		}
		
	}
	
	// Factory function to create a new node
	protected Node<E> createNode(E e, Node<E> p, Node<E> l, Node<E> r){
		return new Node<E>(e, p, l, r);
	}
	
	// LinkedNBinaryTree instance variables
	protected Node<E> root = null;
	private int size = 0;
	
	// Constructor
	public LinkedBinaryTree() {}
	
	// Non-public utilities
	/**
	 * Validates position p as a node
	 * @param p a position
	 * @return position p as a node
	 * @throws IllegalArgumentException
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Not a vaild position");
		Node<E> node = (Node<E>) p;
		if (node.getParent() == node)
			throw new IllegalArgumentException("Position is no longer valid");
		return node;
	}
	
	
	// Accessor methods - these haven't been implemented in AbstractBinaryTree
	
	// Return the number of elements in the tree
	public int size() {
		return size;
	}
	
	// Check if the tree is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Return the root of the tree
	public Position<E> root(){
		return root;
	}
	
	// Return the position of p's parent, or null
	public Position<E> parent(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return node.getParent();
	}
	
	// Return the position of p's left child, or null
	public Position<E> left(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return node.getLeft();
	}
	
	// Return the position of p's right child, or null
	public Position<E> right(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return node.getRight();
	}
	
	// Update methods - supported by this class
	/**
	 * Place an element at the root of an empty tree
	 * @param e an element
	 * @return the roots position
	 * @throws IllegalStateException if the tree is not empty
	 */
	public Position<E> addRoot(E e) throws IllegalStateException{
		if (!isEmpty())
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}
	
	/**
	 * Add a left child to position p
	 * @param p a position
	 * @param e the element stored at the left child
	 * @return the left child's position
	 * @throws IllegalStateException if p already has a left child
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> parent = validate(p);
		if (parent.getLeft() != null)
			throw new IllegalStateException("Postion already has a left child");
		Node<E> left = createNode(e, parent, null, null);
		parent.setLeft(left);
		size++;
		return left;
	}
	
	/**
	 * Add a right child to position p
	 * @param p a position
	 * @param e the element to be stored at the right child
	 * @return the right child's position
	 * @throws IllegalStateException if p already has a right child
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> parent = validate(p);
		if (parent.getRight() != null)
			throw new IllegalStateException("Position already has a right child");
		Node<E> right = createNode(e, parent, null, null);
		parent.setRight(right);
		size++;
		return right;
	}
	
	/**
	 * Replaces the element stored at position p
	 * @param p a position
	 * @param e the new element
	 * @return the original element
	 * @throws IllegalStateException
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		E original = node.getElement();
		node.setElement(e);
		return original;
	}
	
	/**
	 * Attaches trees t1 and t2 as left and right subtrees of external p
	 * @param p a position 
	 * @param t1 a tree
	 * @param t2 a tree
	 * @throws IllegalArgumentException if p is not external
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
														throws IllegalArgumentException{
		Node<E> node = validate(p);
		if (isInternal(p))
			throw new IllegalArgumentException("p must be a leaf node");
		size += t1.size() + t2.size();
		if (! t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.size = 0;
		}
		if (! t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.size = 0;
		}
			
	}
	
	/**
	 * Removes a node at position p and returns it's value
	 * @param p a position
	 * @return the element stored at p
	 * @throws IllegalArgumentException if p has two children
	 */
	public E remove(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		if (numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if (child != null)
			child.setParent(node.getParent());
		if (node == root)
			root = child;
		else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E element = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(child);   // convention for a defunct node
		return element;
	}
	
	// Have redone the traversal methods...
	// See AbstractBinaryTree class
	
//	//------nested iterator class-----------
//	// Implements a breath first traversal of the trees
//	private class PositionsIterator implements Iterator<Position<E>>{
//		
//		private LinkedPositionalList<Position<E>> list = new LinkedPositionalList<>();
//		private boolean removable = false;
//		private Position<E> last;
//		
//		public PositionsIterator() {
//			list.addFirst(root);
//		}
//		
//		public boolean hasNext() {
//			return list.size() > 0;
//		}
//		
//		public Position<E> next() throws NoSuchElementException{
//			if (!hasNext())
//				throw new NoSuchElementException("No next element");
//			Position<E> current = list.remove(list.first());
//			if (left(current) != null)
//				list.addLast(left(current));
//			if (right(current) != null)
//				list.addLast(right(current));
//			removable = true;
//			last = current;
//			return current;
//		}
//		
//		public void remove() throws IllegalStateException{
//			if (!removable)
//				throw new IllegalStateException("Nothing to remove");
//			LinkedBinaryTree.this.remove(last);
//			removable = false;
//		}	
//		
//	}
//	
//	// Nested positions iterable class-----------------
//	private class PositionsIterable implements Iterable<Position<E>>{
//		public Iterator<Position<E>> iterator(){
//			return new PositionsIterator();
//		}
//	}
//	// Return an iterable of the tree's positions
//	public Iterable<Position<E>> positions(){
//		return new PositionsIterable();
//	}
//	
//	// Nested element iterator class
//	private class ElementIterator implements Iterator<E>{
//		Iterator<Position<E>> posIterator = new PositionsIterator();
//		public boolean hasNext() { return posIterator.hasNext(); }
//		public E next() { return posIterator.next().getElement(); }
//		public void remove() { posIterator.remove(); }
//	}
//	// Returns an iterable of the tree's elements
//	public Iterator<E> iterator(){
//		return new ElementIterator();
//	}
//	
//	//--------Nested traversal classes-----------------------------
//	
//	
//	//------Implements a preorder traversal of the tree
//	private class PreorderIterator implements Iterator<Position<E>>{
//		
//		//private ArrayList<Position<E>> arr = new ArrayList<>(height(root) + 1);
//		private PositionalList<Position<E>> list = new LinkedPositionalList<>();
//		private boolean removable = false;
//		private Position<E> last = null;
//
//		public PreorderIterator() {
//			list.addFirst(root);
//		}
//		
//		public boolean hasNext() {
//			return list.size() != 0;
//		}
//		
//		public Position<E> next() throws NoSuchElementException{
//			
//			if (list.size() == 0)
//				throw new NoSuchElementException("No next element");
//			Position<E> current = list.remove(list.first());
//			if (right(current) != null)
//				list.addFirst(right(current));
//			if (left(current) != null)
//				list.addFirst(left(current));
//			last = current;
//			removable = true;
//			return current;
//		}
//		
//		public void remove() throws IllegalStateException{
//			if (!removable)
//				throw new IllegalStateException("Nothing to remove");
//			LinkedBinaryTree.this.remove(last);
//			removable = false;
//		}
//	}
//	
//	// Preorder iterable positions class
//	private class PreorderIterable implements Iterable<Position<E>>{
//		public Iterator<Position<E>> iterator() {
//			return new PreorderIterator();
//		}
//	}
//	
//	// Returns an preorder iterable of the tree's positions
//	public Iterable<Position<E>> preorder(){
//		return new PreorderIterable();
//	}
//	
//	
//	//-----Implements a postorder traversal of the tree
//	private class PostorderIterator implements Iterator<Position<E>>{
//		
//		private LinkedPositionalList<Position<E>> list = new LinkedPositionalList<>();
//		private Position<E> last = null;
//		private boolean removable = false;
//		
//		public PostorderIterator() {
//			list.addFirst(root);
//		}
//		
//		public boolean hasNext() {
//			return list.size() > 0;
//		}
//		
//		public Position<E> next() throws NoSuchElementException{
//			if (!hasNext())
//				throw new NoSuchElementException("No next element");
//			Position<E> current = list.first().getElement();
//			while (numChildren(current) > 0 && (last == null || last != right(current))) {
//				if (right(current) != null)
//					list.addFirst(right(current));
//				if (left(current) != null)
//					list.addFirst(left(current));
//				current = list.first().getElement();
//			}
//			current = list.remove(list.first());
//			last = current;
//			removable = true;
//			return current;
//		}
//		
//		public void remove() throws IllegalArgumentException{
//			if (!removable)
//				throw new IllegalArgumentException("Nothing to remove");
//			LinkedBinaryTree.this.remove(last);
//			removable = false;
//		}
//	}
//	
//	// Postorder iterable positions class
//	private class PostorderIterable implements Iterable<Position<E>>{
//		public Iterator<Position<E>> iterator(){
//			return new PostorderIterator();
//		}
//	}
//	
//	// Returns a postorder positions iterable
//	public Iterable<Position<E>> postorder(){
//		return new PostorderIterable();
//	}
//	
//	//----Inorder Traversal, specifically for binary trees
//	private class InorderIterator implements Iterator<Position<E>>{
//		
//		private LinkedPositionalList<Position<E>> list = new LinkedPositionalList<>();
//		private boolean removable;
//		private Position<E> last = null;
//		
//		public InorderIterator() {
//			list.addFirst(root);
//		}
//		
//		public boolean hasNext() {
//			return list.size() > 0;
//		}
//		
//		public Position<E> next() throws NoSuchElementException{
//			if (!hasNext())
//				throw new NoSuchElementException("No next element");
//			
////			Iterator<Position<E>> iter = list.iterator();
////			System.out.print("[");
////			int i = 0;
////			while (iter.hasNext()) {
////				if (i < list.size() - 1)
////					System.out.print(iter.next().getElement() + ", ");
////				else
////					System.out.print(iter.next().getElement() + "]\n");
////				i++;
////			}
//			
//			Position<E> current = list.first().getElement();
//			while (numChildren(current) > 0 && (list.size() == 1 || (last != left(current) && list.after(list.first()).getElement() != right(current)))) {
//				if (right(current) != null)
//					list.addAfter(list.first(), right(current));
//				if (left(current) != null)
//					list.addFirst(left(current));	
//				current = list.first().getElement();
//			}
//			
////			iter = list.iterator();
////			System.out.print("[");
////			i = 0;
////			while (iter.hasNext()) {
////				if (i < list.size() - 1)
////					System.out.print(iter.next().getElement() + ", ");
////				else
////					System.out.print(iter.next().getElement() + "]\n");
////				i++;
////			}
//			
//			current = list.remove(list.first());
//			last = current;
//			removable = true;
//			return current;
//		}
//		
//		public void remove() throws IllegalArgumentException{
//			if (!removable)
//				throw new IllegalArgumentException("Nothing to remove");
//			LinkedBinaryTree.this.remove(last);
//			removable = false;
//		}
//		
//	}
//	
//	// Inorder iterable positions class
//	private class InorderIterable implements Iterable<Position<E>>{
//		public Iterator<Position<E>> iterator(){
//			return new InorderIterator();
//		}
//	}
//	
//	// Returns an inorder positions iterable
//	public Iterable<Position<E>> inorder(){
//		return new InorderIterable();
//	}
//	
}
