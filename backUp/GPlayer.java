/**********************************************
* Zachary Heth 2016
**********************************************
* GPlayer
*     Graphics for a player...i guess
**********************************************
*     I made this class in order to solve the problem
*     of removing GUnits after a Unit is destoyed via a Color Change
*     So in other classes, if you see places where this class could have been used
*     And made things simpler, well I didn't because this was last minute
*     Oh well..
**********************************************/

public class GPlayer{

  SLL<GUnit> GUnitList;
  Player player;
  int numGUnits = 0;

  GPlayer(Player p){
    player = p;
    GUnitList = new SLL<GUnit>();
  }
  /*********************************************
  * Mainly used after color Changes
  * Removes image of GUnits that were destoyed
  **********************************************/
  public void updateGUnitList(){
      for(int currunit = 0; currunit < numGUnits; currunit++){
        if(!GUnitList.get(currunit).getUnit().getAlive()){
          GUnitList.get(currunit).destoyGUnit();
          GUnitList.removeIndex(currunit);
          numGUnits--;
          currunit--; //to keep the for loop counters in check
        }
    }
  }//end of updateUnitList
  /*********************************************
  * Adds a GUnit to GUnitList
  **********************************************/
  public void addGUnit(GUnit unit){
    GUnitList.add(unit);
    numGUnits++;
  }
  /*********************************************
  * GETTERS
  **********************************************/
  public Player getPlayer(){
    return player;
  }
  public int getnumGUnits(){
    return numGUnits;
  }
}
