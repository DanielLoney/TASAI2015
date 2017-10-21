/*****************************************************
 *    name: scale_example
 *
 *    author: Carlos Delgado
 *    
 *    date:  September, 2014
 *
 *****************************************************/



public class Rectangle {
   public float x;
   public float y; 

   public float sc;
   
   
   private float w;
   private float h; 
   
   public Rectangle (float rhs_w, float rhs_h) {
     w = rhs_w;
     h = rhs_h;  
   }  
   
   public void draw () {
     pushMatrix();
     scale (sc);
     pushMatrix();
      fill (0,0,255);
      
      rect (0, 0, w, h); 


    popMatrix();
    popMatrix();
     
   }
  
}






Rectangle rec = new Rectangle (50, 50);


void setup() {
size(800, 600);
  rec.x =100;
  rec.y =200;
  
  rec .sc = 1.0;
}


void draw() {
   //clear ();
   background (255, 229, 180); // peach

  // change the position of rectangle.
  
  // for illustration purposes 
  rec.sc +=0.02;
  rec.draw ();



}
