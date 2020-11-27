package maps;

/**
 * Test the Map ADT implementations
 * @author User
 *
 */
public class TestMap {
	
	public static void main(String[] args) {
		
		System.out.println("Testing Map implementations");
		
		System.out.println("\nTesting UnsortedTablehMap");
		UnsortedTableMap<String, Integer> uTMap = new UnsortedTableMap<>();
		uTMap.put("Tea", 2);
		uTMap.put("Coffee", 3);
		uTMap.put("Water", 1);
		for (String key: uTMap.keySet()) {
			System.out.println(key + ": " + uTMap.get(key));
		}
		for (Entry<String,Integer> entry: uTMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		System.out.println("\nTesting ChainHashMap");
		ChainHashMap<String, String> cHMap = new ChainHashMap<>();
		cHMap.put("Breakfast", "Wheatabix");
		cHMap.put("Lunch", "Toasties");
		cHMap.put("Diner", "Pasta");
		for (String key: cHMap.keySet()) {
			System.out.println(key + ": " + cHMap.get(key));
		}
		cHMap.remove("Breakfast");
		for (Entry<String,String> entry: cHMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		System.out.println("\nTesting ProbeHashMap");
		ProbeHashMap<String, Integer> pHMap = new ProbeHashMap<>();
		pHMap.put("Aubergines", 6);
		pHMap.put("Chillies", 2);
		pHMap.put("Tin plum tomatoes", 4);
		pHMap.put("Grams of pasta", 500);
		for (String key: pHMap.keySet()) {
			System.out.println(key + ": " + pHMap.get(key));
		}
		cHMap.remove("Pasta");
		for (Entry<String,Integer> entry: pHMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		System.out.println("\nTesting SortedMap");
		SortedMap<Double,String> sMap = new SortedTableMap<>();
		sMap.put(3.20, "Coffee");
		sMap.put(4.00, "Coconut Latte");
		sMap.put(3.80, "Latte");
		sMap.put(2.00, "Tea");
		sMap.put(2.0, "Peppermint tea");
		System.out.println("keySet():");
		for (Double key: sMap.keySet()) {
			System.out.println(key + ": " + sMap.get(key));
		}
		sMap.remove(4.00);
		System.out.println("sMap.remove(4.00)");
		System.out.println("entrSet():");
		for (Entry<Double,String> entry: sMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		System.out.println("subMap(3.20, null):");
		for (Entry<Double,String> entry: sMap.subMap(3.20, null)) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
	

	}
	
	
	
	

}
