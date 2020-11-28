package inplace_heap_sort;

import java.util.Arrays;
import java.util.Random;

public class TestHeapSort {

	public static void main(String[] args) {
		
		
		int[] arr = randomIntArray(50000, 100);
		//System.out.println(Arrays.toString(arr));
		HeapSort heap = new HeapSort();
		StopWatch timer = new StopWatch();
		timer.start();
		heap.sort(arr);
		timer.stop();
		//System.out.println(Arrays.toString(arr));
		System.out.println("\nHeap sort");
		System.out.println("Elapsed time: " + timer.getElapsedTime() + " milliseconds");
		
		
	}
	
	
	/**
	 * Prints out a generic array
	 * @param <E>
	 * @param arr
	 */
	public static <E> void printArray(E[] arr) {
		System.out.print("[");
		for (E e: Arrays.copyOfRange(arr, 0, arr.length - 1)) {
			System.out.print(e + ", ");
		}
		try {
			System.out.print(arr[arr.length - 1]);
		}
		finally {
			System.out.print("]\n");
		}
		
	}
	
	/*
	 * Creates an array filled with random values
	 * @param len the length of the array
	 * @param n the range of possible values
	 * @return an array filled with len numbers between 0 and n-1
	 */
	public static int[] randomIntArray(int len, int n) {
		int[] arr = new int[len];
		Random generator = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = generator.nextInt(n);
		}
		return arr;
	}
	
	
}
