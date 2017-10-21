package graphicsClasses;

/**
 * Abstract class Shape - write a description of the class here
 * 
 * @author Dr. Carlos Delgado
 * @version February 2015
 */


import java.awt.Color;

public abstract class Shape
{
    protected Color   color;
    protected double  pen; 
    protected double  x;
    protected double  y;
    
    public Shape () {
        
        // default values
        color = Color.BLACK;
        pen = 0.01;
        
    }
    
    public Shape (double rhs_x, double rhs_y, Color rhs_c, double rhs_p) {
        color =rhs_c;
        pen =rhs_p;
        x = rhs_x;
        y = rhs_y;
    }
    
    public double getX () {
        return x;
    }
    
    
    
    public abstract void draw ();
    

}
