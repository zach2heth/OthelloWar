/**********************************************
* Zachary Heth 2016
**********************************************
* Gwar Class
*   Determines the user's input and corresponds it to a method call in the War class
*   It then updates the graphics to match the change in the logic grid
**********************************************/

import java.awt.Color;
public class GWar{

  War war;
  Action action;
  GAction gAction;

  Block selectedBlock;
  Block selectedBlock2;
  GPlayer gplay1;
  GPlayer gplay2;
  GPlayer gplay3;
  GPlayer gplay4;
  GWarInterface gwarInter;
  /*********************************************
  * Boolean to track current options available with the given block selected
  **********************************************/
  boolean displayCC; // Color Change
  boolean displayUnitMO; // Unit Move Options
  boolean displayUnitSO; // Unit Skill Options (what blocks it can do it on)
  boolean displaySpawn; // Spawn a Unit
  boolean displayCapture;
  boolean displayGB; // Move Guard Block

  public GWar(War w){
      war = w;
      action = war.getAction();
      gAction = new GAction(war.getMap(),action);
      selectedBlock = gAction.getGBlock()[0][0].getBlock(); //prevents a null exception
      selectedBlock2 = gAction.getGBlock()[0][0].getBlock();
      gplay1 = new GPlayer(war.getP1());
      gplay2 = new GPlayer(war.getP2());
      if(war.getnumPlayers() >= 3) gplay3 = new GPlayer(war.getP3());
      if(war.getnumPlayers() == 4) gplay4 = new GPlayer(war.getP4());
      gwarInter = new GWarInterface(this);
  }//end of constructor
  /***********************************************************
  * After selecting a block, determines what actions can be taken from there
  * uses other methods which provide dropdown menus
  * Simply toggles what options are available given the block selected, does not call any methods
  * In main, if mouse if clicked, pass in the mouse's x and y coordinate
  * PROBLEMS/WHAT I want
  *   User can double click for colorChange. First click maybe gives preview of change
  *   Double click on same block will deselect option, go back to normal state
  *   Unit options will be available on sideORbottom of screen
  /**********************************************************/

  public void displayOptions(int posx,int posy){
    Player player = war.currTurn();
    cancelSelectedBlock(selectedBlock);
    cancelSelectedBlock(selectedBlock2);
    selectedBlock = selectABlock(posx, posy);
    Block block = selectedBlock;
    if(block.getRow() != -1){
        switch(block.getStatus()){
          case 1: //give colorChangeOption
              if(player.getCC()){
                    displayCC = true;
                }
                break;
          case 2: //give colorChangeOption or moveUnit option if available
                if(player.getCC() && block.getColor() == player.getColor()){
                    displayCC = true;
                }
                case_HasUnit(player,block);
                break;
          case 3: //give spawnUnitOption and if has unit, give unit options
                case_HasUnit(player,block);
                if(block.getVacant() && player.getSpawn() > 0 && block.getColor() == player.getColor()){
                    displaySpawn = true;
                }
                break;
          case 4: //if has unit, give unit options
                  case_HasUnit(player,block);
                  break;
          case 5: //give moveGuardBlockOption
                if(player.getMoveGB() && block.getColor() == player.getColor()){
                    displayGB = true;
                }
                break;
        }//end of switch block status
    }//end of if selectedBlock not null
    else if(gwarInter.checkInsideUnitBox(posx,posy)){
        gwarInter.displaySelectedUnitBox();
    }
    else if(gwarInter.checkInsideObjectiveBox(posx,posy)){
       gwarInter.displaySelectedObjectiveBox();
    }
  }//end of displayOptions

