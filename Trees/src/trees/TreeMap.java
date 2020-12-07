package trees;

import java.util.Comparator;
import maps.*;
import array_list.*;

/**
 * Implements a sorted map using a binary tree
 * see https://github.com/xuelvming/DataStructuresandAlgorithmsinJava6thEditionInternationalStudent/blob/master/src/main/java/net/datastructures/TreeMap.java
 * @author User
 *
 * @param <K>
 * @param <V>
 */
public class TreeMap<K,V> extends AbstractSortedMap<K,V> {

	//--------------Nested BalanceableBinaryTree class------------------------------------------
	/**
	 * Specialised version of a LinkedBinaryTree with additional mutators 
	 * and specialised Node class capable of balancing data
	 */
	protected static class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>>{
		//--------------Nested BSTNode class--------------
		protected static class BSTNode<E> extends Node<E> {
			int aux = 0;
			BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild){
				super(e, parent, leftChild, rightChild);
			}
			public int getAux() { return aux; }
			public void setAux(int value) { aux = value; }
		}//-----------------------------------------------
		
		//
		public int getAux(Position<Entry<K,V>> p) {
			return ((BSTNode<Entry<K,V>>)p).getAux();
		}
		
		//
		public void setAux(Position<Entry<K,V>> p, int value) {
			((BSTNode<Entry<K,V>>)p).setAux(value);
		}
		
		// Override factory function to create BSTNode
		@Override
		protected Node<Entry<K,V>> createNode(Entry<K,V> e, Node<Entry<K,V>> parent, Node<Entry<K,V>> left, Node<Entry<K,V>> right){
			return new BSTNode<>(e ,parent, left, right);
		}
		
