/**
 * 
 */

/**
 * @author delgadomatac
 *
 */


import java.util.Scanner;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class ScannerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	       JFileChooser chooser = new JFileChooser  (".");
	       
	       int result = chooser.showDialog(null,"Open");
	       Scanner in = null;
	       
	       if (result == JFileChooser.APPROVE_OPTION) {
	           JOptionPane.showMessageDialog(null,"file: " + 
	                                  chooser.getSelectedFile().getAbsolutePath());
	           // Here use Scanner
	        try {   
	           in = new Scanner(chooser.getSelectedFile());
	           
	           String s = null;
	           while (in.hasNext()) {
	        	 s = in.nextLine ();
	             JOptionPane.showMessageDialog(null,"line: " + s);
	             
	           }
	        } finally {
	            in.close();
	            
	        }
	       } // end of if
	}

}
