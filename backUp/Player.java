/**********************************************
* Zachary Heth 2016
**********************************************
* Player Class
*     Contains the properties required to identify different sides in a game
*     Tracks what things a player has done within a given turn
**********************************************/
public class Player{

  int color;
  int spawnLimit; //how many units can be spawned per turn; depends on map/rules
  int numUnits; //track number of units player currently has
  int spawn; //track if player created a new unit that turn;
  boolean cc; //track if player cc'd that turn
  boolean moveGB; //track if player moved a GuardBlock that turn
  boolean defeated;
  SLL<Block> objBlocks;//track objective blocks under control
  SLL<Unit> unitList;//track list of all spawned, and alive units

  public Player(int clr, int spwnLim){
    color = clr;
    cc = true;
    moveGB = true;
    defeated = false;
    spawnLimit = spwnLim;
    spawn = spawnLimit;
    numUnits = 0;
    unitList = new SLL<Unit>();
    objBlocks = new SLL<Block>();
  }
  public void reset(){
    cc = true;
    moveGB = true;
    spawn = spawnLimit;
  }
  public void setSpawnLimit(int spwnlim){
    spawnLimit = spwnlim;
  }
  /*********************************************
  * Goes through list. Removes dead units and resets the others
  **********************************************/
  public void updateUnitList(){
    for(int currunit = 0; currunit < numUnits; currunit++){
      if(!unitList.get(currunit).getAlive()){
        unitList.removeIndex(currunit);
        decNumUnits();
        currunit--; //to keep the for loop counters in check
      }
    }
  }//end of updateUnitList
  public void resetAllUnits(){
    for(int currunit = 0; currunit < numUnits; currunit++){
      unitList.get(currunit).resetUnit();
    }
  }
  /*********************************************
  * Object Block Related
  **********************************************/
  public void addObjBlock(Block block){
    objBlocks.add(block);
  }
  public void removeObjBlock(Block block){
    objBlocks.removeElement(block);
  }
  public int checkObjBlocks(){
    int stillRemaining = objBlocks.size();
    if(stillRemaining == 0)
        defeated = true;
    return stillRemaining;
  }
  /*********************************************
  * "Use" methods.
  **********************************************/
  public void useCC(){
    cc = false;
  }
  public void useGB(){
    moveGB = false;
  }
  public void useSpawn(Unit u){
    spawn = spawn - 1;
    incNumUnits();
    unitList.add(u);
  }
  public void incNumUnits(){
    numUnits++;
  }
  public void decNumUnits(){
    numUnits--;
  }
  /*********************************************
  * GETTERS
  **********************************************/
  public int getColor(){
    return color;
  }
  public int getSpawnLimit(){
    return spawnLimit;
  }
  public int getSpawn(){
    return spawn;
  }
  public int getnumUnits(){
    return numUnits;
  }
  public boolean getCC(){
    return cc;
  }
  public boolean getMoveGB(){
    return moveGB;
  }
  public boolean getDefeated(){
    return defeated;
  }
  public SLL<Unit> getUnitList(){
    return unitList;
  }
  //public Unit createUnit(int unitKey){}
}
