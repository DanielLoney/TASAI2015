package graphicsClasses;


/**
 * This is a simple example on how a class can be extended
 * to specialize a class.
 * That is this FilledSquare is like a Square (but Filled).
 * 
 * 
 * @author Dr. Carlos Delgado
 * @version February 2015
 */
import java.awt.Color;

public class FilledSquare extends Square
{
    /**
     * Constructor for objects of class FilledSquare
     */
    public FilledSquare()
    {
        super (0,0, Color.BLUE, 0.02, 0.5 );

    }

    /**
     * Constructor for objects of class Square
     * 
     * @param  col   Color
     * @param  pen   pen's radius
     * 
     */
    public FilledSquare(double x, double y, Color c, double p, double s)
    {
        super (x,y,c, p,s);
   
    }
    
    
    /**
     * Method that implements the abstract draw method
     * 
     * @return     void
     */
    public void draw()
    {
       // call the parent's draw method.
       super.draw (); 
       StdDraw.filledSquare (x,y, side/2);   // in the Graphics library you pass the radius ... OK
        
    }
}
