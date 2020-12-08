package deque;

public interface Position<E> {

	/*
	 * Return the element stored at this position
	 * @return the stored element
	 * @exception IllegalStateException if position no longer valid
	 */
	E getElement() throws IllegalStateException;
}