  /*********************************************
  * If the block has a unit, here is what must be done/checked for
  **********************************************/
  public void case_HasUnit(Player player, Block block){
    if(!block.getVacant() && block.getColor() == player.getColor()){
        if(block.getUnit().getMove()){
            displayUnitMO = true;
            Block options[][] = action.moveOptions(player, block.getUnit());
            gAction.displayMoveOptions(options);
        }   //give moveOptions, outlining them all
        if(block.getUnit().getSkill()){
            displayUnitSO = true;
            gAction.displayUnitSkillOptions(getBlockGBlock(selectedBlock).getGUnit());
        }
        gAction.displayObjBlockCaptureOptions(getBlockGBlock(selectedBlock).getGUnit());
        displayCapture = true;
    }
  }//end case_HasUnit
  /*********************************************
  * Run this method after calling displayOptions
  * This is sort of like the MASTER METHOD
  * It calls the logic methods, and if they pass through and return true
  *     then the graphic methods will be called as well.
  * Pass in the x and y coordinate of the mouse if the mouse was clicked
  **********************************************/
  public void chooseOption(int posx, int posy){
    Block nullBlock = new Block(-1,-1);
    Unit nullUnit = null;
     if(displayCC){
         //check if mouse inside Color change option text thing
         if(gAction.getGGrid().checkIfInsideBlock(posx, posy) == selectedBlock){
                war.game(1,0,selectedBlock,nullBlock,nullUnit);
                gAction.displayColorChange(war.getChangedList());
                updateAllUnitLists();
         }
     }
     if(displayUnitMO){
          selectedBlock2 = gAction.checkInsideMoveOptions(posx, posy);
          gAction.cancelMoveOptions();
          if(!EZInteraction.isKeyDown(' ')
          && war.game(3,3,selectedBlock, selectedBlock2, selectedBlock.getUnit())){
              displayUnitMove(getBlockGBlock(selectedBlock),getBlockGBlock(selectedBlock2));
              displayUnitSO = false;
              displayCapture = false;
              gAction.cancelUnitSkill();
              gAction.cancelObjBlockCapture();
          }

          //display unit actually moving
          //if next to an objective block after moving, or even before moving, display capture options
     }
     if(displayUnitSO){
           selectedBlock2 = gAction.checkInsideUnitSkill(posx, posy);
           gAction.cancelUnitSkill();
           if(war.game(3,4,selectedBlock,selectedBlock2,selectedBlock.getUnit()) == true){
                displayUnitSkill(getBlockGBlock(selectedBlock));
                displayCapture = false;
                gAction.cancelObjBlockCapture();
           }
     }
     if(displayCapture){
          selectedBlock2 = gAction.checkInsideObjBlockCapture(posx,posy);
          gAction.cancelObjBlockCapture();

            if(gwarInter.getDestroyObjective())
              if(war.game(3,1,selectedBlock,selectedBlock2,selectedBlock.getUnit()))
                  gAction.displayCaptureObjective(getBlockGBlock(war.getAction().getCapturedBlock()));

           if(gwarInter.getCaptureObjective())
              if(war.game(3,2,selectedBlock,selectedBlock2,selectedBlock.getUnit()))
                  gAction.displayCaptureObjective(getBlockGBlock(war.getAction().getCapturedBlock()));
     }
     if(displaySpawn){
          if(gwarInter.getSpawnFighter()){
              if(war.game(2,1,selectedBlock,nullBlock,nullUnit))
                  displaySpawnUnit(getBlockGBlock(selectedBlock));
          }
          if(gwarInter.getSpawnPaver()){
              if(war.game(2,2,selectedBlock,nullBlock,nullUnit))
                  displaySpawnUnit(getBlockGBlock(selectedBlock));
          }
          if(gwarInter.getSpawnCleaner()){
              if(war.game(2,3,selectedBlock,nullBlock,nullUnit))
                  displaySpawnUnit(getBlockGBlock(selectedBlock));
          }
        /*
        if click on shooter
            war.game(2,4,selectedBlock,nullBlock,nullUnit);
         */
     }

     if(displayGB){
           selectedBlock2 = selectABlock(posx, posy);
           if(war.game(4,0,selectedBlock,selectedBlock2,nullUnit) == true){
                gAction.displayMoveGuardBlock(war.currTurn(),selectedBlock, selectedBlock2);
           }
     }
     if(gwarInter.checkInsideEndTurnBox(posx,posy)){
           war.game(5,0,nullBlock,nullBlock,nullUnit);
     }
     resetBooleans();
  }//end of chooseOption

  /*********************************************
  * Moves the GUnit on the screen from start to dest
  **********************************************/
  public void displayUnitMove(GBlock start, GBlock dest){
    GUnit gUnit = start.getGUnit();
    gUnit.moveImage(start,dest);
  }
  /*********************************************
  * Diplays the Unit skill animation thing, updates the graphics and all
  * Not too important. Just for extra flash
  **********************************************/
  public void displayUnitSkill(GBlock gBlock){
      GUnit gUnit = gBlock.getGUnit();
      switch(gUnit.getUnit().getUnitKey()){
         case 1: GFighter gF = (GFighter) gUnit;
                 gF.gFight(getBlockGBlock(selectedBlock2));
                 updateAllUnitLists();
                 break;
         case 2: GPaver gP = (GPaver) gUnit;
                 gP.gPave(getBlockGBlock(selectedBlock2),gAction.requestColor(gP.getUnit().getColor()));
                 break;
         case 3: GCleaner gC = (GCleaner) gUnit;
                 gC.gClean(getBlockGBlock(selectedBlock2),gAction.requestColor(100));
                 break;
      }//end of switch
  }//end of displayUnitSkill
  /*********************************************
  * Spawns the GUnit on the spawnBlock and does all the necessary assignments
  **********************************************/
  public void displaySpawnUnit(GBlock spawn){
      Block block = spawn.getBlock();
      Unit unit = spawn.getBlock().getUnit();
      switch(unit.getUnitKey()){
        case 1: GFighter gF = new GFighter((Fighter)unit,spawn);
                gF.createGFighter();
                currTurnG().addGUnit(gF);
                break;

        case 2: GPaver gP = new GPaver((Paver)unit,spawn);
                gP.createGPaver();
                currTurnG().addGUnit(gP);
                break;
        case 3: GCleaner gC = new GCleaner((Cleaner)unit,spawn);
                gC.createGCleaner();
                currTurnG().addGUnit(gC);
                break;

      }//en of switch
      //get unit from SpawnBlock
      //do a switch case so you know which type of GUnit to create
      //Assign
  }//end of displaySpawnUnit

