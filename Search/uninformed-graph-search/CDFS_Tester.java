/****************************************************************
 * 
 * @author Dr. Carlos Delgado Mata
 * 
 * @version October 2014
 * 
 * This is a BFS Tester
 *
 * The edges are undirected, that is a difference from a tree node.
 *
 *
 ******************************************************************/

public class CDFS_Tester {


	
	
	public static void main (String args []) {
		System.out.println ("Depth First Search");
		System.out.println ("===================");		
		CSimpleGraph  graph = new CSimpleGraph();
		graph.dfs ();
		
		
	}
}

