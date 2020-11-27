package maps;

import java.util.Random;

import array_list.ArrayList;

/**
 * An implementation of a Sorted map using a skip list
 * @author User
 *
 */
public class SkipList<K,V> extends AbstractSortedMap<K,V>{

	// Nested Node class
	private static class Node<K,V> extends MapEntry<K,V>{
		private Node<K,V> next;
		private Node<K,V> previous;
		private Node<K,V> above;
		private Node<K,V> below;
		// Constructor
		public Node(Node<K,V> n, Node<K,V> p, Node<K,V> a, Node<K,V> b, K k, V v){
			super(k, v);
			next = n;
			previous = p;
			above =  a;
			below = b;
		}
		// Constructor for start and end Nodes
		// Null is the default value for start and end Nodes
		public Node(Node<K,V> n, Node<K,V> p, Node<K,V> a, Node<K,V> b){
			this(n, p, a, b, null, null);
		}
		// Get related Nodes
		public Node<K,V> getNext() { return next; }
		public Node<K,V> getPrevious(){ return previous; }
		public Node<K,V> getAbove(){ return above; }
		public Node<K,V> getBelow(){ return below; }
		// Set related Nodes
		public void setNext(Node<K,V> n) { next = n; }
		public void setPrevious(Node<K,V> p){ previous = p; }
		public void setAbove(Node<K,V> a){ above = a; }
		public void setBelow(Node<K,V> b){ below = b; }
		// Set all values as null - help with garbage collection on removal
		public void setNull() {
			next = null;
			previous = null;
			above =  null;
			below = null;
			setKey(null);
			setValue(null);
		}
	}
	
	// instance variables for the SkipList
	Node<K,V> start; // the top left Node
	Node<K,V> end;	// the top right Node
	int height = 0;  //start height
	int size = 0;
	
	/**
	 * Constructor
	 * Creates a single level consisting of a null start and end Node
	 */
	public SkipList() {
		// null is the default value for the start and end Nods
		start = new Node<K,V>(null, null, null, null);
		end = new Node<K,V>(null, start, null, null);
		start.setNext(end);
		addLevel();
	}
	
	/**
	 * @return the number of elements in the bottom level of the SkipList
	 */
	@Override
	public int size() { return size; }
	
	/**
	 * @return true if there are no elements in the SkipList, otherwise false;
	 */
	@Override
	public boolean isEmpty() { return size == 0; }
	
	
	// Utility methods
	
	/**
	 * Traverses the skip list
	 * Finds Node p in the bottom list with the largest key having key(p) <= k
	 * @param key the key being searched for
	 * @return p a Node having getKey(node) <= k
	 */
	private Node<K,V> skipSearch(K key) {
		Node<K,V> p = start;
		while (p.getBelow() != null) {
			p = p.getBelow();
			System.out.println("skipSearch");
			while (p.getNext().getKey() != null && compare(key, p.getNext().getKey()) >= 0) {
				p = p.getNext();
				System.out.println("scan");
			}
		}
		return p;
	}
	
	/**
	 * Adds a new level to the skip list
	 * Resets the start Node to the new top left Node
	 */
	private void addLevel() {
		System.out.println("Add level");
		Node<K,V> left = new Node<>(null, null, null, start);
		Node<K,V> right = new Node<>(null, left, null, end);
		left.setNext(right);
		start.setAbove(left);
		end.setAbove(right);
		start = left;
		end = right;
		height++;
	}
	
	/**
	 * Inserts a Node at the next level, building a tower for that Node
	 * @param node the Node at at the highest level for a key
	 * @return the new Node at the next level
	 */
	private Node<K,V> insertAfterAbove(Node<K,V> node) {
		Node<K,V> p = node.getPrevious();
		Node<K,V> n = node.getNext();
		int i = 0;
		while (p.getAbove() == null) {
			System.out.println(p.getKey());
			System.out.println("insertAfterAbove");
			p = p.getPrevious();
			System.out.println(p.getKey());
			//if (i > 10)
				//break;
		}
		p = p.getAbove();
		n = p.getNext();
		Node<K,V> aboveNode = new Node<>(n, p, null, null, node.getKey(), node.getValue());
		n.setPrevious(aboveNode);
		p.setNext(aboveNode);
		aboveNode.setBelow(node);
		node.setAbove(aboveNode);
		return aboveNode;
	}
	
	/**
	 * Gets the value associated with a key
	 * @param key
	 * @return the value associated with the key, or null if the key is not in the skip list
	 */
	@Override
	public V get(K key) throws IllegalArgumentException{
		checkKey(key);
		Node<K,V> p = skipSearch(key);
		return p.getValue();
	}
	
