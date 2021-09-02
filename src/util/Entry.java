package util;

/**
 *Entry for storing Key - Value pairs
 */
public interface Entry<K,V> {
	
	K getKey();
	
	V getValue();
	
}
