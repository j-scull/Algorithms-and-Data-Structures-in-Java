package linked_list_stack;

import array_stack.Stack;
import array_stack.EmptyStackException;

public class LinkedListStack<E> implements Stack<E>{
	
	private int size;
	private Node top;
	
	/*
	 * Constructs a LinkedListStack
	 */
	public LinkedListStack() {
		size = 0;
		top = null;
	}
	
	/*
	 * Returns the size of the stack
	 * @return size the number of elements in the stack
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Checks if the stack is empty
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Adds a new element to the top of the stack
	 * @param element the object to be added to the stack
	 */
	public void push(E element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = top;
		top = newNode;
	}
	
	/*
	 * Returns the element at the top of the stack without removing it
	 * @return the element at the top of the stack
	 */
	public E top() throws EmptyStackException{
		if (isEmpty())
			throw new EmptyStackException("Stack is empty");
		return top.data;
	}
	
	/*
	 * Returns and removes the element at the top of the stack without removing it
	 * @return the element at the top of the stack 
	 */
	public E pop() throws EmptyStackException{
		if (isEmpty())
			throw new EmptyStackException("Stack is empty");
		E element = top.data;
		top = top.next;
		return element;
	}
	
	/*
	 * Implement a Node in the LinkedListstack
	 */
	class Node{
		
		public Node next;
		public E data;
		
	}
	
	

}
