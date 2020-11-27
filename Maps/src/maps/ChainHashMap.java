package maps;

import array_list.*;

/**
 * An implementation of a hash map using chaining
 * @author User
 *	3/11/20
 */
public class ChainHashMap<K,V> extends AbstractHashMap<K,V>{
	
	// An UnsortedTableMap array serves as the buckets
	// Using a simple solution to create a more 
	// advanced solution is known as bootstrapping
	private UnsortedTableMap<K,V>[] table;
	
	/**
	 * Constructor
	 * @param capacity the initial capacity of the hash map
	 * @param prime the prime number used to obtain the hash value
	 */
	public ChainHashMap(int capacity, int prime) {
		super(capacity, prime);
	}
	/**
	 * Constructor with default prime (109345121)
	 * @param capacity
	 */
	public ChainHashMap(int capacity) {
		super(capacity);
	}
	// Default constructor
	public ChainHashMap() {
		super();
	}
	
	/**
	 * Creates an empty table of length capacity
	 */
	@SuppressWarnings("unchecked")
	protected void createTable() {
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
	}
	
	/**
	 * Get the value associated with a key in a bucket
	 * @param h the bucket's index, the hash value of the key
	 * @param key the key
	 * @return the value paired with the key, or null if key is not in the map
	 */
	protected V bucketGet(int h, K key) {
		UnsortedTableMap<K,V> bucket = table[h];
		if (bucket == null)
			return null;
		return bucket.get(key);
	}
	
	/**
	 * Add a key value pair to a bucket
	 * @param h the bucket's index, the hash code of the key
	 * @param key the key
	 * @param value the value
	 * @return the original value paired with the key, or null if the key is new to the hash map
	 */
	protected V bucketPut(int h, K key, V value) {
		UnsortedTableMap<K,V> bucket = table[h];
		if (bucket == null)
			bucket = table[h] = new UnsortedTableMap<>();
		int oldSize = bucket.size();
		V result = bucket.put(key, value);
		n += (bucket.size() - oldSize);    // necessary to update size
		return result;
	}
	
	/**
	 * Remove a key value pair to a bucket
	 * @param h the bucket's index, the hash code of the key
	 * @param key the key
	 * @param value the value
	 * @return the value paired with the key, or null if thekey is not in the map
	 */
	protected V bucketRemove(int h, K key) {
		UnsortedTableMap<K,V> bucket = table[h];
		if (bucket == null)
			return null;
		int oldSize = bucket.size();
		V result = bucket.remove(key);
		n -= (oldSize - bucket.size());
		return result;
	}
	
	public Iterable<Entry<K,V>> entrySet(){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
		for (int h = 0; h < capacity; h++) {
			if (table[h] != null)
				for (Entry<K,V> entry: table[h].entrySet())
					buffer.add(entry);
		}
		return buffer;
	}
	
	
	
}
