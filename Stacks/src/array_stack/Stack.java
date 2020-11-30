package array_stack;

/*
 * Interface contains the mian stack operations
 */
public interface Stack<E> {
	
	/*
	 * Returns the number of element s in the stack
	 * @return number of elements in the stack
	 */
	public int size();
	
	/*
	 * Checks if the stack is empty
	 * @return true if there are no elements in the stack
	 */
	public boolean isEmpty();
	
	/*
	 * Adds a new element to the stack
	 * @param e the element to be added
	 */
	public void push(E e);
	
	/*
	 * Returns the value at the top of the stack, without removing it
	 * @return top the element at the top of the stack
	 */
	public E top() throws EmptyStackException;
	
	/*
	 * Returns and removes the element at the top f the stack
	 * @return top the element at the top of the stack
	 */
	public E pop() throws EmptyStackException;
	

}
