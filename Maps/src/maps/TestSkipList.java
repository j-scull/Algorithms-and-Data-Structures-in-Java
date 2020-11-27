package maps;

public class TestSkipList {

	public static void main(String[] args) {
		
		SkipList<String, Integer> skip = new SkipList<>();
		System.out.println("Is empty: " + skip.isEmpty());
		System.out.println("Size: " + skip.size());
		skip.put("Tea", 1);
		System.out.println("Tea added");
		skip.put("Coffee", 2);
		System.out.println("Coffee added");
		skip.put("Water", 3);
		System.out.println("Water added");
		System.out.println("OK");
		
		System.out.println("Is empty: " + skip.isEmpty());
		System.out.println("Size: " + skip.size());
		
		for (String item: skip.keySet()) {
			System.out.println(item);
		}
		
		
	}
	
	
}
