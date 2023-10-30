package work;

public class CourseDBElement implements Comparable<CourseDBElement>{
	
	private String professor;
	private String roomNum;
	private int courseCRN;
	private int creditAmount;
	private String courseID;
	

	
	public CourseDBElement()
	{
		this("", 0, 0, "", "");
	}
	
	public CourseDBElement(int courseCRN)
	{
		this.courseCRN = courseCRN;
	}
	
	public CourseDBElement(String courseID, int courseCRN, int creditAmount, String roomNum, String professor)
	{
		this.professor = professor;
		this.roomNum = roomNum;
		this.courseCRN = courseCRN;
		this.creditAmount = creditAmount;
		this.courseID = courseID;
	}
	
	
	public String getID()
	{
		return courseID;
	}
	
	public void setID(String courseID)
	{
		this.courseID = courseID;
	}
	
	public int getCRN()
	{
		return courseCRN;
	}
	
	public void setCRN(int courseCRN)
	{
		this.courseCRN = courseCRN;
	}
	
	public int getCreditAmount()
	{
		return creditAmount;
	}
	
	public void setCreditAmount(int creditAmount)
	{
		this.creditAmount = creditAmount;
	}
	
	public String getRoomNum()
	{
		return roomNum;
	}
	
	public void setRoomNum(String roomNum)
	{
		this.roomNum = roomNum;
	}
	
	public String getProfessor()
	{
		return professor;
	}
	
	public void setProfessor(String professor)
	{
		this.professor = professor;
	}
	
	public boolean dataMatches(CourseDBElement object)
	{
		return this.getCRN() == object.getCRN() && 
				this.getRoomNum().equals(object.getRoomNum()) && 
				this.getProfessor().equals(object.getProfessor()) && 
				this.getID().equals(object.getID()) && 
				this.getCreditAmount() == object.getCreditAmount();
	}
	
	public int hashCode()
	{
		return String.valueOf(courseCRN).hashCode();
	}
	
	@Override
	public int compareTo(CourseDBElement object)
	{
		return hashCode() - object.hashCode();
	}
	
	@Override
	public String toString()
	{
		return "Course:" + courseID + " CRN:" + courseCRN + " Credits:" + creditAmount + " Instructor:" + professor + " Room:" + roomNum;
	}
	

}

