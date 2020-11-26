package array_list;

//import java.util.ArrayList;

public class TestArrayList {

	
	public static void main(String[] args) {
		
		System.out.println("Testing java.util.ArrayLsit");
		//ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arr = new ArrayList<Integer>(4);
		arr.add(0, 1);
		System.out.println(arrToString(arr));
		arr.remove(0);
		arr.add(0, 3);
		arr.add(0, 2);
		System.out.println(arrToString(arr));
		arr.remove(0);
		arr.add(0, 5);
		arr.add(0, 4);
		System.out.println(arrToString(arr));
		arr.remove(0);
		System.out.println(arrToString(arr));
		arr.remove(0);
		System.out.println(arrToString(arr));
		arr.remove(0);
		arr.add(0, 7);
		arr.add(0, 6);
		System.out.println(arrToString(arr));
		arr.remove(0);
		System.out.println(arrToString(arr));
		arr.remove(0);
		System.out.println(arrToString(arr));
		
		System.out.println("Is empty: " + arr.isEmpty());
		System.out.println("Size: " + arr.size());
	
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



