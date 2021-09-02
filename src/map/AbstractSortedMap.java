package map;

import util.DefaultComparator;
import util.Entry;

import java.util.Comparator;

/**
 * An abstract base for Sorted map implementations
 */
public abstract class AbstractSortedMap<K,V> 
	extends AbstractMap<K,V> implements SortedMap<K,V>{

	private Comparator<K> comp;
	
	/**
	 * Creates a sorted map with the given comparator
	 * @param c
	 */
	public AbstractSortedMap(Comparator<K> c) {
		comp = c;
	}
	/**
	 * Creates a sorted map with the default comparator
	 */
	public AbstractSortedMap() {
		this(new DefaultComparator<K>());
	}
		
	/**
	 * Method for comparing two keys
	 * @param a the first key
	 * @param b the second key
	 * @return -1, 0 or 1
	 */
	protected int compare(K a, K b) {
		return comp.compare(a, b);
	}
	/**
	  * Method for comparing a key ad an entry
	 * @param a an key
	 * @param b an entry
	 * @return -1, 0 or 1
	 */
	protected int compare(K a, Entry<K,V> b) {
		return comp.compare(a, b.getKey());
	}
	/**
	 * Method for comparing an entry and a key
	 * @param a an entry
	 * @param b a key
	 * @return -1, 0 or 1
	 */
	protected int compare(Entry<K,V> a, K b) {
		return comp.compare(a.getKey(), b);
	}
	/**
	 * Method for comparing two entries
	 * @param a the first entry
	 * @param b the second entry
	 * @return -1, 0 or 1
	 */
	protected int compare(Entry<K,V> a, Entry<K,V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	
	/**
	 * Check whether a key is valid
	 * @param key the key being validates
	 * @return true if the key is valid
	 * @throws IllegalArgumentException if the key is not valid
	 */
	protected boolean checkKey(K key) throws IllegalArgumentException{
		try {
			return (comp.compare(key, key) == 0);
		}
		catch (ClassCastException ex) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
		
}
