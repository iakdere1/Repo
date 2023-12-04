package work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road>{
	
	private Set<Town> towns;
	private Set<Road> roads;
	private Set<Town> visited;
	private Set<Town> notVisited;
	private Map<Town, Town> previous;
	private Map<Town, Integer> distance;
	
	public Graph()
	{
		towns = new HashSet<>();
		roads = new HashSet<>();
		visited = new HashSet<>();
		notVisited = new HashSet<>();
		previous = new HashMap<>();
		distance = new HashMap<>();
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road r : roads)
			if (r.contains(sourceVertex) && r.contains(destinationVertex))
				return r;
		return null;
		
		
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road freshRoad = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(freshRoad);
		return freshRoad;
	}

	@Override
	public boolean addVertex(Town v) {
		for (Town t : towns)
		{
			if (t.equals(v))
			{
				return false;
			}
		}
		towns.add(v);
		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (Road r : roads)
		{
			if (r.contains(sourceVertex) && r.contains(destinationVertex))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		for (Town t : towns)
		{
			if (t.equals(v))
				return true;
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> roadCopies = new HashSet<>();
		for (Road r : roads)
			roadCopies.add(r);
		return roadCopies;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> hasTown = new HashSet<>();
		for (Road r : roads)
		{
			if (r.contains(vertex))
				hasTown.add(r);
		}
		return hasTown;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road deletedRoad = null;
		Iterator<Road> iterator = roads.iterator();
		while (iterator.hasNext())
		{
			Road r = iterator.next();
			if (r.contains(sourceVertex) && r.contains(destinationVertex) && r.getWeight() == weight && r.getName().equals(description))
			{
				deletedRoad = r;
				iterator.remove();
				break;
			}
		}
		return deletedRoad;
	}

	@Override
	public boolean removeVertex(Town v) {
		towns.remove(v);
		deleteConnectedRoads(v);
		return true;
		
	}
	
	private void deleteConnectedRoads(Town v)
	{
		Set<Road> connectedRoads = edgesOf(v);
		for (Road r : connectedRoads)
		{
			removeEdge(r.getStart(),r.getTarget(), r.getWeight(), r.getName());
		}
	}

	@Override
	public Set<Town> vertexSet() {
		Set<Town> townCopies = new HashSet<>();
		for (Town t : towns)
		{
			townCopies.add(t);
		}
		return townCopies;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> pathway = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		Town currentTown = destinationVertex;
		while(currentTown != null)
		{
			Town nextTown = previous.get(currentTown);
			if(nextTown != null)
			{
				Road path = getEdge(currentTown, nextTown);
				pathway.add(nextTown.getName() + " via " + path.getName() + " to " + currentTown.getName() + " " + path.getWeight() + " mi");
			}
			currentTown = nextTown;
		}
		Collections.reverse(pathway);
		return pathway;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		for (Town t : towns)
		{
			distance.put(t, Integer.MAX_VALUE);
			previous.put(t,null);
			notVisited.add(t);
		}
		distance.put(sourceVertex, 0);
		while(!notVisited.isEmpty())
		{
			Town closestTown = getClosestUnvisitedTown();
			notVisited.remove(closestTown);
			Set<Town> townNeighbors = unvisitedNeighborTown(closestTown);
			for (Town n : townNeighbors)
			{
				int totalWeight = distance.get(closestTown) + getEdge(closestTown, n).getWeight();
				if (totalWeight < distance.get(n))
				{
					distance.put(n, totalWeight);
					previous.put(n, closestTown);
				}
			}
			
		}
		
	}
	
	private Set<Town> unvisitedNeighborTown(Town town)
	{
		Set<Town> unvisitedNeighboringTowns = new HashSet<>();
		Iterator<Road> iterator = edgesOf(town).iterator();
		while(iterator.hasNext())
		{
			Road r = iterator.next();
			Town neighbor = r.getStart() == town ? r.getTarget() : r.getStart();
			if (notVisited.contains(neighbor) && !visited.contains(neighbor))
				unvisitedNeighboringTowns.add(neighbor);
		}
		return unvisitedNeighboringTowns;
	}
	
	private Town getClosestUnvisitedTown()
	{
		int cheapest = Integer.MAX_VALUE;
		Town cheapestTown = null;
		for (Town t: notVisited)
		{
			if (distance.get(t) <= cheapest)
			{
				cheapest = distance.get(t);
				cheapestTown = t;
			}
		}
		return cheapestTown;
	}

}
