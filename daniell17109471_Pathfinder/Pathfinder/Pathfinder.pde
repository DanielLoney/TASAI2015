
import java.util.ArrayList;
import java.util.Collections;
int method;
int w = 500;
int h = 500;
int cells = 10;
ArrayList<cell> celllist = new ArrayList<cell>();
ArrayList<route> possroute = new ArrayList<route>();
int a=1000000;
int b=1000000;
cell end0 = new cell();
cell start0 = new cell();
boolean inBoundaries(int x, int y)
{
  if(0<=x&&x<cells&&0<=y&&y<cells)
  {
    return true;
  }
  return false;
}
void mousePressed()
{
  for(cell c: celllist)
    {
      if(a!=mouseX/(w/cells)||b!=mouseY/(h/cells))
      {
        if((c.x == mouseX/(w/cells)) && c.y == mouseY/(h/cells))
        {
            if(mouseButton == RIGHT)
          {
             if(c.state == 0)
             {
             c.state = 2;
             }else
             {
             c.state = 0;
             }
             break;
          }else if(mouseButton == CENTER)
          {
             if(c.state == 0)
             {
             c.state = 3;
             }else
             {
             c.state = 0;
             }
             break;
          }
         
        }
      }
    }
}
void mouseDragged() {
    for(cell c: celllist)
    {
      if(a!=mouseX/(w/cells)||b!=mouseY/(h/cells))
      {
        if((c.x == mouseX/(w/cells)) && c.y == mouseY/(h/cells))
        {
          if(mouseButton == LEFT)
          {
             if(c.state == 0)
             {
             c.state = 1;
             c.visited = false;
             }else
             {
             c.state = 0;
             c.visited = false;
             }
             a = c.x;
             b = c.y;
             break;
          }
         
        }
      }
    }
}
void setup()
{
  javax.swing.JOptionPane.showMessageDialog(null, "Drag while holding left mouse button to make walls or revert any cell back to the 0 state.\n Click with the middle button for the starting cell, and the right for the end cell");
  Object[] options = { "Greedy", "Uniform Cost","A-Star" };
  method = javax.swing.JOptionPane.showOptionDialog(null, "Please select your method.", "Method Option", 
  javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE, null,  options, options[0]);
  System.out.println(options[method]);
  size(w, h);
  smooth();
  for (int i = 0; i < cells; i++) {
    for (int j = 0; j < cells; j++) {
      celllist.add(new cell(i, j, 0));
    }
  }
  for(cell c : celllist)
  {
    c.printcell();
  }
}
void draw() {
  for(cell c : celllist)
  {
    c.printcell();
  }
  aStar();
  delay(50);
}

class cell
{
  ArrayList<cell> adjacent = new ArrayList<cell>();
  
