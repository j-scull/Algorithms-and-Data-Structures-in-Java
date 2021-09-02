package queue;

/*
 * Represents a Queue ADT
 */
public interface Queue<E> {

	/*
	 * Returns the number of elements in the queue
	 * @return number of elements in the queue
	 */
	public int size();
	
	/*
	 * Checks if the queue is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty();
	
	/*
	 * Inspects the element at the front of the queue
	 * @return front the element at the front of the queue
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E front() throws EmptyQueueException;
	
	/*
	 * Adds an element to the back of the queue
	 * @param element the element to be added
	 */
	public void enqueue(E element); 
	
	/*
	 * Removes an element from the front of the queue
	 * @return element the element at the front of the queue
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException;
	
}
