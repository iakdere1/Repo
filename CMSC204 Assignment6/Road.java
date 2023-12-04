package work;

public class Road implements Comparable<Road>{
	
	private int weight;
	private String townName;
	private Town start;
	private Town target;
	
	public Road(Town start, Town target, int weight, String townName)
	{
		this.start = start;
		this.target = target;
		this.weight = weight;
		this.townName = townName;
	}
	
	public Road(Town start, Town target, String townName)
	{
		this(start, target, 1, townName);
	}
	
	public Town getStart()
	{
		return start;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public Town getTarget()
	{
		return target;
	}
	
	public String getName()
	{
		return townName;
	}
	
	@Override
	public boolean equals(Object o)
	{
		Road newRoad = (Road) o;
		boolean isSameRoad = (newRoad.target == target && newRoad.start == start) ||
				(newRoad.target == start && newRoad.start == target);
		return isSameRoad;
	}
	
	@Override
	public String toString()
	{
		return start + " connects to " + target + " by " + weight + " miles.";
	}

	@Override
	public int compareTo(Road r) {
		return weight - r.getWeight();
	}
	
	public boolean contains(Town town)
	{
		if (town == null)
			return false;
		return start.equals(town) || target.equals(town);
	}

	

}
