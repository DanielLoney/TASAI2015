public class Vehicle {
   public float x;
   public float y; 
   
   public float vx;
   public float vy;
   
   public float sensorValue;
   
   public int prevTime;
   public double distance;
   
   public Vehicle () {
       vx = 0.3;
       vy = 0.0;
       
       x = 0.0;
       y = 300.0;
       prevTime = 0;
     
   }
   
   public void sense() 
   {
   
   }
   
   public void doAI() {
     if(Math.pow(tar.getTargetRadius()-30,2)>Math.pow(x-tar.getTargetX()+25,2)+Math.pow(y-tar.getTargetY()+25,2))
     {
       if(y+25>tar.getTargetY())
       {
         
        
           vy+= 0.005;
         
       }
       else
       {
         vy-=0.005;
       }
     }
     else
     {
       vy = 0;
     }
     if(x>800)
     {
       x = 0.0;
       y = 300.0;
     }
   }
   
   public void update () {
      sense ();
      doAI ();
      int time = millis ();
      int tick = time - prevTime; 
      prevTime = time;
      x = x + vx * tick;
      y = y + vy * tick;
   }
   
   public void draw () {
     
     pushMatrix();
     translate (x,y); 
     pushMatrix ();      
       rotate(vy/vx);
       translate (-50/2,-50/2);  // to get centre.
       pushMatrix();     
            fill (0,0,255);
            rect (0, 0, 50, 50); 
            fill(0,255,0);
            rect(0-20, 0+5, 30, 10);
            fill(0,255,0);
            rect(0-20, 0+35, 30, 10);
            stroke(0,255,0);
            line(0+50-1,0+9, 0+70, 0+9);
            stroke(255,0,0);
            noFill();
            arc(0+80, 0+9, 20,20, PI/2, 3*PI/2);
            stroke(0,255,0);
            line(0+50-1,0+39, 0+70, 0+39);
            stroke(255,0,0);
            noFill();
            arc(0+80, 0+39, 20,20, PI/2, 3*PI/2);
       popMatrix();
      popMatrix();
     popMatrix();
     
      
     
   }
  
}


public class Target {
  
 public float x;
 public float y;
 public float radius;

 
 public void draw () {
   fill (255, 255, 255);
   ellipse (x, y, radius, radius); 
 }float getTargetX()
 {
   return x;
 }
 float getTargetY()
 {
   return y;
 }
 
 float getTargetRadius()
 {
   return radius;
 }
 
}


float x;
float y;
float px;
float py;
float easing = 0.05;


Target tar = new Target();
Vehicle ve = new Vehicle ();


void setup() {
size(800, 600);
//smooth();
//stroke(0, 102);
tar.radius = 200;
}
void draw() {
   clear ();
  tar.x = mouseX;
  tar.y = mouseY;

  tar.draw ();
  ve.update();
  ve.draw();
}

