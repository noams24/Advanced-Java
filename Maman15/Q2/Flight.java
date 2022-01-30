
/*
 * A class that represents a single flight from one destination to another 
 */
import java.util.Random;

public class Flight extends Thread
{
	private int flightNum; // the flight number
	private Airport departAirport; // departing airport
	private Airport landingAirport; // landing airport
	private Random rand = new Random();
	
	// constructs a new light
	public Flight(int flightNum, Airport departAirport, Airport landingAirport)
	{
		this.flightNum = flightNum;
		this.departAirport = departAirport;
		this.landingAirport = landingAirport;
	}
	
	// Overrides the run method 
	public void run (){
		
		// flight is departing
		int departRunway = departAirport.depart(flightNum);
		try {
			sleep((rand.nextInt(3)+2)*1000);
		}catch (InterruptedException e){
			System.out.println("Good Morning !!");
		}
		departAirport.freeRunway(departRunway, flightNum);
		
		// waiting in flight
		try {
			sleep(rand.nextInt(10)*1000);
		}catch (InterruptedException e){
			System.out.println("Good Morning !!");
		}
		
		// flight is landing
		int landRunway = landingAirport.land(flightNum);
		try {
			sleep((rand.nextInt(3)+2)*1000);
		}catch (InterruptedException e){
			System.out.println("Good Morning !!");
		}
		landingAirport.freeRunway(landRunway, flightNum);
	}
	
	
}
