package work;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownTestStudent {
	Town t1;
	Town t2;
	Town t3;
	Town t4;


	@BeforeEach
	void setUp() throws Exception {
		t1 = new Town("Rockville");
		t2 = new Town("Gaithersburg");
		t3 = new Town("Montgomery Village");
		t4 = new Town("Germantown");
	}

	@AfterEach
	void tearDown() throws Exception {
		t1 = t2 = t3 = t4 = null;
	}

	@Test
	void getNameTest() {
		assertTrue(t1.getName().equals("Rockville"));
		assertTrue(t2.getName().equals("Gaithersburg"));
		assertTrue(t3.getName().equals("Montgomery Village"));
	}
	
	@Test
	void createCopyTest() {
		Town t5 = new Town(t1);
		assertTrue(t1.equals(t5));
		Town t6 = new Town(t3);
		assertTrue(t3.equals(t6));
	}
	
	@Test
	void equalsTest() {
		Town t5 = new Town("Rockville");
		assertFalse(t2.equals(t5));
		Town t6 = new Town("Rockville");
		assertTrue(t1.equals(t6));
	}
	
	@Test
	void hashCodeTest() {
		Town t5 = new Town("Germantown");
		assertNotEquals(t5.hashCode(),t2.hashCode());
		assertNotEquals(t3.hashCode(),t4.hashCode());
		assertNotEquals(t2.hashCode(),t3.hashCode());
	}
	
	@Test
	void compareToTest() {
		assertTrue(t1.compareTo(t2) >= 0);
		assertTrue(t3.compareTo(t4) >= 0);
		assertTrue(t2.compareTo(t3) <= 0);
	}

}
