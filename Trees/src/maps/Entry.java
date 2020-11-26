package maps;

/**
 * Public Entry
 * @author User
 * 2/11/20
 * @param <K> key
 * @param <V> value
 */
public interface Entry<K,V> {
	
	K getKey();
	
	V getValue();
	
}
