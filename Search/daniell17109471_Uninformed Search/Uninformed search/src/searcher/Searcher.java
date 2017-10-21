package searcher;
import javax.swing.JOptionPane;
 

import java.util.ArrayList;
import searcher.WebNode;

public class Searcher {
	
	ArrayList<String> list = new ArrayList<String>();
	boolean stopper = false;
	ArrayList<ArrayList<WebNode>> MatrixDepthLists = new ArrayList<ArrayList<WebNode>>();
	ArrayList<WebNode> DepthList = new ArrayList<WebNode>();//all the nodes in depth order
	void depthFirst(ArrayList<WebNode> ancestor, String s)
	{
		depth(ancestor, s);
		ArrayList<WebNode> ShortestPath = new ArrayList<WebNode>();
		boolean exists = false;
		
		for(int f = 0; f<DepthList.size(); f++)
		{
			if(DepthList.get(f).equals(s))
			{
				ShortestPath.add(DepthList.get(f));
				exists = true;
				MatrixDepthLists.add(ShortestPath);
			}
			else if(WebNode.compareLists(DepthList, ancestor)==true)
			{
				ShortestPath = new ArrayList<WebNode>();
			}
			else
			{
				ShortestPath.add(DepthList.get(f));
			}
		}
		if(exists != true)
		{
			//JOptionPane.showMessageDialog(null, s+" does not exist!");
		}
		String stringtoreturn = "The following are possible paths to " + s;
		for(int k = 0; k<MatrixDepthLists.size(); k++)
		{
			stringtoreturn = stringtoreturn+"/n";
			for(int x = 0; x<MatrixDepthLists.get(k).size(); x++)
			{
				stringtoreturn = stringtoreturn + " "+ MatrixDepthLists.get(k).get(x);
			}
		}
		if(exists == true)
		{
			JOptionPane.showMessageDialog(null, stringtoreturn);
		}
		for(int k = 0; k<DepthList.size(); k++)
		{
			System.out.println(DepthList.get(k).toString());
		}
	}
	void depth(ArrayList<WebNode> ancestor, String s)
	{
		
		for(int k = 0; k<ancestor.size(); k++)
		{
			if(ancestor.get(k).data.equals(s))
			{
				//System.out.println(ancestor.get(k).toString());
				DepthList.add(ancestor.get(k));
			}
			else
			{
				//System.out.println(ancestor.get(k).toString());
				DepthList.add(ancestor.get(k));
				depth(ancestor.get(k).child, s);
			}
		}		
	}
	void breadthFirst(ArrayList<WebNode> ancestor, String s)
	{
		ArrayList<WebNode> breadthList = new ArrayList<WebNode>();//order, contains all the WebNodes
		breadthList = WebNode.hardCopyArrayList(ancestor);
		
		breadthList = WebNode.hardCopyArrayList(ancestor);
		
		ArrayList<WebNode> toBeProcessed = new ArrayList<WebNode>();
		ArrayList<WebNode> sub = new ArrayList<WebNode>();
		
		toBeProcessed = WebNode.hardCopyArrayList(ancestor);
		
		while(toBeProcessed.size()!=0)//generates breadthList
		{
			for(WebNode k : toBeProcessed)
			{
				for(int x = 0; x<k.child.size(); x++)
				{
					sub.add(k.child.get(x));
					breadthList.add(k.child.get(x));
				}
			}
			toBeProcessed = WebNode.hardCopyArrayList(sub);
			sub = new ArrayList<WebNode>();
		}
		
		for(WebNode f : breadthList)
		{
			System.out.println(f.toString());
		}//prints breadthlist
		
		
	}
}
