package trees;

import util.Position;
import util.Entry;

import java.util.Comparator;

/**
 * An implementation of a sorted map using a Splay Tree
 */
public class SplayTreeMap<K,V> extends TreeMap<K,V>{
	
	// Default Constructor
	public SplayTreeMap() { super(); }
	
	// Constructor with a user defined comparator
	public SplayTreeMap(Comparator<K> c) { super(c); }
	
	/**
	 * Utility, perform the splay operation for re-balancing the tree
	 * @param p a newly inserted position, or the parent of a recently deleted position 
	 */
	private void splay(Position<Entry<K,V>> p) {
		while (!isRoot(p)) {
			Position<Entry<K,V>> parent = parent(p);
			Position<Entry<K,V>> grand = parent(parent);
			if (grand == null)
				rotate(p);   // zig
			else if ((p == left(parent)) == (parent == left(grand))){
				// zig-zig
				rotate(parent);
				rotate(p);
			} else {
				// zig-zag
				rotate(p);
				rotate(p);
			}
		}
	}
	
	// Override the TreeMap hook after accessing an entry
	@Override
	protected void rebalanceAccess(Position<Entry<K,V>> p) {
		if (isExternal(p)) p = parent(p);
		if (p != null) splay(p);
	}
	
	// Override the TreeMap hook after inserting an entry
	@Override
	protected void rebalanceInsert(Position<Entry<K,V>> p) {
		splay(p);
	}
	
	// Override the TreeMap hook after deleting an entry
	@Override
	protected void rebalanceDelete(Position<Entry<K,V>> p) {
		if (!isRoot(p)) splay(parent(p));
	}

}
