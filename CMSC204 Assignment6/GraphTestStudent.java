package work;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTestStudent {
	
	Graph graphOfTown;
	Town t1;
	Town t2;
	Town t3;
	Town t4;
	Town t5;
	Town t6;
	Town t7;
	Town t8;

	@BeforeEach
	void setUp() throws Exception {
		graphOfTown = new Graph();
		t1 = new Town("Rockville");
		t2 = new Town("Gaithersburg");
		t3 = new Town("Montgomery Village");
		t4 = new Town("Clarksburg");
		t5 = new Town("Frederick");
		t6 = new Town("Bethesda");
		t7 = new Town("Germantown");
		t8 = new Town("Darnestown");
		graphOfTown.addVertex(t1);
		graphOfTown.addVertex(t2);
		graphOfTown.addVertex(t3);
		graphOfTown.addVertex(t4);
		graphOfTown.addVertex(t5);
		graphOfTown.addVertex(t6);
		graphOfTown.addVertex(t7);
		graphOfTown.addVertex(t8);
		graphOfTown.addEdge(t1, t2, 3, "road1");
		graphOfTown.addEdge(t1,t3,8,"road2");
		graphOfTown.addEdge(t2, t3, 5, "road3");
		graphOfTown.addEdge(t3,t4,10,"road4");
		graphOfTown.addEdge(t4, t5, 3, "road5");
		graphOfTown.addEdge(t3, t8, 2, "road6");
		graphOfTown.addEdge(t3, t7, 3, "road7");
		graphOfTown.addEdge(t3, t6, 1, "road8");
		graphOfTown.addEdge(t1, t8, 16, "road9");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		t1 = t2 = t3 = t4 = t5 = t6 = t7 = t8 = null;
		graphOfTown = null;
	}

	@Test
	void testGetEdge() {
		graphOfTown.addEdge(t4, t1, 200, "nonexistent");
		assertTrue(graphOfTown.getEdge(t4, t1).getName().equals("nonexistent"));
		graphOfTown.addEdge(t1, t5, 100, "w");
		assertTrue(graphOfTown.getEdge(t1, t5).getName().equals("w"));
		graphOfTown.addEdge(t2,t4,30,"road");
		assertTrue(graphOfTown.getEdge(t2, t4).getName().equals("road"));
		graphOfTown.addEdge(t4, t3, 20, "a");
		assertFalse(graphOfTown.getEdge(t4, t3).getName().equals("aq"));
	}
	
	@Test
	void testAddVertex() {
		Town town1 = new Town("townName");
		Town town2 = new Town("townName2");
		graphOfTown.addVertex(town1);
		assertTrue(graphOfTown.containsVertex(town1));
		assertFalse(graphOfTown.containsVertex(town2));
		graphOfTown.addVertex(town2);
		assertTrue(graphOfTown.containsVertex(town2));
	}
	
	@Test
	void testContainsEdge() {
		assertTrue(graphOfTown.containsEdge(t1, t2));
		assertTrue(graphOfTown.containsEdge(t1, t8));
		assertFalse(graphOfTown.containsEdge(t4, t6));
	}
	
	@Test
	void testContainsVertex() {
		assertTrue(graphOfTown.containsVertex(t1));
		assertTrue(graphOfTown.containsVertex(t4));
		assertTrue(graphOfTown.containsVertex(t3));
		assertTrue(graphOfTown.containsVertex(t7));
		Town town1 = new Town("qwe");
		assertFalse(graphOfTown.containsVertex(town1));
	}
	
	@Test
	void testEdgeSet() {
		Set<String> w = new HashSet<>();
		for (Road r : graphOfTown.edgeSet())
			w.add(r.getName());
		assertTrue(w.contains("road1"));
		assertTrue(w.contains("road2"));
		assertTrue(w.contains("road3"));
		assertTrue(w.contains("road4"));
		assertTrue(w.contains("road5"));
		assertTrue(w.contains("road6"));
		assertTrue(w.contains("road7"));
		assertTrue(w.contains("road8"));
		assertTrue(w.contains("road9"));
	}
	
	@Test
	void testEdgesOf() {
		Set<String> w = new HashSet<>();
		for (Road r : graphOfTown.edgesOf(t1))
			w.add(r.getName());
		assertTrue(w.contains("road1"));
		assertTrue(w.contains("road2"));
		assertTrue(w.contains("road9"));
		w.clear();
		for(Road r : graphOfTown.edgesOf(t2))
			w.add(r.getName());
		assertTrue(w.contains("road1"));
		assertTrue(w.contains("road3"));
	}
	
	@Test
	void testRemoveEdge() {
		graphOfTown.removeEdge(t1, t2, 3, "road1");
		assertFalse(graphOfTown.containsEdge(t1, t2));
		graphOfTown.removeEdge(t4, t5, 3, "road5");
		assertFalse(graphOfTown.containsEdge(t4, t5));
	}
	
	@Test
	void testRemoveVertex() {
		graphOfTown.removeVertex(t1);
		assertFalse(graphOfTown.containsVertex(t1));
		assertFalse(graphOfTown.containsEdge(t1, t2));
	}
	
	@Test
	void testVertexSet() {
		Set<Town> setTown = graphOfTown.vertexSet();
		assertTrue(setTown.contains(t1));
		assertTrue(setTown.contains(t2));
		assertTrue(setTown.contains(t3));
		assertTrue(setTown.contains(t4));
		assertTrue(setTown.contains(t5));
		assertTrue(setTown.contains(t6));
		assertTrue(setTown.contains(t7));
		assertTrue(setTown.contains(t8));
	}
	
	@Test
	void testShortestPath() {
		ArrayList<String> shortest = graphOfTown.shortestPath(t1, t8);
		assertTrue(shortest.get(0).equals("Rockville via road2 to Montgomery Village 8 mi"));
		assertTrue(shortest.get(1).equals("Montgomery Village via road6 to Darnestown 2 mi"));
		shortest = graphOfTown.shortestPath(t4, t1);
		assertTrue(shortest.get(0).equals("Clarksburg via road4 to Montgomery Village 10 mi"));
		assertTrue(shortest.get(1).equals("Montgomery Village via road2 to Rockville 8 mi"));

	}

}