  int state = 0;
  int x;
  int y;
  int size = w/cells;
  boolean visited = false;
  cell(int xx, int yy, int states)
  {
    state = states;
    x=xx;
    y=yy;
    if(findCell(x+1, y+1)!=null)
    adjacent.add(findCell(x+1, y+1));
    
    
    visited = false;
  }
  cell()
  {
  }
  void printcell()
  { if(state == 4)
   {
     fill(255,42,0);
   }
   else if(visited == true)
   {
     fill(123,124,231);
   }
   else if(state == 0)
   {
     fill(255,255,255);
   }else if(state == 1)//wall
   {
     fill(0,0,0);
   }else if(state == 2)//end
   {
     fill(255,255,0);
   } else if(state == 3)//start
   {
     fill(150,0,0);
     
   }
   rect(w/cells*x,y*h/cells,w/cells, h/cells);
  }
  String toString()
  {
    return x + " " + y;
  }
}
boolean searchposs(cell end)
{
  for(route r: possroute)
  {
    if(r.route.get(r.route.size()-1).state == 2)
    {
      return true;
    }
  }
  return false;
}
cell findCell(int xx, int yy)
{
  for(cell c: celllist)
  {
    if(c.x == xx && c.y == yy)
    {
      return c;
    }
  }
  return null;
}
void aStar()
{
  
  int a=1000000;
  int b=1000000;
   cell end = new cell(a,b,0);
   cell start = new cell(a,b,0);
   for(cell c: celllist)
   {
     if(c.state == 2)
     {
       end = c;
       break;
     }
   }
   if(end0!=end)
   {
     for(cell c:celllist)
     {
       c.visited = false;
     }
     possroute = new ArrayList<route>();
     end0 = end;
   }
   for(cell c: celllist)
   {
     if(c.state == 3)
     {
       
       start = c;
       break;
     }
   }
   if(start0!=start)
   {
     for(cell c:celllist)
     {
       c.visited = false;
     }
     possroute = new ArrayList<route>();
     start0 = start;
   }
   if((start.x != a&&start.y!=b)&&(end.x!=a&&end.y!=b))
   {
     possroute.add(new route(findCell(start.x, start.y)));
     start.visited = true;
     route answer = new route(new cell());
     for(route r: possroute)
     {
       if(findCell(r.route.get(r.route.size()-1).x,r.route.get(r.route.size()-1).y)==null)
       {
         possroute.remove(r);
       }
     }
     Collections.sort(possroute);
     if(searchposs(end) == false)
     {
       ArrayList<route> temporaryposs = new ArrayList<route>();
       for(route r:possroute)
       {
         temporaryposs.add(r);
       }
       for(route r: temporaryposs)
       {
         if(r.route.get(r.route.size()-1)==null)
         {
           possroute.remove(r);
         }
       }
       
                                int recordlowi = 1000000;
                                route recordlowr = new route();//temp
                                ArrayList<route> temproutes = new ArrayList<route>();
                                for(route s: possroute)
                                {
                                  temproutes.add(s);//HARDCOPY
                                }
                                for(route s: possroute)
                                {
                                  ArrayList<cell> tempstat = s.route;
                                  cell temps = tempstat.get(tempstat.size()-1);//the last cell of each of the possible routes
                                  temps.adjacent.add(findCell(temps.x+1, temps.y));
                                  temps.adjacent.add(findCell(temps.x+1, temps.y+1));
                                  temps.adjacent.add(findCell(temps.x+1, temps.y-1));
                                  temps.adjacent.add(findCell(temps.x, temps.y+1));
                                  temps.adjacent.add(findCell(temps.x, temps.y-1));
                                  temps.adjacent.add(findCell(temps.x-1, temps.y+1));
                                  temps.adjacent.add(findCell(temps.x-1, temps.y));
                                  temps.adjacent.add(findCell(temps.x-1, temps.y-1));
                                  for(cell z: temps.adjacent)
                                  {
                                    ArrayList<cell> tamp = new ArrayList<cell>();
                                    for(cell f: tempstat)//HARDCOPY
                                    {
                                      tamp.add(f);
                                    }
                                    tamp.add(z);
                                    if(method==2)
                                    {
                                      if(z == end)
                                      {
                                        recordlowr = new route(tamp);
                                        recordlowi = s.cost+Math.abs(end.x-z.x)+Math.abs(end.y-z.y);
                                      }
                                      else if(z!=null&&s.cost+Math.abs(end.x-z.x)+Math.abs(end.y-z.y)<recordlowi&&z.visited==false&&z.state!=1)
                                      {
                                        recordlowr = new route(tamp);
                                        recordlowi = s.cost+Math.abs(end.x-z.x)+Math.abs(end.y-z.y);
                                      }
                                    }else if(method == 1){
                                      int xyz= 0;
                                    
                                      if(z == end)
                                      {
                                        for(cell zyx: tamp)
                                        {
                                        xyz += Math.abs(end.x-zyx.x)+Math.abs(end.y-zyx.y);
                                        }
                                        recordlowr = new route(tamp);
                                        recordlowi = xyz+Math.abs(end.x-z.x)+Math.abs(end.y-z.y);
                                        recordlowr.cost = recordlowi;
                                      }
                                      else if(z!=null&&s.cost+Math.abs(end.x-z.x)+Math.abs(end.y-z.y)<recordlowi&&z.visited==false&&z.state!=1)
                                      {
                                        for(cell zyx: tamp)
                                        {
                                        xyz += Math.abs(end.x-zyx.x)+Math.abs(end.y-zyx.y);
                                        }
                                        recordlowr = new route(tamp);
                                        recordlowi = xyz+Math.abs(end.x-z.x)+Math.abs(end.y-z.y);
                                        recordlowr.cost = recordlowi;
                                      }
                                    }else if(method == 0)
                                    {
                                      if(z == end)
                                      {
                                        recordlowr = new route(tamp);
                                        recordlowi = Math.abs(end.x-z.x)+Math.abs(end.y-z.y);
                                        recordlowr.cost = recordlowi;
                                      }
                                      else if(z!=null&&Math.abs(end.x-z.x)+Math.abs(end.y-z.y)<recordlowi&&z.visited==false&&z.state!=1)
                                      {
                                        recordlowr = new route(tamp);
                                        recordlowi = Math.abs(end.x-z.x)+Math.abs(end.y-z.y);
                                        recordlowr.cost = recordlowi;
                                      }
                                    }
                                  }
                                }
                                possroute.add(recordlowr);
                                if(recordlowr.route.size() == 0)
                                {
                                  javax.swing.JOptionPane.showMessageDialog(null,"An error has occurred. The end cell could be unreachable");
                                  System.exit(0);
                                }
                                recordlowr.route.get(recordlowr.route.size()-1).visited=true;
                  
       
     }
     else{
       for(route r: possroute)
       {
         if(r.route.get(r.route.size()-1)==end)
         {
           answer = r;
         }
       }
       for(cell c: answer.route)
       {
         c.state = 4;
       }
    }
 }
}
class route implements Comparable 
{
  ArrayList<cell> route = new ArrayList<cell>();
  int cost;
  public int compareTo(Object o)
  {
    route other = (route)o;
    return cost-other.cost;
  }
  route(cell z)
  {
    cost = 1;
    route.add(z);
  }
  route(ArrayList<cell> r)
  {
    for(cell x: r)
    {
      route.add(x);
    }
    cost = route.size();
  }
  route()
  {
  }
  String toString()
  {
    String s = "";
    for(cell c: route)
    {
      s+=c.x+" "+c.y+":";
    }
    return s;
  }
}
