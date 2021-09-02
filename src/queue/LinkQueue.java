package queue;

/*
 * Implements a Queue ADT using a linked list
 */
public class LinkQueue<E> implements Queue<E>{

	private Node front;
	private Node rear;
	private int size;
	
	public LinkQueue() {
		front = null;
		rear = null;
		size = 0;
	};
	
	/*
	 * Returns the number of elements in the queue
	 * @return number of elements in the queue
	 */
	public int size() {
		return size;
	};
	
	/*
	 * Checks if the queue is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	};
	
	/*
	 * Inspects the element at the front of the queue
	 * @return front the element at the front of the queue
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E front() throws EmptyQueueException{
		if (front == null)
			throw new EmptyQueueException("Queue is empty");
		return front.data;
	};
	
	/*
	 * Adds an element to the back of the queue
	 * @param element the element to be added
	 */
	public void enqueue(E element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = null;
		if (front == null)
			front = newNode;
		else
			rear.next = newNode;
		rear = newNode;
		size++;
	}; 
	
	/*
	 * Removes an element from the front of the queue
	 * @return element the element at the front of the queue
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException{
		if (front == null)
			throw new EmptyQueueException("Queue is empty");
		E element = front.data;
		front = front.next;
		if (front == null)
			rear = null;
		size--;
		return element;
	};
	
	
	/*
	 * Implements a node in the linked list
	 */
	class Node{
		
		public E data;
		public Node next;
		
	}
	
	/*
	 * A string representation of the queue
	 * @return result a string representation of the queue
	 */
	public String toString() {
		String result = "[";
		if (size > 0) {
			Node current = front;
			for (int i = 0; i < size-1; i++) {
				result += current.data + ", ";
				current = current.next;
			}
			result += current.data;
		}
		result += "]";
		return result;
	}
	
}
