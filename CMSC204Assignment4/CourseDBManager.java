package work;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface{
	CourseDBStructure DBStructure;
	CourseDBElement DBElement;
	
	
	public CourseDBManager()
	{
		DBStructure = new CourseDBStructure(100);
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		
		CourseDBElement elm = new CourseDBElement(id, crn, credits, roomNum, instructor);
		DBStructure.add(elm);
		
	}

	@Override
	public CourseDBElement get(int crn) {
		try
		{
			return DBStructure.get(crn);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException{
		
		try(Scanner scanner = new Scanner(input))
		{
			while(scanner.hasNext());
			{
				String courseID = scanner.next();
				int courseCRN = scanner.nextInt();
				int creditAmount = scanner.nextInt();
				String roomNum = scanner.next();
				String professor = scanner.nextLine();
				add(courseID, courseCRN, creditAmount, roomNum, professor);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
			e.printStackTrace();
		}
	}
		

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> objects = new ArrayList<>();
		for(LinkedList<CourseDBElement> linkedList : DBStructure.hashTable)
		{
			if (linkedList != null)
			{
				linkedList.forEach(element -> objects.add(element.toString()));
			}
		}
		return objects;
	}
	
	

}
