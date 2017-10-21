/**
 * 
 */

/**
 * @author delgadomatac
 *
 */

import java.util.Formatter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class FormatterTester {

	/**
	 * @param args
	 */
    public static void main (String args[]) throws IOException{
        Formatter out = null;
        try {
          out = new Formatter("Test.txt");
          
          String s = JOptionPane.showInputDialog("Write something to file");
          while (s.length() > 0) {
              out.format("%s%n",s);
              s = JOptionPane.showInputDialog("Write something to file");
          }
          
          out.flush();
            
            
        } finally {
        
           if (out!= null) {
               out.close();
           }
        }
        
    }
}
