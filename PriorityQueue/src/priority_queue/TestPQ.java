package priority_queue;

import positional_list.*;

/**
 * Test the Unsorted and Sorted Priority Queue implementations
 * @author User
 *
 */
public class TestPQ {
	
	public static void main(String[] args) {
		
		// Unsorted PQ
		// insert O(1)
		// min O(n)
		// removeMin() O(n)
		System.out.println("Testing the unsorted priority queue implementation");
		UnsortedPriorityQueue<Integer, String> uPQ = new UnsortedPriorityQueue<Integer, String>();
		uPQ.insert(4, "Apples");
		uPQ.insert(3, "Oranges");
		uPQ.insert(6, "Coconuts");
		uPQ.insert(1, "Coffee");
		System.out.println("Size: " + uPQ.size());
		System.out.println("Min: " + uPQ.min().getValue());
		System.out.println("Removing items: ");
		while (!uPQ.isEmpty()) {
			System.out.println(uPQ.removeMin().getValue());
		}
		
		// Unsorted PQ
		// insert O(n)
		// min O(1)
		// removeMin() O(1)
		System.out.println("\nTesting the sorted priority queue implementation");
		SortedPriorityQueue<Integer, String> sPQ = new SortedPriorityQueue<Integer, String>();
		sPQ.insert(2, "Walk");
		sPQ.insert(1, "Lunch");
		sPQ.insert(3, "Groceries");
		sPQ.insert(4, "College work");
		System.out.println("Size: " + sPQ.size());
		System.out.println("Min: " + sPQ.min().getValue());
		System.out.println("Removing items: ");
		while (!sPQ.isEmpty()) {
			System.out.println(sPQ.removeMin().getValue());
		}
		
		// Heap PQ - the most efficient implementation of a Priority Queue 
		// insert O(log(n))
		// min O(1)
		// removeMin() O(log(n))
		// Run times are Amortised for an array-based representation
		System.out.println("\nTesting the heap priority queue implementation");
		HeapPriorityQueue<Integer, String> hPQ = new HeapPriorityQueue<Integer, String>();
		hPQ.insert(3, "The Singularity Is Near");
		hPQ.insert(1, "Homesick For Another World");
		hPQ.insert(2, "Stoner");
		hPQ.insert(4, "Data Structures and Algorithms in Java");
		System.out.println("Size: " + hPQ.size());
		System.out.println("Min: " + hPQ.min().getValue());
		System.out.println("Removing items: ");
		while (!hPQ.isEmpty()) {
			System.out.println(hPQ.removeMin().getValue());
		}
		
		// Test bottom-up heap construction with heap PQ
		// Constructs HeapPriorityQueue efficiently with given key-value arrays
		System.out.println("\nTesting Priority Queue with bottom-up heap initialisation");
		Integer[] keys = {4, 7, 2, 7, 4, 9, 1, 10};
		String[] values = {"Dinner", "Film", "Walk", "Read", "Hang-out", "Run", "Cleaing", "nap"};
		HeapPriorityQueue<Integer, String> h2PQ = new HeapPriorityQueue<Integer, String>(keys, values);
		while (!h2PQ.isEmpty()) {
			System.out.println(h2PQ.removeMin().getValue());
		}
		
		// Test sorting with a priority queue
		// Using an Unsorted PQ for this is effectively selection sort - O(n)
		// Using a sorted PQ is insertion sort - also O(n)
		
		System.out.println("\nTesting priority queue sort");
		LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(5);
		System.out.println("First: " + list.first().getElement());
		System.out.println("Last: " + list.last().getElement());
		System.out.println("Sorting...");
		pqSort(list, h2PQ);
		System.out.println("First: " + list.first().getElement());
		System.out.println("Last: " + list.last().getElement());

		System.out.println("\nTesting Adaptable priority queue");
		HeapAdaptablePriorityQueue<Integer, String> aPQ = new HeapAdaptablePriorityQueue<Integer, String>(keys, values);
		//HeapAdaptablePriorityQueue<Integer, String> aPQ = new HeapAdaptablePriorityQueue<>();
		//aPQ.insert(0, "Listen to music");
		//aPQ.insert(2, "Eat");
		//aPQ.swap(0, 1);
		System.out.println(aPQ.min().getKey() + ": " + aPQ.min().getValue());
		
		System.out.println("Replacing key of minimal entry...");
		aPQ.replaceKey(aPQ.min(), 10);
		System.out.println("First: " + aPQ.min().getKey() + ": " + aPQ.min().getValue());
		
		
		
	}

	/**
	 * Sorts sequence S using an initially empty Priority queue
	 * If used with a HeapPriorityQueue becomes a heap sort algorithm
	 * @param S a LinkedPositional list
	 * @param P an empty PriorityQueue
	 */
	public static <E> void pqSort(LinkedPositionalList<E> S, PriorityQueue<E,?> P) {
		int n = S.size();
		// fill P with all elements removed from S
		for (int i = 0; i < n; i ++) {
			E element = S.remove(S.first());
			P.insert(element, null);
		}
		// remove the elements from P in order and insert in S
		for (int j = 0; j < n; j++) {
			E element = P.removeMin().getKey();
			S.addLast(element);
		}
	}
	
}
