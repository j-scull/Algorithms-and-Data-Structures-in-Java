package maps;

import array_list.*;
import java.util.Comparator;

/**
 * An implementation of a map which stores keys in order
 * @author User
 *
 */
public class SortedTableMap<K,V> extends AbstractSortedMap<K,V>{
	
	private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
	
	/**
	 * Creates a SortedTableMap using the default comparator
	 */
	public SortedTableMap() {
		super();
	}
	/**
	 * Creates a SortedTableMap using the given comparator
	 * @param c
	 */
	public SortedTableMap(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * Returns the smallest index for the range table[low,..high] inclusive storing an entry 
	 * with a key greater than or equal to k (or else index high+1 by convention)
	 * @param key the key being searched for
	 * @param low the lowest index to start searching
	 * @param high the highest index to start searching
	 * @return the smallest index of the element >= key, or high + 1
	 */
	private int findIndex(K key, int low, int high) {
		if (high < low) return high + 1;             // no entry matches the key
		int mid = (high + low) / 2;
		int comp = compare(key, table.get(mid).getKey());
		if (comp == 0)
			return mid;
		else if (comp < 0)
			return findIndex(key, low, mid - 1);
		else
			return findIndex(key, mid + 1, high);
	}
	/**
	 * Version of findIndex above without index parameters
	 * @param key the key being searched for
	 * @return the smallest index of the element >= key, or high + 1
	 */
	private int findIndex(K key) { return findIndex(key, 0, table.size() - 1); }
	
		
	/**
	 * Return the number of entries in the map
	 */
	@Override
	public int size() { return table.size(); }
	
	/**
	 * Get the value associated with a key
	 * @param key the key
	 * @return the value associated with the key, or null if the key is not in the map
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		checkKey(key);
		int j = findIndex(key);
		if (j == size() || compare(key, table.get(j).getKey()) != 0) return null;
		return table.get(j).getValue();
	}
	
	/**
	 * Add a key-value pair to the map in order
	 * @param key the key
	 * @param value the value
	 * @return the original value paired with the key, or null if the key is new to the map
	 */
	@Override
	public V put(K key, V value) throws IllegalArgumentException  {
		checkKey(key);
		int j = findIndex(key);
		if (j < size() && compare(key, table.get(j).getKey()) == 0) 
			return table.get(j).setValue(value);
		table.add(j, new MapEntry<>(key, value));  //adds at the appropriate index j
		return null;
	}
	
	/**
	 * Removes a key value pair from the map
	 * @param key the key to be removed
	 * @return the value associated with the key, or null if the key is not in the map
	 */
	@Override
	public V remove(K key) throws IllegalArgumentException {
		checkKey(key);
		int j = findIndex(key);
		if (j == size() || compare(key, table.get(j).getKey()) != 0) return null;
		return table.remove(j).getValue();
	}
	
	// Utility
	/**
	 * Return the entry at index j
	 * @param j an index
	 * @return the entry at index j, of null if j is out of bounds
	 */
	private Entry<K,V> safeEntry(int j) {
		if (j < 0 || j >= table.size()) return null;
		return table.get(j);
	}
	
	// SortedMap interface methods
	
	/**
	 * Get the first entry in the map
	 * @return the first entry in the map, or null if empty
	 */
	@Override
	public Entry<K,V> firstEntry() { return safeEntry(0); }
	
	/**
	 * Get the last entry in the map
	 * @return the last entry in the map of null if empty
	 */
	@Override
	public Entry<K,V> lastEntry() { return safeEntry(size() - 1); }
	
	/**
	 * Get the entry with the least key value greater to or equal to the k
	 * @param k a key
	 * @return the entry with least key >= k, , or null if the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	@Override
	public Entry<K,V> ceilingEntry(K key)  throws IllegalArgumentException {
		checkKey(key);
		return safeEntry(findIndex(key));
	}
	
	/**
	 * Get the entry with the greatest key value lower than of equal to k
	 * @param k a key
	 * @return the entry with greatest key <= k, , or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	@Override
	public Entry<K,V> floorEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		int j = findIndex(key);
		if (j == size() || ! key.equals(table.get(j).getKey()))
			j--;
		return safeEntry(j);
	}
	
	/**
	 * Get the entry with the greatest key value strictly lower than k
	 * @param k a key
	 * @return the entry with greatest key < k, or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map	
	 */
	@Override
	public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		return safeEntry(findIndex(key) - 1);
	}
	
	/**
	 * Get the entry with the least key strictly greater then k
	 * @param k a key
	 * @return the entry with least key > k, or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	@Override
	public Entry<K,V> higherEntry(K key) throws IllegalArgumentException{
		checkKey(key);
		int j = findIndex(key);
		if (j < size() && key.equals(table.get(j).getKey()))
			j++; 
		return safeEntry(j);
	}
	
	// support for iterators entrySet() and submap()
	private Iterable<Entry<K,V>> snapshot(int startIndex, K stop){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		int j = startIndex;
		while (j < table.size() && (stop == null || compare(stop, table.get(j)) > 0))
			buffer.add(table.get(j++));
		return buffer;
	}
	
	/**
	 * Returns an iterable collection of all entries in the map
	 * @return an iterable collection of entries
	 */
	@Override
	public Iterable<Entry<K,V>> entrySet(){ return snapshot(0, null); }
	
	
	/**
	 * Returns an iterable of all entries with key values in the range [k1, k2]
	 * @param k1 the lower bound key value
	 * @param k2 the upper bound key value
	 * @return an iteration of all entries with key greater than or equal to k1, but strictly less than k2
	 * @throws IllegalArgumentException if k1 or k2 are not compatible with the map
	 */
	@Override
	public Iterable<Entry<K,V>> subMap(K k1, K k2) throws IllegalArgumentException{
		return snapshot(findIndex(k1), k2);
	}
	
}
