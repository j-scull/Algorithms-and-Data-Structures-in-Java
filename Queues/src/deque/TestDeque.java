package deque;

/*
 * Tests the Linked List implementation of the deque
 */
public class TestDeque {

	
	public static void main(String[] args){
		
		System.out.println("Testing deque");
		LinkDeque<String> dQ = new LinkDeque<String>();
		System.out.println("Deque size: " + dQ.size());
		System.out.println("Deque is empty: " + dQ.isEmpty());
		dQ.insertFirst("Water");
		dQ.insertLast("Potatoes");
		dQ.insertLast("Garlic");
		dQ.insertFirst("Kale");
		System.out.println("\n" + dQ);
		System.out.println("Deque size: " + dQ.size());
		System.out.println("Deque is empty: " + dQ.isEmpty());
		System.out.println("Front: " + dQ.front());
		System.out.println("Rear: " + dQ.rear());
		
		dQ.removeFirst();
		dQ.removetLast();
		System.out.println("\nAfter removing two elements:");
		System.out.println(dQ);
		System.out.println("Deque size: " + dQ.size());
		System.out.println("Deque is empty: " + dQ.isEmpty());
		System.out.println("Front: " + dQ.front());
		System.out.println("Rear: " + dQ.rear());
		
		dQ.removeFirst();
		dQ.removetLast();
		System.out.println("\nThrow and catch exception:");
		try {
			System.out.println("Attempt to removeFirst() on empty deque:");
			dQ.removeFirst();
		}
		catch (EmptyDequeException ex) {
			System.out.println(ex.getMessage());
		}
		try {
			System.out.println("Attempt to removeLast() on empty deque:");
			dQ.removetLast();
		}
		catch (EmptyDequeException ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println(dQ);
		System.out.println("Deque size: " + dQ.size());
		System.out.println("Deque is empty: " + dQ.isEmpty());
	}
}
