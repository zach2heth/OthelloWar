/**********************************************
* Zachary Heth 2016
**********************************************
* Map Class
*    Map Class deals with the layout of a grid.
*    Reads in a file to set it up
*    All classes progenate from here
*    GUI and Logic classes rely on it.
**********************************************
*   After the war class is initialized,
*      the players need to be set in this class from the war class
**********************************************/
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Map{
  Grid grid;
  Player p1;
  Player p2;
  Player p3;
  Player p4;
  int numPlayers = 0;
  int dimensions;
  int gridSize = 500;
  int shiftX;
  int shiftY;
  final int DimensionShift = 1;

  Map(int columns, int rows){
    grid = new Grid(columns,rows);
    if(rows >= columns)
        dimensions = rows + DimensionShift;
    else
        dimensions = columns + DimensionShift;
    if(columns >= 15)     shiftX = 4;
    else if(columns >=10) shiftX = 3;
    else if(columns >= 5) shiftX = 2;
    else                  shiftX = 1;
    if(rows >= 15)        shiftY = 1;
    else if(rows >= 10)   shiftY = 2;
    else                  shiftY = 1;
  }
  /*********************************************
  * Takes a txt for the grid layout.
  * Easy to use, but it only works for one grid size and a specific color
  * Unless I say if(key == 99) setColor(play1.Color)
  * Need to account for team color and guardblocks
  **********************************************/
    public void textToGrid(String file){
      int bColor; //block color
       int bStatus;//block status
      String temp;
      try{
        Scanner scan = new Scanner(new File(file));
        scan.useDelimiter("\\s*|\\s*");
        for(int rw = 0; rw < grid.getRows(); rw++){
          for(int col = 0; col < grid.getColumns(); col++){
            bColor = scan.nextInt();
            switch(bColor){
              case 0: grid.getCell(col,rw).setColor(100); break;
              case 1: grid.getCell(col,rw).setColor(p1.getColor()); break;
              case 2: grid.getCell(col,rw).setColor(p2.getColor()); break;
              case 3: grid.getCell(col,rw).setColor(p3.getColor()); break;
              case 4: grid.getCell(col,rw).setColor(p4.getColor()); break;
            }
            temp = scan.next();
            bStatus = scan.nextInt();
            grid.getCell(col,rw).setStatus(bStatus);
            if(bStatus == 5){
              grid.getCell(col,rw).setPrevColor(100);
              grid.getCell(col,rw).setPrevStatus(1);
            }
            if(bStatus == 0){//deadBlock
                grid.getCell(col,rw).setColor(-1);
            }
          }
        }
        scan.close();
      }
      catch(FileNotFoundException e){}
    }
  /*********************************************
  * Dimensions are 1:1 Square Field
  * P1 in lower left corner, P2 in upper right corner
  * P3 in upper left corner, P4 in lower right corner
  * Each have 2 spawn block, 2 GuardBlocks, 2 objectiveBlocks
  * No special blocks, the rest is Neutral
  **********************************************/
  public void basicfield(int size){
      if(numPlayers == 2){

      }
      else if(numPlayers == 4){

      }
  }
  /*********************************************
  * Player Setters
  **********************************************/
  public void setP1(Player player){
    p1 = player;
    numPlayers++;
  }
  public void setP2(Player player){
    p2 = player;
    numPlayers++;
  }
  public void setP3(Player player){
    p3 = player;
    numPlayers++;
  }
  public void setP4(Player player){
    p4 = player;
    numPlayers++;
  }
  /*********************************************
  * GETTERS
  **********************************************/
  public int getRows(){
    return grid.getRows();
  }
  public int getColumns(){
    return grid.getColumns();
  }
  public Grid getGrid(){
    return grid;
  }
  public int getDimensions(){
    return dimensions;
  }
  public int getGridSize(){
    return gridSize;
  }
  public int getShiftX(){
    return shiftX;
  }
  public int getShiftY(){
    return shiftY;
  }

}
