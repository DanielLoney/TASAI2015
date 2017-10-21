import java.util.LinkedList;

/****************************************************************
 * 
 * @author Dr. Carlos Delgado Mata
 * 
 * @version October 2014
 * 
 * This class is to create a simple graph
 *
 *
 ******************************************************************/

public class CSimpleGraph {
	
	CGraphNode m_root = null;
	

	
    public void setRootNode (CGraphNode node) {
    	m_root = node;
    }
    
    public CGraphNode getRootNode () {
    	return m_root;
    }
	
	public void connectNodes (CGraphNode a, CGraphNode b) {
		a.edges.add(b);
		b.edges.add(a);
		
	}
	
	/**
	 *   method to create all the nodes as show in fig 
	 */
	
	public void createGraph () {
		

		CGraphNode nA=new CGraphNode("A");
		CGraphNode nB=new CGraphNode("B");
		CGraphNode nC=new CGraphNode("C");
		CGraphNode nD=new CGraphNode("D");
		CGraphNode nE=new CGraphNode("E");
		CGraphNode nF=new CGraphNode("F");
		CGraphNode nG=new CGraphNode("G");
		
		// set root node
		setRootNode (nA);
		
		// Connect the nodes (create the graph)

		connectNodes(nA,nB);
		connectNodes(nA,nC);
		connectNodes(nB,nD);
		
		connectNodes(nB,nE);
		connectNodes(nC,nF);
		connectNodes(nC,nG);		
		
	}
	
	public CSimpleGraph () {
		createGraph ();
		
		
	}
	
	public  void bfs () {
		// get root node
		CGraphNode root = getRootNode();
		System.out.println (root.data);
	    // it is visited now.
		root.visited = true;
	
		bfs (root);
		
		
		
	}
	
	public  void bfs (CGraphNode node) {
		// base case
		if (node == null  )
			return;
		
		LinkedList<CGraphNode> fringe = new LinkedList<CGraphNode> ();
		// get the children
		for (CGraphNode child: node.edges  ) {
			if (!child.visited) {
				System.out.println (child.data);
				child.visited = true;
				fringe.add(child);
			}		
		}
		
		// Now expand the children (fringe)
		while (!fringe.isEmpty() ) {			
				bfs (fringe.remove());			
		}		
		
		
		
	}
	
	public  void dfs () {
		// get root node
		CGraphNode root = getRootNode();
		System.out.println (root.data);
	    // it is visited now.
		root.visited = true;
	
		dfs (root);
		
		
		
	}
	
	public  void dfs (CGraphNode node) {
		// base case
		if (node == null  )
			return;
		
		//LinkedList<CGraphNode> fringe = new LinkedList<CGraphNode> ();
		// get the children
		for (CGraphNode child: node.edges  ) {
			if (!child.visited) {
				
				child.visited = true;
				System.out.println (child.data);
				dfs(child);


			}		
		}
		

		
		
	}

}
