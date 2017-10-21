/****************************************************************
 * 
 * @author Dr. Carlos Delgado Mata
 * 
 * @version October 2014
 * 
 * This node is a Graph Node.
 *
 * The edges are undirected, that is a difference from a tree node.
 *
 *
 ******************************************************************/


import java.util.ArrayList;

public class CGraphNode {
    public String data;
	public ArrayList <CGraphNode> edges;
	
	public boolean visited;
	
	/*
	 * Default constructor
	 */
	public CGraphNode () {
		data = "EMPTY";
		edges = new ArrayList <CGraphNode> ();
		visited = false;
		
	}
	/*
	 * Constructor with one parameter (data)
	 * 
	 */
	public CGraphNode (String rhs) {
		data = rhs;
		edges = new ArrayList <CGraphNode> ();
		visited = false;
		
	}	
	
}
