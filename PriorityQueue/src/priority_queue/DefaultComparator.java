package priority_queue;

import java.util.Comparator;

/**
 * Default Comparator for the Priority Queue, 
 * relies on the natural ordering of element types
 * @author User
 *
 * @param <E> the element to be compared
 */
public class DefaultComparator<E> implements Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a,  E b) throws ClassCastException{
		return ((Comparable<E>) a).compareTo(b);
	}
	
}
