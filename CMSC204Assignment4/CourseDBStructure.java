package work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {
	protected ArrayList<LinkedList> hashTable;
	protected int tableSize;
	protected final double a = 1.5;

	public CourseDBStructure(int numOfCourses)
	{
		hashTable = new ArrayList<LinkedList>();
		tableSize = tableSize(numOfCourses);
		int i = 0;
		while (i < tableSize)
		{
			hashTable.add(null);
			i++;
		}
	}

	public CourseDBStructure(String dummy, int hashTableSize2)
	{
		hashTable = new ArrayList<LinkedList>(hashTableSize2);
		tableSize = hashTableSize2;
		int i = 0;
		while (i < tableSize)
		{
			hashTable.add(null);
			i++;
		}
	}

	public void add(CourseDBElement object) {
		int num = hashTable(object.getCRN());
		LinkedList<CourseDBElement> linkedList = hashTable.get(num);
		if (linkedList == null)
		{
			linkedList = new LinkedList<CourseDBElement>();
			linkedList.add(object);
			hashTable.set(num, linkedList);
		}
		else
		{
			boolean shouldBeCorrect = false;
			for (CourseDBElement pointer : linkedList)
			{
				if (object.compareTo(pointer) == 0)
				{
					shouldBeCorrect = true;
					if(!object.dataMatches(pointer))
					{
					pointer.setCreditAmount(object.getCreditAmount());
					pointer.setID(object.getID());
					pointer.setProfessor(object.getProfessor());
					pointer.setRoomNum(object.getRoomNum());
					}
					break;
				}
			}
			if(!shouldBeCorrect)
			{
				linkedList.add(object);
			}
		}
	}
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement currentObject = null;
		int num = hashTable(crn);
		LinkedList<CourseDBElement> linkedList = hashTable.get(num);
		if(linkedList != null)
		{
			for(CourseDBElement pointer : linkedList)
			{
				if(pointer.getCRN() == crn)
				{
					currentObject = pointer;
					break;
				}
			}
		}
		if (currentObject != null)
			return currentObject;
		else
			throw new IOException("The entered CRN does not exist");
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courseCRNList = new ArrayList<>();
		for (LinkedList<CourseDBElement> linkedList : hashTable)
		{
			if (linkedList != null)
			{
				for(CourseDBElement pointer : linkedList)
				{
					courseCRNList.add(pointer.toString());
				}
			}
		}
		return courseCRNList;
	}

	@Override
	public int getTableSize() {
		return tableSize;
	}

	
	public int tableSize(int p)
	{
		int num;
		int size;
		boolean isProperNum = false;
		
		size = (int) (p / a);
		num = (size % 2 == 0) ? size + 1: size + 2;
		while (!isProperNum)
		{
			isProperNum = true;
			for (int i = 2; i <= Math.sqrt(num); i++)
			{
				if (num % i == 0)
				{
					num += 2;
					isProperNum = false;
					break;
				}
			}
			if (isProperNum && num % 4 != 3)
			{
					num += 2;
					isProperNum = false;
			}
		}
		return num;
	}
	public int hashTable(int CRN)
	{
		int returnThis = Math.abs(CRN % tableSize);
		return returnThis;
	}
}