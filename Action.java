/**********************************************
* Zachary Heth 2016
**********************************************
* Action Class
*     All "actions" that can occur in the game are handled in this class
*     It contains the logic of the main game mechanics
*     All methods in actions class require a Player as the first parameter
**********************************************/

public class Action {
  Grid grid;
  Block capturedBlock;
  private int debug = 0; //Set to 1 - FOR TESTING PURPOSES

  public Action(int col, int rw){
     grid = new Grid(col,rw);
  }
  public Action(Grid g){
     grid = g;
  }//end of constructor
  /*********************************************
  * GETTERS
  **********************************************/
  public Grid getGrid(){
    return grid; //is this method neccessary? YES!
  }
  public Block getCapturedBlock(){
    return capturedBlock;
  }
  /*********************************************
  * Return true if able to use colorChange on the given block
  **********************************************/
  public boolean checkColorChange(Player player, Block startBlock){
    boolean allowed = false;
    if(player.getCC()
       && (startBlock.getStatus() == 1
       || (startBlock.getStatus() == 2 && startBlock.getColor() == player.getColor())))
    {
                allowed = true;
    }
    return allowed;
  }
/*********************************************
* Return a list of blocks affected by the ColorChange
* Does not actually change their color or status
**********************************************
* If changed.add(startBlock) is not in the code
*     A colorChange that only affect 1 block, can't occur.
*     That is not neccessarily bad. It could be an interesting feature,
*     gives more meaning to pavers and cleaners
**********************************************/
  public SLL<Block> colorChange(Player player, Block startBlock ){
    SLL<Block> changedBlocks = new SLL<Block>();
    Block nullBlock = new Block(-1,-1);
  //  changedBlocks.add(nullBlock); //to verify if colorchange was success or not
    if(debug==1)
      System.out.println("Inside colorChange");
    if(checkColorChange(player, startBlock)){
      changedBlocks.removeElement(nullBlock);
    //  changedBlocks.add(startBlock); //REMOVING THIS LINE MEANS THAT 1 BLOCK COLOR CHANGES WILL NOT OCCUR
      Queue<Block> queN = grid.queueNorth(startBlock); queN.poll(); //polling to remove the startblock from the queus
      Queue<Block> queE = grid.queueEast(startBlock); queE.poll();
      Queue<Block> queS = grid.queueSouth(startBlock); queS.poll();
      Queue<Block> queW = grid.queueWest(startBlock); queW.poll();
      //Make sure to deal with if the queue is empty
      boolean cc = true;
      boolean ccN = false, ccE = false, ccS = false, ccW = false;
      //AboveVariableName: colorChangeNorth, colorChangeEast...
      //CHECKS ALL DIRECTIONS FOR FIRST OCCURENCE OF SAME COLORED CB OR CC STOPPING BLOCK
      while(cc){
        if(debug==1)
          System.out.println("while(cc)");
        boolean n = true, e = true, s = true, w = true;//is there a reason its in the loop?
        //CHECKS FOR FIRST OCCURENCE OF NON-NEUTRAL OR SAME COLORED BLOCK IN EACH DIRECTION
        if(!ccN && n && queN.isFull()){
          if(queN.peek().getStatus() == 1){
            queN.poll();
            if(debug==1)
              System.out.println("North == 1");
          }
          else if(queN.peek().getStatus() == 2){
            if(queN.peek().getColor() == player.getColor()){
              queN = grid.queueNorth(startBlock, queN.peek());
              ccN = true;
              if(debug==1)
                System.out.println("North == 2");
            }
            else{
              queN.poll();
            }
          }// end of getStatus == 2
          else{
            n = false;
          }
        } // end of isFull
        else{
        n = false;
       }
       if(!ccE && e && queE.isFull()){
         if(queE.peek().getStatus() == 1){
           queE.poll();
           if(debug==1)
             System.out.println("East == 1");
         }
         else if(queE.peek().getStatus() == 2){
           if(queE.peek().getColor() == player.getColor()){
             queE = grid.queueEast(startBlock, queE.peek());
             ccE = true;
             if(debug==1)
               System.out.println("East == 2 YesColor");
           }
           else{
             queE.poll();
             if(debug==1)
               System.out.println("East == 2 NoColor");
           }
         }// end of getStatus == 2
         else{
           e = false;
         }
       } // end of isFull
       else{
       e = false;
      }
        if(!ccS && s && queS.isFull()){
          if(queS.peek().getStatus() == 1){
            queS.poll();
          }
          else if(queS.peek().getStatus() == 2){
            if(queS.peek().getColor() == player.getColor()){
              queS = grid.queueSouth(startBlock, queS.peek());
              ccS = true;
            }
            else{
              queS.poll();
            }
          } //end of getStatus == 2
          else{
            s = false;
          }
        }// end of isFull
        else{
        s = false;
       }
        if(!ccW && w && queW.isFull()){
          if(queW.peek().getStatus() == 1){
            queW.poll();
          }
          else if(queW.peek().getStatus() == 2){
            if(queW.peek().getColor() == player.getColor()){
              queW = grid.queueWest(startBlock, queW.peek());
              ccW = true;
            }
            else{
              queW.poll();
            }
          }// end of getStatus == 2
          else{
            w = false;
          }
        } // end of isFull
        else{
        w = false;
       }
       if(!n && !e && !s && !w){
         cc = false;
       }
      }//end of while(cc)

      //THIS IS WHERE THE COLOR CHANGE OCCURS
      cc = true;
      if(debug==1)
        System.out.println("CHANGING COLORS");
      while(cc){
        if(debug==1)
          System.out.println("while(cc)");
        if(ccN){
          if(queN.getSize() == 1){ //Gets rid of end point, that you don't want to overwrite
            queN.poll();
            ccN = false;
            if(debug==1)
              System.out.println("Remove Last");
          }
          else{
            changedBlocks.add(0,queN.peek());
            queN.poll();
            if(debug==1)
              System.out.println("SetNorthColor");
          }
        }
        if(ccE){
          if(queE.getSize() == 1){
            queE.poll();
            ccE = false;
          }
          else{
            changedBlocks.add(0,queE.peek());
            queE.poll();
          }
        }
        if(ccS){
          if(queS.getSize() == 1){
            queS.poll();
            ccS = false;
          }
          else{
            changedBlocks.add(0,queS.peek());
            queS.poll();
          }
        }
        if(ccW){
          if(queW.getSize() == 1){
            queW.poll();
            ccW = false;
          }
          else{
            changedBlocks.add(0,queW.peek());
            queW.poll();
          }
        }
        if(!ccN && !ccE && !ccS && !ccW){
          cc = false;
        }
      }//end of while(cc))
    }//end of if startBlock.getStatus == 1 || 2
    changedBlocks = uniqueBlocks(changedBlocks);
    return changedBlocks;
  }
  /*********************************************
  * Removes the repetitive blocks within the changed blocks recieved from Color Change
  * Basically this prevents false color changes from being allowed
  * If three red blocks are in a line and I click on the middle blocks
  *   nothing is supposed to happen
  *   This method ensure that nothing happens
  * If you don't beleive me, comment out this ffrom the end of color change and test it out
  *  What will happen is you burn your color change without actually changing anything
  **********************************************/
  public SLL<Block> uniqueBlocks(SLL<Block> changed){
    SLL<Block> unique = new SLL<Block>();
    for(int k = 0; k < changed.size(); k++){
      if(unique.size() != 0){
        for(int i = 0; i < unique.size(); i++){
          if(changed.get(k) != unique.get(i)){
            unique.add(changed.get(k));
          }
        }
      }
      else{
        unique.add(changed.get(k));
      }
    }
    return unique;
  }
  /*********************************************
  * To be used with colorChange as input parameter
  * Updates the color and status of the blocks
  * Destoys Units, determines if player's cc was valid or not
  **********************************************/
  public boolean colorChangeUpdate(Player player, SLL<Block> changed){
    boolean success = false;
    if(changed.size() > 1){ //&& changed.get(0).getRow() != -1){
      success = true;
      player.useCC();
    }
    if(success){
      while(changed.size() != 0){//nothing was changed will make it false
        changed.get(0).setColor(player.getColor());
        changed.get(0).setStatus(2);
        if(!changed.get(0).getVacant()){
          if(changed.get(0).getUnit().getColor() != player.getColor()){
            changed.get(0).getUnit().destroy();
          }
        }
        changed.removeIndex(0);
      }//end of while
    }
    return success;
  }
  /*********************************************
  * returns true if successfully moved, false otherwise
  **********************************************/
  public boolean moveGuardBlock(Player player, Block startBlock, Block endBlock){
    boolean moved = false;
    if(player.getMoveGB() && startBlock.getStatus() == 5 && player.getColor() == startBlock.getColor()){
      if((endBlock.getStatus() == 1 || (endBlock.getStatus() == 2 && endBlock.getColor() == player.getColor()))
        && endBlock.getVacant()){
        startBlock.setStatus(startBlock.getPrevStatus()); //Returns the startBlock to its state prior to having a GB
        startBlock.setColor(startBlock.getPrevColor());
        endBlock.setPrevStatus(endBlock.getStatus());
        endBlock.setPrevColor(endBlock.getColor());
        endBlock.setStatus(5);
        endBlock.setColor(player.getColor());
        player.useGB();
        moved = true;
      }
    }
    return moved;
  }
  public Unit chooseUnit(int unitKey, Block spawnBlock, int clr){
    Unit unit = null;
    switch(unitKey){
      case 1: unit = new Fighter(spawnBlock,clr); break;
      case 2: unit = new Paver(spawnBlock,clr); break;
      case 3: unit = new Cleaner(spawnBlock,clr); break;
      case 4: unit = new Shooter(spawnBlock, clr); break;
    }
    return unit;
  }
  public boolean spawnUnit(Player player, Block spawnBlock, int unitKey){
    boolean spawned = false;
    if(player.getSpawn() != 0 && spawnBlock.getStatus() == 3 && spawnBlock.getVacant()){
      Unit unit = chooseUnit(unitKey, spawnBlock, player.getColor());
      spawnBlock.setUnit(unit);
      player.useSpawn(unit);
      spawned = true;
    }
    return spawned;
  }
  /*********************************************
  * In the future new block types may be implemented. Thus this will have be updated
  * Try keep it generic. Or add special move conditions later like for teleportation blocks
  **********************************************/
  public boolean moveCondition(Player player, Block block){
      boolean okToMove = false;
      if(player.getColor() == block.getColor()){
          if(block.getStatus() >= 2 && block.getStatus() <= 4)
              okToMove = true;
      }
      else if(block.getStatus() >= 6)
          okToMove = true;
      return okToMove;
  }
  /*********************************************
  * Returns a 2d array of blocks where
  * Row 0 is North, Row 1 is East, Row 2 is South, Row 3 is West of start block
  **********************************************/
  public Block[][] moveOptions(Player player, Unit unit){
    Block[][] options;
    int size;
    if(grid.getColumns() > grid.getRows()){
      options = new Block[grid.getColumns()][4];
      size = grid.getColumns();
    }
    else{
      options = new Block[grid.getRows()][4];
      size = grid.getRows();
    }
    if(unit.getMove()){
      Block start = unit.getLocation();
      Block currN = start, currE = start, currS = start, currW = start;
      Block nullBlock = new Block(-1,-1);
      int currNum = 0; //incremented at the end      //WHAT IF IT'S NULL | what are you talking about?
      boolean checkN = true, checkE = true, checkS = true, checkW = true;
      while(currNum < size){ //
        if(!checkN) options[currNum][0] = nullBlock;
        if(!checkE) options[currNum][1] = nullBlock;
        if(!checkS) options[currNum][2] = nullBlock;
        if(!checkW) options[currNum][3] = nullBlock;

        if(moveCondition(player,currN) && checkN){
          if(currN.getVacant()){
if(debug == 1)
System.out.println("currN.getVacant == true"+" currN: "+currN.getRow());
              options[currNum][0] = currN;
          }
          else{
  if(debug == 1)
  System.out.println("currN.getVacant == false");
              options[currNum][0] = nullBlock;
          }
          if(currN.hasN()){
if(debug == 1)
System.out.println("HasNorth == true");
            currN = currN.getN();
          }
          else{
            checkN = false;
          }
        }//end of moveCondition && checkN
        else{
          checkN = false;
        }//end of checking North
        if(moveCondition(player,currE) && checkE){
          if(currE.getVacant()){
if(debug == 1)
System.out.println("currE.getVacant == true");
              options[currNum][1] = currE;
          }
          else{
              options[currNum][1] = nullBlock;
          }
          if(currE.hasE()){
if(debug == 1)
System.out.println("HasEast == true");
            currE = currE.getE();
          }
          else{
            checkE = false;
          }
        }else{
          checkE = false;
        }//end of checking East

        if(moveCondition(player,currS) && checkS){
          if(currS.getVacant()){
              options[currNum][2] = currS;
          }
          else{
              options[currNum][2] = nullBlock;
          }
          if(currS.hasS()){
            currS = currS.getS();
          }
          else{
            checkS = false;
          }
        }
        else{
          checkS = false;
        }//end of checking South
        if(moveCondition(player,currW) && checkW){
          if(currW.getVacant()){
              options[currNum][3] = currW;
          }
          else{
              options[currNum][3] = nullBlock;
          }
          if(currW.hasW()){
            currW = currW.getW();
          }
          else{
            checkW = false;
          }
        }
        else{
          checkW = false;
        }//end of checking West
        currNum++;
      }//end of while currNum < size
    }//end of if unit.getMove
    return options;
  }//end of moveOptions
  /*********************************************
  * Uses moveOptions and checks if the destination is within a valid move option
  * could be more efficient if you pass options as a parameter
  * The user will first see the options, so the method will have to be called once
  * If you save the options from that first call (in the GUI) you can pass it in here
  **********************************************/
  public boolean checkMove(Player player, Unit unit, Block destination){
      Block[][] options = moveOptions(player, unit);
      int size;
      boolean validMove = false;
      if(grid.getColumns() > grid.getRows()){
          size = grid.getColumns();
      }
      else{
          size = grid.getRows();
      }
      for(int y = 0; y < 4; y++){
          for(int x = 0; x < size; x++){
              if(destination == options[x][y]){
                  validMove = true;
              }
          }
      }
      return validMove;
  }//end of checkMove
  /*********************************************
  * Uses checkMove, which uses move options, to move the unit to a specified block
  **********************************************/
  public boolean moveUnit(Player player, Unit unit, Block destination){
    boolean moved = false;
    if(checkMove(player,unit,destination)){
        if(unit.getColor() == player.getColor() && unit.getMove()){
            unit.getLocation().setUnit(null);
            unit.setLocation(destination);
            destination.setUnit(unit);
            unit.useMove();
            moved = true;
      }
    }
    return moved;
  }//end of moveUnit
/*********************************************
* Checks if an block capture request is valid
**********************************************/
  public boolean checkCapture(Block captBlock, Unit unit, int blockType){
    boolean confirmed = false;
    if(captBlock.getStatus() == blockType){
      if(unit.getLocation().checkAdjacent(captBlock)){
        if(captBlock.getVacant()){
          confirmed = true;
        }
      }
    }
    return confirmed;
  }//end of checkCapture
  /*********************************************
  * Changes into DeadBlock or changes color
  **********************************************/
  public boolean captureObjective(Player player, SLL<Player> targetedPlayer, Block objBlock, Unit unit, int blockFate){
    boolean captured = false;
    if(checkCapture(objBlock,unit,4)){
        captured = true;
        capturedBlock = objBlock;
        Player target = objectiveCaptured(targetedPlayer);
        switch(blockFate){
            case 1: objBlock.setStatus(0);
                    objBlock.setColor(100);
                    break; //change into DeadBlock
            case 2: objBlock.setColor(player.getColor());
                    player.addObjBlock(objBlock);
                    break; //change into player's ObjB
        }//end of switch(blockFate)
        target.removeObjBlock(objBlock);
        target.checkObjBlocks();
    }
    return captured;
  }//end of captureObjective
  /*********************************************
  * Returns the player whose objective block just got captured
  * Relies on the member variable captured block,
  *     which is set within capture objective
  **********************************************/
  public Player objectiveCaptured(SLL<Player> list){
    Player p = new Player(-1,-1);
    while(list.size() > 0){
      if(list.get(0).getColor() == capturedBlock.getColor()){
        p = list.get(0);
      }
        list.removeIndex(0);
    }
    return p;
  }//end of objectiveCaptured
  /*********************************************
  * If a player is defeated, removes all of their blocks/units
  * Changes them to neutral color
  * Returns all blocks that were affected
  **********************************************/
  public SLL<Block> destroyAllFromPlayer(Player player){
      SLL<Block> blocks = new SLL<Block>();
      player.selfDestruct();
      return blocks;
  }
} //end of Action Class
