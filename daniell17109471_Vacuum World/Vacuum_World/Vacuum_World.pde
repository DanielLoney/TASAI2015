import javax.swing.JOptionPane;
states state = new states(0);
void setup() 
{ 
  size(180,180);  
  state.begin();
}
void draw()
{
  state.printStates();
}
void mousePressed()
{
  state.react();
  state.printStates();
}
public class box
{
  int mode = 0;
  boolean box1 = false;
  boolean box2 = false;
  boolean box3 = false;
  boolean box4 = false;
  box(int i)
  {
    mode = i;
    if(i == 1)
    {
      box1 = true;
      box3 = true;
      box4 = true;
    } else if(i==2)
    {
      box2 = true;
      box3 = true;
      box4 = true;
    }else if(i==3)
    {
      box1 = true;
      box3 = true;
    }else if(i==4)
    {
      box2 = true;
      box3 = true;
    }else if(i==5)
    {
      box1 = true;
      box4 = true;
    }else if(i==6)
    {
      box2 = true;
      box4 = true;
    }else if(i==7)
    {
      box1 = true;
    }else if(i==8)
    {
      box2 = true;
    }
  }
}
public class states
{ 
  int cost = 0;
  box space = new box(0);
  states(int i)
  {
  space = new box(i);
  }
  void printStates()
  {
    background(255,255,255);
    if(space.box1 == true)
    {
      fill(0,0,0);
      rect(0,0,90,90);
    }
    if(space.box2 == true)
    {
      fill(0,0,0);
      rect(90,0,90,90);
    }
    if(space.box3 == true)
    {
      fill(51,25,0);
      rect(0,90,90,90);
    }
    if(space.box4 == true)
    {
      fill(51,25,0);
      rect(90,90,90,90);
    }
    
  }
  void begin()
  {
    Object[] possibleValues = {1, 2,3,4,5,6,7};
    Object selectedValue = JOptionPane.showInputDialog(null,"Please select starting state", 
    "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
    int i = (Integer) selectedValue;
    space = new box(i);
  }
  void react()
  {
    if(space.mode == 1)
    {
      cost++;
      space = new box(5);
    } else if(space.mode == 2)
    {
      cost++;
      space = new box(4);
    }else if(space.mode == 3)
    {
      cost++;
      space = new box(7);
    }else if(space.mode == 4)
    {
      cost++;
      space = new box(3);
    }else if(space.mode == 5)
    {
      cost++;
      space = new box(6);
    }else if(space.mode == 6)
    {
      cost++;
      space = new box(8);
    }else{
      JOptionPane.showMessageDialog(null, "The cost is " + cost);
      exit();
    }
  }
}
