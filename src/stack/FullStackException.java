package stack;

/*
 * FullStackException is part of an Array based implementation of a stack.
 * Is thrown when attempting to push onto a full stack.
 */
public class FullStackException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FullStackException() {}
	
	public FullStackException(String message) {
		super(message);
	}
	
}
