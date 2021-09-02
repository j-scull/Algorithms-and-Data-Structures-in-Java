package map;

import array_list.ArrayList;
import util.Entry;

import java.util.Random;


/**
 * Abstract base for hash map implementations
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V>{
	
	protected int n = 0;
	protected int capacity;
	private int prime;
	private long scale, shift;
	
	/**
	 * Constructor 
	 * @param c the capacity of the hash map
	 * @param p the prime number
	 */
	public AbstractHashMap(int c, int p) {
		prime = p;
		capacity = c;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
	}
	/**
	 * Constructor with default prime
	 * @param c the capacity of the hash map
	 */
	public AbstractHashMap(int c) {
		this(c, 109345121);
	}
	/**
	 * Default constructor
	 */
	public AbstractHashMap() {
		this(17);
	}
	
	/**
	 * Get the size of the hash map
	 * @return the number of elements in the hash map
	 */
	public int size() { return n; }
	
	/**
	 * Get the value associated with a key
	 * @param key the key
	 * @return the associated value
	 */
	public V get(K key) { return bucketGet(hashValue(key), key); }
	
	/**
	 * Remove a key value pair from the hash map
	 * @param key the key
	 * @return the value associated with the key, or null if the key is not in the map
	 */
	public V remove(K key) { return bucketRemove(hashValue(key), key); }
	
	/**
	 * Add a key value pair to the hash map, or update the value of an already existing key
	 * @param key the key
	 * @param value the value 
	 * @return the original value paired with the key, or null if the key is new to the hash map
	 */
	public V put(K key, V value) {
		V result = bucketPut(hashValue(key), key, value);
		if (n > capacity / 2)
			resize(capacity * 2 - 1);
		return result;
	}
	
	// private utilities
	/**
	 * Get the hash value for a given key
	 * Uses java.Object.hashCode()
	 * @param key the key
	 * @return the hash value, an int
	 */
	private int hashValue(K key) {
		return (int)((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
	}
	/**
	 * Resize the hash map when necessary
	 * @param newCap the new capacity of the hash map
	 */
	private void resize(int newCap) {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for (Entry<K,V> entry: entrySet())
			buffer.add(entry);
		capacity = newCap;
		createTable();
		n = 0;
		for (Entry<K,V> entry: buffer)
			put(entry.getKey(), entry.getValue());
	}
	
	// protected abstract methods to be implemented by subclasses
	protected abstract void createTable();
	protected abstract V bucketGet(int h, K key);
	protected abstract V bucketPut(int h, K key, V value);
	protected abstract V bucketRemove(int h, K key);
	
}
