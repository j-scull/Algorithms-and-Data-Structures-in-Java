package queue;

/*
 * Implements a Queue ADT
 * Uses a circular array
 * Finite capacity - can be determined by user
 */
public class ArrayQueue<E> implements Queue<E>{

	private int N;
	public static final int CAPACITY = 1000;
	private E[] arr;
	private int size;
	private int front;  //points to the front of the queue
	private int rear;   // points to the end of the queue
	
	
	/*
	 * Construct the ArrayQueue - default
	 */
	public ArrayQueue() {
		this(CAPACITY);
	}
	
	/*
	 * Construct the ArrayQueue with capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int cap) {
		N = cap;
		arr = (E[]) new Object[N];
		size = 0;
		front = 0;
		rear = 0;
	}
	
	
	/*
	 * Returns the number of elements in the queue
	 * @return number of elements in the queue
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Checks if the queue is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Inspects the element at the front of the queue
	 * @return front the element at the front of the queue
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E front() throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException("Empty queue");
		return arr[front];
	}
	
	/*
	 * Adds an element to the back of the queue
	 * @param element the element to be added
	 */
	public void enqueue(E element) throws FullQueueException{
		if (size() == N)
			throw new FullQueueException("Queue is full");
		arr[rear] = element;
		rear = (rear + 1) % N;
		size++;
	}
	
	/*
	 * Removes an element from the front of the queue
	 * @return element the element at the front of the queue
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException("Queue is empty");
		E element = arr[front];
		arr[front] = null;
		front = (front + 1) % N;
		size--;
		return element;
	}
	
	/*
	 * A string representation of the Queue
	 * @return result a string representation of the queue
	 */
	public String toString() {
		String result = "[";
		if (size > 0) {
			int i = 0;
			for (; i < size - 1; i++) {
				result += arr[(front + i) % N] + ", ";
			}
			result += arr[(front + i) % N];
		}
		result += "]";
		return result;
	}
	
	
}
