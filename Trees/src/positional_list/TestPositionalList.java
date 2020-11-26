package positional_list;

import java.util.Iterator;

public class TestPositionalList {

	public static void main(String[] args) {
		
		System.out.println("______________________");
		System.out.println("\nTesting PositionalList");
		System.out.println("______________________");
		
		LinkedPositionalList<String> lst = new LinkedPositionalList<String>();
		System.out.println("List is empty: " + lst.isEmpty());
		System.out.println("List size: " + lst.size());
		Position<String> p1 = lst.addFirst("Bread");
		Position<String> p2 = lst.addLast("Tomatoes");
		Position<String> p3 = lst.addFirst("Pesto");
		Position<String> p4 = lst.addLast("Cheese");
		Position<String> p5 = lst.addFirst("Veggie Burger");
		Position<String> p6 = lst.addFirst("Onion");
		System.out.println("List is empty: " + lst.isEmpty());
		System.out.println("List size: " + lst.size());
		
		System.out.println();
		System.out.println("First: " + lst.first().getElement());
		System.out.println("Last: " + lst.last().getElement());
		lst.remove(p1);
		lst.remove(p5);
		lst.addAfter(p2, "Chocolate");
		lst.addBefore(p4, "Bananas");
		System.out.println("List is empty: " + lst.isEmpty());
		System.out.println("List size: " + lst.size());
		System.out.println("Before p3: " + lst.before(p3).getElement());
		System.out.println("After p6: " + lst.after(p6).getElement());
		
		System.out.println("\nTesting iteration");
		Iterator<String> iterator = lst.iterator();
		String item;
		while (iterator.hasNext()) {
			item = iterator.next();
			System.out.println(item);
		}
		
		System.out.println("\nTesting position iteration");
		for (Position<String> p: lst.positions()) {
			System.out.println(p.getElement());
		}
		
		
	}
	
	
}
