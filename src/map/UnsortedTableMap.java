package map;

import array_list.ArrayList;
import util.Entry;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of a Map ADT using an unsorted table
 */
public class UnsortedTableMap<K,V> extends AbstractMap<K,V>{
	
	// Underlying storage for entries
	private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
	
	// Constructor
	public UnsortedTableMap() {}

	// Utility method
	/**
	 * Return the index of an entry with given key
	 * @param key
	 * @return the index of the key, or -1 if not found
	 */
	private int findIndex(K key) {
		int n = table.size();
		for (int i = 0; i < n; i++) {
			if (table.get(i).getKey().equals(key))
				return i;
		}
		return -1;
	}
	
	// public methods
	/**
	 * @return the number of elements in the map
	 */
	public int size() { return table.size(); }
	
	/**
	 * Get the value paired with a key
	 * @param key
	 * @return the value associated with the key, or null if the key is not in the map
	 */
	public V get(K key) {
		int index = findIndex(key);
		if (index != -1)
			return table.get(index).getValue();
		return null;
	}
	
	/**
	 * Replace the value associated with a key, or add a new key-value pair to the map
	 * @param key
	 * @param value
	 * @return the old value associated with the key, or null if the key isn't in the map
	 */
	public V put(K key, V value) {
		int index = findIndex(key);
		if (index != -1)
			return table.get(index).setValue(value);  // returns old value
		else {
			table.add(table.size(), new MapEntry<>(key, value));
			return null;
		}
	}
	
	/**
	 * Remove an entry from the map
	 * @param key
	 * @return the value associated with the key, or null if the key is not in the map
	 */
	public V remove(K key) {
		int i = findIndex(key);
		int n = table.size();
		if (i == -1)
			return null;
		V value = table.get(i).getValue();
		// swaps the entry with the last entry, then removes the last entry
		// the table doesn't need to be sorted
		table.set(i, table.get(n - 1));
		table.remove(n - 1);
		return value;
	}
	
	
	// Support for a public enrtySet method
	private class EntryIterator implements Iterator<Entry<K,V>>{
		private int i = 0;
		public boolean hasNext() { return i < table.size();	}
		public Entry<K,V> next(){
			if (i == table.size())
				throw new NoSuchElementException("Nothing left");
			return table.get(i++);
		}
		public void remove(){
			throw new UnsupportedOperationException("Unsupported Operation");
		}
	}
	private class EntryIterable implements Iterable<Entry<K,V>>{
		public Iterator<Entry<K,V>> iterator(){
			return new EntryIterator();
		}
	}
	public Iterable<Entry<K,V>> entrySet(){
		return new EntryIterable();
	}
	
	
	
	
	
}
