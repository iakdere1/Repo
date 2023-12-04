package work;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {

	
	private Queue<Integer> queue;
	private Random randomizer = new Random();
	
	public CarQueue()
	{
		queue = new LinkedList<>();
		queue.add(randomizer.nextInt(4));
		queue.add(randomizer.nextInt(4));
		queue.add(randomizer.nextInt(4));
		queue.add(randomizer.nextInt(4));
		queue.add(randomizer.nextInt(4));
		queue.add(randomizer.nextInt(4));
	}
	
	public void addToQueue()
	{
		class AddRandom implements Runnable
		{
			@Override
			public void run()
			{
				while(true)
				{
					queue.add(randomizer.nextInt(4));
					try
					{
						Thread.sleep(200);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		new Thread(new AddRandom()).start();
	}
	public int deleteQueue()
	{
		return queue.remove();
	}
}
