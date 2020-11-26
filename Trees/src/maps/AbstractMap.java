package maps;

import java.util.Iterator;

/**
 * Abstract base class for Map ADT implementations
 * @author User
 * 2/11/20
 */
public abstract class AbstractMap<K,V> implements Map<K,V>{

	// Nested MapEntry class
	protected static class MapEntry<K,V> implements Entry<K,V>{
		private K key;
		private V value;
		public MapEntry(K k, V v) {
			key = k;
			value = v;
		}
		// entry interface methods
		public K getKey() { return key; }
		public V getValue() { return value; }
		// other methods
		protected void setKey(K k) { key = k; }
		protected V setValue(V v) { 
			V oldValue = value;
			value = v; 
			return oldValue;
		}
	}

	// check if the map is empty
	public boolean isEmpty() {
		return size() == 0;
	}
	
	// Support for the public keySet method
	private class KeyIterator implements Iterator<K>{
		// entrySet() is defined in implementations 
		private Iterator<Entry<K,V>> entries = entrySet().iterator();
		public boolean hasNext() { return entries.hasNext(); }
		public K  next() { return entries.next().getKey(); }
		public void remove() { throw new UnsupportedOperationException("Unsuppored Operation"); }
	}
	private class KeyIterable implements Iterable<K>{
		public Iterator<K> iterator(){
			return new KeyIterator();
		}
	}
	public Iterable<K> keySet(){ return new KeyIterable(); }
	
	// Support for the public values method
	private class ValueIterator implements Iterator<V>{
		// entrySet() is defined in implementations 
		private Iterator<Entry<K,V>> entries = entrySet().iterator();
		public boolean hasNext() { return entries.hasNext(); }
		public V next() { return entries.next().getValue(); }
		public void remove() { throw new UnsupportedOperationException("Unsupported Operation"); }
	}
	private class ValueIterable implements Iterable<V>{
		public Iterator<V> iterator(){
			return new ValueIterator();
		}
	}
	public Iterable<V> values(){
		return new ValueIterable();
	}
	
	
	
	
}
