/**********************************************
* Zachary Heth 2016
**********************************************
* Grid class
*     Creates a 2D Array of Blocks
*     Sets each block's relation to one another
**********************************************/
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
public class Grid {

  Block[][] grid;
  int rows; // y-coordinate
  int columns; //x -coordinate
  public Grid(int col, int rw){
    grid = new Block[col][rw];
    rows = rw;
    columns = col;
    createBlocks();
    setPointers();
  }
  /*********************************************
  * Return member variables
  **********************************************/
  public Block getCell(int col, int rw){
    return grid[col][rw];
  }
  public int getRows(){
    return rows;
  }
  public int getColumns(){
    return columns;
  }
  /*********************************************
  * Returns a queue of all blocks that are (north) of a given starting block
  **********************************************/
  public Queue<Block> queueNorth(Block startBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    Queue<Block> que = new Queue<Block>();
    for(int rw = y;rw >= 0;rw--){
      que.offer(grid[x][rw]);
    }
    return que;
  }
  public Queue<Block> queueEast(Block startBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    Queue<Block> que = new Queue<Block>();
    for(int col = x;col < columns; col++){
      que.offer(grid[col][y]);
    }
    return que;
  }
  public Queue<Block> queueSouth(Block startBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    Queue<Block> que = new Queue<Block>();
    for(int rw = y; rw < rows; rw++){
      que.offer(grid[x][rw]);
    }
    return que;
  }
  public Queue<Block> queueWest(Block startBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    Queue<Block> que = new Queue<Block>();
    for(int col = x;col >= 0; col--){
      que.offer(grid[col][y]);
    }
    return que;
  }
  /*********************************************
  * Returns a queue of blocks between a given start and end point
  **********************************************/
  public Queue<Block> queueNorth(Block startBlock, Block endBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    int y2 = endBlock.getRow();
    Queue<Block> que = new Queue<Block>();
    for(int rw = y;rw >= y2;rw--){
      que.offer(grid[x][rw]);
    }
    return que;
  }
  public Queue<Block> queueEast(Block startBlock, Block endBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    int x2 = endBlock.getColumn();
    Queue<Block> que = new Queue<Block>();
    for(int col = x;col <= x2; col++){
      que.offer(grid[col][y]);
    }
    return que;
  }
  public Queue<Block> queueSouth(Block startBlock, Block endBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    int y2 = endBlock.getRow();
    Queue<Block> que = new Queue<Block>();
    for(int rw = y; rw <= y2; rw++){
      que.offer(grid[x][rw]);
    }
    return que;
  }
  public Queue<Block> queueWest(Block startBlock, Block endBlock){
    int x = startBlock.getColumn();
    int y = startBlock.getRow();
    int x2 = endBlock.getColumn();
    Queue<Block> que = new Queue<Block>();
    for(int col = x;col >= x2; col--){
      que.offer(grid[col][y]);
    }
    return que;
  }
/*********************************************
* Used for testing without a GUI. Takes a txt for the grid layout
* OLD VERSION. BETTER VERSION IN MAP CLASS
**********************************************/
  public void textToGrid(String file){
    try{
      Scanner scan = new Scanner(new File(file));
      for(int rw = 0; rw < rows; rw++){
        for(int col = 0; col < columns; col++){
          grid[col][rw].setStatus(scan.nextInt());
        }
      }
    }
    catch(FileNotFoundException e){}
  }
/*********************************************
* Prints key numbers of each block in the grid
**********************************************/
//To print if a block has a unit on it,
//First, use two spaces between each block normally
//If there is a unit, print a second number and then only put one space
  public void printGrid(){
    for(int rw = 0; rw < rows; rw++){
      for(int col = 0; col < columns; col++){
        if(grid[col][rw].getVacant()){
          System.out.print(grid[col][rw].getStatus()+"   ");
        }
        else if(!grid[col][rw].getVacant()){
          System.out.print(grid[col][rw].getStatus()+"U  ");
          //+grid[col][rw].getUnit().getColor()+" ");
        }
        if(col == columns - 1){System.out.println();}
      }
    }
  }
/*********************************************
* All methods below are associated with setting up the Grid
**********************************************/
  public void createBlocks(){
    for(int rw = 0;rw < rows; rw++){
      for(int col = 0;col < columns; col++){
        grid[col][rw] = new Block(col,rw);
      }
    }
  }
/*********************************************
* Set N E S W variables for all blocks in Grid
**********************************************/
  public void setPointers(){
    for(int rw = 0;rw < rows; rw++){
      for(int col = 0;col < columns; col++){
        if(rw == 0){
          if(col == 0){
            grid[col][rw].setE(grid[col+1][rw]);
            grid[col][rw].setS(grid[col][rw+1]);
          }
          else if(col == columns - 1){
            grid[col][rw].setS(grid[col][rw+1]);
            grid[col][rw].setW(grid[col-1][rw]);
          }
          else{
            grid[col][rw].setE(grid[col+1][rw]);
            grid[col][rw].setS(grid[col][rw+1]);
            grid[col][rw].setW(grid[col-1][rw]);
          }
        }//if rw == 0
        else if(rw == rows - 1){
          if(col == 0){
            grid[col][rw].setN(grid[col][rw-1]);
            grid[col][rw].setE(grid[col+1][rw]);
          }
          else if(col == columns - 1){
            grid[col][rw].setN(grid[col][rw-1]);
            grid[col][rw].setW(grid[col-1][rw]);
          }
          else{
            grid[col][rw].setN(grid[col][rw-1]);
            grid[col][rw].setE(grid[col+1][rw]);
            grid[col][rw].setW(grid[col-1][rw]);
          }
        }//if rw == rows - 1
        else{
          if(col == 0){
            grid[col][rw].setN(grid[col][rw-1]);
            grid[col][rw].setE(grid[col+1][rw]);
            grid[col][rw].setS(grid[col][rw+1]);
          }
          else if(col == columns - 1){
            grid[col][rw].setN(grid[col][rw-1]);
            grid[col][rw].setS(grid[col][rw+1]);
            grid[col][rw].setW(grid[col-1][rw]);
          }
          else{
            grid[col][rw].setN(grid[col][rw-1]);
            grid[col][rw].setE(grid[col+1][rw]);
            grid[col][rw].setS(grid[col][rw+1]);
            grid[col][rw].setW(grid[col-1][rw]);
          }
        }
      } //end of col loop
    } //end of rw loop
  }

}
