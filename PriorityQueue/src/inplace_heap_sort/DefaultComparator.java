package inplace_heap_sort;

import java.util.Comparator;

/**
 * Default Comparator for use with in-place heap sort
 * @author User
 *
 * @param <E>
 */
public class DefaultComparator<E> implements Comparator<E>{

	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException{
		return ((Comparable<E>) a).compareTo(b);
	}
	
}
