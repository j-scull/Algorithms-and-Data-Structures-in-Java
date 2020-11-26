package array_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>, Iterable<E>{
	
	private static final int CAPACITY = 256;
	private E[] arr;
	private int size = 0;
		
	/*
	 * Constructor with ArrayList capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int cap) {
		arr = (E[]) new Object[cap]; // safe cast
	}
	
	/*
	 * Default constructor
	 */
	public ArrayList() {
		this(CAPACITY);
	}
	
	/* Returns the size of the list
	 * @return the number of elements in the list
	 */
	public int size() {
		return size;
	}
	
	/* Checks if the list is empty
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/* Retrieves the element at the given index
	 * @param i the index of the element
	 * @return the element
	 * @exception IndexOutOfBoundsException if index is not in the list
	 */
	public E get(int i) throws IndexOutOfBoundsException{
		checkIndex(i, size);
		return arr[i];
	}
	
	/* Sets the element at the given index
	 * @param i the index of the element
	 * @param the element to be added
	 * @returns the replaced element
	 * @exception IndexOutOfBoundsException if index is not in the list
	 */
	public E set(int i, E element) throws IndexOutOfBoundsException{
		checkIndex(i, size);
		E replaced = arr[i];
		arr[i] = element;
		return replaced;
	}

	/* Add an element at the given index
	 * Resizes the ArrayList if necessary
	 * @param index the where the element is to be added
	 * @param the element to be added
	 * @exception IndexOutOfBoundsException if index is not in the list
	 */
	public void add(int i, E element) throws IndexOutOfBoundsException{
		checkIndex(i, size + 1);
		if (size == arr.length)
			resize(arr.length * 2);
		for (int j = size; j > i; j--)
			arr[j] = arr[j-1];
		arr[i] = element;
		size++;
	}
	
	/*
	 * Default add with no index parameter supplied
	 * @param element the element to be added
	 */
	public void add(E element) throws IndexOutOfBoundsException{
		add(size, element);
	}
	
	/* Remove an element at the given index
	 * @param index the where the element is to be removed
	 * @exception IndexOutOfBoundsException if index is not in the list
	 */
	public E remove(int i) throws IndexOutOfBoundsException{
		checkIndex(i, size);
		E element = arr[i];
		for (int j = i; j < size - 1; j++) {
			arr[j] = arr[j+1];
		}
		arr[size-1] = null;
		size--;
		return element;
	}
	
	/*
	 * Utility method
	 * Checks whether the given index is out of bound
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");
	}
	
	/*
	 * Resize the array if the maximum capacity is reached
	 * @param the new capacity
	 */
	@SuppressWarnings("unchecked")
	protected void resize(int capacity) {
		E[] newArray = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			newArray[i] = arr[i];
		arr = newArray;
	}
	
	//----Nested iterator class
	
	private class ArrayIterator implements Iterator<E>{
		
		private int j = 0;
		private boolean removable = false;
		
		public boolean hasNext() { 
			return j < size; 
		}
		
		public E next() throws NoSuchElementException{
			if (j == size)
				throw new NoSuchElementException("No next element");
			removable = true;
			return arr[j++];
		}
		
		public void remove() throws IllegalStateException{
			if (!removable)
				throw new IllegalStateException("Nothing to remove");
			ArrayList.this.remove(j-1);
			j--;
			removable = false;
		}
	}
	
	/**
	 * 
	 * @return an iterator for the ArrayList
	 */
	public Iterator<E> iterator(){
		return new ArrayIterator();
	}
	
	public String toString() {
		String result = "[";
		for (int i = 0; i < arr.length - 1; i++)
			result += arr[i] + ", ";
		if (arr.length > 0)
			result += arr[arr.length - 1];
		result += "]";
		return result;
	}
	
	
}