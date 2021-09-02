package map;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Count the occurrences of words in a document
 */
public class WordCount {
	
	public static void main(String[] args) {
		
		Map<String, Integer> frequency = new HashMap<>();
		String file_name = "test.txt";
		File file = new File(file_name);
		
		try {
			 Scanner doc = new Scanner(file);
			 doc.useDelimiter("[^a-zA-Z]+");
			 
			 while (doc.hasNext()) {
				 String word = doc.next().toLowerCase();
				 Integer count = frequency.get(word);
				 if (count == null)
					 count = 0;
				 frequency.put(word, count + 1);
			 }
			 
			 while (!frequency.isEmpty()) {
				 int maxCount = 0;
				 String maxWord = "none";
				 for (java.util.Map.Entry<String, Integer> e: frequency.entrySet()) {
					 if (e.getValue() > maxCount) {
						 maxWord = e.getKey();
						 maxCount = e.getValue();
					 }
				 }
				 System.out.println(maxWord + ": " + maxCount);
				 frequency.remove(maxWord);
			 }
			 doc.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not Found");
		}
		
		
	}
	

}
