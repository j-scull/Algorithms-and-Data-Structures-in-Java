package positional_list;

import util.Position;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * A doubly linked list implementation of a positional list
 */
public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E>{

	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;
	
	/*
	 * Constructor for LinkedPostionalList
	 */
	public LinkedPositionalList() {
		header = new Node<E>(null, null, null);
		trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}
	
	// Public accessor methods
	 
	/**
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Checks if the list is empty
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Get the first Position
	 * @return the first element in the list, or null if empty
	 */
	public Position<E> first(){
		return position(header.getNext());
	}
	
	/**
	 * Get the last Position
	 * @return the last element in the list, null if empty
	 */
	public Position<E> last(){
		return position(trailer.getPrevious());
	}
	
	/**
	 * Get the Position before a given Position
	 * @param p the position
	 * @return the Position before p
	 * @exception IllegalArgumentException
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return position(node.getPrevious());
	}
	
	/**
	 * Get the Position after a given Position
	 * @param p the position
	 * @return the position after p
	 * @exception IllegalArgumentException
	 */
	public Position<E> after(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	
	// Public update methods
	
	/**
	 * Insert an element at the front of the list
	 * @param e the element to be inserted
	 * @return e's new position
	 */
	public Position<E> addFirst(E e){
		return addBetween(e, header, header.getNext());
	}
	
	/**
	 * Insert an element at the end of the list
	 * @param e the element to be inserted
	 * @return e's new position
	 */
	public Position<E> addLast(E e){
		return addBetween(e, trailer.getPrevious(), trailer);
	}
	
	/**
	 * Adds an element before Position p
	 * @param e the element to be added
	 * @param p the Position
	 * @return e's new Position
	 * @exception IllegalArgumentException
	 */
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return addBetween(e, node.getPrevious(), node);
	}
	
	/**
	 * Adds an element after Position p
	 * @param e the element to be added
	 * @param p the Position
	 * @return e's new Position
	 * @exception IllegalArgumentException
	 */
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}
	
	/**
	 * Sets the value at Position p
	 * @param p the Position
	 * @param e the new value
	 * @return the original element stores at p
	 * @exception IllegalArgumentException
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		E replaced = node.getElement();
		node.setElement(e);
		return replaced;
	}
	
	/**
	 * Removes the element at Position p
	 * @param p the Position
	 * @return the element stored at p
	 * @exception IllegalArgumentException
	 */
	public E remove(Position<E> p) throws IllegalArgumentException{
		Node<E> node = validate(p);
		Node<E> previous = node.getPrevious();
		Node<E> next = node.getNext();
		previous.setNext(next);
		next.setPrevious(previous);
		size--;
		E element = node.getElement();
		node.setElement(null);
		node.setPrevious(null);
		node.setNext(null);
		return element;
	}
	
	
	// private utilities
	
	/**
	 * Validates a position
	 * @return the position's Node
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if (!(p instanceof Node ))
			throw new IllegalArgumentException("Invalid position p");
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null) // this is the convention for Nodes
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	
	/**
	 * @return the given Node as a Position, or null if it is a sentinel
	 */
	private Position<E> position(Node<E> node){
		if (node == header || node == trailer)
			return null;  // sentinels shouldn't be exposed to users
		return node;
	}
	
	/**
	 * Adds an element e between two nodes
	 * @return the new Position of e
	 */
	private Position<E> addBetween(E e,  Node<E> pred, Node<E> succ){
		Node<E> newNode = new Node<>(e, pred, succ);
		succ.setPrevious(newNode);
		pred.setNext(newNode);
		size++;
		return position(newNode);
	}
	
	
	/*
	 * Nested Node class--------------------------------
	 */
	private static class Node<E> implements Position<E>{
		
		private E element;
		private Node<E> next;
		private Node<E> previous;
		
		/*
		 * Node constructor
		 */
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			previous = p;
			next = n;
		}
		
		/*
		 * Returns the element stored by the Node
		 */
		public E getElement() throws IllegalStateException{
			if (next == null)
				throw new IllegalStateException("Position is no longer valid");
			return element;
		}
		
		/*
		 * Returns the next Node
		 */
		public Node<E> getNext(){
			return next;
		}
		
		/*
		 * Returns the previous Node
		 */
		public Node<E> getPrevious(){
			return previous;
		}
		
		/*
		 * Sets the value of the Node
		 */
		public void setElement(E e) {
			element = e;
		}
		
		/*
		 * Sets the next Node
		 */
		public void setNext(Node<E> n){
			next = n;
		}
		
		/*
		 * Sets the previous Node
		 */
		public void setPrevious(Node<E> p) {
			previous = p;
		}
		
	}	
	
	// Nested PostionIteratr class---------------
	private class PositionIterator implements Iterator<Position<E>>{
		
		private Position<E> cursor = first();
		private Position<E> recent = null;
		
		/**
		 * Check if the iterator has a next object
		 * @returns true if the iterator has a a next Position, false otherwise
		 * 
		 */
		public boolean hasNext() {
			return cursor != null;
		}
		
		/**
		 * @return the next Position in the iterator
		 * @exception NoSuchElementException if there is no next Position
		 */
		public Position<E> next() throws NoSuchElementException{
			if (cursor == null)
				throw new NoSuchElementException("Nothing left");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
		
		/**
		 * Remove the element return by the most recent call to next
		 * Can only be called after next
		 * @exception IllegalStateException if recent is null
		 */
		public void remove() throws IllegalStateException{
			if (recent == null)
				throw new IllegalArgumentException("Nothing to remove");
			LinkedPositionalList.this.remove(recent); // remove from outer list
			recent = null;							  // set recent to null	
		}
		
	}
	
	// Nested PositionIterable class----------------------
	private class PositionIterable implements Iterable<Position<E>>{
		public Iterator<Position<E>> iterator(){
			return new PositionIterator();
		}
	}
	
	/*
	 * Returns an iterable of the list's Positions
	 */
	public Iterable<Position<E>> positions(){
		return new PositionIterable();
	}
	
	// Nested ElementIterator class
	/*
	 * Adapts the iterator produced by positions() to return elements
	 */
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = new PositionIterator();
		public boolean hasNext() { return posIterator.hasNext(); }
		public E next() { return posIterator.next().getElement(); }
		public void remove() { posIterator.remove(); }
	}
	
	/*
	 * Returns an iterator for elements
	 */
	public Iterator<E> iterator(){
		return new ElementIterator();
	}
	
}
