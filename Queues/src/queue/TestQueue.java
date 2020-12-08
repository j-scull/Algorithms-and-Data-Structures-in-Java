package queue;

/*
 * Tests LinkQueue and ArrayQueue
 */
public class TestQueue {

	
	public static void main(String[] args) {
		
		System.out.println("Testing LinkQueue");
		LinkQueue<Integer> lQ = new LinkQueue<Integer>();
		lQ.enqueue(1);
		lQ.enqueue(2);
		lQ.enqueue(3);
		lQ.enqueue(4);
		lQ.dequeue();
		lQ.dequeue();
		lQ.enqueue(5);
		lQ.enqueue(6);
		System.out.println(lQ);
		System.out.printf("LinkQueue size: %d\n", lQ.size());
		System.out.printf("Front of queue: %d\n", lQ.front());
		int num;
		while (!lQ.isEmpty()) {
			num = lQ.dequeue();
			System.out.printf("Dequeue: %d\n", num);
		}
		System.out.printf("LinkQueue size: %d\n", lQ.size());

		
		System.out.println("\nTesting ArrayQueue");
		ArrayQueue<String> aQ = new ArrayQueue<String>(4);
		aQ.enqueue("Apple");
		aQ.enqueue("Orange");
		aQ.enqueue("Coffee");
		aQ.enqueue("Coconut");
		System.out.println(aQ);
		System.out.printf("ArrayQueue size: %d\n", aQ.size());
		aQ.dequeue();
		aQ.dequeue();
		aQ.enqueue("Chocolate");
		aQ.enqueue("Pasta");
		System.out.printf("ArrayQueue size: %d\n", aQ.size());
		System.out.printf("Front of queue: %s\n", aQ.front());
		String item;
		while (!aQ.isEmpty()) {
			item = aQ.dequeue();
			System.out.printf("Dequeue: %s\n", item);
		}
		System.out.printf("ArrayQueue size: %d\n", aQ.size());
		
	}
	
}
