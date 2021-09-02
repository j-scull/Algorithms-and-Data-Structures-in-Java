package map;

import util.Entry;


/**
 * An interface for Map ADTs
 */
public interface Map<K,V> {

	/**
	 * Get the size of the map
	 * @return the number of key - value pairs in the map
	 */
	int size();
	
	/**
	 * Check if the map is empty
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Get the value associated with a key
	 * @param key the key
	 * @return the associated value, or null if key is not in the map
	 */
	V get(K key);
	
	/**
	 * Adds a key value pair to the map
	 * @param key a key in the map
	 * @param value the new value
	 * @return the old value if the key is in the map, otherwise returns null
	 */
	V put(K key, V value);
	
	/**
	 * Removes a key value pair from the map
	 * @param key the key in the map
	 * @return the value associated with the key
	 */
	V remove(K key);
	
	/**
	 * Get all keys in the map
	 * @return an iterable of keys
	 */
	Iterable<K> keySet();
	
	/**
	 * Get all values in the map
	 * @return an iterable of values
	 */
	Iterable<V> values();
	
	/**
	 * Get all entries in the map
	 * @return an iterable of entries
	 */
	Iterable<Entry<K,V>> entrySet();
	
}
