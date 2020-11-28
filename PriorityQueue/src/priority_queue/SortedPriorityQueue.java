package priority_queue;

import positional_list.*;
import java.util.Comparator;

/**
 * Implements a priority queue with a sorted list
 * @author User
 *
 * @param <K> the key
 * @param <V> the associated value
 */
public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{

	// Use a positional linked list as the primary collection of PQ Entries
	private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<Entry<K,V>>();
	
	/**
	 * Create an empty PQ based on the natural ordering of its keys
	 */
	public SortedPriorityQueue() {
		super();
	}
	
	/**
	 * Create an empty PQ using the given comparator to order keys
	 * @param c the comparator
	 */
	public SortedPriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Insert a key-value pair into the PQ
	 * @param key
	 * @param value
	 * @return newEntry the new PQ Entry
	 * @throws IllegalArgumentException
	 */
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Entry<K,V> newEntry = new PQEntry<>(key, value);
		Position<Entry<K,V>> walk = list.last();
		while (walk != null && compare(newEntry, walk.getElement()) < 0) {
			walk = list.before(walk);
		}
		if (walk == null)
			list.addFirst(newEntry);
		else
			list.addAfter(walk, newEntry);
		return newEntry;
	}
	
	/**
	 * Return, without removing, the minimal entry in the PQ
	 */
	public Entry<K,V> min() {
		if (list.isEmpty())
			return null;
		return list.first().getElement();
	}
	
	/**
	 * Return and remove the minimal entry in the PQ
	 */
	public Entry<K,V> removeMin(){
		if (list.isEmpty())
			return null;
		return list.remove(list.first());
	}
	
	/**
	 * Return the number of element in the PQ
	 */
	public int size() {
		return list.size();
	}	
	
}