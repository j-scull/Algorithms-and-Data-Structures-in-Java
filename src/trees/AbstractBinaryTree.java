package trees;

import array_list.ArrayList;
import queue.LinkQueue;
import queue.Queue;
import util.Position;

import java.util.Iterator;


/**
 * An abstract base for the Binary Tree ADT
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{

	/**
	 * Return position p's sibling 
	 * @param p a position
	 * @return p's sibling, or null if p has no sibling
	 */
	public Position<E> sibling(Position<E> p){
		Position<E> parent = parent(p);
		if (parent == null)
			return null;
		if (p == left(parent))
			return right(parent);
		else
			return left(parent);
	}
	
	/**
	 * Return the number of children of position p
	 * @param p a position
	 * @return the number of p's children
	 */
	public int numChildren(Position<E> p) {
		int count = 0;
		if (left(p) != null)
			count++;
		if (right(p) != null)
			count++;
		return count;
	}
	
	/**
	 * Return an iterable of Position p's children
	 */
	public Iterable<Position<E>> children(Position<E> p){
		ArrayList<Position<E>> list = new ArrayList<>(2);
		if (left(p) != null)
			list.add(left(p));
		if (right(p) != null)
			list.add(right(p));
		return list;
	}
	
	//------Nested ElementIterator class
	/** 
	 * Adapts the postions() iterator, returns elements
	 */
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = positions().iterator();
		public boolean hasNext() { return posIterator.hasNext(); }
		public E next() { return posIterator.next().getElement(); }
		public void remove() { posIterator.remove(); }
	}
	public Iterator<E> iterator() { return new ElementIterator(); }
	
	/** 
	 * Returns an iterable of positions in the tree
	 * There is a choice of traversal methods for the positions() method
	 */
	public Iterable<Position<E>> positions() { return preorder(); }
	
	
	
	//-------------------------------Preorder Traversal--------------------------
	// Recursively adds positions to the snapshot in preorder
	private void preorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) {
		snapshot.add(p);
		for (Position<E> c: children(p))
			preorderSubtree(c, snapshot);
	}
	// Returns an Iterable collection of positions in preorder
	public Iterable<Position<E>> preorder(){
		ArrayList<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			preorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	
	//-------------------------------Postorder Traversal--------------------------
	// Recursively adds positions to the snapshot in postorder
	private void postorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) {
		for (Position<E> c: children(p))
			postorderSubtree(c, snapshot);
		snapshot.add(p);
	}
	// Returns an Iterable collection of positions in postorder
	public Iterable<Position<E>> postorder(){
		ArrayList<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty())
			postorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	
	//-------------------------------Inorder Traversal--------------------------
	// Only applies to binary trees
	private void inorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) {
		if (left(p) != null)
			inorderSubtree(left(p), snapshot);
		snapshot.add(p);
		if (right(p) != null)
			inorderSubtree(right(p), snapshot);
	}
	public Iterable<Position<E>> inorder(){
		ArrayList<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			inorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	
	//----------------------------Breath First Traversal------------------------
	// Returns an Iterable collection of positions in breath first
	public Iterable<Position<E>> breathFirst(){
		ArrayList<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			Queue<Position<E>> queue = new LinkQueue<>();
			queue.enqueue(root());
			while (!queue.isEmpty()) {
				Position<E> p = queue.dequeue();
				snapshot.add(p);
				for (Position<E> c: children(p))
					queue.enqueue(c);
			}
		}
		return snapshot;
	}
	
	
	
	
	
	
}
