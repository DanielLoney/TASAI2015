import java.util.ArrayList;
space testingground = new space();
void setup()
{
size(360, 360);
}
void draw()
{
  testingground.printSpace();
}
public class cell{
  boolean life;
  int l;//l being the location
  cell(boolean live, int ll)
  {
    l=ll;
    life = live;
  }
  boolean getLife()
  {
    return life;
  }
  void die()
  {
    life=false;
  }
  void live()
  {
    life=true;
  }
  int getL()
  {
    return l;
  }
}
public class space
{
  ArrayList<cell> row = new ArrayList<cell>();
  space(){}
  void randomise()
  {
    for(int x=0;x <36; x++)
    {
      row.add(new cell(getRandomOneZero(), 60));//60 because we're assuming that the space will be 360*360 large
    }
  }
  void printSpace()
  {
    randomise();
    for(int y = 0; y<6; y++)
    {
      for(int z = 0; z<6; z++)
      {
        if(row.get(y*6+z).getLife())
        {
          
          fill(255,0,0);
        }
        else
        {
          fill(0,0,0);
        }
        rect(z*60, y*60, 60, 60);
      }
      
    }
  }
  void updateSpace()
  {
    
  }
}
//our array list will be based on 6*6, meaning that x will be multiples of 6 and y will be 1.
boolean getRandomOneZero()
{
  int onezero = (int)(Math.random()*2);
  if( onezero == 1)
  {
    return true;
  }
  else
  {
    return false;
  }
}
void mousePressed()
{
   background(0); 
}
