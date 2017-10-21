GridWorldView view;



void setup() {
  size(800, 600);
  background(255, 255, 255);
  frameRate(24);
  view = new GridWorldView();


}

void draw() { 




    view.display();
    view.updateMatrix();
  
}



// Class for animating a sequence of GIFs

class GridWorldView {
  int x;  // robot's x position
  int y;  // robot's y position
  
  int [][] grid = { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},           
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},  
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1}, 
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},  
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};                  

  PImage[] images;


  
  GridWorldView() {

    images = new PImage[3];


      images[0] = loadImage("gridworld_floor.gif");
      images[1] = loadImage("gridworld_wall.gif");
      images[2] = loadImage("gridworld_robot.gif");
      
      x = 5;
      y = 7;
      
      grid [y][x] =2;
      
  }


  void display () {
      int size = images[0].width;
      for (int k = 0; k < grid.length; k ++)
         for (int i =0; i <grid[0].length; i++) 
            image(images[grid[k][i]], size *i, size*k);
  }


  
  int getWidth() {
    return images[0].width;
  }
  
  void updateMatrix()
  {

    boolean x1=false;
    boolean x2=false;
    boolean x3=false;
    boolean x4=false;
    
    
    System.out.println ( "x : " + x + "   y: " +y);
    if(grid[y][x-1]==0&&grid[y-1][x-1]==0)
    {
      x4 = true;
    }
    if(grid[y-1][x]==0&&grid[y-1][x+1]==0)
    {
      x1 = true;

    }  
    if(grid[y+1][x+1]==0&&grid[y][x+1]==0)
    {
      x2 = true;
    }
    if(grid[y+1][x]==0&&grid[y+1][x-1]==0)
    {
      x3 = true;
    }
    
    
    if(x1&&x4==false)
    {
      grid[y-1][x] = 2;
      grid[y][x] = 0;
      y = y-1;
    }
    else if(x2&&x1==false)
    {
      grid[y][x+1] = 2;
      grid[y][x] = 0;
      x = x+1;
    }
    else if(x3&&x2==false)
    {
      grid[y+1][x] = 2;
      grid[y][x] = 0;
      y = y+1;
    }else if(x4&&x3==false)
    {
      grid[y][x-1] = 2;
      grid[y][x] = 0;
      x = x-1;
    }

  }
}
