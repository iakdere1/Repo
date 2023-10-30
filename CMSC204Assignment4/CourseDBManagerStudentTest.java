package work;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManagerStudentTest {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("CMSC204",21001,4,"SW213","David Kuijt");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("MATH182",21383,4,"online","Sirisha Kolloru");
		dataMgr.add("ASTR101",21250,4,"SC406","Carrie Fitzgerald");
		dataMgr.add("CMSC204",21001,4,"SW213","David Kuijt");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:CMSC204 CRN:21001 Credits:4 Instructor:David Kuijt Room:SW213");
	 	assertEquals(list.get(1),"\nCourse:ASTR101 CRN:21250 Credits:4 Instructor:Carrie Fitzgerald Room:SC406");
		assertEquals(list.get(2),"\nCourse:MATH182 CRN:21383 Credits:4 Instructor:Sirisha Kolloru Room:online");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inFile = new File("iShouldSeeThis.txt");
			PrintWriter printWriterInFile = new PrintWriter(inFile);
			printWriterInFile.println("CMSC204 21001 4 SW213 David Kuijt");
			printWriterInFile.print("ASTR101 21250 4 SW406 Carrie Fitzgerald");
			
			printWriterInFile.close();
			dataMgr.readFile(inFile);
			assertEquals("CMSC204",dataMgr.get(21001).getID());
			assertEquals("MATH182",dataMgr.get(21383).getID());
			assertEquals("SC406",dataMgr.get(21250).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
