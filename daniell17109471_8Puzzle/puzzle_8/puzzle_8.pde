import java.util.ArrayList;//printmap issues
import java.util.Collections;
int method;
int looper = 0;//variable to help loop
int looper2 = 0;
int w = 300;//width
int h = 300;//height
int cells = 3;//side length
map initial = new map();//the random map created at the begining of the code
map displayed = new map();//the map that is to be displayed
ArrayList<route> possroute = null;//testing nodes
ArrayList<map> usedmaps = new ArrayList<map>();//visited nodes
route thefinalanswer = null;//The route that will be printed
final map solutionmap = new map();
void setup()//final
{
  solutionmap.cellorder.add(new cell(0,0,1));//These create my solution map which is the final map I want {123},{456},{780}
  solutionmap.cellorder.add(new cell(1,0,2));
  solutionmap.cellorder.add(new cell(2,0,3));
  solutionmap.cellorder.add(new cell(0,1,4));
  solutionmap.cellorder.add(new cell(1,1,5));
  solutionmap.cellorder.add(new cell(2,1,6));
  solutionmap.cellorder.add(new cell(0,2,7));
  solutionmap.cellorder.add(new cell(1,2,8));
  solutionmap.cellorder.add(new cell(2,2,0));
  ArrayList<Integer> states = new ArrayList<Integer>();
  states.add(0);//These are added so that I can have a libary of states to call when I first create my initial map
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
  Object[] options = { "Random Initial",

"Solvable Initial 1","Solvable Initial 2"};
  method = javax.swing.JOptionPane.showOptionDialog(null, "Please select your initial.", "Initial Option", 
  javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE, null,  options, options[0]);
  if(method == 1)
  {
  initial.cellorder.add(new cell(0,0,0));
  initial.cellorder.add(new cell(1,0,1));
  initial.cellorder.add(new cell(2,0,3));
  initial.cellorder.add(new cell(0,1,4));
  initial.cellorder.add(new cell(1,1,2));
  initial.cellorder.add(new cell(2,1,5));
  initial.cellorder.add(new cell(0,2,7));
  initial.cellorder.add(new cell(1,2,8));
  initial.cellorder.add(new cell(2,2,6));
  }
  else if(method == 2)
  {
  initial.cellorder.add(new cell(0,0,8));
  initial.cellorder.add(new cell(1,0,1));
  initial.cellorder.add(new cell(2,0,3));
  initial.cellorder.add(new cell(0,1,4));
  initial.cellorder.add(new cell(1,1,0));
  initial.cellorder.add(new cell(2,1,2));
  initial.cellorder.add(new cell(0,2,7));
  initial.cellorder.add(new cell(1,2,6));
  initial.cellorder.add(new cell(2,2,5));
  }
  else
  {
  //The following is a random generator for the map
    for (int i = 0; i < cells; i++) {//this is where I create a random map by going through each coordinate on a 3*3 grid and adding a random state to it, everytime a state
      //is added it's removed from the state arraylist so that I only have one of each state per map
      for (int j = 0; j < cells; j++) {
        int temp = (int)(Math.random()*states.size());
        int temp2 =states.get(temp);
        initial.cellorder.add(new cell(i, j, temp2));
        states.remove(temp);
      }
    }
  }
  /*displayed = solutionmap;
  displayed= initial;*/
  initial.setCoord();
  initial.addAdj();
  displayed = initial;
  delay(100);
  printMap();
}
void draw() {
  delay(100);
  if(thefinalanswer!=null)
  {
    if(looper<thefinalanswer.route.size())//prints the answer
    {
      displayed = thefinalanswer.route.get(looper);
      delay(500);
      printMap();
      looper++;
    }
    else
    {
    javax.swing.JOptionPane.showMessageDialog(null,"The puzzle has been solved!");
    System.exit(0);
    }
  }else{
    printMap();
    solve();
    looper2++;
  }
  if(looper2>3)
  {
    System.exit(0);
  }
}
void printMap()//Prints each cell in the displayed map onto the window
  {
    for(int k = 0; k<displayed.cellorder.size();k++)
    {
      displayed.cellorder.get(k).printcell();
    }  
  }
