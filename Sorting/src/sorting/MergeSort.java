package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {
	
	/**
	 * Merges two sorted arrays into a larger sorted array
	 * @param <K> 
	 * @param S1 the first sub-array
	 * @param S2 the second sub-array
	 * @param S the array to contain the resulting merged sub-arrays
	 * @param comp the comparator to be used
	 */
	public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
		int i = 0, j = 0;
		while (i + j < S.length) {
			if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
				S[i+j] = S1[i++];
			else
				S[i+j] = S2[j++];
		}
	}
	
	/**
	 * Merge Sort an array
	 * @param <K>
	 * @param S the array to be sorted
	 * @param comp the comparator to be used
	 */
	public static <K> void mergeSort(K[] S, Comparator<K> comp) {
		int n = S.length;
		if (n < 2) return;
		// Divide the array in two sub-arrays
		int mid = n / 2;
		K[] S1 = Arrays.copyOfRange(S, 0, mid);
		K[] S2 = Arrays.copyOfRange(S, mid, n);
		// Recursive mergeSort
		mergeSort(S1, comp);
		mergeSort(S2, comp);
		// Merge the sub-arrays
		merge(S1, S2, S, comp);
	}
	
	public static void main(String[] args) {
		
		Comparator<String> comp = new DefaultComparator<>();
		String[] crows = {"Carrion Crow", "Jackdaw", "Rook", "Jay", "Cough", "Magpie", "Raven", "Grey Crow"};
		mergeSort(crows, comp);
		for (String crow: crows)
			System.out.println(crow);
		
	}
	
	

}
