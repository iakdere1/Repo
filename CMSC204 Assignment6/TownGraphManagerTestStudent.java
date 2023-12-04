package work;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TownGraphManagerTestStudent {
	TownGraphManager manager;
	
	String t1;
	String t2;
	String t3;
	String t4;
	String t5;
	String t6;
	String t7;
	String t8;
	String t9;
	String t10;

	

	@Before
	public void setUp() throws Exception {
		manager = new TownGraphManager();
		t1 = "Rockville";
		t2 = "Gaithersburg";
		t3 = "Montgomery Village";
		t4 = "Germantown";
		t5 = "Silver Spring";
		t6 = "Bethesda";
		t7 = "Darnestown";
		t8 = "Clarksburg";
		t9 = "Derwood";
		t10 = "Aspen Hill";
		manager.addTown(t1);
		manager.addTown(t2);
		manager.addTown(t3);
		manager.addTown(t4);
		manager.addTown(t5);
		manager.addTown(t6);
		manager.addTown(t7);
		manager.addTown(t8);
		manager.addRoad(t1, t2, 3, "road1");
		manager.addRoad(t1, t3, 8, "road2");
		manager.addRoad(t2, t3, 5, "road3");
		manager.addRoad(t3, t4, 10, "road4");
		manager.addRoad(t4, t5, 3, "road5");
		manager.addRoad(t3, t8, 2, "road6");
		manager.addRoad(t3, t7, 3, "road7");
		manager.addRoad(t3, t6, 1, "road8");
		manager.addRoad(t1, t8, 16, "road9");


	}

	@After
	public void tearDown() throws Exception {
		t1 = t2 = t3 = t4 = t5 = t6 = t7 = t8 = null;
		manager = null;
	}

	@Test
	public void testAddRoad() {
		manager.addRoad(t4, t1, 200, "nonexistent");
		assertTrue(manager.getRoad(t4,t1).equals("nonexistent"));
		manager.addRoad(t1,t5,100,"q");
		assertTrue(manager.getRoad(t1, t5).equals("q"));
		manager.addRoad(t2, t4, 30, "w");
		assertTrue(manager.getRoad(t2, t4).equals("w"));
		manager.addRoad(t4, t3, 20, "a");
		assertFalse(manager.getRoad(t4, t3).equals("as"));
	}
	
	@Test
	public void testGetRoad() {
		assertTrue(manager.getRoad(t1, t2).equals("road1"));
		assertTrue(manager.getRoad(t1, t3).equals("road2"));
		assertTrue(manager.getRoad(t1, t8).equals("road9"));
	}
	
	@Test
	public void testAddTown() {
		String town1 = "qwe";
		String town2 = "asd";
		manager.addTown(town1);
		assertTrue(manager.containsTown(town1));
		assertFalse(manager.containsTown(town2));
		manager.addTown(town2);
		assertTrue(manager.containsTown(town2));
		}
	
	@Test
	public void testGetTown() {
		assertNotNull(manager.getTown("Montgomery Village"));
		assertNotNull(manager.getTown("Gaithersburg"));
		assertNotNull(manager.getTown("Rockville"));
		assertNotNull(manager.getTown("Silver Spring"));
		assertNull(manager.getTown("Germantown"));
	}
	
	@Test
	void testContainsTown() {
		assertTrue(manager.containsTown(t1));
		assertTrue(manager.containsTown(t4));
		assertTrue(manager.containsTown(t6));
		assertFalse(manager.containsTown("Washington"));
	}
	
	@Test
	void testContainsRoadConnection() {
		assertTrue(manager.containsRoadConnection(t1, t2));
		assertTrue(manager.containsRoadConnection(t1, t3));
		assertTrue(manager.containsRoadConnection(t3, t4));
		assertFalse(manager.containsRoadConnection(t1, t5));
	}
	
	@Test
	void testAllRoads() {
		ArrayList<String> roads = manager.allRoads();
		assertTrue(roads.contains("road1"));
		assertTrue(roads.contains("road2"));
		assertTrue(roads.contains("road3"));
		assertTrue(roads.contains("road4"));
		assertTrue(roads.contains("road5"));
		assertTrue(roads.contains("road6"));
		assertTrue(roads.contains("road7"));
		assertTrue(roads.contains("road8"));
		assertTrue(roads.contains("road9"));
	}
	
	@Test
	void testDeleteRoadConnection() {
		manager.deleteRoadConnection(t1, t2, "road1");
		assertFalse(manager.containsRoadConnection(t1, t2));
		assertTrue(manager.containsRoadConnection(t4, t5));
		manager.deleteRoadConnection(t4, t5, "road5");
		assertFalse(manager.containsRoadConnection(t4, t5));
		}
	
	@Test
	void testDeleteTown() {
		manager.deleteTown(t1);
		assertTrue(!manager.containsTown(t1));
		assertFalse(manager.containsRoadConnection(t1, t2));
		assertTrue(manager.containsTown(t4));
		manager.deleteTown(t4);
		assertFalse(manager.containsTown(t4));
		assertFalse(manager.containsRoadConnection(t4, t5));
	}
	
	@Test
	void testAllTowns() {
		ArrayList<String> towns = manager.allTowns();
		assertTrue(towns.contains("Rockville"));
		assertTrue(towns.contains("Gaithersburg"));
		assertTrue(towns.contains("Montgomery Village"));
		assertTrue(towns.contains("Germantown"));
		assertTrue(towns.contains("Silver Spring"));
		assertTrue(towns.contains("Bethesda"));
		assertTrue(towns.contains("Darnestown"));
		assertTrue(towns.contains("Clarksburg"));
		assertTrue(towns.contains("Derwood"));
		assertTrue(towns.contains("Aspen Hill"));
	}
	
	@Test
	void testGetPath() {
		ArrayList<String> path = manager.getPath(t1, t7);
		assertTrue(path.get(0).equals("Rockville via road2 to Montgomery Village 8 mi"));
	}
	
	

}
