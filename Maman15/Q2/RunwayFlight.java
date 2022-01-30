
// represents a  flight on a runway
// with a flight number and if its landing or taking off
public class RunwayFlight
{
	private int flightNum;
	private boolean isDepart;
	
	public RunwayFlight(int flightNum,  boolean isDepart)
	{
		this.flightNum = flightNum;
		this.isDepart = isDepart;
	}
	
	public int getFlightNum()
	{
		return flightNum;
	}

	public boolean isDepart()
	{
		return isDepart;
	}

}
