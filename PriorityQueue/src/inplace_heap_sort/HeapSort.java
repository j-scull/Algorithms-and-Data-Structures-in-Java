package inplace_heap_sort;

//import array_list.*;
//import java.util.Comparator;

/**
 * Implements in place heap sort
 * @author User
 *
 * @param <E>
 */
public class HeapSort {

	// the heap size
	int size = 0;
	//Comparator<E> comp;
	//E[] heap;
	int[] heap;
	
	/**
	 * Constructor with given comparator
	 * @param c the comparator to be used
	 */
//	public HeapSort(Comparator<E> c) {
//		comp = c;
//	}
	
	/**
	 * Default constructor
	 */
//	public HeapSort() {
//		this(new DefaultComparator<E>());
//	}
	public HeapSort() {
		
	}
	
	//--------Utility methods
	
	/**
	 * Compare method
	 * @param a an element
	 * @param b an element
	 * @return
	 */
//	protected int compare(E a, E b) {
//		return comp.compare(a, b);
//	}
	
	/**
	 * Get the parent of a given index
	 * @param i an index in the array heap
	 * @return the parent index
	 */
	protected int parent(int i) {
		return (i - 1) / 2; 
	}
	
	/**
	 * Get the left child of a given index
	 * @param i an index in the array heap
	 * @return the left index
	 */
	protected int left(int i) {
		return (i * 2) + 1;
	}
	
	/**
	 * Get the right child of a given index
	 * @param i an index in the array heap
	 * @return the right index
	 */
	protected int right(int i) {
		return (i * 2) + 2;
	}
	
	/**
	 * Check whether a given index has a left child
	 * @param i an index in the array heap
	 * @return true if i has a left child, false otherwise
	 */
	protected boolean hasLeft(int i) {
		return left(i) < size;
	}
	
	/**
	 * Check whether a given index has a right child
	 * @param i an index in the array heap
	 * @return true if i has a right index, false otherwise
	 */
	protected boolean hasRight(int i) {
		return right(i) < size;
	}
	
	/**
	 * Swaps two elements 
	 * @param i the first index
	 * @param j the second index
	 */
//	protected void swap(int i, int j) {
//		E temp = heap[i];
//		heap[i] = heap[j];
//		heap[j] = temp;
//	}
	protected void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	
	
	/**
	 * Moves the entry at index i lower if necessary to restore heap order property
	 * @param i an index in the array heap
	 */
	protected void downHeap(int i) {
		while (hasLeft(i)) {
			int leftIndex = left(i);
			int largest = leftIndex;
			if (hasRight(i)) {
				int rightIndex = right(i);
				//if (compare(heap[leftIndex], heap[rightIndex]) < 0)
				if (heap[leftIndex] < heap[rightIndex])
					largest = rightIndex;
			}
			//if (compare(heap[i], heap[largest]) >= 0)
			if (heap[i] > heap[largest])
				break;
			swap(i, largest);
			i = largest;
		}
	}
	
	/**
	 * Recursive version of heapify
	 * @param i
	 */
	protected void recursiveHeapify(int i) {
		int largest = i;
		int l = i * 2 + 1;
		int r = i * 2 + 2;
		
		if (l < size && heap[l] > heap[largest])
			largest = l;
		if (r < size && heap[r] > heap[largest])
			largest = r;
		if (largest != i) {
			swap(i, largest);
			recursiveHeapify(largest);
		}
		
	}
	
	/**
	 * Sort the array using in place heap sort
	 * @param array
	 */
	//public void sort(E[] array) {
	public void sort(int[] array) {
		
		StopWatch timer = new StopWatch();
		timer.start();
		
		// Add elements to the heap
		heap = array;
		size = array.length;
		for (int j = size / 2 - 1; j >= 0; j--) {
			//recursiveHeapify(j);
			downHeap(j);
		}
		System.out.println(timer.getElapsedTime());
		
		for (int i = size - 1; i > 0; i --) {
			swap(0, i);  // remove the largest element from the heap
			size--;      // decrease heap size
			//recursiveHeapify(0);   // restore heap property order
			downHeap(0);
		}
		System.out.println(timer.getElapsedTime());
		timer.stop();
	}
	
	
}
