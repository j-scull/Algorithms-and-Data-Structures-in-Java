package sort;

/*
 * Iplements insertion sort
 */
public class InsertionSort {

	private int[] arr;
	
	public InsertionSort(int[] unsorted) {
		arr = unsorted;
	}
	
	/*
	 * Sorts the array using insertion sort
	 */
	public void sort() {
		int val, j;
		for (int i = 1; i < arr.length; i++) {
			val = arr[i];
			j = i;
			while (j > 0 && arr[j-1] > val) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = val;
		}
	}
	
	
}

