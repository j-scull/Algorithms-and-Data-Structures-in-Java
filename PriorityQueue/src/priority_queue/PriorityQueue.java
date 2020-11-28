package priority_queue;
/**
 * Interface for the PriorityQueue ADT
 * @author User
 *
 * @param <K> the Key
 * @param <V> the associated Value
 */
public interface PriorityQueue<K,V> {
	
	/**
	 * 
	 * @return the number of elements in the queue
	 */
	int size();
	
	/*
	 * Check if the queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Inserts an element in the queue
	 * @param key
	 * @param value
	 * @return the Entry inserted in the priority queue
	 * @throws IllegalArgumentException
	 */
	Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
	
	/**
	 * Find the minimum element in the queue
	 * @return the minimum element
	 */
	Entry<K,V> min();
	
	/**
	 * Removes the minimum element in the queue
	 * @return
	 */
	Entry<K,V> removeMin();
}