class cell implements Comparable
{ 
  int state = -1;
  int x;//x coordinate
  int y;//y coordinate
  int size = w/cells;
  cell(int xx, int yy, int states)
  {
    state = states;// state of the cell
    x=xx;
    y=yy;
  }
  cell()
  {
  }
  cell hardcopyCell()//method to hardcopy the cell
  {
    return new cell(x,y,state);
  }
  public int compareTo(Object o)//method to compare the cell 
  {
    cell other = (cell)o;
    return x*3+y-(other.x*3+other.y);
  }
  void printcell()//prints color depending on the state
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
  boolean equals(Object o)
  {
    cell other = (cell)o;
    if(other.state ==state&&other.x==x&&other.y==y)
    {
      return true;
    }else{
      return false;
    }
  }
}
boolean searchPoss()//searches the last map of all the possible routes to see if it's the answer
{
  for(route r: possroute)
  {
    if(r.route.get(r.route.size()-1).equals(solutionmap))
    {
      return true;
    }
  }
  return false;
}
route findShortestRoute()//finds the route with the shortest length out of the possible routes
{
  
  route recordr = new route();
  int recordi = 100000;
  for(int k = 0; k<possroute.size();k++)
  {
    if(possroute.get(k).routelength()<recordi&&possroute.get(k).route.get(possroute.get(k).route.size()-1).visited()!=true)
    {
      recordi = possroute.get(k).routelength();
      recordr = possroute.get(k);
    }
  }
  return recordr;
}
void findAnswer()//finds the answer route with the final map equal to the solution map
{
  if(searchPoss()==false)
  {
    javax.swing.JOptionPane.showMessageDialog(null,"SearchPoss has not been forfilled");
  }
  else
  {
    for(route r: possroute)
    {
      if(r.route.get(r.route.size()-1).equals(solutionmap))
      {
        thefinalanswer = r;
      }
    }
  }
}
void solve()//astar method
{
  int restrictor = 0;
  possroute = new ArrayList<route>();//resetting the possroutes
  ArrayList<route> altpossroute = null;
  for(int k1 = 0; k1<initial.adjacent.size(); k1++)
  {
    ArrayList<map> temp = new ArrayList<map>();
    temp.add(initial);
    temp.add(initial.adjacent.get(k1));
    possroute.add(new route(temp));
  }
  usedmaps.add(initial);
  while(searchPoss()==false)//infinite loop until the shortest has ben found
  {
    if(restrictor>100)
    {
      javax.swing.JOptionPane.showMessageDialog(null,"Unsolvable initial, or taking too long");
      System.exit(0);
    }
    for(int k = 0; k<possroute.size();k++)
    {
      if(usedmaps.contains(possroute.get(k).route.get(possroute.get(k).route.size()-1))==true)
      {
        possroute.remove(k);
      }
    }
    altpossroute = new ArrayList<route>();//hardcopy for the possroutes
    for(int k=0; k<possroute.size();k++)
    {
      altpossroute.add(possroute.get(k));
    }//HARDCOPY
    route recordr = findShortestRoute();//finds the shortest route
    map lastofrecordr = recordr.route.get(recordr.route.size()-1);//finds the last map of the shortest route
    lastofrecordr.setCoord();//Sets the coordinates for the map
    lastofrecordr.addAdj();//adds the possible moves from the map
    for(int k = 0; k<lastofrecordr.adjacent.size();k++)//adds the adjacent routes to the possroutes, then removes the original route
    {
      if(lastofrecordr.adjacent.get(k).visited()==false)//Tests to see whether or not the move has been made before
      {
        route recordrcopy = null;
        recordrcopy = new route();
        for(int l = 0; l<recordr.route.size();l++)//HARDCOPY of the shortest route
        {
          recordrcopy.route.add(recordr.route.get(l));
        }
        recordrcopy.route.add(lastofrecordr.adjacent.get(k));//adds one possible move
        
        altpossroute.add(recordrcopy);//adds the route with the added map to the list of possible routes
      }
    }
    usedmaps.add(lastofrecordr);
    altpossroute.remove(recordr);//removes the record route because all of the possible moves from that route has been expanded.
    possroute = altpossroute;//sets the possroute variable to the adjusted poss routes
    restrictor = possroute.size();
    altpossroute = null;
  }
  findAnswer();//finds the answer route
}
class map 
{
  ArrayList<cell> cellorder = new ArrayList<cell>();
  ArrayList<map> adjacent = new ArrayList<map>();
  int xvalue;
  int yvalue;
  map(ArrayList<cell> r)//constructor
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
  map hardcopyMap()//hardcopys map
  {
    map returning = new map();
    for(int k = 0; k<cellorder.size();k++)
    {
      returning.cellorder.add(cellorder.get(k).hardcopyCell());
    }
    returning.xvalue = xvalue;
    returning.yvalue = yvalue;
    return returning;
  }
  void setCoord()//sets the x and y value to the coordinates of the movable cell
  {
    for(int k = 0; k<cellorder.size(); k++)
    {
      if(cellorder.get(k).state == 0)
      {
        xvalue = cellorder.get(k).x;
        yvalue = cellorder.get(k).y;
        break;
      }
    }
  }
  cell findCell(int xx, int yy)//finds the cells with the given coordinates from the given map
  {
    if((xx>=cells||xx<0)||(yy>=cells||yy<0))
    {
      return null;
    }
    for(int k = 0; k<cellorder.size();k++)
    {
      if(cellorder.get(k).x == xx && cellorder.get(k).y == yy)
      {
       // cell returning = new cell(c.x, c.y, c.state);
        return cellorder.get(k);
      }
    }
  return null;
  }
  void sortmap()//sorts the map
  {
    Collections.sort(cellorder);
  }
  void addAdj()//adds the possible moves from the map
  {
    if(findCell(xvalue, yvalue+1)!=null)
    {
      adjacent.add(swap(findCell(xvalue,yvalue+1),findCell(xvalue, yvalue)));
    }
    if(findCell(xvalue, yvalue-1)!=null)
    {
      adjacent.add(swap(findCell(xvalue,yvalue-1),findCell(xvalue, yvalue)));
    }
    if(findCell(xvalue+1, yvalue)!=null)
    {
      adjacent.add(swap(findCell(xvalue+1,yvalue),findCell(xvalue, yvalue)));
    }
    if(findCell(xvalue-1, yvalue)!=null)
    {
      adjacent.add(swap(findCell(xvalue-1,yvalue),findCell(xvalue, yvalue)));
    }
  }
  String toString()
  {
    String s = "\n";
    for(int k = 0; k<cells; k++)
    {
      for(int i = 0; i<cells; i++)
      {
        s+= cellorder.get(i*3+k).state+" ";
      }
      s+="\n";
    }
    return s+"\n";
  }
  boolean visited()//returns whether or not the route has been visted, may be obsolete
  {
    for(int k = 0; k<usedmaps.size();k++)
    {
      if(equals(usedmaps.get(k)))
      {
        return true;
      }
    }
    return false;
  }
  map swap(cell first, cell second)//swaps the states of the given first and second cells from the map and returns a new map after sorting the map
  {
    map returning = new map();
    int coord1=-1;
    int coord2=-1;
    for(int k = 0; k<cellorder.size();k++)
    {
      returning.cellorder.add(cellorder.get(k));
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
  int manhattan()//returns the manhattan distance from the map to the solution
  {
    int returning=0;
    for(int k = 0; k<cellorder.size();k++)
    {
      cell solutionscell = new cell();
      for(int s = 0; s<solutionmap.cellorder.size();s++)
      {
        if(solutionmap.cellorder.get(s).state==cellorder.get(k).state)
        {
          solutionscell = solutionmap.cellorder.get(s);
        }
      }
      returning+=(cellorder.get(k).manhattan(solutionscell));
    }
    return returning;
  }
  boolean equals(Object other)//tests whether or not the maps have equal variables
  {
    map m = (map)other;
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
    for(int k = 0; k<one.size();k++)
    {
      if(one.get(k).equals(two.get(k))==false)
      {
        return false;
      }
    }
    return true;
  }
}
class route implements Comparable //route class
{
  ArrayList<map> route = new ArrayList<map>();
  int cost;
  public int compareTo(Object o)//compares the route, may be obsolete
  {
    route other = (route)o;
    return cost-other.cost;
  }
  route(map z)//constructor with only one map in the arraylist
  {
    cost = 1;
    route.add(z);
  }
  route(ArrayList<map> r)//constructor with multiple maps
  {
    for(int x = 0; x<r.size(); x++)
    {
      route.add(r.get(x));
    }
    cost = route.size();
  }
  route()
  {
  }
  int routelength()//method to find the length of the route, it's the size-1+the manhattan distance of the last map
  {
    int returning=0;
    returning+=route.get(route.size()-1).manhattan();
    returning+=route.size()-1;
    return returning;
  }
  String toString()
  {
    String s = "\nRoute Start";
    for(map c: route)
    {
      s+=c;
    }
    return s+"Route end";
  }
}
