package trees;

import java.util.Comparator;
import maps.*;
/**
 * An implementation of a sorted map using an AVL tree
 * @author User
 *
 * @param <K>
 * @param <V>
 */
public class AVLTreeMap<K,V> extends TreeMap<K,V>{

	// Default Constructor, uses the natural ordering of keys
	public AVLTreeMap(){ super(); }
	
	// Constructor with user defined comparator
	public AVLTreeMap(Comparator<K> c) { super(c); }
	
	// Returns the height of a given position
	protected int height(Position<Entry<K,V>> p) {
		return tree.getAux(p);
	}
	
	// Recomputes the height of a position based on its children's position
	protected void recomputeHeight(Position<Entry<K,V>> p) {
		tree.setAux(p,  1 + Math.max(height(left(p)), height(right(p))));
	}
	
	// Returns whether a position has a balance factor of 1
	protected boolean isBalanced(Position<Entry<K,V>> p) {
		return Math.abs(height(left(p)) - height(right(p))) <= 1;
	}
	
	/**
	 * Returns a child of p with a height no less than the other child of p
	 * @param p a position in the tree
	 * @return a position child of p with height greater than its sibling
	 */
	protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
		if (height(left(p)) > height(right(p)))
				return left(p);
		if (height(left(p)) < height(right(p)))
				return right(p);
		// if even
		if(isRoot(p)) return left(p); // p is root, choice doesn't matter
		if (p == left(parent(p))) return left(p); //return the child aligned with the parent's orientation
		else return right(p);
	}
	
	/**
	 * Re-balances the tree after an insert of removal operation. 
	 * Traverses up the tree from position p, performing trinode restructuring if any imbalance is found
	 * @param p a position inserted into the tree, or the parent of a position removed from the tree
	 */
	protected void rebalance(Position<Entry<K,V>> p) {
		int oldHeight, newHeight;
		do {
			oldHeight = height(p);
			if (!isBalanced(p)) {
				// x, y, z, trinode restructure
				p = restructure(tallerChild(tallerChild(p)));
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = height(p);
			p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}
	
	// Overrides the TreeMap re-balancing hook called after insertion
	@Override
	protected void rebalanceInsert(Position<Entry<K,V>> p) {
		rebalance(p);
	}
	
	// Overrides the TreeMap re-balancing hook called after insertion
	@Override
	protected void rebalanceDelete(Position<Entry<K,V>> p) {
		if (!isRoot(p))
			rebalance(parent(p));
	}
	
	
}
