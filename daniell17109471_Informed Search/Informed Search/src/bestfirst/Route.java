	package bestfirst;

import java.util.ArrayList;

public class Route implements Comparable{

	
	int cost;
	ArrayList<Station> route = new ArrayList<Station>();
	Route()
	{
		
	}
	Route(Station s)
	{
		route.add(s);
	}
	Route(int totalcost, ArrayList<Station> stationslist)
	{
		cost = totalcost;
		
		for(Station s:stationslist){
			route.add(s);
		}
	}
	Route(int totalcost, Station[] stationslist)
	{
		cost = totalcost;
		for(int k = 0; k<stationslist.length; k++)
		{
			route.add(stationslist[k]);
		}
	}
	void addStation(Station newstation)
	{
		route.add(newstation);
	}
	public String toString()
	{
		String s = "\nRoute: ";
		for(Station st: route)
		{
			s+= st.data + "-> ";
		}
		s = s.substring(0, s.length()-3);
		s+= "\n Distance Traveled: "+cost;
		return s;
	}
	public int compareTo(Object o)
	{
		Route other = (Route)o;
		return cost-other.cost;
	}
	//asList(s.toArray)
}
