package array_stack;

public class TestArrayStack {

	public static void main(String[] args) {
		
		ArrayStack<Integer> stackA = new ArrayStack<Integer>(); 
		System.out.println(stackA.size());
		stackA.push(1);
		stackA.push(2);
		stackA.push(3);
		stackA.status();
	}
		
}
