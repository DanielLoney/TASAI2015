import java.util.ArrayList;
import java.util.Collections;
int looper = 0;
int method = 2;
int w = 300;
int h = 300;
int cells = 3;
map displayed = new map();//displayed nodes
ArrayList<route> possroute = new ArrayList<route>();//testing nodes
ArrayList<map> usedmaps = new ArrayList<map>();//visited nodes
route thefinalanswer = null;
int a=1000000;
int b=1000000;
final map solutionmap = new map();
void setup()//final
{
  solutionmap.cellorder.add(new cell(0,0,0));
  solutionmap.cellorder.add(new cell(1,0,1));
  solutionmap.cellorder.add(new cell(2,0,2));
  solutionmap.cellorder.add(new cell(0,1,3));
  solutionmap.cellorder.add(new cell(1,1,4));
  solutionmap.cellorder.add(new cell(2,1,5));
  solutionmap.cellorder.add(new cell(0,2,6));
  solutionmap.cellorder.add(new cell(1,2,7));
  solutionmap.cellorder.add(new cell(2,2,8));
  ArrayList<Integer> states = new ArrayList<Integer>();
  states.add(0);
  states.add(1);
  states.add(2);
  states.add(3);
  states.add(4);
  states.add(5);
  states.add(6);
  states.add(7);
  states.add(8);
  size(w, h);
  smooth();
  
  for(int i = 0; i<solutionmap.cellorder.size(); i++)
  {
    displayed.cellorder.add(solutionmap.cellorder.get(i));
  }
  /*
  for (int i = 0; i < cells; i++) {
    for (int j = 0; j < cells; j++) {
      int temp = (int)(Math.random()*states.size());
      int temp2 =states.get(temp);
      displayed.cellorder.add(new cell(i, j, temp2));
      states.remove(temp);
    }
  }
  */
  displayed.printMap();
}
void draw() {
  displayed.printMap();
  delay(100);
  if(thefinalanswer!=null)
  {
    while(looper<thefinalanswer.route.size())
    {
      displayed = thefinalanswer.route.get(looper);
      looper++;
    }
    javax.swing.JOptionPane.showMessageDialog(null,"The puzzle has been solved!");
    System.exit(0);
  }else{
    displayed.addAdj();
    for(map m: displayed.adjacent)
    {
      System.out.println(m);//m.printMap();
    }
  }
}

class cell implements Comparable
{ 
  int state = -1;
  int x;
  int y;
  int size = w/cells;
  cell(int xx, int yy, int states)
  {
    state = states;
    x=xx;
    y=yy;
  }
  cell()
  {
  }
  public int compareTo(Object o)
  {
    cell other = (cell)o;
    return x*3+y-(other.x*3+other.y);
  }
  void printcell()
  { 
    if(state == 0)
      fill(255,255,255);
    if(state == 1)
      fill(255,255,150);
    if(state == 2)
      fill(255,255,75);
    if(state == 3)
      fill(255,255,0);
    if(state == 4)
      fill(255,175,0);
    if(state == 5)
      fill(255,75,0);
    if(state == 6)
      fill(255,0,0);
    if(state == 7)
      fill(100,0,0);
    if(state == 8)
      fill(0,0,0);
   rect(w/cells*x,y*h/cells,w/cells, h/cells);
  }
  String toString()
  {
    return x + " " + y;
  }
  int manhattan(cell c)
  {
    return Math.abs(c.x-x)+Math.abs(c.y-y);
  }
}
boolean searchposs()
{
  for(route r: possroute)
  {
    if(r.route.get(r.route.size()-1).equalmaps(solutionmap))
    {
      return true;
    }
  }
  return false;
}

