package trees;

import util.Position;

/**
 * Binary tree ADT
 */
public interface BinaryTree<E> extends Tree<E>{

	/**
	 * Return position p's left child
	 * @param p a position
	 * @return p's left child, or null if no child exists
	 * @throws IllegalArgumentException
	 */
	Position<E> left(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Return position p's right child
	 * @param p a position
	 * @return p's right child, or null if no child exists
	 * @throws IllegalArgumentException
	 */
	Position<E> right(Position<E> p) throws IllegalArgumentException;
	
	/**
	 * Return position p's sibling
	 * @param p a position
	 * @return p's sibling, or null if no sibling exists
	 * @throws IllegalArgumentException
	 */
	Position<E> sibling(Position<E> p) throws IllegalArgumentException;
	
}
