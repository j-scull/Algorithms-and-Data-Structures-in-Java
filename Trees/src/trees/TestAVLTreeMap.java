package trees;

import maps.Entry;

public class TestAVLTreeMap {
	
	public static void main(String[] args) {
		
		TreeMap<Double, String> tree = new AVLTreeMap<>();
		tree.put(2.00, "Tea");
		tree.put(0.70, "Apple");
		tree.put(0.90, "Banana");
		tree.put(3.20, "Coffee");
		tree.put(5.00, "Sandwich");
		tree.put(3.90, "Coconut Latte");
		tree.put(1.80, "Chocolate");
		
		System.out.println("------------------");
		System.out.println("Testing entry set:");
		for (Entry<Double, String> entry: tree.entrySet()) {
			System.out.println(entry.getValue() + ": " + entry.getKey());
		}
		System.out.println("\n------------------");
		System.out.println("Testing Sub Map:");
		for (Entry<Double, String> entry: tree.subMap(1.50, 3.50)) {
			System.out.println(entry.getValue() + ": " + entry.getKey());
		}
		
		System.out.println("\n------------------");
		System.out.println("Testing accessors:  ");
		System.out.println("get(5.00) = " + tree.get(5.00));
		System.out.println("firstEntry() = " + tree.firstEntry().getValue());
		System.out.println("lastEntry() = " + tree.lastEntry().getValue());
		System.out.println("ceilingEntry(3.50) = " + tree.ceilingEntry(3.50).getValue());
		System.out.println("floorEntry(1.00) = " + tree.floorEntry(1.00).getValue());
		System.out.println("higherEntry(3.20) = " + tree.higherEntry(3.20).getValue());
		System.out.println("lowerEntry(3.20) = " + tree.lowerEntry(3.20).getValue());
		
	
	}
}