cell findState(int s)
{
  for(cell c: displayed.cellorder)
  {
    if(c.state == s)
    {
      return c;
    }
  }
  return null;
}
void solve()
{
   map end = solutionmap;
   map start = displayed;
   if(start.equalmaps(end))
   {
     thefinalanswer = new route(start);
     return;
   }
  
   possroute.add(new route(start));
   usedmaps.add(start);
   Collections.sort(possroute);
   while(searchposs() == false) {
      ArrayList<route> temporaryposs = new ArrayList<route>();
      for(route r:possroute) {
         temporaryposs.add(r);
      }
      for(route r: temporaryposs) {
         if(r.route.get(r.route.size()-1)==null) {
           possroute.remove(r);
         }
      }
       
      int recordlowi = 1000000;
      route recordlowr = new route();//temp
      ArrayList<route> temproutearray = new ArrayList<route>();
      for(route s: possroute) {
        temproutearray.add(s);//HARDCOPY
      }
      for(route s: possroute) {//s is the original route
        ArrayList<map> tempindroute = s.route;
        map tempmap = tempindroute.get(tempindroute.size()-1);//the last map of each of the possible routes
        for(cell c: tempmap.cellorder)
        {
          if(c.state == 8)
          {
            tempmap.xvalue = c.x;
            tempmap.yvalue = c.y;
            break;
          }
        }
        tempmap.addAdj();
        for(map z: tempmap.adjacent)
        {
        }
        for(map z: tempmap.adjacent) {//z is all the expanded maps for the routes
          ArrayList<map> tempexpandedmap = new ArrayList<map>();
          for(map f: tempindroute) {//HARDCOPY 
            tempexpandedmap.add(f);
          } // end of for (map: f: tempindroute)
          tempexpandedmap.add(z);
            if(z.equalmaps(end)&&z!=null) {
              recordlowr = new route(tempexpandedmap);
              recordlowr.cost = s.cost+1;
              recordlowi = s.cost+z.manhattan();
            }  //end of if z==end
            else if(z!=null&&s.cost+z.manhattan()<recordlowi&&z.visited()==false){
              recordlowr = new route(tempexpandedmap);
              recordlowr.cost = s.cost+1;
              recordlowi = s.cost+z.manhattan();
            }//end of else if
        }//end of for map z:temp.adjacent
      }//end of for route s:possroute
      possroute.add(recordlowr);
      if(recordlowr.route.size() == 0) {
        javax.swing.JOptionPane.showMessageDialog(null,"An error has occurred.");
        System.exit(0);
      }//end of if recordlowr.route.size()==0
      usedmaps.add(recordlowr.route.get(recordlowr.route.size()-1));

 
     }//end of while searchposs == false
     
     
     if(searchposs()!=false){
       for(route r: possroute) {
         if(r.route.get(r.route.size()-1).equalmaps(end)) {
           thefinalanswer = r;
         }//end of if
       }//end of for route r possroute
    }//end of if searchposs
}//end of Function
class map 
{
  ArrayList<cell> cellorder = new ArrayList<cell>();
  ArrayList<map> adjacent = new ArrayList<map>();
  int xvalue;
  int yvalue;
  map(ArrayList<cell> r)
  {
    if(r.size()!=9)
    {
      javax.swing.JOptionPane.showMessageDialog(null,"Invalid arraylist size");
    }
    else
    {
      for(cell x: r)
      {
        cellorder.add(x);
      }
    }
  }
  map()
  {
  }
  void setCoord()
  {
    for(cell c: cellorder)
    {
      if(c.state == 8)
      {
        xvalue = c.x;
        yvalue = c.y;
        break;
      }
    }
  }
  void printMap()
  {
    for(cell c : cellorder)
    {
      c.printcell();
    }  
  }
  cell findCell(int xx, int yy)
  {
    if((xx>=cells||xx<0)||(yy>=cells||yy<0))
    {
      return null;
    }
    for(cell c: cellorder)
    {
      if(c.x == xx && c.y == yy)
      {
        return c;
      }
    }
  return null;
  }
  void sortmap()
  {
    Collections.sort(cellorder);
  }
  void addAdj()
  {
    setCoord();
    if(findCell(xvalue +1, yvalue )!=null&&findCell(xvalue +1, yvalue ).state!=-1){
      adjacent.add(swap(findCell(xvalue +1, yvalue ),findCell(xvalue ,yvalue )));
    }
    if(findCell(xvalue , yvalue =1)!=null&&findCell(xvalue , yvalue +1).state!=-1){
      adjacent.add(swap(findCell(xvalue , yvalue +1),findCell(xvalue ,yvalue )));
    }
    if(findCell(xvalue -1, yvalue )!=null&&findCell(xvalue -1, yvalue ).state!=-1){
      adjacent.add(swap(findCell(xvalue -1, yvalue ),findCell(xvalue ,yvalue )));
    }
    if(findCell(xvalue , yvalue -1)!=null&&findCell(xvalue , yvalue +1).state!=-1){
      adjacent.add(swap(findCell(xvalue , yvalue -1),findCell(xvalue ,yvalue )));
    }
  }
  String toString()
  {
    String s = "";
    for(cell c: cellorder)
    {
      s+="X: " + c.x+" Y: "+c.y+" State: "+c.state + ":::";
    }
    return s;
  }
  boolean visited()
  {
    for(map x: usedmaps)
    {
      if(equalmaps(x))
      {
        return true;
      }
    }
    return false;
  }
  map swap(cell first, cell second)
  {
    map returning = new map();
    int coord1=-1;
    int coord2=-1;
    for(cell c: cellorder)
    {
      returning.cellorder.add(c);
    }
    if(returning.cellorder.contains(first)!=true||returning.cellorder.contains(second)!=true)
    {
      return null;
    }
    for(int k = 0; k<cellorder.size(); k++)
    {
      if(returning.cellorder.get(k).equals(first))
      {
        coord1=k;
      }
    }
    for(int k = 0; k<cellorder.size(); k++)
    {
      if(returning.cellorder.get(k).equals(second))
      {
        coord2=k;
      }
    }
    returning.cellorder.set(coord2, new cell(first.x, first.y, second.state));//swaps first
    returning.cellorder.set(coord1, new cell(second.x, second.y, first.state));//swaps second 
    if(coord1==-1||coord2==-1)
    {
      return null;
    }
    returning.sortmap();
    return returning;
  }
  int manhattan()
  {
    int returning=0;
    for(cell c:cellorder)
    {
      cell solutionscell = new cell();
      for(cell s:solutionmap.cellorder)
      {
        if(s.state==c.state)
        {
          solutionscell = s;
        }
      }
      returning+=(c.manhattan(solutionscell));
    }
    return returning;
  }
  boolean equalmaps(map m)
  {
    if (cellorder == null && m.cellorder == null){
        return true;
    }

    if((cellorder == null && m.cellorder != null) 
      || cellorder != null && m.cellorder == null
      || cellorder.size() != m.cellorder.size()){
        return false;
    }

    //to avoid messing the order of the lists we will use a copy
    //as noted in comments by A. R. S.
    ArrayList<cell> one = new ArrayList<cell>(cellorder); 
    ArrayList<cell> two = new ArrayList<cell>(m.cellorder);   

    Collections.sort(one);
    Collections.sort(two);      
    return cellorder.equals(m.cellorder);
  }
}
class route implements Comparable 
{
  ArrayList<map> route = new ArrayList<map>();
  int cost;
  public int compareTo(Object o)
  {
    route other = (route)o;
    return cost-other.cost;
  }
  route(map z)
  {
    cost = 1;
    route.add(z);
  }
  route(ArrayList<map> r)
  {
    for(map x: r)
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
    String s = "1";
    for(map c: route)
    {
      
    }
    return s;
  }
}