  /*********************************************
  * Returns the block that was clicked at the passed in mouse coordinates
  **********************************************/
  public Block selectABlock(int posx, int posy){
    Block block = gAction.getGGrid().selectBlock(posx,posy,gAction.requestColor(0));
    return block;
  }//end of selectABlock
  /*********************************************
  * Changes line color of selectedBlock back to normal
  **********************************************/
  public void cancelSelectedBlock(Block block){
      if(block.getRow() != -1)
          getBlockGBlock(block).changeLineColor(gAction.requestColor(1));
  }//end of cancelSelectedBlock
  /*********************************************
  * Returns the GBlock associated with a block
  **********************************************/
  public GBlock getBlockGBlock(Block block){
     GBlock gb = null;
     if(block.getRow() != -1){
        gb = gAction.getGBlock()[block.getColumn()][block.getRow()];
     }
     return gb;
  }//end of getBlockGBlock

  /*********************************************
  * Returns GPlayer of player currently has their turn
  **********************************************/
  public GPlayer currTurnG(){
    Player nullPlay = new Player(-1,-1);
    GPlayer gplayer = new GPlayer(nullPlay);
    if(gplay1.getPlayer() == war.currTurn()){
       gplayer = gplay1;
    }
    else if(gplay2.getPlayer() == war.currTurn()){
       gplayer = gplay2;
    }
    else if(gplay3.getPlayer() == war.currTurn()){
       gplayer = gplay3;
    }
    else if(gplay4.getPlayer() == war.currTurn()){
       gplayer = gplay4;
    }
    return gplayer;
  }//end of currTurnG
  /*********************************************
  * Updates Logic and Graphic Unit Lists
  **********************************************/
  public void updateAllUnitLists(){
    SLL<GPlayer> playList = allGPlayers();//otherGPlayers();
      while(playList.size() != 0){
        playList.get(0).getPlayer().updateUnitList();
        playList.get(0).updateGUnitList();
        playList.removeIndex(0);
      }
  }//end of updateAllUnitLists
  public SLL<GPlayer> allGPlayers(){
    SLL<GPlayer> players = new SLL<GPlayer>();
    players.add(gplay1);
    players.add(gplay2);
    if(war.getnumPlayers() >= 3) players.add(gplay3);
    if(war.getnumPlayers() == 4) players.add(gplay4);
    return players;
  }
  /*********************************************
  * Returns the GPlayers who are not having their turn
  **********************************************/
  public SLL<GPlayer> otherGPlayers(){
    SLL<GPlayer> other = new SLL<GPlayer>();
    if(gplay1.getPlayer() == war.currTurn()){
        other.add(gplay2);
        if(war.getnumPlayers() >= 3) other.add(gplay3);
        if(war.getnumPlayers() == 4) other.add(gplay4);
    }
    else if(gplay2.getPlayer() == war.currTurn()){
        other.add(gplay1);
        if(war.getnumPlayers() >= 3) other.add(gplay3);
        if(war.getnumPlayers() == 4) other.add(gplay4);
    }
    else if(gplay3.getPlayer() == war.currTurn()){
        other.add(gplay2);
        other.add(gplay3);
        if(war.getnumPlayers() == 4) other.add(gplay4);
    }
  else if(gplay4.getPlayer() == war.currTurn()){
        other.add(gplay2);
        other.add(gplay3);
        other.add(gplay4);
    }
    return other;
  }
  /*********************************************
  * Resets the display booleans to false
  **********************************************/
  public void resetBooleans(){
     displayCC = false;
     displayUnitMO = false;
     displayUnitSO = false;
     displaySpawn = false;
     displayCapture = false;
     displayGB = false;
  }//end of resetBooleans
  /*********************************************
  * Calls method to update graphics of all blocks
  **********************************************/
  public void updateAfterInit(){
    gAction.updateEntireGrid();
  }
  /*********************************************
  * Getter Methods
  **********************************************/
  public Block getSelectedBlock(){
    return selectedBlock;
  }
  public Block getSelectedBlock2(){
    return selectedBlock2;
  }
  public GAction getGAction(){
    return gAction;
  }
  /*********************************************
  * Setter Methods
  **********************************************/
  public Block setSelectedBlock(Block block){
      selectedBlock = block;
      return block;
  }
  public void setSelectedBlock2(Block block){
      selectedBlock2 = block;
  }
}//end of GWar
