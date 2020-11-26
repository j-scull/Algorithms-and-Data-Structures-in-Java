package trees;

import java.util.Iterator;

public class TestBinaryTree {
	
	public static void main(String[] args) {
		
		System.out.println("Testing Binary tree implementation");
		LinkedBinaryTree<Integer> T = new LinkedBinaryTree<>();
		Position<Integer> root = T.addRoot(1);
		Position<Integer> left = T.addLeft(root, 2);
		Position<Integer> right = T.addRight(root, 3);
		T.addLeft(left, 4);
		T.addRight(left, 5);
		T.addLeft(right, 6);
		T.addRight(right, 7);
		
		System.out.println("\nTesting positions iterable");
		for (Position<Integer> i: T.positions()) {
			System.out.println(i.getElement());
			if (i.getElement() == 7)
				T.remove(i);
		}
		
		
		System.out.println("\nTesting element iterator");
		Iterator<Integer> iter = T.iterator();
		System.out.println(iter.next());
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		
		
		System.out.println("\nTesting preorder iterable");
		T.addRight(right, 7);
		for (Position<Integer> i: T.preorder()) {
			System.out.println(i.getElement());
		}
		
		System.out.println("\nTesting postorder iterable");
		for (Position<Integer> i: T.postorder()) {
			System.out.println(i.getElement());
		}
		
		System.out.println("\nTesting inorder iterable");
		for (Position<Integer> i: T.inorder()) {
			System.out.println(i.getElement());
		}
		
		System.out.println("\nTesting breath first iterable");
		for (Position<Integer> i: T.breathFirst()) {
			System.out.println(i.getElement());
		}
	}

}
