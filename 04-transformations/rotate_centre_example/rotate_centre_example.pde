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
   public float w;
   public float h;
   
   public float theta;


   public Rectangle (float rhs_w, float rhs_h) {
      w = rhs_w;
      h = rhs_h;   
   }
   public void draw () {
    
    pushMatrix();
     translate (x,y); 
     pushMatrix ();      
       rotate(radians (theta));
       translate (-w/2,-h/2);  // to get centre.
       pushMatrix();     
         fill (0,0,255);      
         rect (0, 0, w, h); 
       popMatrix();
      popMatrix();
     popMatrix();
   
     
   }
  
}






Rectangle rec = new Rectangle (50,50);


void setup() {
size(800, 600);
  rec.x =150;
  rec.y =250;
  rec.theta =0;
}


void draw() {
   //clear ();
   background (255, 229, 180); // peach

  // change the position of rectangle.
  

  rec.theta +=1;
  rec.x += 0.5;
  rec.draw ();



}
