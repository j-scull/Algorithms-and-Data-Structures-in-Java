package util;

public interface Position<E> {

	/*
	 * Returns the element stored at this position
	 * @return the stored element
	 * @exception IllegalStateException 
	 */
	E getElement() throws IllegalStateException;
	
}
