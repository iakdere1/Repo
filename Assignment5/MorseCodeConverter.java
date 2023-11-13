package work;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree morseCodeTree = new MorseCodeTree();
	
	public static String printTree()
	{
		ArrayList<String> treeArrayList = morseCodeTree.toArrayList();
		String tree = String.join(" ", treeArrayList);
		return tree.trim();
	}
	
	public static String convertToEnglish(String data) {
		String[] phrases = data.split(" / ");
		StringBuilder stringBuilder = new StringBuilder();
		for (String word : phrases)
		{
			String[] chars = word.split(" ");
			for (String character : chars)
			{
				String morseValue = morseCodeTree.fetch(character);
				if(morseValue != null)
				{
					stringBuilder.append(morseValue);
				}
			}
			stringBuilder.append(" ");
		}
		return stringBuilder.toString().trim();
	}
	
	public static String convertToEnglish(File fileData) throws FileNotFoundException {
		String data = "how do i love thee let me count the ways";
		try(Scanner scanner = new Scanner(fileData))
		{
			data = scanner.nextLine();
			data = convertToEnglish(data);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.print(data);
		return data;

	}
}
