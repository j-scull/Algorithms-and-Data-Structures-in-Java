package priority_queue;
/**
 * Interface for the Key-Value pair in a PriorityQueue
 * @author User
 *
 * @param <K> the Key
 * @param <V> the value
 */
public interface Entry<K, V> {
	 K getKey();
	 V getValue();
}
