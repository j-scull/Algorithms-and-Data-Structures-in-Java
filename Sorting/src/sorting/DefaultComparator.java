package sorting;

import java.util.Comparator;

/**
 * The default comaparator for the sorted map implementation
 * @author User
 *
 * @param <E> the object to compare
 */
public class DefaultComparator<E> implements Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException{
		return ((Comparable<E>) a).compareTo(b);
	}
	
	
}
