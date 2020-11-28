package priority_queue;

import array_list.*;
import java.util.Comparator;

/**
 * A heap based implementation of a priority queue
 * Using an ArrayList structure instead of a linked list for the binary tree
 * @author User
 *
 * @param <K> the key
 * @param <V> the associated value
 */
public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{

	// Use an ArrayList as the primary collection of PQ entries
	protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
	
	/**
	 * Construct an empty PQ using the natural order of entries
	 */
	public HeapPriorityQueue() {
		super();
	}
	
	/**
	 *  Construct an empty PQ using the given comparator to order entries
	 * @param c the Comparator to be used
	 */
	public HeapPriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Creates a PQ, with heap order property given key-value pairs
	 * @param keys an array of keys
	 * @param values an array of the associated values
	 */
	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for (int i = 0; i <  Math.min(keys.length, values.length); i++)
			heap.add(new PQEntry<>(keys[i], values[i]));
		heapify();
	}
	
	
	// protected utilities
	
	/**
	 * Get the parent entry index
	 * @param i the index of the current entry
	 * @return the parent entry index
	 */
	protected int parent(int i) {
		return (i - 1) / 2;
	}
	
	/**
	 * Get the left entry index
	 * @param i the index of the current entry
	 * @return the left entry index
	 */
	protected int left(int i) {
		return (i * 2) + 1;
	}
	
	/**
	 * Get the right entry index
	 * @param i the index of the current entry
	 * @return the right entry index
	 */
	protected int right(int i) {
		return (i * 2) + 2;
	}
	
	/**
	 * Check if an entry has a left child
	 * @param i the current entry
	 * @return true if i has a left child, false otherwise
	 */
	protected boolean hasLeft(int i) {
		return left(i) < heap.size();
	}
	
	/**
	 * Check if an entry has a right child
	 * @param i the current entry
	 * @return true if i has a  right child, false otherwise
	 */
	protected boolean hasRight(int i) {
		return right(i) < heap.size();
	}
	
	/**
	 * Swaps two entries
	 * @param i the first entry
	 * @param j the second entry
	 */
	protected void swap(int i, int j) {
		Entry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	/**
	 * Moves the entry at index i higher if necessary to restore heap order property
	 * @param i the entry's index
	 */
	protected void upHeap(int i) {
		while (i > 0) {
			int p = parent(i);
			if (compare(heap.get(i), heap.get(p)) >= 0)
				break;
			swap(i, p);
			i = p;
		}
	}
	
	/**
	 * Moves the entry at index i lower if necessary to restore heap order property
	 * @param i the entry's index
	 */
	protected void downHeap(int i) {
		while (hasLeft(i)) {
			int leftIndex = left(i);
			int smallestIndex = leftIndex;
			if (hasRight(i)) {
				int rightIndex = right(i);
				// check if right is smaller
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallestIndex = rightIndex;
			}
			if (compare(heap.get(smallestIndex), heap.get(i)) >= 0)
				break;
			swap(i, smallestIndex);
			i = smallestIndex;
		}
	}
	
	/**
	 * Performs a bottom up construction of the heap
	 */
	protected void heapify() {
		int startIndex = parent(heap.size() - 1);
		for (int j = startIndex; j >= 0; j --)
			downHeap(j);
	}
	
	//--------------------public methods---------------------
	
	/**
	 * Return the number of entries in the PQ
	 */
	public int size() {
		return heap.size();
	}
	
	/**
	 * Return, without removing, the minimal entry in the PQ
	 */
	public Entry<K,V> min(){
		if (heap.isEmpty())
			return null;
		return heap.get(0);
	}
	
	/**
	 * Insert a new entry into the PQ
	 * @param key the entry's key
	 * @param value the key's associated value
	 * @throw IllegalArgumentException
	 */
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Entry<K,V> newEntry = new PQEntry<>(key, value);
		heap.add(newEntry);
		upHeap(heap.size() - 1);
		return newEntry;
	}
	
	/**
	 * Returns and removes the minimal entry in the PQ
	 */
	public Entry<K,V> removeMin(){
		if (heap.isEmpty())
			return null;
		Entry<K,V> removed = heap.get(0);
		swap(0, heap.size() - 1);
		heap.remove(size() - 1);
		downHeap(0);
		return removed;
	}

	
}
