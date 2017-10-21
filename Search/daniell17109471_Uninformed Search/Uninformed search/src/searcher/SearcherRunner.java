package searcher;
import java.util.ArrayList;
import searcher.WebNode;
 
public class SearcherRunner {
	public static void main (String args []) {
		Searcher search = new Searcher();
		WebNode a = new WebNode("a");//creating nodes
		WebNode b = new WebNode("b");
		WebNode c = new WebNode("c");
		WebNode d = new WebNode("d");
		WebNode e = new WebNode("e");
		/*
		 a   b
		  \ /
		   c
		  / \
		 d   e 
		 */
		WebNode[] two = {c};//creating connection arrays
		WebNode[] one = {a,b};
		WebNode[] three = {d,e};
		//WebNode[] four = {f};
		a.addChildren(WebNode.toArrayList(two));//connecting connections
		b.addChildren(WebNode.toArrayList(two));
		c.addCAndP(WebNode.toArrayList(three), WebNode.toArrayList(one));
		d.addParents(WebNode.toArrayList(two));
		e.addParents(WebNode.toArrayList(two));
		System.out.println("Depth First");
		search.depthFirst(WebNode.toArrayList(one), "e");
		System.out.println("Breadth First");
		search.breadthFirst(WebNode.toArrayList(one), "e");
		/*
		//testing compareto
		ArrayList<WebNode> test = new ArrayList<WebNode>();
		ArrayList<WebNode> test2 = new ArrayList<WebNode>();
		test.add(new WebNode("a"));
		test.add(new WebNode ("b"));
		test2.add(new WebNode("d"));
		test2.add(new WebNode("e"));
		System.out.println(WebNode.compareLists(test, test2));*/
	 }
}
