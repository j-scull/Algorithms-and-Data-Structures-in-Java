package array_list;

//import java.util.ArrayList;

public class TestArrayList {

	
	public static void main(String[] args) {
		
		System.out.println("Testing java.util.ArrayLsit");
		//ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(1, 2);
		arr.add(4);
		arr.add(6);
		arr.add(2, 3);
		arr.add(4, 5);
		System.out.println("Is empty: " + arr.isEmpty());
		System.out.println("Size: " + arr.size());
		System.out.println(arrToString(arr));
		System.out.println("\nPerforming remove operation");
		arr.remove(3);
		arr.remove(3);
		System.out.println("Is empty: " + arr.isEmpty());
		System.out.println("Size: " + arr.size());
		System.out.println(arrToString(arr));
	
	}
	
	public static <E> String arrToString(ArrayList<E> arr) {
		String result = "[";
		if (arr.size() > 0) {
			int i = 0;
			for (; i < arr.size() - 1; i++) {
				result += arr.get(i) + ", ";
			}
			result += arr.get(i);
		}
		result += "]";
		return result;
	}
	
	
	
}



