package work;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadTestStudent {
	Town t1;
	Town t2;
	Town t3;
	Town t4;
	Town t5;
	Road r1;
	Road r2;
	Road r3;
	Road r4;
	Road r5;

	@BeforeEach
	void setUp() throws Exception {
		t1 = new Town("Rockville");
		t2 = new Town("Gaithersburg");
		t3 = new Town("Montgomery Village");
		t4 = new Town("Clarksburg");
		t5 = new Town("Germantown");
		r1 = new Road(t1, t2, 3, "road1");
		r2 = new Road(t1, t3, 8, "road2");
		r3 = new Road(t2, t3, 5, "road3");
		r4 = new Road(t3, t4, 10, "road4");
		r5 = new Road(t4, t5, 3, "road5");
	}

	@AfterEach
	void tearDown() throws Exception {
		t1 = t2 = t3 = t4 = t5 = null;
		r1 = r2 = r3 = r4 = r5 = null;
	}

	@Test
	void containsTest() {
		assertTrue(r1.contains(t1));
		assertTrue(r1.contains(t2));
		assertFalse(r5.contains(t3));
		assertTrue(r3.contains(t2));
		assertFalse(r3.contains(t5));
	}
	
	@Test
	void equalsTest() {
		Road road = new Road(t1, t2, 3, "road1");
		assertTrue(r1.equals(road));
		assertFalse(r3.equals(r1));
		assertFalse(r4.equals(r5));
		assertFalse(road.equals(r4));
	}
	
	@Test
	void getTargetTest() {
		assertTrue(r1.getTarget().getName().equals("Gaithersburg"));
		assertTrue(r2.getTarget().getName().equals("Montgomery Village"));
		assertFalse(r3.getTarget().getName().equals("Germantown"));
	}
	
	@Test
	void getNameTest() {
		assertTrue(r1.getName().equals("road1"));
		assertTrue(r2.getName().equals("road2"));
		assertFalse(r3.getName().equals("road4"));
	}
	
	@Test
	void getStartTest() {
		assertTrue(r1.getStart().getName().equals("Rockville"));
		assertTrue(r2.getStart().getName().equals("Rockville"));
		assertTrue(r3.getStart().getName().equals("Gaithersburg"));
	}
	
	@Test
	void getWeightTest() {
		assertEquals(r1.getWeight(), 3);
		assertEquals(r2.getWeight(), 8);
		assertEquals(r5.getWeight(), 3);
		assertEquals(r4.getWeight(), 10);
	}
	
	@Test
	void compareToTest() {
		assertTrue(r1.compareTo(r2) < 0);
		assertTrue(r4.compareTo(r2) > 0);
		assertFalse(r4.compareTo(r1) < 0);
	}

}
