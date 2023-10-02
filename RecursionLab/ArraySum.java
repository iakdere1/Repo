package work;

public class ArraySum {
	
	public int sumOfArray(Integer[] a, int index)
	{
		
		if (index < 0)
		{
			return 0;
		}
		return a[index] + sumOfArray(a,index - 1);
		
	}

}
