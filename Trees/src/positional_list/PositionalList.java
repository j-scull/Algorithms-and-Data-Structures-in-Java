package positional_list;

/*
 * Interface for PositionalLists
 */
public interface PositionalList <E>{
	
	/*
	 * @return the size of the list
	 */
	int size();
	
	/*
	 * Checks if the list is empty
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/*
	 * Returns the first element in the list, or null if empty
	 */
	Position<E> first();
	
	/*
	 * Returns the last element in the list, or null if empty
	 */
	Position<E> last();
	
	/*
	 * Returns the element before position p, or null if p is the first element
	 * @param p a position in the list
	 * @return the position before p
	 * @exception IllegalArgumentException
	 */
	Position<E> before(Position<E> p) throws IllegalArgumentException;
	
	/*
	 * Returns the element after position p, or null if p is the last element
	 * @param p a position in the list
	 * @return the position after p
	 * @exception IllegalArgumentException
	 */
	Position<E> after(Position<E> p) throws IllegalArgumentException;
	
	/*
	 * Adds element e to the front of the list
	 * @return e's new position
	 */
	Position<E> addFirst(E e);
	
	/*
	 * Adds element e to the end of the list
	 * @return e's new position
	 */
	Position<E> addLast(E e);
	
	/*
	 * Adds element e before Position p
	 * @return e's new Position
	 * @exception IllegalArgumentException
	 */
	Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
	
	/*
	 * Adds element e after Position p
	 * @return e's new Position
	 * @exception IllegalArgumentException
	 */
	Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
	
	/*
	 * Replaces the element stored at Position p
	 * @return the replaced element
	 * @exception IllegalArgumentException
	 */
	E set(Position<E> p, E e) throws IllegalArgumentException;
	
	/*
	 * Removes the element at Position p, invalidating it
	 * @return the element stored at Position p
	 * @exception IllegalArgumentException
	 */
	E remove(Position<E> p) throws IllegalArgumentException;
	
}
