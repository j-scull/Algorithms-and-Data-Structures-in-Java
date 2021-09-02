package util;

import java.util.Comparator;

/**
 * The default comaparator for the sorted map implementation
 */
public class DefaultComparator<E> implements Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException{
		return ((Comparable<E>) a).compareTo(b);
	}
	
	
}
