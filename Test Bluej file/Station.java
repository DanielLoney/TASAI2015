 

import java.util.ArrayList;

public class Station {
	boolean visited;
	int knowledge;//straight line distance from station to the end point
	String data = "";
	ArrayList<Station> adjacent = new ArrayList<Station>();
	Station()
	{
		
	}
	Station(String name)
	{
		data = name;
	}
	Station(String name, int background)
	{
		data = name;
		knowledge = background;
	}
	public String toString()
	{
		return data;
	}
	
}