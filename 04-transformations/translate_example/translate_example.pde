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
  
   private float w;
   private float h; 
   
   public Rectangle (float rhs_w, float rhs_h) {
     w = rhs_w;
     h = rhs_h;  
   }
     
     

   public void draw () {
     pushMatrix();
       translate(x,y);
       pushMatrix();
        fill (0,0,255);      
        rect (0, 0, w, h); 
       popMatrix();
     popMatrix();
     
   }
  
}






Rectangle rec = new Rectangle (50,50);
Rectangle rec2 = new Rectangle (100,100);

void setup() {
  size(800, 600);
  rec.x =100;
  rec.y =200;
  
  rec2.x =0;
  rec2.y =0;
}


void draw() {
   //clear ();
   background (255, 229, 180); // peach

  // change the position of rectangle.
  // for illustration purposes if animation use d = v*t
  rec.x +=0.1;
  rec.draw ();
  rec2.draw();



}
