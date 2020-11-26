package trees;

import java.util.Iterator;

/**
 * Defines a Tree ADT
 * @author User
 *
 * @param <E>
 */
public interface Tree<E> extends Iterable<E>{

	/**
	 * Returns the root of the tree
	 * @return the root position
	 */
	Position<E> root();
	
	/**
	 * Get the parent of a given posiion
	 * @param p a position
	 * @return p's parent a position
	 * @throws IllegalArgumentException
	 */
	Position<E> parent(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Returns an iterable collection containing the children of p
	 * The collection is in order if the tree is ordered
	 * @param p a position
	 * @returnan iterable collection
	 * @throws IllegalArgumentException
	 */
	Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Returns the number of children of position p
	 * @param p a position
	 * @return the number of p's children
	 * @throws IllegalArgumentException
	 */
	int numChildren(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Check if position p is internal
	 * @param p a position
	 * @return true if p has at least one child position, false otherwise
	 * @throws IllegalArgumentException
	 */
	boolean isInternal(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Check if position p is external,  i.e. a leaf node
	 * @param p a position
	 * @return true if p has no children, false otherwise
	 * @throws IllegalArgumentException
	 */
	boolean isExternal(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Checks if position p is the root
	 * @param p a position
	 * @return true if p is the root, false otherwise
	 * @throws IllegalArgumentException
	 */
	boolean isRoot(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Get the size of the tree
	 * @return the number of positions in the tree
	 */
	int size();
	
	/**
	 * Check if the tree is empty
	 * @return true if the tree does not contain any elements, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * @return an iterator for all the elements in the tree 
	 */
	Iterator<E> iterator();
	
	
	/**
	 * 
	 * @return an iterable collection of all elements of the tree
	 */
	Iterable<Position<E>> positions();
	
	
}
