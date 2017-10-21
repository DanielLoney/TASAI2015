 
import javax.swing.JOptionPane;

public class SearcherRunner {

	
	public static void main(String[] args) {
		
		//Creating the station map and its connections
		Station Sibiu = new Station("Sibiu",253);//Format is Station(Name, Linear distance to Bucharest)
		Station Bucharest = new Station("Bucharest",0);
		Station Faragas = new Station("Faragas",178);
		Station Pitesti = new Station("Pitesti",98);
		Station Rimnicu = new Station("Rimnicu",193);
		Station Craiova = new Station("Craiova",160);
		Station Aarad = new Station("Aarad",366);
		Station Zerind = new Station("Zerind",374);
		Station Timisoara = new Station("Timisoara",329);
		Station Lugoj = new Station("Lugoj",244);
		Station Oradea = new Station("Oradea",380);
		Station Dobreta = new Station("Dobreta",242);
		Station[] map = {Bucharest, Sibiu, Faragas, Pitesti, Rimnicu, Craiova, Aarad, Zerind
				, Timisoara, Lugoj, Oradea, Dobreta};
		Connect[] connections = {new Connect(Oradea, Zerind, 71),new Connect(Zerind, Aarad, 75)
		,new Connect(Aarad, Sibiu, 140),new Connect(Aarad, Timisoara, 118)
		,new Connect(Timisoara, Lugoj, 111),new Connect(Dobreta, Craiova, 120)
		,new Connect(Craiova, Rimnicu, 146),new Connect(Rimnicu, Sibiu, 80)
		,new Connect(Sibiu, Faragas, 99),new Connect(Rimnicu, Pitesti, 97)
		,new Connect(Faragas, Bucharest, 211),new Connect(Pitesti, Bucharest, 101)};
		
		StationMap Romania = new StationMap(map);//adding stations to the Stationmap
		Romania.addConnections(connections);//adding a reference for the distance values between each adjacent station
		
		
		
		
		//Running the search methods
		JOptionPane.showMessageDialog(null, "Best First:");
		Romania.bestFirst(Sibiu, Bucharest);//(Starting station, end station)

		JOptionPane.showMessageDialog(null, "Greedy:");
		Romania.greedy(Sibiu, Bucharest);

		JOptionPane.showMessageDialog(null, "A*:");
		Romania.aStar(Sibiu, Bucharest);
	}

}
