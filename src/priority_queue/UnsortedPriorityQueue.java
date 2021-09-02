package priority_queue;

import positional_list.LinkedPositionalList;
import util.Position;
import util.Entry;

import java.util.Comparator;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{
	
	// Use a positional list as the primary collection of PQ Entries
	private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

	/**
	 * Construct an empty PQ using the natural ordering of its Keys
	 */
	public UnsortedPriorityQueue() {
		super();
	}
	
	/**
	 * Construct an empty PQ using the given Comparator
	 * @param c the Comparator to be used
	 */
	public UnsortedPriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Find the position with the minimal key
	 * @return the Position with the minimal key
	 */
	private Position<Entry<K,V>> findMin(){
		Position<Entry<K,V>> smallest = list.first();
		for (Position<Entry<K,V>> walk: list.positions()) {
			if (compare(walk.getElement(), smallest.getElement()) < 0)
				smallest = walk;
		}
		return smallest;
	}
	
	/**
	 * Insert a key-value pair into the PQ
	 * @param key
	 * @param value
	 */
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Entry<K,V> newEntry = new PQEntry<>(key, value);
		list.addLast(newEntry);
		return newEntry;
	}
	
	/**
	 * Return, but does not remove, theEntry with the minimal key
	 */
	public Entry<K,V> min(){
		if (list.isEmpty())
			return null;
		return findMin().getElement();
	}
	
	/**
	 * Returns and removes the Entry with the minimal key
	 */
	public Entry<K,V> removeMin(){
		if (list.isEmpty())
			return null;
		return list.remove(findMin());
	}
	
	/**
	 * Returns the number of elements in the PQ
	 */
	public int size() {
		return list.size();
	}
	
	
}
