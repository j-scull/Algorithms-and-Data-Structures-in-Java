package priority_queue;

/**
 * An Adaptable Priority Queue ADT
 * Allows for changing arbitrary keys and value
 * Can remove Entries other than min
 * @author User
 *
 * @param <K> the key
 * @param <V> the associated value
 */
public interface AdaptablePriorityQueue<K,V> {
	
	/**
	 * Remove the given entry from the priority queue
	 * @param entry the entry to be removed
	 */
	void remove(Entry<K,V> entry);
	
	/**
	 * Replaces the key of an entry
	 * @param entry the entry 
	 * @param key the new key
	 */
	void replaceKey(Entry<K,V> entry, K key);
	
	/**
	 * Replaces the value of an entry
	 * @param entry the entry
	 * @param value the new value
	 */
	void replaceValue(Entry<K,V> entry, V value);

}
