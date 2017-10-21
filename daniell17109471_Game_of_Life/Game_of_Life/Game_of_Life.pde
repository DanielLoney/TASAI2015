//Note: set cells to specific places, or set them to random and comment other stuff out

space area = new space();
void setup() 
{ 
  size(360, 360);  
}
void draw()
{
  area.printSpace();
}
void mousePressed()
{
  background(0);
  area.react();
  area.printSpace();
}
public class cell
{
  boolean life;
  int l;
  cell(int size, boolean live)
 {
   life = live;
   l = size;
 } 
 boolean getLife()
 {
   return life;
 }
 int getSize()
 {
   return l;
 }
}
public class space
{
  ArrayList<cell> cells = new ArrayList<cell>();
  space()
  {
    
    for(int k = 0; k<36; k++)
   {
      //cells.add(new cell(60, getOneZero()));//  This just gives me random cells
      cells.add(new cell(60, false));
   }
   cells.set(8, new cell(60, true));
   cells.set(15, new cell(60, true));
   cells.set(21, new cell(60, true));
   cells.set(20, new cell(60, true));
   cells.set(19, new cell(60, true));
   
  }
  void react()
  {
    ArrayList<cell> newCells = new ArrayList<cell>();
    for (int copy = 0; copy<36; copy++)
    {
      newCells.add(new cell(60, cells.get(copy).getLife()));
    }
    for( int z = 0; z<36; z++)
    {
      newCells.set(z, new cell(60, cellStep(z)));
    }
    cells = newCells;
  }
  boolean cellStep(int centreCell)
  {
     boolean original = cells.get(centreCell).getLife();
    if(neighbors(centreCell)==3)
    {
      return true;
    }
    else if(neighbors(centreCell)==1)
    {
      return false;
    }
    else if(neighbors(centreCell)>3)
    {
      return false;
    }
    return original;
  }
  int neighbors(int reactor)
  {
    int neighbors = 0;
    if(reactor%6!=0)
    {
      if(doesExist(reactor-7))
      {
        if(cells.get(reactor-7).getLife())
        {
          neighbors++;
        }
      }
      if(doesExist(reactor-1))
      {
        if(cells.get(reactor-1).getLife())
        {
          neighbors++;
        }
      }
      if(doesExist(reactor+5))
      {
        if(cells.get(reactor+5).getLife())
        {
          neighbors++;
        }
      }
    }
    if((reactor+1)%6!=0)
    {
      if(doesExist(reactor+1))
      {
        if(cells.get(reactor+1).getLife())
        {
          neighbors++;
        }
      }
      if(doesExist(reactor+7))
      {
        if(cells.get(reactor+7).getLife())
        {
          neighbors++;
        }
      }
      if(doesExist(reactor-5))
      {
        if(cells.get(reactor-5).getLife())
        {
          neighbors++;
        }
      }
    }
    if(doesExist(reactor-6))
    {
      if(cells.get(reactor-6).getLife())
      {
        neighbors++;
      }
    }
    if(doesExist(reactor+6))
    {
      if(cells.get(reactor+6).getLife())
      {
        neighbors++;
      }
    }
    
    return neighbors;
    
  }
  void printSpace()
  {
    for(int y = 0; y<6; y++)
    {
      for(int x = 0; x<6; x++)
      {
        if(cells.get(y*6+x).getLife())
        {
          fill(255,0,0);
          rect(x*60, y*60, 60, 60);
        }
        else
        {
          fill(255,255,255);
          rect(x*60, y*60, 60, 60);
        }
      }
    }
  }
}
boolean doesExist(int existing)
{
  if(existing>=0&&existing<=35)
  {
    return true;
  }
  return false;
}
boolean getOneZero()
{
  int i = (int)(Math.random()*2);
  if(i==1)
  {
    return true;
  }
  return false;
}