		// Re-links a parent to its oriented child node
		private void relink(Node<Entry<K,V>> parent, Node<Entry<K,V>> child, boolean makeLeftChild) {
			child.setParent(parent);
			if (makeLeftChild)
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		
		/**
		 * Rotates a position p above its parent
		 * Cannot be called if p is the root
		 */
		public void rotate(Position<Entry<K,V>> p) {
			if (p == root())
				return;
			Node<Entry<K,V>> x = validate(p);
			Node<Entry<K,V>> y = x.getParent();
			Node<Entry<K,V>> z = y.getParent(); //Possibly null
			if (z == null) {
				root = x;
				x.setParent(null);
			} else 
				relink(z, x, y == z.getLeft());
			// rotate x and y
			if (x == y.getLeft()) {
				relink(y, x.getRight(), true);  
				relink(x, y, false);
			} else {
				relink(y, x.getLeft(), false);
				relink(x, y, true);
			}
		}
		
		/**
		 * Returns the position that becomes the root of the restructured subtree
		 * @param x a position in the tree, which must have a grandparent
		 * @return the new root of the subtree
		 */
		public Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){
			Position<Entry<K,V>> y = parent(x);
			if (y == null || parent(y) == null)
				return null;
			Position<Entry<K,V>> z = parent(y);
			if ((x == right(y)) == (y == right(z))) { // Positions are all aligned to left of right
				rotate(y);
				return y;
			} else {
				rotate(x);     // double rotation of x
				rotate(x);
				return x;
			}
		}//------------------------------------------------------------------------------------
		
		
	}
	
	
	protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();
	
	// Default constructor using the natural ordering of keys
	public TreeMap() {
		super();
		tree.addRoot(null);
	}
	/**
	 * Constructor with user defined comparator
	 * @param comp the comparator used to order the map
	 */
	public TreeMap(Comparator<K> comp) {
		super(comp);
		tree.addRoot(null);
	}
	
	// Get the number of elements in the tree
	public int size() {
		// external nodes do not have entries
		return (tree.size() - 1 ) / 2;
	}
	
	// Utility used when inserting a new entry at the leaf of a tree
	private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
		tree.set(p, entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}
	
	// Notational Shorthands
	protected Position<Entry<K,V>> root() { return tree.root; }
	protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p) { return tree.parent(p); } 
	protected Position<Entry<K,V>> left(Position<Entry<K,V>> p) { return tree.left(p); }
	protected Position<Entry<K,V>> right(Position<Entry<K,V>> p) { return tree.right(p); }
	protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p) { return tree.sibling(p); }
	protected boolean isRoot(Position<Entry<K,V>> p) { return tree.isRoot(p); }
	protected boolean isExternal(Position<Entry<K,V>> p) { return tree.isExternal(p); }
	protected boolean isInternal(Position<Entry<K,V>> p) { return tree.isInternal(p); }
	protected void set(Position<Entry<K,V>> p, Entry<K,V> e) { tree.set(p,  e); }
	protected Entry<K,V> remove(Position<Entry<K,V>> p) { return tree.remove(p); }
	protected void rotate(Position<Entry<K,V>> p) { tree.rotate(p); }
	protected Position<Entry<K,V>> restructure(Position<Entry<K,V>> p) { return tree.restructure(p); }
	
	/**
	 * Returns the position having the given key of terminal leaf in the subtree formed at p 
	 * @param p the root of the subtree
	 * @param key the key being searched for
	 * @return the position having the key, of the last node reached during the search
	 */
	private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key){
		if (isExternal(p))
			return p;
		int comp = compare(key, p.getElement());
		if (comp == 0)
			return p;
		else if (comp < 0)
			return treeSearch(left(p), key);
		else
			return treeSearch(right(p), key);
	}
	
	/**
	 * Returns the element with the minimal key in the subtree rooted at p
	 * @param p the root of a subtree
	 * @return the position with the minimal in the subtree 
	 */
	protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p){
		Position<Entry<K,V>> walk = p;
		while (isInternal(walk))
			walk = left(walk);
		return parent(walk); // get parent as leaf nodes are null
	}
	
	/**
	 * Returns the element with the maximum key in the subtree rooted at p
	 * @param p the root of a subtree
	 * @return the position with the maximum in the subtree 
	 */
	protected Position<Entry<K,V>>  treeMax(Position<Entry<K,V>> p){
		Position<Entry<K,V>> walk = p;
		while(isInternal(walk))
			walk = right(walk);
		return parent(walk);
	}
	
	/**
	 * Get the value associated with an element
	 * @param key, the key
	 * @return the value associated with a key, or null if the key is not in the tree
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	public V get(K key) throws IllegalArgumentException{
		checkKey(key);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		rebalanceAccess(p);  // a hook for balanced tree subclass
		if(isExternal(p)) return null;
		return p.getElement().getValue();
	}
	
	/**
	 * Add a new key value pair to the TreeMap, of update an already existing key-value pair
	 * @param key the key
	 * @param value the value associated with the key
	 * @return the value originally paired with the key, or null if the key is new to the treemap 
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	public V put(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Entry<K,V> newEntry = new MapEntry<>(key, value);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isExternal(p)) {
			expandExternal(p, newEntry);
			rebalanceInsert(p);  // a hook for balanced tree subclass
			return null;
		} else {
			V old = p.getElement().getValue();
			set(p, newEntry);
			rebalanceAccess(p);  // a hook for balanced tree subclass
			return old;
		}
	}
	
	/**
	 * Removes the entry with the specified key
	 * @param key the key to be removed
	 * @return the value associated with the key, or null if the key is not found
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	public V remove(K key) throws IllegalArgumentException{
		checkKey(key);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isExternal(p)) {
			rebalanceAccess(p);   // a hook for balanced tree subclass
			return null;
		} else {
			V old = p.getElement().getValue();
			if (isInternal(left(p)) && isInternal(right(p))) {
				Position<Entry<K,V>> replacement = treeMax(left(p));
				set(p, replacement.getElement());
				p = replacement;
			}
			// a p is the maximum of the subtree, it can have at most one internal child node
			Position<Entry<K,V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
			Position<Entry<K,V>> sib = sibling(leaf);
			remove(leaf);
			remove(p);    // sib is promoted to p's position, recursive remove call 
			rebalanceDelete(sib);  // a hook for balanced tree subclass
			return old;
		}
	}
	
	/**
	 * Returns the Entry with the smallest key, or null if empty
	 */
	public Entry<K,V> firstEntry(){
		if(isEmpty()) return null;
		return treeMin(root()).getElement();	
	}
	
	/**
	 * Returns the Entry with the greatest key, or null if empty
	 */
	public Entry<K,V> lastEntry(){
		if(isEmpty()) return null;
		return treeMax(root()).getElement();
	}
	
	/**
	 * Returns the entry with the least key greater than or equal to a given key
	 * @param key, the key
	 * @return the entry with the least key greater than or equal to a given key, or null if none is found
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isInternal(p)) return p.getElement();
		while(!isRoot(p)) {
			if (p == left(parent(p)))
				return parent(p).getElement();
			p = parent(p);
		}
		return null;
	}
	
	/**
	 * Find the entry with the key greatest key less than or equal to a given key
	 * @param key, the key
	 * @return the entry with the key greatest key less than or equal to a given key, or null if none is found
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	public Entry<K,V> floorEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isInternal(p)) return p.getElement();
		while(!isRoot(p)) {
			if (p == right(parent(p)))
				return parent(p).getElement();
			else
				p = parent(p);
		}
		return null;
	}
	
	/**
	 * Find the entry with the key greatest key strictly less than a given key
	 * @param key, the key
	 * @return the entry with the key greatest key strictly less than a given key, or null if none is found
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isInternal(p) && isInternal(left(p))) // an exact match is found, so get the maximum of the sub tree
			return treeMax(left(p)).getElement();
		while(!isRoot(p)) {
			if (p == right(parent(p)))
				return parent(p).getElement();
			else
				p = parent(p);
		}
		return null;
	}
	
	/**
	 * Return the entry with the least key strictly greater than a given key
	 * @param key, the key
	 * @return the entry with the key greatest key strictly less than a given key, or null if none is found
	 * @throws IllegalArgumentException if the key is incompatible with the map
	 */
	@Override
	public Entry<K,V> higherEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if (isInternal(p) && isInternal(right(p))) 
			return treeMin(right(p)).getElement();
		while(!isRoot(p)) {
			if (p == left(parent(p)))
				return parent(p).getElement();
			else
				p = parent(p);
		}
		return null;
	}
	
	// Iteration
	
	/**
	 * Returns anIterable collection of all key-value entries in the tree map, using inorder traversal
	 * 
	 */
	@Override
	public Iterable<Entry<K,V>> entrySet(){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
		for (Position<Entry<K,V>> p : tree.inorder())
			if (isInternal(p)) buffer.add(p.getElement());
		return buffer;
	}
	
	
	/**
	 * Returns an iterable containing all entries with keys such that: fromKey <= key < toKey
	 */
	@Override
	public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException{
		checkKey(fromKey);
		checkKey(toKey);
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
		if (compare(fromKey, toKey) < 0)
			subMapRecursive(fromKey, toKey, root(), buffer);
		return buffer;
	}
	
	// Utility function to recursively fill the submap
	private void subMapRecursive(K fromKey, K toKey, Position<Entry<K,V>> p, ArrayList<Entry<K,V>> buffer) {
		if (isInternal(p)) {
			if (compare(p.getElement(), fromKey) < 0) // p's key is less than fromKey
				subMapRecursive(fromKey, toKey, right(p), buffer);
			else {
				subMapRecursive(fromKey, toKey, left(p), buffer); // check for lower keys to the left
				if (compare(p.getElement(), toKey) < 0) {
					buffer.add(p.getElement());
					subMapRecursive(fromKey, toKey, right(p), buffer);
				}
			}
		}
	}
	
	
	// Stubs for balanced search tree operations, subclasses can override these
	/**
	 * Re-balances the tree after an insertion of a position
	 * The function does nothing for this class
	 * @param p a position which was inserted
	 */
	protected void rebalanceInsert(Position<Entry<K,V>> p) {}
	
	/**
	 * Re-balances the tree after an insertion of a position
	 * The function does nothing for this class
	 * @param p a position which was inserted
	 */
	protected void rebalanceDelete(Position<Entry<K,V>> p) {}
	
	/**
	 * Re-balances the tree after the child of a position has been removed
	 * The function does nothing for this class
	 * @param p the position of the sibling of the removed entry
	 */
	protected void rebalanceAccess(Position<Entry<K,V>> p) {}
	
	// Prints a representation of the tree for debugging
	protected void dump() {
		dumpRecursive(root(), 0);
	}
	
	// Recursively prints entries in the tree
	private void dumpRecursive(Position<Entry<K,V>> p, int depth) {
		String indent = (depth == 0 ? "" : String.format("%" + (2*depth) + "s", ""));
		if (isExternal(p))
			System.out.println(indent + "leaf");
		else {
			System.out.println(indent + p.getElement());
			dumpRecursive(left(p), depth + 1);
			dumpRecursive(right(p), depth + 1);
		}
	}
	
	
}
