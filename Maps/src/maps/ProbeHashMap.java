package maps;

import array_list.*;

/**
 * An implementation of a hash map using linear probing
 * @author User
 * 3/11/20
 * @param <K> the key
 * @param <V> the value
 */
public class ProbeHashMap<K,V> extends AbstractHashMap<K,V>{

	private MapEntry<K,V>[] table;
	private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null); // sentinel
	
	/**
	 * Constructor
	 * @param capacity the initial capacity of the map
	 * @param prime the prime used to obtain the has value
	 */
	public ProbeHashMap(int capacity, int prime) {
		super(capacity, prime);
	}
	/**
	 * Constructor with default prime (109345121)109345121
	 * @param capacity the initial capacity of the map
	 */
	public ProbeHashMap(int capacity) {
		super(capacity);
	}
	// Default constructor
	public ProbeHashMap() {
		super();
	}
	
	/**
	 * Create an empty table with length capacity
	 */
	@SuppressWarnings("unchecked")
	protected void createTable() {
		table = (MapEntry<K,V>[]) new MapEntry[capacity];
	}
	
	/**
	 * Check if a location in the table is available
	 * @param i an index in the table
	 * @return true if the location is available, false otherwise
	 */
	private boolean isAvailable(int i) {
		return (table[i] == null || table[i] == DEFUNCT);
	}
	
	/**
	 * Finds an index for a key in the table
	 * @param h the hash value of the key, an index in the table
	 * @param k the key
	 * @return the index with the key, or -(a+1) where the key can be added at a
	 */
	private int findSlot(int h, K k) {
		int available = -1;
		int j = h;
		do {
			if (isAvailable(j)) {
				if (available == -1) available = j;
				if (table[j] == null) break;          // if the location is empty
			}
			else if (table[j].getKey().equals(k))
				return j;							  // if the key at a location equals input key
			j = (j + 1) % capacity;
		}while (j != h);
		return - (available + 1);					  // no match, new key can be added at available index
	}
	
	/**
	 * Get the value associated with a key
	 * @param h the key's hash value
	 * @param k the key
	 * @return the value associated with k, or null if k is not in the hash map
	 */
	protected V bucketGet(int h, K k) {
		 int j = findSlot(h, k);
		 if (j < 0)
			 return null;
		 return table[j].getValue();
	}
	
	/**
	 * Add a new key value pair to the map, or update an already existing key
	 * @param h the key's hash code
	 * @param k the key
	 * @param v the value
	 * @return the original value paired with k, or null if k is new to the map
	 */
	protected V bucketPut(int h, K k, V v) {
		int j = findSlot(h, k);
		if (j >= 0)
			return table[j].setValue(v);
		table[-(j + 1)] = new MapEntry<>(k, v);
		n++;
		return null;
	}
	
	/**
	 * Remove a key value pair from the hash map
	 * @param h the key's hash value
	 * @param k the key
	 * @return the value paired with k, or null if k is not in the hash map
	 */
	protected V bucketRemove(int h, K k) {
		int j = findSlot(h, k);
		if (j < 0)
			return null;
		V result = table[j].getValue();
		table[j] = DEFUNCT;
		n--;
		return result;
	}
	
	/**
	 * Returns an iterable collection of all key-value pairs in the hash map
	 */
	public Iterable<Entry<K,V>> entrySet(){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for (int i = 0; i < capacity; i++)
			if (!isAvailable(i)) buffer.add(table[i]);
		return buffer;
	}
	
	
	
	
	
	
	
	
}
