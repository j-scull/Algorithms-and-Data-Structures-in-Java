package map;

import util.Entry;

/**
 * An interface for a sorted map ADT
 */
public interface SortedMap<K,V> extends Map<K,V>{
	
	/**
	 * Find the entry with the smallest key
	 * @return the entry with the smallest key, or null if the map is empty
	 */
	Entry<K,V> firstEntry();
	
	/**
	 * Get the entry with the largest key value
	 * @return the entry with the largest key value, or null if the map is empty
	 */
	Entry<K,V> lastEntry();
	
	/**
	 * Get the entry with the least key value greater to or equal to the k
	 * @param k a key
	 * @return the entry with least key >= k, , or null if the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	Entry<K,V> ceilingEntry(K k) throws IllegalArgumentException;
	
	/**
	 * Get the entry with the greatest key value lower than of equal to k
	 * @param k a key
	 * @return the entry with greatest key <= k, , or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	Entry<K,V> floorEntry(K k) throws IllegalArgumentException;
	
	/**
	 * Get the entry with the greatest key value strictly lower than k
	 * @param k a key
	 * @return the entry with greatest key < k, or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map	
	 */
	Entry<K,V> lowerEntry(K k) throws IllegalArgumentException;
	
	/**
	 * Get the entry with the least key strictly greater then k
	 * @param k a key
	 * @return the entry with least key > k, or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	Entry<K,V> higherEntry(K k) throws IllegalArgumentException;
	
	/**
	 * Returns an iterable of all entries with key values in the range [k1, k2]
	 * @param k1 the lower bound key value
	 * @param k2 the upper bound key value
	 * @return an iteration of all entries with key greater than or equal to k1, but strictly less than k2
	 * @throws IllegalArgumentException if k1 or k2 are not compatible with the map
	 */
	Iterable<Entry<K,V>> subMap(K k1, K k2) throws IllegalArgumentException;
	
}
