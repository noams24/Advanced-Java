/*
 * A class representing one airport
 */

import java.util.concurrent.ConcurrentLinkedQueue;

public class Airport {

	private final int RUNWAYS_NUM; // the number of runways
	private String name; // Airport name
	private boolean[] runways; // represents the runway states. false = empty , true = taken
	private ConcurrentLinkedQueue <RunwayFlight> queue = new ConcurrentLinkedQueue<RunwayFlight>();
	
	// constructs a new airport with empty runways
	public Airport(String name,int runNum){
		this.name = name;
		RUNWAYS_NUM = runNum;
		runways = new boolean[RUNWAYS_NUM];
		System.out.println("Airport " + name + " with number of runways: " + runNum + " initialized");
	}
	
	// method for the flight to depart from the airport
	public int depart(int flightNum){
		
		// adds the flight to the queue for using a runway
		queue.add(new RunwayFlight(flightNum,true));
		
		synchronized (this)
		{
			// if there is no free runway wait for one
			while ( getFreeRunway()== -1 || !queue.peek().isDepart())
			{
				try{
					wait();
				} catch (InterruptedException e){
					System.out.println("rude awakening!!");
				}
			}
			int freeRunway = getFreeRunway(); // get a runway for departure
			runways[freeRunway] = true; // mark the runway as occupied 
			System.out.println("^^ -- Flight " + queue.remove().getFlightNum() + " departs from runway number " 
			+ (freeRunway + 1) +	" from Airport: " + name + "\n");
			return freeRunway; // return the number of the used runway
		}
	}
		
	// method for the flight to land at the airport
	public int land (int flightNum){
		
		// adds the flight to the queue for using a runway
		queue.add(new RunwayFlight(flightNum,false));
		
		synchronized (this)
		{
			// if there is no free runway wait for one
			while ( getFreeRunway() == -1 || queue.peek().isDepart())
			{
				try{
					wait();
				} catch (InterruptedException e){
					System.out.println("rude awakening!!");
				}
			}
			int freeRunway = getFreeRunway();// get a runway for departure
			runways[freeRunway] = true;// mark the runway as occupied
			System.out.println("vv -- Flight " + queue.remove().getFlightNum() + " departs from runway number " 
			+ (freeRunway + 1) +	" from Airport: " + name + "\n");
			return freeRunway;// return the number of the used runway
		}
	}
		
	// clears the given runway and notifies waiting threads
	public void freeRunway(int runNum, int flightNum){
		synchronized (this) {
			runways[runNum] = false;
			System.out.println("00 -- Flight " + flightNum + " cleared from runway " + (runNum + 1) + 
					" from Airport: " + name + "\n");
			notifyAll();
		}
	}
	
	// looks for the next free runway. if not found returns -1
	private int getFreeRunway(){
		for (int i = 0 ; i < RUNWAYS_NUM ; i++)
			if ( !runways[i])
				return i;
		return -1;
	}
}
