package sort;

/* 
 * Implements merge sort
 * 
 */
public class MergeSort {

	private int[] arr;
	
	/* 
	 * Constructs MergeSort
	 */
	public MergeSort(int[] unsorted) {
		arr = unsorted;
	}
	
	/*
	 * Sorts the array using merge sort
	 */
	public void sort() {
		
		if (arr.length <= 1)
			return;
		int[] first = new int[arr.length/2];
		int[] second = new int[arr.length - arr.length/2];
		for (int i = 0; i < first.length; i++) {
			first[i] = arr[i];
		}
		for (int i = 0; i < second.length; i ++) {
			second[i] = arr[first.length + i];
		}
	
		MergeSort firstSorter = new MergeSort(first);
		MergeSort secondSorter = new MergeSort(second);
		firstSorter.sort();
		secondSorter.sort();
		merge(first, second);
	}
	
	
	/*
	 * Merges too sorted sub-arrays together
	 * @param first
	 * @param second
	 */
	private void merge(int[] first, int[] second) {
		
		int iFirst = 0;   // index for the first array
		int iSecond = 0;  // index for the second array
		int j = 0;		  // index for the merged array
		
		while (iFirst < first.length && iSecond < second.length) {
			
			if (first[iFirst] < second[iSecond]) {
				arr[j] = first[iFirst];
				iFirst++;
			}
			else {
				arr[j] = second[iSecond];
				iSecond++;
			}
			j++;	
		}
		// Copy any remaining entries
		while (iFirst < first.length) {
			arr[j] = first[iFirst];
			iFirst++;
		}
		while (iSecond < second.length) {
			arr[j] = second[iSecond];
			iSecond++;
		}
	}
	
}
