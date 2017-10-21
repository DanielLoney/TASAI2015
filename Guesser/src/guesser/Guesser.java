package guesser;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class Guesser {
	CNode root = new CNode("animal");
	ArrayList<String> list = new ArrayList<String>();
	String[] nodes;
	ArrayList<String> stringlist = new ArrayList<String>();//Loaded strings
	ArrayList<Integer> intlist = new ArrayList<Integer>();//Loaded ints
	int tracker = 0;//Used to track the line number in method constructTree
	
	
	public Guesser()
	{
		root.right = new CNode("Tiger");
		root.left = new CNode("bamboo");
		
	}
	
	public void startGuessing()//Only method that's run in the main code. This method begins the process, aswell as guesses the root.
	{
	//end option
		load();
		tracker = 0;
		constructTree(root);//part of loading
		CNode current = root;
		
		Object[] possibleValues = {0, 1, 2};
		Object selectedValue = JOptionPane.showInputDialog(null,"is it a(n) " + current.data + "?" +
		" 2 is quit program, 1 is yes and 0 is no.", 
		"Input", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		int i = (int) selectedValue;
		if( i == 1)
		{
			guess(current.right);
		}
		else if (i==2)
		{
		 return;
		} 
		else	
		{
			guess(current.left);
			
		}
	}	
	
	public void guess(CNode c)//Recursive method that continues the guessing process and learns.
	{
		Object[] possibleValues = {0, 1, 2};
		Object selectedValue = JOptionPane.showInputDialog(null,"is it a(n) " + c.data + "?" +
		" 2 is quit, 1 is yes and 0 is no.", 
		"Input", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		int i = (int) selectedValue;
		if( i==2)
		{
		 return;
		}
		else if( i == 1&&c.right==null)
		{
			JOptionPane.showMessageDialog(null, "I did it!");
			save();
			startGuessing();
		}
		else if(i ==1)
		{
			guess(c.right);
		}
		else
		{
			
			
			if(c.left==null)
			{
				String inputValue = JOptionPane.showInputDialog("What makes it different from a(n) " + c.data + "?");
				String answer = JOptionPane.showInputDialog("What is it?");
				c.left = new CNode(c.data);
				c.data = inputValue;
				c.right = new CNode(answer);
				
				JOptionPane.showMessageDialog(null, "Starting over");
				save();
				startGuessing();
				
			}
			else
			{
				guess(c.left);
			}
			
		}
	}
	
	public void save()//saves the tree into a readable file
	{
		Formatter out = null;
        try {
          out = new Formatter("Test.txt");
          
          printPreOrder(root, out);
          /*out.format("%s,%d%n",worker.data , worker.left == null? 0:2);//saves onto text file*/
              
          
          out.flush();
          
            
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        
           if (out!= null) {
               out.close();
           }
        }
	}
	
	public void printPreOrder (CNode node, Formatter o) {//Recursive method that is used to print the tree into a readable file.
        // Base case is node is null
        if (node ==  null)
           return;
        
     // process parent (print data)
        o.format("%s%d%n",node.data , node.left == null? 0:2);
        list.add(node.data+=node.left == null? 0:2);
        // process left sub tree
        printPreOrder (node.left, o);

        // process right subtree.
        printPreOrder (node.right, o);
        
        
    }
	
	public void load()//Stores information in the readable file into arraylists intlist and stringlist
	{
		stringlist = new ArrayList<String>();
		intlist = new ArrayList<Integer>();
		// TODO Auto-generated method stub
	       JFileChooser chooser = new JFileChooser  (".");
	       
	       int result = chooser.showDialog(null,"Open");
	       Scanner in = null;
	       
	       if (result == JFileChooser.APPROVE_OPTION) {
	           /*JOptionPane.showMessageDialog(null,"file: " + 
	                                  chooser.getSelectedFile().getAbsolutePath());*/
	           // Here use Scanner
	        try {   
	           in = new Scanner(chooser.getSelectedFile());
	           
	           String s = null;
	           while (in.hasNext()) {
	        	 s = in.nextLine ();
	        	 stringlist.add(s.substring(0,s.length()-1));//does the same as the next line
	        	 intlist.add(Integer.parseInt(s.substring(s.length()-1,s.length())));
	             /*JOptionPane.showMessageDialog(null,"line: " + s);*/
	             
	           }
	           for(int k = 0; k<intlist.size(); k++)
	             {
	            	 /*System.out.println( stringlist.get(k));*/
	             }//Prints the list*/
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            in.close();
	            
	        }
	       } // end of if
	       
	       
	}
	
	public void constructTree(CNode starter){//Constructs a tree based on the stringlist and intlist arraylists.
		if(intlist.size()==0)
		{
			return;
		}
		if(tracker>=intlist.size())
		{
			return;
		}
		
		if(intlist.get(tracker)==2)
		{
			starter.data = stringlist.get(tracker);
			tracker++;
			starter.left = new CNode();
			constructTree(starter.left);
			starter.right = new CNode();
			constructTree(starter.right);
			
		}
		else if(intlist.get(tracker)==0){
			starter.data = stringlist.get(tracker);
			tracker++;
		}
			
	}
}

