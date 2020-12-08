package queue;

/*
 * Implements a solution to the Josephus problem
 * Hot potatoes
 */
public class Josephus {

	/*
	 * Solves the Josephus problem
	 * @param Q a queue of objects
	 * @param k the number of passes
	 * @return the last remaining element in the queue
	 */
	public static <E> E josephus(Queue<E> Q, int k) {
		
		if (Q.isEmpty())
			return null;
		while (Q.size() > 1) {
			System.out.println("Queue: " + Q);
			System.out.println("k: " + k);
			for (int i = 0; i < k; i++) {
				Q.enqueue(Q.dequeue());
			}
			E e = Q.dequeue();
			System.out.println(e + " is out");
		}
		return Q.dequeue();
		
	}
	
	/*
	 * Build a queue from an array of objects
	 * @param arr an array of objects
	 * @return Q a queue
	 */
	public static <E> LinkQueue<E> buildQueue(E arr[]){
		LinkQueue<E> Q = new LinkQueue<E>();
		for (int i = 0; i < arr.length; i++)
			Q.enqueue(arr[i]);
		return Q;
	}
	
	/*
	 * Test!
	 */
	public static void main(String[] args) {
		String[] s1 = {"Joe", "Shinzo", "Job", "Kenzo", "Josephus", "Sink"};
		
		LinkQueue<String> Q = buildQueue(s1);
		System.out.println("K = 10");
		System.out.println("The winner is " + josephus(Q, 10));
		
		Q = buildQueue(s1);
		System.out.println("\nK = 25");
		System.out.println("The winner is " + josephus(Q, 25));
		
	}
	
	
	
}
