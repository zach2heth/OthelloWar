/**********************************************
* Zachary Heth 2016
**********************************************
* War Class
*     This class will be where it all comes together on the logic side.
*     It handles turn switching between players and so forth
*     It calls methods in the action class after given information from the GUI
**********************************************/

public class War{

  Player play1;
  Player play2;
  Player play3;
  Player play4;
  boolean play1Turn;
  boolean play2Turn;
  boolean play3Turn;
  boolean play4Turn;
  Action action;
  Map map;
  int turnCycle = 1;
  int numPlayers;
  SLL<Block> changedList;
  SLL<Player> playerList;

  /*********************************************
  * Constructor
  **********************************************/
  War(Map m, int clr1, int clr2,int clr3,int clr4, int spawnLimit1, int spawnLimit2,int spawnLimit3,int spawnLimit4){
    playerList = new SLL<Player>();
    play1 = new Player(clr1,spawnLimit1); play1Turn = true; playerList.add(play1);
    play2 = new Player (clr2,spawnLimit2); play2Turn = false; playerList.add(play2);
    map = m;
    changedList = new SLL<Block>();
    action = new Action(map.getGrid());
    map.setP1(play1);
    map.setP2(play2);
    numPlayers = 2;
    if(clr3 != -1){
        play3 = new Player(clr3,spawnLimit3); play3Turn = false; playerList.add(play3);
        map.setP3(play3);
        numPlayers++;
    }
    if(clr4 != -1){
        play4 = new Player(clr4,spawnLimit4); play4Turn = false; playerList.add(play4);
        map.setP4(play4);
        numPlayers++;
    }
  }//end of  constructor
  /*********************************************
  * Play a Game of OTHELLO WAR
  * Calls the appropriate number of players game
  **********************************************/
  public boolean game(int act, int act2, Block block, Block block2, Unit unit){
    boolean actionResult = false;
    if(numPlayers == 2){
        actionResult = twoPlayerGame(act, act2, block, block2, unit);
    }
    else if(numPlayers == 3){
        actionResult = threePlayerGame(act, act2, block, block2, unit);
    }
    else if(numPlayers == 4){
        actionResult = fourPlayerGame(act, act2, block, block2, unit);
    }
    return actionResult;
  }//end of game
  /*********************************************
  * Check if either player has been defeated, if not keep going
  **********************************************/
  public boolean twoPlayerGame(int act, int act2, Block block, Block block2, Unit unit){
    boolean actionResult = false;
    if(!play1.getDefeated() && !play2.getDefeated()){
        actionResult = determinePlayerTurn(act, act2, block, block2, unit);
    }
    else{
      endGame();
    }
    return actionResult;
  }//end of twoPlayerGame

  public boolean threePlayerGame(int act, int act2, Block block, Block block2, Unit unit){
    boolean actionResult = false;
    if((!play1.getDefeated() && !play2.getDefeated())
    || (!play1.getDefeated() && !play3.getDefeated())
    || (!play2.getDefeated() && !play3.getDefeated())){
        actionResult = determinePlayerTurn(act, act2, block, block2, unit);
    }
    else{
      endGame();
    }
    return actionResult;
  }//end of threePlayerGame

  public boolean fourPlayerGame(int act, int act2, Block block, Block block2, Unit unit){
    boolean actionResult = false;
    if((!play1.getDefeated() && !play2.getDefeated())
    || (!play1.getDefeated() && !play3.getDefeated())
    || (!play1.getDefeated() && !play4.getDefeated())
    || (!play2.getDefeated() && !play3.getDefeated())
    || (!play2.getDefeated() && !play4.getDefeated())
    || (!play3.getDefeated() && !play4.getDefeated())){
        actionResult = determinePlayerTurn(act, act2, block, block2, unit);
    }
    else{
      endGame();
    }
    return actionResult;
  }//end of fourPlayerGame

  /*********************************************
  * Whosever turn it is , gets to do their turn
  **********************************************/
  public boolean determinePlayerTurn(int act, int act2, Block block, Block block2, Unit unit){
      boolean actionResult = false;
       actionResult = PlayerTurn(currTurn(), act, act2, block, block2, unit);
      return actionResult;
  }//end of determinePlayerTurn

