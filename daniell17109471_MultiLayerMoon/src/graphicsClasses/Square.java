package graphicsClasses;

/**
 * This class extends the abstract class Shape
 * You can use it as a base for your lab.
 * 
 * @author Dr. Carlos Delgado
 * @version February 2015
 */

import java.awt.Color;


public class Square extends Shape
{
    // The length
    protected double side;

    /**
     * Constructor for objects of class Square
     */
    public Square()
    {
        super (0,0, Color.RED, 0.02);
        side = 0.5;
    }

    /**
     * Constructor for objects of class Square
     * 
     * @param  col   Color
     * @param  pen   pen's radius
     * 
     */
    public Square(double x, double y, Color c, double p, double s)
    {
        super (x,y,c, p);
        side = s;
    }
    
    
    /**
     * Method that implements the abstract draw method
     * 
     * @return     void
     */
    public void draw()
    {
       StdDraw.setPenColor (color);
       StdDraw.setPenRadius (pen);
       StdDraw.square (x,y, side/2);   // in the Graphics library you pass the radius ... OK
        
    }
}
