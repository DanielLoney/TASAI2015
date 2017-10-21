package searcher;
import java.util.ArrayList;
  
public class WebNode {

	public ArrayList<WebNode> parent = new ArrayList<WebNode>();
	public ArrayList<WebNode> child = new ArrayList<WebNode>();
    public String data;

    

    /**
     * Constructor WebNode
     */    
    public WebNode () {

        child = new ArrayList<WebNode>();
        data = null;
        parent = new ArrayList<WebNode>();

    }

    public void addParents(ArrayList<WebNode> Parents)
    {
    	for( int k = 0; k< Parents.size(); k++)
    	{
    		parent.add(Parents.get(k));
    	}
    }
    public void addChildren(ArrayList<WebNode> Children)
    {
    	for( int k = 0; k< Children.size(); k++)
    	{
    		child.add(Children.get(k));
    	}
    }
    public void addCAndP(ArrayList<WebNode> Children, ArrayList<WebNode> Parents)
    {
    	addChildren(Children);
    	addParents(Parents);
    }
    public static ArrayList<WebNode> hardCopyArrayList(ArrayList<WebNode> ToBeCopied)
    {
    	ArrayList<WebNode> b = new ArrayList<WebNode>();
    	for(int k = 0; k<ToBeCopied.size(); k++)
    	{
    		b.add(ToBeCopied.get(k));
    	}
    	return b;
    }
    public static ArrayList<WebNode> toArrayList(WebNode[] o)
    {
    	ArrayList<WebNode> list = new ArrayList<WebNode>();
    	for(int k = 0; k<o.length; k++)
    	{
    		list.add(o[k]);
    	}
    	return list;
    }
    
    
    static boolean compareLists(ArrayList<WebNode> Comparing, ArrayList<WebNode> ComparedTo)
	{
		
		for(WebNode k : ComparedTo)
		{
			for(WebNode z : Comparing)
			{
				//System.out.println(z.data);
			}
			if(Comparing.contains(k));
			{
				//System.out.println(k.data);
				return true;
			}
		
		}
		return false;
	}
    
    

    /**
     * Constructor WebNode
     */    
    public WebNode (ArrayList<WebNode> Parents, ArrayList<WebNode> Children, String s) {

    	addParents(Parents);
        addChildren(Children);
        data = s;

    }
    
    public WebNode (String s)
    {
    	parent = new ArrayList<WebNode>();
    	child = new ArrayList<WebNode>();
    	data = s;
    }
    
    public String toString()
    {
    	return data;
    }
    
    


}
