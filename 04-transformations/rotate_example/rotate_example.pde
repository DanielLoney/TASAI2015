/*****************************************************
 *    name: translate_example
 *
 *    author: Carlos Delgado
 *    
 *    date:  September, 2014
 *
 *****************************************************/



public class Rectangle {
   public float x;
   public float y; 
   public float theta;

   public void draw () {
     pushMatrix();
     rotate(radians (theta));
     pushMatrix();
      fill (0,0,255);
      
      rect (0, 0, 50, 50); 


    popMatrix();
    popMatrix();
     
   }
  
}






Rectangle rec = new Rectangle ();


void setup() {
size(800, 600);
  rec.x =100;
  rec.y =200;
  rec.theta =0;
}


void draw() {
   //clear ();
   background (255, 229, 180); // peach

  // change the position of rectangle.
  

  rec.theta +=0.5;
  rec.draw ();



}
