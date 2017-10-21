package graphicsClasses;

/**
 * Simple example on using StdDraw library.
 * 
 * @author Dr. Carlos Delgado
 * @version February 2015
 */
import java.awt.Color;

public class TriangleTester
{
  public static void main (String args []) {
      StdDraw.setXscale (-0.1, 1.1);
      StdDraw.setPenRadius (0.05);
      StdDraw.setYscale (-0.1, 1.1);
      double t = Math.sqrt (3.0)/2.0;
      StdDraw.setPenColor (Color.BLUE);
      StdDraw.line (0.0, 0.0, 1.0, 0.0);
      StdDraw.line (1.0, 0.0, 0.5, t); 
      StdDraw.line (0.5, t, 0.0, 0.0);
      StdDraw.point (0.5, t/3);

      
      
  }
}
