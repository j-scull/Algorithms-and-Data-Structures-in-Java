package queue;

/*
 * EmptyQueueException is thrown on attempting to access an empty queue
 */
public class EmptyQueueException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmptyQueueException() {};
	
	public EmptyQueueException(String message) {
		super(message);
	}
	
}
