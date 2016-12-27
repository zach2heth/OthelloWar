/**********************************************
* Zachary Heth 2016
**********************************************
* GGrid Class
*     GUI for the grid
*     Creates 2D Array of GBlocks
**********************************************/

import java.awt.Color;
public class GGrid{
//I need access to the Grid so that I can connect each block to each one in here
//This will be done via Action.getGrid();
    Block selectedBlock;/*do I need multiple selectedBlocks. should it be in GAction?
                          How about a 2d array that holds the selected blocks, all else is null*/
    Grid grid; //The logic grid
    GBlock gGrid[][]; //the GUI grid
    /*********************************************
    * Creates the gGrid with the same Dimensions as the grid
    **********************************************/
    GGrid(Grid grd, int Dimensions,int gridSize, int shiftX, int shiftY){
      grid = grd;
      gGrid = new GBlock[grid.getColumns()][grid.getRows()];
      for(int y = 0; y < grid.getRows(); y++){
        for(int x = 0; x < grid.getColumns(); x++){
          gGrid[x][y] = new GBlock(Dimensions, gridSize, grid.getCell(x,y), shiftX, shiftY);
        }
      }
    }//end of constructor

    /**************************************************
    * Returns the block that posx/posy are inside of on the screen
    ***************************************************/
    public Block checkIfInsideBlock(int posx, int posy){
      Block block = new Block(-1,-1);
      for(int y = 0; y < grid.getRows(); y++){
        for(int x = 0; x < grid.getColumns(); x++){
          if(gGrid[x][y].insideBlock(posx, posy)){
            block = gGrid[x][y].getBlock();
            //System.out.print("Coord: "+x+" "+y+" ");
          }//end of if
        }//end of for x
      }//end of for y
      return block;
    }//end of checkIfInsideBlock
    /**********************************************
    * When mouse clicked, check what block it is
    ***********************************************/
    public Block selectBlock(int posx, int posy, Color color){
      Block block = new Block(-1,-1);
    //  if(EZInteraction.wasMouseLeftButtonPressed()){
      block = checkIfInsideBlock(posx,posy);
      if(block.getRow() != -1){
        gGrid[block.getColumn()][block.getRow()].changeLineColor(color);
      }
      return block;
    }//end of selectBlock
    public boolean selected(int posx, int posy, Color color){
        boolean blockNotNull = false;
        Block block = selectBlock(posx, posy, color);
        if(block.getRow() != -1){
            blockNotNull = true;
        }
        return blockNotNull;
    }
    /*********************************************
    * this method is iffy. Need to think it over
    **********************************************/
    public void deselectBlock(Block block, int posx, int posy, Color color){
      if(selectedBlock == block){
        selectedBlock = null;
        gGrid[block.getColumn()][block.getRow()].changeLineColor(color);
      }
      else{
        selectBlock(posx, posy, color);
      }
    }//end of deselectBlock
    /*********************************************
    * GETTERS
    **********************************************/
    public Grid getGrid(){
      return grid;
    }
    public GBlock[][] getGGrid(){
      return gGrid;
    }
    public Block getSelectedBlock(){
      return selectedBlock;
    }

    public void flashyTest(){
        for(int y = 0; y < grid.getRows(); y++){
              for(int x = 0; x < grid.getColumns(); x++){
                  gGrid[x][y].hideBlock();
                  try {Thread.sleep(2);} catch (InterruptedException e) {e.printStackTrace();};
                  gGrid[x][y].showBlock();
              }

        }
    }
}//end of class
