package deque;

/*
 * EmptyDequeException is thrown on attempting to dequeue an empty deque 
 */
public class EmptyDequeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmptyDequeException() {}
	
	public EmptyDequeException(String message) {
		super(message);
	}
	
}
