package graphicsClasses;

/**
 * This is the Runner class.
 * You should Add more shapes
 * 
 * @author Dr. Carlos Delgado
 * @version February 2015
 */


import java.util.ArrayList;
import java.awt.Color;

public class ShapeRunner
{
  public static void main (String args []) {
      
      // This two methods are used to sefine the scale.
      // If you are not seeing your shapes ... you might be drawing
      // them outside the window.
      
      StdDraw.setXscale (-5, 5);
      StdDraw.setYscale (-5, 5);

      // create ArrayList of Shapes
      
      ArrayList <Shape> shapes = new ArrayList <Shape> ();
      
      // You should add the shapes by using input
      // keyboard or file.
      
      
      Shape sh = new Square (0,0, Color.BLUE, 0.05, 0.5);
      

      
      shapes.add (sh);
      
      // add a shape on the fly
      shapes.add (new Square (-1.0, 1.0, Color.RED, 0.02,0.4));
      // create color on the fly
      shapes.add (new FilledSquare (1.0, 1.0, new Color (92,37,92), 0.02,0.4));      
      

      // Add AT LEAST 6 more different Shapes (Circles, Rectangles and Triangles) .
      shapes.add (new FilledSquare (-1,-1,new Color (255,0,255), 0.02, 0.6));

      shapes.add (new FilledSquare (-3,-3,new Color (191,15,15), 0.01, 0.7));
      
      
      Shape ok = new FilledSquare();
      
      for (Shape item: shapes ) {
          
         item.draw();    
          
      }
      
      
      
  }
}