	/**
	 * Adds a key-value pair to the map, or updates an existing key
	 * @param key
	 * @param value
	 * @return the original value associated with the key if updating, otherwise null
	 */
	@Override
	public V put(K key, V value) throws IllegalArgumentException{
		checkKey(key);
		Node<K,V> p = skipSearch(key);
		// Inserting the first key
		//if (p == )
		
		// inserting a new key
		if (p.getKey() != key) {
			// set the Node at the bottom position
			Node<K,V> n = p.getNext();
			Node<K,V> newNode = new Node<>(n, p, null, null, key, value);
			n.setPrevious(newNode);
			p.setNext(newNode);	
			
			// Add levels based on coin toss
			Random r = new Random();
			int i = 1;
			while (r.nextBoolean() && (height <= Math.max(Math.log(size), 10))) {
				System.out.println("Flip");
				if (i == height)
					addLevel();
				newNode = insertAfterAbove(newNode);
				i++;
			}
			size++;
			return null;
		}
		else {
			// update existing key
			V oldValue = p.getValue();
			p.setValue(value);
			while (p.getAbove() != null)
				p = p.getAbove();
				p.setValue(value);
			return oldValue;
		}
	}
	
	/**
	 * Removes a key from the SkipList
	 * @param key
	 * @return the value associated with the key, or null if the key is not in the SkipList
	 */
	@Override
	public V remove(K key) throws IllegalArgumentException{
		if (!isEmpty()) {
			checkKey(key);
			Node<K,V> node = start;
			while (node.below != null) {
				node = node.getBelow();
				while (compare(key, node.getNext().getKey()) >= 0  && node.getNext().getKey() != null) {
					node = node.getNext();
					if (node.getKey() == key) {
						V value = node.getValue();
						Node<K,V> n = node.getNext();
						Node<K,V> p = node.getPrevious();
						Node<K,V> b = node.getBelow();
						
						while (node != null) {
							p.setNext(n);
							n.setPrevious(p);
							if (b != null)
								b.setAbove(node);
							node.setNull();
							node = b;
						}
						size--;
						return value;
					}
				}
			}
		}
		return null;
	}
	
	
	// SortedMap Interface methods
	
	/**
	 * Find the Node with the smallest key
	 * @return the Node with the smallest key, or null if the map is empty
	 */
	@Override
	public Node<K,V> firstEntry(){
		if (isEmpty())
			return null;
		Node<K,V> node = start;
		while (node.getBelow() != null) {
			node = node.getBelow();
		} 
		return node.getNext();
	}
	
	/**
	 * Get the Node with the largest key value
	 * @return the Node with the largest key value, or null if the map is empty
	 */
	@Override
	public Node<K,V> lastEntry() {
		if (isEmpty())
			return null;
		Node<K,V> node = end;
		while (node.getBelow() != null) {
			node = node.getBelow();
		} 
		return node.getPrevious();
	}
	
	/**
	 * Get the Node with the least key value greater to or equal to the k
	 * @param k a key
	 * @return the Node with least key >= k, , or null if the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	@Override
	public Node<K,V> ceilingEntry(K k) throws IllegalArgumentException{
		checkKey(k);
		Node<K,V> node = skipSearch(k);
		if (node.getKey() == k)
			return node;
		if (node.getNext().getKey() == null)
			return null;
		return node.getNext();
	}
	
	/**
	 * Get the Node with the greatest key value lower than of equal to k
	 * @param k a key
	 * @return the Node with greatest key <= k, , or null if no such Node exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	@Override
	public Node<K,V> floorEntry(K k) throws IllegalArgumentException{
		checkKey(k);
		return skipSearch(k);
	}
	
	/**
	 * Get the entry with the greatest key value strictly lower than k
	 * @param k a key
	 * @return the entry with greatest key < k, or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map	
	 */
	@Override
	public Node<K,V> lowerEntry(K k) throws IllegalArgumentException{
		checkKey(k);
		Node<K,V> node = skipSearch(k);
		if (node.getKey() == k) {
			if (node.getPrevious().getKey() == null)
				return null;
			return node.getPrevious();
		}		
		return node;
	}
	
	/**
	 * Get the entry with the least key strictly greater then k
	 * @param k a key
	 * @return the entry with least key > k, or null if no such entry exist or the map is empty
	 * @throws IllegalArgumentException if k is not compatible with the map
	 */
	@Override
	public Node<K,V> higherEntry(K k) throws IllegalArgumentException{
		checkKey(k);
		Node<K,V> node = skipSearch(k);
		if (node.getNext().getKey() == null)
			return null;
		return node.getNext();
	}
	
	
	// Iterators and Iterables
	
	/**
	 * Get all entries in the map
	 * @return an Iterable of entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet(){
		if (isEmpty())
			return null;
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		Node<K,V> node = firstEntry();
		do {
			buffer.add((Entry<K,V>) node);
			node = node.getNext();
		} while (node.getKey() != null);
		return buffer;
	};
	
	
	/**
	 * Returns an Iterable of all entries with key values in the range [k1, k2]
	 * @param k1 the lower bound key value
	 * @param k2 the upper bound key value
	 * @return an iteration of all entries with key greater than or equal to k1, but strictly less than k2
	 * @throws IllegalArgumentException if k1 or k2 are not compatible with the map
	 */
	@Override
	public Iterable<Entry<K,V>> subMap(K k1, K k2) throws IllegalArgumentException{
		if (isEmpty())
			return null;
		checkKey(k1);
		checkKey(k2);
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		Node<K,V> node = firstEntry();
		while (compare(node.getKey(), k1) < 0) {
			node = node.getNext();
		} 
		while (compare(node.getKey(), k2) < 0) {
			buffer.add((Entry<K,V>) node);
			node = node.getNext();
		}
		return buffer;
		
	}
	
	
}
