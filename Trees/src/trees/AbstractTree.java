package trees;

/**
 * Abstract base class for the tree ADT
 * @author User
 *
 */
public abstract class AbstractTree<E> implements Tree<E>{
	
	public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
	public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
	public boolean isRoot(Position<E> p) { return p == root(); }
	public boolean isEmpty() { return size() == 0; }
	
	/**
	 * Return the depth of position p
	 * @param p a position
	 * @return the depth of p
	 */
	public int depth(Position<E> p) {
		if (isRoot(p))
			return 0;
		return 1 + depth(parent(p));
	}
	
	/**
	 * Returns the height pf the subtree rooted at position p
	 * @param p a position
	 * @return the height of p
	 */
	public int height(Position<E> p) {
		int h = 0;
		for (Position<E> c: children(p))
			h = Math.max(h, height(c));
		return h;
	}
	
}
