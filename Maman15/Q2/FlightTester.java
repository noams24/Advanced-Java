import java.util.Random;

public class FlightTester
{
	public static void main (String[] args){
		
		// make two airports
		Airport moscowAirport = new Airport("Moscow", 1);
		Airport telavivAirport = new Airport("Tel Aviv", 2);
		Flight flight;
		Random rand = new Random();
		
		// create and start 10 flight randomly between the airports
		for (int i = 0 ; i < 10 ; i++)
		{
			if ( rand.nextInt(100)%2 == 1 ){
				flight = new Flight(rand.nextInt(9999),moscowAirport,telavivAirport);
			}
			else {
				flight = new Flight(rand.nextInt(9999),telavivAirport,moscowAirport);
			}
			flight.start();
		}
		
		
	}
}
