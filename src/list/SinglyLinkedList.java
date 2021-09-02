package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Singly Linked List - p. 127 Data Structures and Algorithms in Java
 */
public class SinglyLinkedList<E> implements Iterable<E>, Cloneable{

	// Private Node Class
	private static class Node<E>{
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> next) {
			element = e;
			this.setNext(next);
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
	
	// Initial Linked List state
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	public SinglyLinkedList() {};
	
	// Access methods
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public E first() {
		if (isEmpty()) return null;
		return head.getElement();
	}
	public E last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}
	
	// Update methods
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if (size == 0) tail = head;
		size++;
	}
	
	public void addLast(E e) {
		Node<E> node = new Node<>(e, null);
		if (size == 0) 
			head = node;
		else
			tail.setNext(node);
		tail = node;
		size++;
	}
	
	public E removeFirst() {
		if (isEmpty()) return null;
		E element = head.getElement();
		head = head.getNext();
		if (size == 1)
			tail = null;
		size--;
		return element;
	}
	
	public E removeLast() {
		if (isEmpty()) return null;
		E element = tail.getElement();
		// O(n)
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			Node<E> walk = head;
			Node<E> next = head.getNext();
			while (next.getNext() != null) {
				walk = next;
				next = next.getNext();
			}
			walk.setNext(null);
			tail = walk;
		}		
		size--;
		return element;
	}

	// Iteration
	private class LinkedListIterator implements Iterator<E> {
		private Node<E> cursor = head;
		private Node<E> recent = null;
		private Node<E> previous = null;
		 
		public boolean hasNext() {
			return cursor != null;
		}
		
		public E next() throws NoSuchElementException {
			if (cursor == null)
				throw new NoSuchElementException("Nothing left");
			if (recent != null)
				previous = recent;
			recent = cursor;
			cursor = cursor.getNext();
			return recent.getElement();
		}
		
		public void remove() throws IllegalStateException {
			if (recent == null)
				throw new IllegalArgumentException("Nothing to remove");
			if (recent == head)
				removeFirst();
			else {
				if (recent == tail) {
					previous.setNext(null);
					tail = previous;
				}
				else {
					previous.setNext(cursor);
				}
				recent = null;
				size--;
			}
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	
	
	// Clone
	@SuppressWarnings("unchecked")
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();     // Use Object.clone() and safe cast
		// A shallow copy has been made, this.head == other.head and this.tail == other.tail
		// Need to create a copy of the original LinkedList without included any references to the original list
		if (size > 0) {
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();    // walk through original nodes
			Node<E> otherTail = other.head;   
			while (walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest);
				otherTail = newest;
				walk = walk.getNext();
			}
			other.tail = otherTail;
		}
		return other;
	}
	
	
	
}
