package priority_queue;

import java.util.Comparator;


public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V> 
											implements AdaptablePriorityQueue<K,V>{
	
	//---------------- Nested AdaptablePQEntry class------------------
	protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V>{
		// Stores its position in the PQ
		private int index;
		public AdaptablePQEntry(K key, V value, int i) {
			super(key, value);
			index = i;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int i) {
			index = i;
		}
	}
	
	/**
	 * Constructs an empty PQ using the natural ordering of keys
	 */
	public HeapAdaptablePriorityQueue() {
		super();
	}
	
	/**
	 * Constructs an empty PQ using the given Comparator to order keys
	 * @param c the Comparator
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Creates a PQ, with heap order property given key-value pairs
	 * Need to redefine this function as it involves AdaptablePQEntry instances
	 * Can't cast PQEntry to AdaptablePQEntry - PQEntry is not an instance of AdaptablePQEntry
	 * @param keys an array of keys
	 * @param values an array of the associated values
	 */
	public HeapAdaptablePriorityQueue(K[] keys, V[] values) {
		super();
		for (int i = 0; i <  Math.min(keys.length, values.length); i++)
			heap.add(new AdaptablePQEntry<>(keys[i], values[i], i));
		heapify();
	}
	
	
	//------------------protected utilities-----------------
	/**
	 * Validate an entry as an location aware AdaptablePQEntry
	 * @param entry to entry to be validated
	 * @return the validated AdaptablePQEntry
	 * @throws IllegalArgumentException
	 */
	protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry) throws IllegalArgumentException{
		if (!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid entry");
		AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K,V>) entry;
		int i = locator.getIndex();
		if (i >= heap.size() || heap.get(i) != locator)
			throw new IllegalArgumentException("Invalid entry");
		return locator;
	}
	
	/**
	 * Swaps two entries
	 * @param i the index of the first entry
	 * @param j the index of the second entry
	 */
	protected void swap(int i, int j) {
		super.swap(i, j);
		((AdaptablePQEntry<K,V>) heap.get(i)).setIndex(i);
		((AdaptablePQEntry<K,V>) heap.get(j)).setIndex(j);
	}
	
	/**
	 * Restores the heap property, moves entry at index i either upward of downward
	 * @param i the index
	 */
	protected void bubble(int i) {
		if (i > 0 && compare(heap.get(i), heap.get(parent(i))) < 0)
			upHeap(i);
		else
			downHeap(i);
	}
	
	/**
	 * Insert a new key-value pair into the PQ
	 * @param key the key
	 * @param value the associated value
	 * @return newEntry the entry inserted into the PQ
	 */
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Entry<K,V> newEntry = new AdaptablePQEntry<>(key, value, heap.size());
		heap.add(newEntry);
		upHeap(heap.size() - 1);
		return newEntry;
	}
	
	/**
	 * Remove an entry from the PQ
	 * @param entry the entry to be remove
	 */
	public void remove(Entry<K,V> entry) throws IllegalArgumentException{
		AdaptablePQEntry<K,V> locator = validate(entry);
		int i = locator.getIndex();
		if (i == heap.size() - 1)
			heap.remove(heap.size() - 1);
		else {
			swap(i, heap.size() - 1);
			heap.remove(heap.size() - 1);
			bubble(i);
		}
	}
	
	/**
	 * Replace the key of a given entry in the PQ
	 * @param entry
	 * @param key the new key
	 */
	public void replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException{
		AdaptablePQEntry<K,V> locator = validate(entry);
		checkKey(key);
		locator.setKey(key);
		bubble(locator.getIndex());
	}
	
	/**
	 * Replace the value of a given entry in the PQ
	 * @param entry
	 * @param value the new value
	 */
	public void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException{
		AdaptablePQEntry<K,V> locator = validate(entry);
		locator.setValue(value);
	}
	
	
}
