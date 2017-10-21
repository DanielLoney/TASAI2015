/**
 * Simple example to show a tree and how to traverse it
 * This is a Node class
 * Please note that we are not using encapsulation.
 * Do not tell the APCS kids!
 * 
 * 
 * @author Dr. Carlos Delgado
 * @version September 18, 2014
 */

public class CNode {

    public CNode left;
    public CNode right;
    public String data;

    

    /**
     * Constructor CNode
     */    
    public CNode () {

        left = null;
        right = null;
        data = null;

    }


    /**
     * Constructor CNode
     */    
    public CNode (String s) {

        left = null;
        right = null;
        data = s;

    }




}
