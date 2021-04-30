package sort;

import java.util.Arrays;
import java.util.Comparator;
import sort.DefaultComparator;

/**
 * Provides several common sorting algorithms
 * @author User
 *
 */
public class Sort<K> {
	
	
	//---------------------Merge Sort--------------------------
	/**
	 * Merges two sorted arrays
	 * @param <K> a Comparable type
	 * @param s1 a sorted sub-array
	 * @param s2 a sorted sub-array
	 * @param S an array
	 */
	private static <K> void merge(K[] s1, K[] s2, K[] S, Comparator<K> comp) {
		int i =0, j = 0;
		while (i + j < S.length) {
			if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0))
				S[i+j] = s1[i++];
			else
				S[i+j] = s2[j++];
		}
		
	}
	
	/**
	 * Recursively calls merge sort on an array
	 * @param <K> a Comparable type
	 * @param S an array
	 * @param comp the comparator to be used
	 */
	public static <K> void mergeSort(K[] S, Comparator<K> comp) {
		int n = S.length;
		if (n < 2) return;
		int mid = n / 2;
		// Divide into two sub-arrays
		K[] s1 = Arrays.copyOfRange(S, 0, mid);
		K[] s2 = Arrays.copyOfRange(S, mid, n);
		// Recursively sort the sub-arrays
		mergeSort(s1, comp);
		mergeSort(s2, comp);
		// Merge sorted sub-arrays
		merge(s1, s2, S, comp);		
	}
	
	/**
	 * Merge sort using default comparator - uses natural ordering of elements
	 * @param <K> a Comparable
	 * @param S an array
	 */
	public static <K> void mergeSort(K[] S) {
		mergeSort(S, new DefaultComparator<K>());
	}
	
	
	
	//--------------------Quick Sort---------------------------------------------
	
	/**
	 * In-place Quick Sort
	 * @param <K> a Comparable type
	 * @param S an array
	 * @param comp the comparator to be used
	 * @param a the first index in the array
	 * @param b the last index in the array
	 */
	public static <K> void quickSort(K[] S, Comparator<K> comp, int a, int b) {
		// Check if need to sort
		if (a >= b) return;
		// The last index is the pivot
		int left = a;
		int right = b - 1;
		K pivot = S[b];
		K temp;
		while (left <= right) {
			// Scan left - move elements less than the pivot to the left
			while (left <= right && comp.compare(S[left], pivot) < 0) left ++;
			// Scan right - move elements greater than the pivot to the right
			while (left <= right && comp.compare(S[right], pivot) > 0) right --;
			if (left <= right) {
				temp = S[left];
				S[left] = S[right];
				S[right] = temp;
				left++; right--;
			}
		}
		// Put the pivot between the left and right sub-arrays
		temp = S[left];
		S[left] = S[b];
		S[b] = temp;
		// Recursive Quick Sort
		quickSort(S, comp, a, left - 1);
		quickSort(S, comp, left + 1, b);
	}
	
	/**
	 * Quick sort using default comparator - uses the natural order of elements
	 * @param <K> a Comparable type
	 * @param S an array
	 */
	public static <K> void quickSort(K[] S) {
		quickSort(S, new DefaultComparator<K>(), 0, S.length - 1);
	}
	
	
	
}
