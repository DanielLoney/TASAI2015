/**
 * 
 */

/**
 * @author delgadomatac
 *
 */
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class StringTokenizerTester {
  public static void main (String args[]) {
	  
	  String s = "Glory, glory , Man United!";
	  StringTokenizer st = new StringTokenizer(s);
	  
	  JOptionPane.showMessageDialog(null, "String spilt with the default option (space)");
	  
	  while (st.hasMoreTokens()) {
		  JOptionPane.showMessageDialog(null, st.nextToken());
	  }
	  StringTokenizer st2 = new StringTokenizer(s,",");
	  JOptionPane.showMessageDialog(null, "String spilt with comma \',\'");

	  while (st2.hasMoreTokens()) {
		  JOptionPane.showMessageDialog(null, st2.nextToken());
	  }	  
	  JOptionPane.showMessageDialog(null, "Done! Have a nice day --- Remember to support Manchester United");
	  
	  
  }
}
