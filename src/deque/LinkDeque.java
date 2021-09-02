package deque;

/**
 * An implementation of a Deque using a linked list structure
 */
public class LinkDeque<E> implements Deque<E>{

	private Node<E> front;
	private Node<E> rear;
	private int size = 0;
	
	/*
	 * Construct the Queue
	 */
	public LinkDeque() {
		front = null;
		rear = null;
	}
	
	/*
	 * Returns the number of elements in the deque
	 * @return number of elements in the deque
	 */
	public int size() {
		return size;
	};
	
	/*
	 * Checks if the deque is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	};
	
	/*
	 * Inspect the element at the front of the queue
	 * @return the first element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E front() throws EmptyDequeException{
		if (front == null)
			throw new EmptyDequeException("Deque is empty");
		return front.getElement();
	};
	
	/*
	 * Inspects the element at the rear of the queue
	 * @return the last element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E rear() throws EmptyDequeException{
		if (rear == null)
			throw new EmptyDequeException("Deque is empty");
		return rear.getElement();
	};
	
	/*
	 * Adds an element to the front of the deque
	 * @param element the element to be added
	 */
	public void insertFirst(E element) {
		Node<E> newNode = new Node<E>();
		newNode.setElement(element);
		newNode.setNext(front);
		front = newNode;
		if (rear == null)
			rear = newNode;
		size++;
	}; 
	
	/*
	 * Adds an element to the rear of the deque
	 * @param element the element to be added
	 */
	public void insertLast(E element) {
		Node<E> newNode = new Node<E>();
		newNode.element = element;
		newNode.setPrevious(rear);
		rear.setNext(newNode);
		rear = newNode;
		if (front == null)
			front = newNode;
		size++;
	};
	
	/*
	 * Removes an element from the front of the deque
	 * @return the first element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E removeFirst() throws EmptyDequeException{
		if (front == null)
			throw new EmptyDequeException("Deque is empty");
		E element = front.getElement();
		front = front.getNext();
		if (size == 1) {
			front = null;
			rear = null;
		}
		size--;
		return element;
	}; 
	
	/*
	 * Removes an element from the rear of the deque
	 * @return the last element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E removetLast() throws EmptyDequeException{;
		if (rear == null)
			throw new EmptyDequeException("Deque is empty");
		E element = rear.getElement();
		rear = rear.getPrevious();
		if (size == 1) {
			front = null;
			rear = null;
		}
		size--;
		return element;
	}
	
	/*
	 * A string representation of the queue
	 * @return result a string representation of the queue
	 */
	public String toString() {
		String result = "[";
		if (size > 0) {
			Node<E> current = front;
			for (int i = 0; i < size-1; i++) {
				result += current.element + ", ";
				current = current.next;
			}
			result += current.element;
		}
		result += "]";
		return result;
	}
	
	/*
	 * Nested Node class
	 * Implements a Node in the linked list implementation of the queue
	 */
	private static class Node<E>{
		
		public Node<E> next;
		public Node<E> previous;
		public E element;
		
		public Node() {
			next = null;
			previous = null;
			element = null;;
		}
		
		/*
		 * @return element the data stored by the node
		 */
		public E getElement(){
			return element;
		}
		
		/*
		 * Get the next Node
		 * @return the next Node
		 */
		public Node<E> getNext(){
			return next;
		}
		
		/*
		 * Get the previous Node
		 * @return the previous Node
		 */
		public Node<E> getPrevious(){
			return previous;
		}
		
		/*
		 * Set the value of the element
		 * @param e the element
		 */
		public void setElement(E e) {
			element = e;
		}
		
		/*
		 * Set the next Node
		 * @param the next Node
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
		
		/*
		 * Set the previous Node
		 * @param the previous Node
		 */
		public void setPrevious(Node<E> n) {
			previous = n;
		}
		
	}
	
	
}
