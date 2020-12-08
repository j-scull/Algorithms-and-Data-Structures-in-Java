package deque;

import deque.EmptyDequeException;

/*
 * A deque ADT
 * Like a queue but can remove or add from either end
 */
public interface Deque<E>{

	/*
	 * Returns the number of elements in the deque
	 * @return number of elements in the deque
	 */
	public int size();
	
	/*
	 * Checks if the deque is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty();
	
	/*
	 * Inspect the element at the front of the queue
	 * @return the first element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E front() throws EmptyDequeException;
	
	/*
	 * Inspects the element at the rear of the queue
	 * @return the last element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E rear() throws EmptyDequeException;
	
	/*
	 * Adds an element to the front of the deque
	 * @param element the element to be added
	 */
	public void insertFirst(E element); 
	
	/*
	 * Adds an element to the rear of the deque
	 * @param element the element to be added
	 */
	public void insertLast(E element);
	
	/*
	 * Removes an element from the front of the deque
	 * @return the first element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E removeFirst() throws EmptyDequeException; 
	
	/*
	 * Removes an element from the rear of the deque
	 * @return the last element in the queue
	 * @exception EmptyDequeException if the deque is empty
	 */
	public E removetLast() throws EmptyDequeException;
	
}
