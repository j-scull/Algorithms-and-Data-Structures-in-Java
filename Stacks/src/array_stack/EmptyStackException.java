package array_stack;

/*
 * EmptyStackException 
 * Is thrown on attempting to access an empty stack
 */
public class EmptyStackException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyStackException() {}
	
	public EmptyStackException(String message) {
		super(message);
	}
	
}
