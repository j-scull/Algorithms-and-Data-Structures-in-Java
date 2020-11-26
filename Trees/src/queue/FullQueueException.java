package queue;

/*
 * FullQueueException is thrown if attempting to add to a queue 
 * at maximum capacity.
 * For use with an array based implementation of a Queue ADT.
 */
public class FullQueueException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FullQueueException() {};
	
	public FullQueueException(String message) {
		super(message);
	}
	
}
