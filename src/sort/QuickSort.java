package sort;

/*
 * Implementts QuickSort
 */
public class QuickSort {

	private int[] arr;
	
	/*
	 * Construct QuickSort
	 * @param unsorted an array of integers
	 */
	public QuickSort(int[] unsorted) {
		arr = unsorted;
	}
	
	/*
	 * Sort the array
	 */
	public void sort() {
		recursiveSort(0, arr.length-1);
	}
	
	/*
	 * Partitions the array and sorts each half recursively
	 * @param from the first index of the array subset
	 * @param to the last index of the array subset
	 */
	private void recursiveSort(int from, int to) {
		if (from >= to)
			return;
		int p = partition(from, to);
		recursiveSort(from, p);
		recursiveSort(p + 1, to);
	}
	
	/*
	 * Partitions the array around the pivot
	 * @param from the first index of the array subset
	 * @param to the last index of the array subset
	 * @return pivot the element used to partition the array
	 */
	private int partition(int from, int to) {
		int pivot = arr[from];
		int i = from - 1;
		int j = to + 1;
		while (i < j) {
			i++; while (arr[i] < pivot) i++;
			j--; while (arr[j] > pivot) j--;
			if (i < j) swap(i, j);
		}
		return j;
	}
	
	/*
	 * Swaps to elements in an array
	 * @param i an index in the array
	 * @param j an index in the array
	 */
	private void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