  /*********************************************
  * All the options a player has in their turn
  * Recieves input from the user-interface and does the action
  **********************************************/
    public boolean PlayerTurn(Player player, int act, int act2, Block block, Block block2, Unit unit){
      boolean actionResult = false;
      switch(act){
          case 1:
                  changedList = action.colorChange(player,block);
                  SLL<Block> copyChangedList = changedList.copySLL();
                  actionResult =
                  action.colorChangeUpdate(player, copyChangedList);
                  break;
          case 2: actionResult =
                  action.spawnUnit(player,block,act2);
                  break;
          case 3: actionResult =
                  unitOptions(player, unit, block2, act2);
                  break;
          case 4: actionResult =
                  action.moveGuardBlock(player, block, block2);
                  break;
          case 5: endTurn(player);
                   break;
          default: break;
        }//end of switch act
        return actionResult;
    }//end of playerTurn
    /*********************************************
    * Available options for any selected unit
    * Needs to check to Unit already did an action that turn
    * Do you even need block2?
    **********************************************/
    public boolean unitOptions(Player player, Unit unit, Block block2, int act){
      boolean actionResult = false;
      SLL<Player> target;
      switch(act){
          case 1: target = playerList.copySLL();
                  actionResult =
                  action.captureObjective(player,target,block2,unit,act);
                  break; //captureObjective case 1: DeadBlock
          case 2: target = playerList.copySLL();
                  actionResult =
                  action.captureObjective(player,target,block2,unit,act);
                  break; //captureObjective case 2: ColorChange
                        //block2 is the Objective Block being captured
          case 3: actionResult =
                  action.moveUnit(player, unit, block2);
                  break;
          case 4: int key = unit.getUnitKey();
                  actionResult =
                  useUnitSkill(unit,key,block2);
                  break;
      }
      return actionResult;
    }//end of unitOptions
    /*********************************************
    * Casts the generic unit to a type in order to use unit skills
    **********************************************/
    private boolean useUnitSkill(Unit unit, int key, Block block){
        boolean actionResult = false;
        switch(key){
          case 1: Fighter f = (Fighter)unit;
                  actionResult = f.fight(block);
                  break;
          case 2: Paver p = (Paver)unit;
                  actionResult = p.pave(block);
                  break;
          case 3: Cleaner c = (Cleaner)unit;
                 actionResult = c.clean(block);
                 break;
          case 4: Shooter s = (Shooter)unit;
                  //actionResult = s.shoot(block);
        }
        return actionResult;
    }//end of useUnitSkill
    /*********************************************
    * Reset's player's booleans/etc, and then switches the turn
    **********************************************/
    public void endTurn(Player player){
      player.reset();
      turnCycle++;
      switchTurns();
    }//end endTurn
    public void endGame(){
      System.out.println("END GAME");
      //display some end game things like who the winner is, etc.
      //maybe even have some confetti fall down or something
    }
    public void restart(){
      turnCycle = 1;
      switchTurns();
      play1.reset();
      play2.reset();
      if(numPlayers >= 3) play3.reset();
      if(numPlayers == 4) play4.reset();

    } //end of endGame
    /*********************************************
    * Updates turn-booleans. Tracks who's turn it is
    **********************************************/
    public void switchTurns(){
      switch(numPlayers){
          case 2:
              if(turnCycle % 2 == 1){
                if(!play1.getDefeated()){
                  play1Turn = true; play1.resetAllUnits();
                  play2Turn = false;
               }
               else{
                  turnCycle++;
                  switchTurns();
               }
              }
              else{
                  play1Turn = false; //play1.updateUnitList();
                  play2Turn = true; play2.resetAllUnits();//play2.updateUnitList();
              }
          break;

          case 3:
              if(turnCycle % 3 == 1){
                if(!play1.getDefeated()){
                  play1Turn = true; play1.resetAllUnits();
                  play2Turn = false;
                  play3Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
              else if(turnCycle % 3 == 2){
                if(!play2.getDefeated()){
                  play2Turn = true; play2.resetAllUnits();
                  play3Turn = false;
                  play1Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
              else if(turnCycle % 3 == 0){
                if(!play3.getDefeated()){
                  play3Turn = true; play3.resetAllUnits();
                  play1Turn = false;
                  play2Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
          break;
          case 4:
              if(turnCycle % 4 == 1){
                if(!play1.getDefeated()){
                  play1Turn = true; play1.resetAllUnits();
                  play2Turn = false;
                  play3Turn = false;
                  play4Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
              else if(turnCycle % 4 == 2){
                if(!play2.getDefeated()){
                  play2Turn = true; play2.resetAllUnits();
                  play3Turn = false;
                  play4Turn = false;
                  play1Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
              else if(turnCycle % 4 == 3){
                if(!play3.getDefeated()){
                  play3Turn = true; play3.resetAllUnits();
                  play1Turn = false;
                  play4Turn = false;
                  play2Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
              else if(turnCycle % 4 == 0){
                if(!play4.getDefeated()){
                  play4Turn = true; play4.resetAllUnits();
                  play1Turn = false;
                  play2Turn = false;
                  play3Turn = false;
                }
                else{
                   turnCycle++;
                   switchTurns();
                }
              }
          break;
      }//end of switch(numPlayers)
//System.out.println("play1Turn: "+play1Turn+ " play2Turn: "+play2Turn
//                    +" play3Turn: "+play3Turn+ " play4Turn: "+play4Turn);
    }//end of switchTurns
    /*********************************************
    * Returns the player who currently has their turn
    **********************************************/
    public Player currTurn(){
      Player player = null;
      if(play1Turn){
          player = play1;
      }
      else if(play2Turn){
          player = play2;
      }
      else if(play3Turn){
          player = play3;
      }
      else if(play4Turn){
          player = play4;
      }
      return player;
    }
    /*********************************************
    * Assigns objective blocks to the players
    **********************************************/
    public void assignObjectiveBlocks(){
      for(int y = 0; y < action.getGrid().getRows(); y++){
        for(int x = 0; x < action.getGrid().getColumns(); x++){
          if(action.getGrid().getCell(x,y).getStatus() == 4){
              if(action.getGrid().getCell(x,y).getColor() == play1.getColor()){
                  play1.addObjBlock(action.getGrid().getCell(x,y));
              }
              else if(action.getGrid().getCell(x,y).getColor() == play2.getColor()){
                  play2.addObjBlock(action.getGrid().getCell(x,y));
              }
              else if(action.getGrid().getCell(x,y).getColor() == play3.getColor()){
                  play3.addObjBlock(action.getGrid().getCell(x,y));
              }
              else if(action.getGrid().getCell(x,y).getColor() == play4.getColor()){
                  play4.addObjBlock(action.getGrid().getCell(x,y));
              }
          }
        }//end of for x
      }//end of for y
    }//end of assignObjectiveBlocks
    /*********************************************
    * Getters
    **********************************************/
    public Action getAction(){
      return action;
    }
    public Map getMap(){
      return map;
    }
    public SLL<Block> getChangedList(){
      return changedList;
    }
    public int getnumPlayers(){
      return numPlayers;
    }
    public Player getP1(){
      return play1;
    }
    public Player getP2(){
      return play2;
    }
    public Player getP3(){
      return play3;
    }
    public Player getP4(){
      return play4;
    }
}//end of War class
