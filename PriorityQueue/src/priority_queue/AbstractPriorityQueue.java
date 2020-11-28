package priority_queue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V>{

	
	//--------Nested PQ Entry class----------
	protected static class PQEntry<K,V> implements Entry<K,V>{
		private K k;
		private V v;
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		// methods of the Entry Interface
		public K getKey() {
			return k;
		}
		public V getValue() {
			return v;
		}
		// utilities not exposed in the Entry interface
		protected void setKey(K key) {
			k = key;
		}
		protected void setValue(V value) {
			v = value;
		}
	}
	
	// Instance variables for an AbstractPriorityQueue
	// The comparator defining the ordering of the PQ
	private Comparator<K> comp;
	
	/**
	 * Creates a PQ using the given Comparator
	 * @param c the Comparator to be used
	 */
	protected AbstractPriorityQueue(Comparator<K> c) {
		comp = c;
	}
	
	/**
	 * Creates a PQ using the DefaultComparator
	 */
	protected AbstractPriorityQueue() {
		this(new DefaultComparator<K>());
	}
	/**
	 * Method for comparing two keys
	 * @param a the first key
	 * @param b the second key
	 * @return -1, 0 or 1
	 */
	protected int compare(Entry<K,V> a, Entry<K,V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	
	/**
	 * Determine whether a key is valid
	 * @param key the key object being checked
	 * @return true if the key is valid
	 * @throws IllegalArgumentException the key is not valid
	 */
	protected boolean checkKey(K key) throws IllegalArgumentException{
		try {
			return (comp.compare(key, key) == 0);
		}
		catch (ClassCastException ex) {
			throw new IllegalArgumentException("Incompatable key");
		}
	}
	
	/**
	 * Check whether the PQ is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
}
