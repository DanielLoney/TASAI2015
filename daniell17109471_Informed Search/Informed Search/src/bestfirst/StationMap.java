package bestfirst;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class StationMap {

	int steps = 0;
	boolean endboolean = false;
	ArrayList<Station> graph = new ArrayList<Station>();
	ArrayList<Route> possroutes = new ArrayList<Route>();//NOTE: create a method that runs infinitely until a route is found. 
	//In the beginning, create routes that include the fringes, after that create more routes based on the neighbors the fringes have when expanding, if it expands into the end station, return the end route.
	ArrayList<Connect> connections = new ArrayList<Connect>();
	StationMap(Station[] allstations)
	{
		for(int k =0; k < allstations.length; k++)
		{
			graph.add(allstations[k]);
		}
	}
	void initialize(Station begin)
	{
		steps = 0;
		endboolean = false;
		possroutes = new ArrayList<Route>();
		ArrayList<Station> newroute = new ArrayList<Station>();
		newroute.add(begin);
		possroutes.add((new Route(0,newroute)));
		for(Station s: graph)
		{
			s.visited = false;
		}
		begin.visited = true;
	
	}
	void filterUseless()
	{
		ArrayList<Route> temproutes = new ArrayList<Route>();
		for(Route s: possroutes)
		{
			temproutes.add(s);
		}
		for(Route s: possroutes)
		{
			boolean temp = true;//all adjacents of the last station are visited
			ArrayList<Station> tempstat = s.route;
			if(s.route.size()==0)
			{
				temproutes.remove(s);
			}
			else
			{
				Station temps = tempstat.get(tempstat.size()-1);//the last station of each of the possible routes
				for(Station z: temps.adjacent)
				{
					if(z.visited==false)
					{
						temp = false;
					}
				}
				if(temp)
				{
					temproutes.remove(s);
				}
			}
		}
		possroutes = temproutes;
	}
	void expandAStar()
	{
		int recordlowi = 100000;
		Route recordlowr = new Route();//temp
		ArrayList<Route> temproutes = new ArrayList<Route>();
		for(Route s: possroutes)
		{
			temproutes.add(s);//HARDCOPY
		}
		for(Route s: possroutes)
		{
			ArrayList<Station> tempstat = s.route;
			Station temps = tempstat.get(tempstat.size()-1);//the last station of each of the possible routes
			for(Station z: temps.adjacent)
			{
				ArrayList<Station> tamp = new ArrayList<Station>();
				for(Station f: tempstat)//HARDCOPY
				{
					tamp.add(f);
				}
				tamp.add(z);
				if(s.cost+searchConnection(temps,z).travelcost+z.knowledge<recordlowi&&z.visited==false)
				{
					recordlowr = new Route(s.cost+searchConnection(temps,z).travelcost,tamp);
					recordlowi = s.cost+searchConnection(temps,z).travelcost+z.knowledge;
				}
				
			}
		}
		possroutes.add(recordlowr);
		recordlowr.route.get(recordlowr.route.size()-1).visited=true;
		steps++;
	}
	void expandClosest()
	{
		int recordlowi = 100000;
		Route recordlowr = new Route();//temp
		ArrayList<Route> temproutes = new ArrayList<Route>();
		for(Route s: possroutes)
		{
			temproutes.add(s);//HARDCOPY
		}
		for(Route s: possroutes)
		{
			ArrayList<Station> tempstat = s.route;
			Station temps = tempstat.get(tempstat.size()-1);//the last station of each of the possible routes
			for(Station z: temps.adjacent)
			{
				ArrayList<Station> tamp = new ArrayList<Station>();
				for(Station f: tempstat)//HARDCOPY
				{
					tamp.add(f);
				}
				tamp.add(z);
				if(z.knowledge<recordlowi&&z.visited==false)
				{
					recordlowr = new Route(s.cost+searchConnection(temps,z).travelcost,tamp);
					recordlowi = z.knowledge;
				}
				
			}
		}
		possroutes.add(recordlowr);
		recordlowr.route.get(recordlowr.route.size()-1).visited=true;
		steps++;
	}
	void expandSmallest()
	{
		int recordlowi = 100000;
		Route recordlowr = new Route();//temp
		ArrayList<Route> temproutes = new ArrayList<Route>();
		for(Route s: possroutes)
		{
			temproutes.add(s);//HARDCOPY
		}
		for(Route s: possroutes)
		{
			ArrayList<Station> tempstat = s.route;
			Station temps = tempstat.get(tempstat.size()-1);//the last station of each of the possible routes
			for(Station z: temps.adjacent)
			{
				ArrayList<Station> tamp = new ArrayList<Station>();
				for(Station f: tempstat)//HARDCOPY
				{
					tamp.add(f);
				}
				tamp.add(z);
				if(s.cost+searchConnection(temps,z).travelcost<recordlowi&&z.visited==false)
				{
					recordlowr = new Route(s.cost+searchConnection(temps,z).travelcost,tamp);
					recordlowi = s.cost+searchConnection(temps,z).travelcost;
				}
				
			}
		}
		possroutes.add(recordlowr);
		recordlowr.route.get(recordlowr.route.size()-1).visited=true;
		steps++;
	}
	void searchRoutes(Station end)
	{
		Collections.sort(possroutes);
		
		if(possroutes.size()==0)
		{
			endboolean = true;
		}
		for(Route s: possroutes)
		{
			ArrayList<Station> tempstat = s.route;
			Station temps = tempstat.get(tempstat.size()-1);//the last station of each of the possible routes
			if(temps.data.equals(end.data))
			{
				JOptionPane.showMessageDialog(null, "Shortest route is: \n"+ s.toString()+"\n Stations Visited: "+steps);
				endboolean = true;
			}
		}
	}
	void searchAStarRoutes(Station end)
	{
		Collections.sort(possroutes);
		
		if(possroutes.size()==0)
		{
			endboolean = true;
		}
		for(Route s: possroutes)
		{
			ArrayList<Station> tempstat = s.route;
			Station temps = tempstat.get(tempstat.size()-1);//the last station of each of the possible routes
			if(temps.data.equals(end.data))
			{
				int i = 0;
				for(int k = 0; k<s.route.size()-1;k++)
				{
					i+=searchConnection(s.route.get(k),s.route.get(k+1)).travelcost;
				}
				s.cost = i;
				JOptionPane.showMessageDialog(null, "Shortest route is: \n"+ s.toString()+"\n Stations Visited: "+steps);
				endboolean = true;
			}
		}
	}
	void greedy(Station begin, Station end)
	{
		//invalid tester
		boolean truthvariable = true;
		for(Station g: graph)
		{
			if(end == g)
			{
				truthvariable = false;
			}
			
		}
		if(truthvariable == true)
		{
			JOptionPane.showMessageDialog(null, "End station does not exist.");
			System.exit(0);
		}
		for(Station g: graph)
		{
			if(begin == g)
			{
				truthvariable = false;
			}	
		}
		if(truthvariable == true)
		{
			JOptionPane.showMessageDialog(null, "Start station does not exist.");
			System.exit(0);
		}
		initialize(begin);
		while(endboolean == false)
		{
			
			searchRoutes(end);
			filterUseless();
			expandClosest();
		}
	}
	void aStar(Station begin, Station end)
	{
//invalid tester
		boolean truthvariable = true;
		for(Station g: graph)
		{
			if(end == g)
			{
				truthvariable = false;
			}
			
		}
		if(truthvariable == true)
		{
			JOptionPane.showMessageDialog(null, "End station does not exist.");
			System.exit(0);
		}
		for(Station g: graph)
		{
			if(begin == g)
			{
				truthvariable = false;
			}	
		}
		if(truthvariable == true)
		{
			JOptionPane.showMessageDialog(null, "Start station does not exist.");
			System.exit(0);
		}
		initialize(begin);
		while(endboolean == false)
		{
			searchAStarRoutes(end);
			filterUseless();
			expandAStar();
		}
		//initialize
		
		//searchroutes
		//filterroutes
		//expandsmallest
		
	}
	void bestFirst(Station begin, Station end)
	{
		//invalid tester
		boolean truthvariable = true;
		for(Station g: graph)
		{
			if(end==g)
			{
				truthvariable = false;
			}
			
		}
		if(truthvariable == true)
		{
			JOptionPane.showMessageDialog(null, "End station does not exist.");
			System.exit(0);
		}
		for(Station g: graph)
		{
			if(begin == g)
			{
				truthvariable = false;
			}	
		}
		if(truthvariable == true)
		{
			JOptionPane.showMessageDialog(null, "Start station does not exist.");
			System.exit(0);
		}
		
		
		initialize(begin);
		while(endboolean == false)
		{
			
			searchRoutes(end);
			filterUseless();
			expandSmallest();
		}
		//initialize
		
		//searchroutes
		//filterroutes
		//expandsmallest
		
	}
	void addConnections(Connect[] c)//adds connections to the map
	{
		for(int k = 0; k<c.length; k++)
		{
			connections.add(c[k]);
			connections.add(new Connect(c[k].b, c[k].a, c[k].travelcost));
			c[k].b.adjacent.add(c[k].a);
			c[k].a.adjacent.add(c[k].b);
		}
	}
	Connect searchConnection(Station a, Station b)
	{
		for(Connect c:connections)
		{
			if(c.a == a &&c.b == b)
			{
				return c;
			}
		}
		return null;
	}
}
