package trees;

import util.Position;
import util.Entry;

import java.util.Comparator;

/**
 * An implementation of a sorted map using a Red Black Tree
 */
public class RBTreeMap<K,V> extends TreeMap<K,V>{
	
	// default constructor, uses the natural ordering of keys
	public RBTreeMap() { super(); }
	
	// Constructor with user defined comparator
	public RBTreeMap(Comparator<K> c) { super(c); }
	
	// Uses the BSTNode aux field: 0 for black, 1 for red
	private boolean isBlack(Position<Entry<K,V>> p) { return tree.getAux(p) == 0; }
	private boolean isRed(Position<Entry<K,V>> p) { return tree.getAux(p) == 1; }
	private void makeBlack(Position<Entry<K,V>> p) { tree.setAux(p, 0); }
	private void makeRed(Position<Entry<K,V>> p) { tree.setAux(p,  1); }
	private void setColour(Position<Entry<K,V>> p, boolean toRed) {
		tree.setAux(p, toRed ? 1 : 0);
	}
	
	// Overrides the TreeMap re-balancing hook called after insertion
	@Override
	protected void rebalanceInsert(Position<Entry<K,V>> p) {
		if(!isRoot(p)) {
			makeRed(p);
			resolveRed(p);
		}
	}
	
	/**
	 * Fixes a double red problem above a red position p
	 * @param p
	 */
	private void resolveRed(Position<Entry<K,V>> p) {
		Position<Entry<K,V>> parent, uncle, middle, grand;
		parent = parent(p);
		if (isRed(parent)) {			 //double red
			uncle = sibling(parent);
			if (isBlack(uncle)) {		 // Case 1: misshapen 4-node
				middle = restructure(p); //trinode restructure
				makeBlack(middle);
				makeRed(left((middle)));
				makeRed(right(middle));
			} else {					 // Case 2: over full 5-node
				makeBlack(parent);
				makeBlack(uncle);
				grand = parent(parent);
				if (!isRoot(grand)) {
					makeRed(grand);		 // make grandparent red
					resolveRed(grand);   // Recursively resolveRed up the tree
				}
			}
		}
	}
	
	//Overrides the TreeMap re-balancing hook called after a deletion
	@Override
	protected void rebalanceDelete(Position<Entry<K,V>> p) {
		if (isRed(p))
			makeBlack(p);
		else if (!isRoot(p)) {
			Position<Entry<K,V>> sib = sibling(p);
			if(isInternal(sib) && (isBlack(sib)) || isInternal(left(sib)))
				remedyDoubleBlack(p);
		}
	}
	
	/**
	 * Fixes a double black problem at the given position
	 * @param p a non-root position 
	 */
	private void remedyDoubleBlack(Position<Entry<K,V>> p) {
		Position<Entry<K,V>> z = parent(p);
		Position<Entry<K,V>> y = sibling(p);
		if (isBlack(y)) {
			if (isRed(left(y)) || isRed(right(y))) {    // Case 1: trinode restructure
				Position<Entry<K,V>> x = (isRed(left(y)) ? left(y) : right(y));
				Position<Entry<K,V>> middle = restructure(x);
				setColour(middle, isRed(z)); 			// middle gets z's old colour
				makeBlack(left(middle));
				makeBlack(right(middle));
			} else {									// Case 2: re-colouring
				makeRed(y);
				if (isRed(z))
					makeBlack(z);
				else if ((!isRoot(z)))
					remedyDoubleBlack(z);				// remedy the problem further up the tree
			}
		} else {										// Case 3: re-orient 3-node
			rotate(y);
			makeBlack(y);
			makeRed(z);
			remedyDoubleBlack(p);						// restart the process at p
		}
	}
}
