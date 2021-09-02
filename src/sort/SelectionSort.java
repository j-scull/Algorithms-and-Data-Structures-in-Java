package sort;

/*
 * Sorts an array using selection sort
 */
public class SelectionSort {

	private int[] arr;
	
	/*
	 * Construction the selection sorter
	 */
	public SelectionSort(int[] unsorted) {
		arr = unsorted;
	}
	
	/*
	 * Sorts the array
	 */
	public void sort() {
		
		int minPos;
		for (int i = 0; i < arr.length - 1; i++) {
			minPos = minimumPosition(i);
			swap(i, minPos);
		}
	}
	
	/*
	 * Finds the minimum element in the array
	 * @param start the index to begin searching for the minimum element
	 * @return minpos the minimum element
	 */
	private int minimumPosition(int start) {
		
		int minPos = start;
		for (int i = start + 1; i < arr.length; i++) {
			if (arr[i] < arr[minPos])
				minPos = i;
		}
		return minPos;
	}
	
	/*
	 * Swaps two elements in an array
	 * @param index an index in the array
	 * @param minPos the index of the minimum element in the array
	 */
	private void swap(int index, int minPos) {
		int temp = arr[index];
		arr[index] = arr[minPos];
		arr[minPos] = temp;
	}
	
	
}
