package guesser;

public class CBinaryTree
{
    CNode root = null;
    
    
    /**
     * Constructor for objects of class CTree
     */
    public CBinaryTree()
    {
        // Local variables to create tree
        CNode father =null;
        CNode child = null;
        
        
        // populate tree
        father = new CNode ("5");
        // set 5 to be the root.
        root = father;
        

        
        child = new CNode ("3");
        
        // set left child
        father.left = child;
        
        // now father is 3
        father = father.left;
        child = new CNode ("2");
        // set left child
        father.left = child;
        
        // Now do right sub tree.
        father = root;
        child = new CNode ("7");
        father.right = child;
        
        // 7 is now father
        father = father.right;
        child = new CNode ("11");
        father.right = child;
        
    }
    
    public void printInOrder () {
        System.out.println ("Printing tree's contents INORDER");
        
        // call recursive method.
        printInOrder (root);
    }
    
    public void printInOrder (CNode node) {
        // Base case is node is null
        if (node ==  null)
           return;
        
        // process left sub tree
        printInOrder (node.left);
        // process parent (print data)
        System.out.println (node.data);
        // process right subtree.
        printInOrder (node.right);
    }
    
    public void printPostOrder () {
        System.out.println ("Printing tree's contents POSTORDER");
        
        // call recursive method.
        printPostOrder (root);
    }
    
    public void printPostOrder (CNode node) {
        // Base case is node is null
        if (node ==  null)
           return;
        
        // process left sub tree
        printPostOrder (node.left);

        // process right subtree.
        printPostOrder (node.right);
        
        // process parent (print data)
        System.out.println (node.data);
    }
    
    public void printPreOrder () {
        System.out.println ("Printing tree's contents POSTORDER");
        
        // call recursive method.
        printPreOrder (root);
    }
    
    public void printPreOrder (CNode node) {
        // Base case is node is null
        if (node ==  null)
           return;
        
     // process parent (print data)
        System.out.println (node.data);
        
        // process left sub tree
        printPreOrder (node.left);

        // process right subtree.
        printPreOrder (node.right);
        
        
    }
    
    
}
