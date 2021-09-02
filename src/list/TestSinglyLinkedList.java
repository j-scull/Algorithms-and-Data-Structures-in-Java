package list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class TestSinglyLinkedList {
	
	
	@Test 
	void testInitialisation() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		// Size should be 0, isEmpty should be true
		// Access and remove methods should all return null
		assertEquals(list.size(), 0);
		assertEquals(list.isEmpty(), true);
		assertEquals(list.first(), null);
		assertEquals(list.last(), null);
		assertEquals(list.removeFirst(), null);
		assertEquals(list.removeLast(), null);
	}
	
	@Test
	void testIsEmpty() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		
		// Should be empty initially
		assertEquals(list.isEmpty(), true);

		// isEmpty should reflect updates to the list
		list.addFirst('A');
		assertEquals(list.isEmpty(), false);
		list.removeFirst();
		assertEquals(list.isEmpty(), true);
	}
	
	
	@Test
	void testAddFirst() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		
		// Check size, head and tail update for first addFirst element
		list.addFirst('A');
		assertEquals(list.size(), 1);
		assertEquals(list.first(), 'A');
		assertEquals(list.last(), 'A');
		
		// Check size, head and tail update for subsequent addFirst elements
		list.addFirst('B');
		assertEquals(list.size(), 2);
		assertEquals(list.first(), 'B');
		assertEquals(list.last(), 'A');
	}
	
	
	@Test
	void testAddLast() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		
		// Check size, head and tail update for first addLast element
		list.addLast('A');
		assertEquals(list.size(), 1);
		assertEquals(list.first(), 'A');
		assertEquals(list.last(), 'A');
		
		// Check size, head and tail update for subsequent addLast elements
		list.addLast('B');
		assertEquals(list.size(), 2);
		assertEquals(list.first(), 'A');
		assertEquals(list.last(), 'B');
	}
	
	
	
	@Test
	void testRemoveFirst() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		list.addFirst('A');
		list.addFirst('B');

		// Check removed element and size, head and tail update for removeFirst
		assertEquals(list.removeFirst(), 'B');
		assertEquals(list.size(), 1);
		assertEquals(list.first(), 'A');
		assertEquals(list.last(), 'A');
		
		// Check removed element and size, head and tail update for removeFirst on last node
		assertEquals(list.removeFirst(), 'A');
		assertEquals(list.size(), 0);
		assertEquals(list.first(), null);
		assertEquals(list.last(), null);
		
		// Check removeFirst returns null on empty
		assertEquals(list.removeFirst(), null);
	}
	
	
	@Test
	void testRemoveLast() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		list.addFirst('A');
		list.addFirst('B');
		list.addFirst('C');

		// Check removed element and size, head and tail update for removeLast
		assertEquals(list.removeLast(), 'A');
		assertEquals(list.size(), 2);
		assertEquals(list.first(), 'C');
		assertEquals(list.last(), 'B');
		
		// Check removed element and size, head and tail update for removeLast
		assertEquals(list.removeLast(), 'B');
		assertEquals(list.size(), 1);
		assertEquals(list.first(), 'C');
		assertEquals(list.last(), 'C');
		
		// Check removed element and size, head and tail update for removeLast on last node
		assertEquals(list.removeLast(), 'C');
		assertEquals(list.size(), 0);
		assertEquals(list.first(), null);
		assertEquals(list.last(), null);
		
		// Check removeLast returns null on empty
		assertEquals(list.removeFirst(), null);
	}
	
	
	@Test
	void testIteratorHasNext() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		Iterator<Character> iter = list.iterator();
		
		// iterator should not have next
		assertEquals(iter.hasNext(), false);
		
		list.addFirst('A');
		iter = list.iterator();
		
		// iterator should have next
		assertEquals(iter.hasNext(), true);		
	}
	
	
	@Test
	void testIteratorNext() {

		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		Iterator<Character> iter = list.iterator();
		
		// next on empty list should throw NoSuchElementException
		// see https://www.baeldung.com/junit-assert-exception
		Exception exception = assertThrows(NoSuchElementException.class, () -> {
			iter.next();
		});
		String expectedMessage = "Nothing left";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
		
		// Fill the list
		char[] array = {'A', 'B', 'C', 'D'};
		for (char c: array) 
			list.addLast(c);
		
		// test iteration
		int i = 0;
		while (iter.hasNext())
			assertEquals(iter.next(), array[i++]);
		
		// All elements have been consumed, next should throw NoSuchElementException
		exception = assertThrows(NoSuchElementException.class, () -> {
			iter.next();
		});
		actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	@Test
	void testIteratorRemove() {
		
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		Iterator<Character> iter = list.iterator();
		
		// Fill the list
		char[] array = {'A', 'B', 'C'};
		for (char c: array) 
			list.addLast(c);
		
		// Remove should throw IllegalStateException before next is consumed
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			iter.remove();
		});
		String expectedMessage = "Nothing to remove";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
		
		// remove should update the list
		iter.next();
		iter.remove();   // remove 'A
		assertEquals(list.first(), 'B');
		assertEquals(list.size(), 2);
		iter.next();
		iter.next();
		iter.remove();  // remove 'C'
		assertEquals(list.last(), 'B');
		assertEquals(list.size(), 1);

		// All elements have been consumed, remove should throw IllegalStateException
		exception = assertThrows(IllegalStateException.class, () -> {
			iter.remove();
		});
		actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testClone() {
		SinglyLinkedList<Character> originalList = new SinglyLinkedList<>();
		originalList.addFirst('A');
		originalList.addFirst('B');
		originalList.addFirst('C');

		try {
			SinglyLinkedList<Character> clonedList = originalList.clone();
			
			// Check that sizes are equal
			assertEquals(originalList.size(), clonedList.size());
			
			// Check both lists contain the same elements
			
			
			// Check that the clone list does not change on updates to the original
			
			

		
			
		} catch (CloneNotSupportedException e){
			fail("Cloning throws exception");
		}
		
	}

}
