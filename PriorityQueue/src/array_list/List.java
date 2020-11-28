package array_list;

public interface List<E> {

	/* Returns the size of the list
	 * @return the number of elements in the list
	 */
	int size();
	
	/* Checks if the list is empty
	 * @return true if the list is empty, false otherwise
	 */
	boolean isEmpty();
	
	/* Retrieves the element at the given index
	 * @param i the i of the element
	 * @return the element
	 * @exception IndexOutOfBoundsException if i is not in the list
	 */
	E get(int i) throws IndexOutOfBoundsException;
	
	/* Sets the element at the given index
	 * @param i the i of the element
	 * @param the element to be added
	 * @exception IndexOutOfBoundsException if i is not in the list
	 */
	E set(int i, E element) throws IndexOutOfBoundsException;
	
	/* Add an element at the given index
	 * @param i the where the element is to be added
	 * @param the element to be added
	 * @exception IndexOutOfBoundsException if i is not in the list
	 */
	void add(int i, E element) throws IndexOutOfBoundsException;
	
	/* Remove an element at the given index
	 * @param i the where the element is to be removed
	 * @exception IndexOutOfBoundsException if i is not in the list
	 */
	E remove(int i) throws IndexOutOfBoundsException;
	
	
}
