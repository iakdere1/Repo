package work;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {

	GradeBook book1;
	GradeBook book2;
	
	@BeforeEach
	void setUp() throws Exception {
		book1 = new GradeBook(5);
		book2 = new GradeBook(5);
		
		book1.addScore(43);
		book1.addScore(78);
		book1.addScore(88);
		
		
		book2.addScore(81);
		book2.addScore(84);
		book2.addScore(94);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		book1=null;
		book2=null;
	}

	@Test
	void testAddScore() {
		assertTrue(book1.toString().equals("43.0 78.0 88.0"));
		assertEquals(book1.getScoreSize(),3);
		assertTrue(book2.toString().equals("81.0 84.0 94.0"));
		assertEquals(book2.getScoreSize(),3);
	}

	private void assertTrue(boolean equals) {
		// TODO Auto-generated method stub
		
	}

	@Test
	void testSum() {
		assertEquals(209,book1.sum(),.0001);
		assertEquals(259,book2.sum(),.0001);
	}

	@Test
	void testMinimum() {
		assertEquals(43,book1.minimum(),.001);
		assertEquals(81,book2.minimum(),.001);
	}

	@Test
	void testFinalScore() {
		assertEquals(166,book1.finalScore(),.001);
		assertEquals(178,book2.finalScore(),.001);
	}

	@Test
	void testGetScoreSize() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
