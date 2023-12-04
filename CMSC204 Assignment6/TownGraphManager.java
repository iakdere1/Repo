package work;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface{
	
	private Graph graphOfTown;
	
	public TownGraphManager()
	{
		graphOfTown = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town myTown1 = getTown(town1);
		Town myTown2 = getTown(town2);
		if(myTown1 != null && myTown2 != null && !graphOfTown.containsEdge(myTown1,  myTown2))
		{
		graphOfTown.addEdge(myTown1, myTown2, weight, roadName);
		return true;
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		String selectedRoad = "";
		Town myTown1 = getTown(town1);
		Town myTown2 = getTown(town2);
		for(Road r : graphOfTown.edgeSet())
			if(r.contains(myTown1) && r.contains(myTown2))
				selectedRoad = r.getName();
		return selectedRoad;
	}

	@Override
	public boolean addTown(String v) {
		return graphOfTown.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		Town selectedTown = null;
		for (Town t : graphOfTown.vertexSet())
			{
				if (t.getName().equals(name))
					{
				selectedTown = t;
				break;
					}
			}
		return selectedTown;
	}

	@Override
	public boolean containsTown(String v) {
		if (getTown(v) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graphOfTown.containsEdge(getTown(town1), getTown(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<>();
		Iterator<Road> iterator = graphOfTown.edgeSet().iterator();
		while(iterator.hasNext())
		{
			Road r = iterator.next();
			roads.add(r.getName());
		}
		Collections.sort(roads);
		return roads;
	}
	
	public Road getRoad2(String town1, String town2)
	{
		Road selectedRoad = null;
		Town myTown1 = getTown(town1);
		Town myTown2 = getTown(town2);
		for (Road r : graphOfTown.edgeSet())
			if(r.contains(myTown1) && r.contains(myTown2))
				selectedRoad = r;
		return selectedRoad;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Road selectedRoad = getRoad2(town1, town2);
		graphOfTown.removeEdge(getTown(town1), getTown(town2), selectedRoad.getWeight(), selectedRoad.getName());
		return true;
	}

	@Override
	public boolean deleteTown(String v) {
		return graphOfTown.removeVertex(getTown(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<>();
		Iterator<Town> iterator = graphOfTown.vertexSet().iterator();
		while(iterator.hasNext())
		{
			Town t = iterator.next();
			towns.add(t.getName());
		}
		towns.sort(String.CASE_INSENSITIVE_ORDER);
		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graphOfTown.shortestPath(getTown(town1), getTown(town2));
	}
	
	public void populateTownGraph(File file) throws FileNotFoundException
	{
		List<String> list = new ArrayList<>();
		if(!file.exists())
			throw new FileNotFoundException();
		try (Scanner fileScanner = new Scanner(file))
		{
		while(fileScanner.hasNextLine())
		{
			list.add(fileScanner.nextLine());
		}
		for(String s : list)
		{
			String[] currentLine = s.split(";");
			int comma = currentLine[0].indexOf(",");
			String road = currentLine[0].substring(0,comma);
			String weight = currentLine[0].substring(comma+1,currentLine[0].length());
			String start = currentLine[1];
			String target = currentLine[2];
			addTown(start);
			addTown(target);
			addRoad(start, target, Integer.parseInt(weight), road);
		}
		} catch (FileNotFoundException e)
		{
		e.printStackTrace();
		}
	}
	
	

}
