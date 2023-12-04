package work;

import java.util.ArrayList;

public class Town implements Comparable<Town>{
	
	private String townName;
	
	
	public Town(String townName)
	{
		this.townName = townName;
		
	}
	
	public Town(Town townArrangement)
	{
		townName = townArrangement.getName();
	}
	
	public String getName()
	{
		return townName;
	}
	
	@Override
	public boolean equals(Object o)
	{
		return ((Town) o).getName().equals(townName);
	}
	
	@Override
	public int hashCode()
	{
		return townName.hashCode();
	}
	
	public String toString()
	{
		return getName();
	}
	
	@Override
	public int compareTo(Town o)
	{
		return townName.compareTo(o.getName());
	}

}
