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
   
   public void sense () //senses the object
   {
    
     distance = Math.sqrt(Math.pow(tar.getTargetX()-(x+80),2)+Math.pow(tar.getTargetY()-y+9,2));
     sensorValue = (float)(1/Math.pow(distance, 2));
   
   }
   
   public void doAI() // 
   {
     if(distance<tar.getTargetRadius())
     {
       vx += sensorValue*20;
     }
     else
     {
       vx = 0.3;
     }
     if(x>800)
     {
       x = 0.0;
       y = 300.0;
     }
     
   }
   
   public void update () 
   {
      sense ();
      doAI ();
      int time = millis ();
      int tick = time - prevTime; 
      prevTime = time;
      x = x + vx * tick;
      y = y + vy * tick;
   }
   
   public void draw () 
   {
     
      fill (0,0,255);
      
      rect (x, y, 50, 20); 
      
      stroke(0,255,0);
      
      line(x+50-1,y+9, x+70, y+9);
      
      stroke(255,0,0);
      
      noFill();
      
      arc(x+80, y+9, 20,20, PI/2, 3*PI/2);
      
      stroke(255,255,255);
      
      fill(0,255,0);
      
      rect(x-20, y+5, 30, 10);
   }
  
}


public class Target {
  
 public float x;
 public float y;
 public float radius;

 
   public void draw () {
     fill (255, 255, 255);
     ellipse (x, y, radius, radius); 
   }
 float getTargetX()
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
void draw() 
{
  clear ();
  tar.x = mouseX;
  tar.y = mouseY;

  tar.draw ();
  ve.update();
  ve.draw();


}
