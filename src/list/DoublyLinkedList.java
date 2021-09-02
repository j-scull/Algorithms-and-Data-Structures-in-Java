package list;

/**
 * Implementation of a Doubly LinkedList
 */
public class DoublyLinkedList<E>{

	//-------Nested Node Class---
	private static class Node<E> {
		private E element;
		public Node<E> prev;
		public Node<E> next;
		public Node(E e, Node<E> p, Node<E> n) {
			this.element = e;
			this.prev = p;
			this.next = n;
		}
		public E getElement() { return element; }
		public Node<E> getPrev() { return prev; }
		public Node<E> getNext() { return next; }
		public void setPrev(Node<E> p) { prev = p; }
		public void setNext(Node<E> n) { next = n; }
	}
	//---------------------------

	// Instance variables
	private Node<E> header;   // header and trailer nodes are sentinels,
	private Node<E> trailer;  // they do not contain elements
	private int size = 0;
	
	/** Construct an empty list */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}
	
	/** Returns the size of the Linked List */
	public int size() { return size; }
	
	/** Check if the linked list is empty */
	public boolean isEmpty() { return size == 0; }
	
	/** Returns, but does not remove the first element in the linked list */
	public E first() {
		if (isEmpty()) return null;
		return header.getNext().getElement();
	}
	
	/** Returns, but does not remove the last element in the linked list */
	public E last() {
		if (isEmpty()) return null;
		return trailer.getPrev().getElement();
	}
	
	/** Adds an element to the front of the linked list */
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}
	
	/** Adds an element to the end of a the linked list */
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
	}
	
	/** Removes and returns the element at the front of the linked list */
	public E removeFirst() {
		if(isEmpty()) return null;
		return remove(header.getNext());
	}
	
	/** Removes and returns the element at the end of the linked list */
	public E removeLast() {
		if(isEmpty()) return null;
		return remove(trailer.getPrev());
	}
	
	//-------Private utility methods-------
	
	/** Add an element betwen two given nodes */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> node = new Node<>(e, predecessor, successor);
		predecessor.setNext(node);
		successor.setPrev(node);
		size++;
	}
	
	/** Removes a given node from te Linked Linst and returns its element */
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}
	
	
	
}
