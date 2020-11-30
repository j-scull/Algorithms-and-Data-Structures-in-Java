package array_stack;

/*
 * Implements a stack ADT using a fixed length array
 * Throws a FullStackException if pushing to a full stack
 * Includes the main methods of the built-in class java.util.Stack
 */
public class ArrayStack<E> implements Stack<E>{

	public static final int CAPACITY = 1000;
	protected E[] data; // Generic array
	protected int top = -1; // index for the top of the stack
	
	/*
	 * Default constructor
	 */
	public ArrayStack() {
		this(CAPACITY);
	}
	
	/*
	 * Constructor with user defined capacity
	 */
	public ArrayStack(int cap) {
		data = (E[]) new Object[cap];
	}
	
	/*
	 * Returns the size of the stack
	 */
	public int size() {
		return top + 1;
	}
	
	/*
	 * Checks if the stack is empty
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/*
	 * Adds an element to the top of the stack
	 * Throws a FullStackException if maximum capacity has already been reached
	 * @param element the element to be added
	 */
	public void push(E element) throws FullStackException{
		if (size() == data.length)
			throw new FullStackException("Maximum capacity exceeded");
		data[++top] = element;
	}
	
	/*
	 * Returns the element at the top of the stack
	 * Throws an EmptyStackException if the stack is empty
	 * @return S[top] the element at the top of the stack
	 */
	public E top() throws FullStackException{
		if (isEmpty())
			throw new FullStackException("Stack is empty");
		return data[top];
	}
	
	/*
	 * Removes and returns the element at the of the stack
	 * Throws an EmptyStackException if the stack is empty
	 * @return element the element at the top of the stack
	 */
	public E pop() throws FullStackException {
		if (isEmpty())
			throw new FullStackException("Stack is empty");
		E element = data[top];
		// dereference top for garbage collection
		data[top] = null;
		top--;
		return element;
	}
	
	/*
	 * Returns a String representation of the elements in the stack
	 * @return s a String representing te elements in the stack
	 */
	public String toString() {
		String s = "[";
		for (int i = 0; i < size() - 1; i ++) {
			s += data[i] + ", ";
		}
		s += data[size()-1] + "]";
		return s;
	}
	
	/*
	 * Print out status information about operations and the stack
	 */
	public void status() {
		System.out.println("size = " + size());
		System.out.println("isEmpty = " + isEmpty());
		System.out.print("stack: " + this);
	}
	
}
