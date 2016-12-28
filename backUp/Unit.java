/**********************************************
* Zachary Heth 2016
**********************************************
* Unit Class
*     Parent class of specific types of units
*     Has the general methods that pertain to all units
**********************************************/

public class Unit{

  boolean move = true; //to track if unit moved that turn
  boolean skill = true; //track if unit used ability that turn
  boolean alive = true;
  int color;
  int unitKey;
  int unitNumber; //where in the unitlist they are
  Block location; //block the unit is currently on

  /*********************************************
  * Important, self exaplanatory stuff
  **********************************************/
  public void resetUnit(){
    move = true;
    skill = true;
  }
  public void destroy(){ //if the unit is defeated by a fighter or from a CC, or SD
    location.setUnit(null);
    alive = false;
  }
  /*********************************************
  * USE METHODS
  **********************************************/
  public void useMove(){
    move = false;
  }
  public void useSkill(){
    skill = false;
  }
  /*********************************************
  * SETTERS
  **********************************************/
  public void setLocation(Block bl){
    location = bl;
  }
  public void setUnitKey(int key){
    unitKey = key;
  }
  /*********************************************
  * GETTERS
  **********************************************/
  public int getUnitKey(){
    return unitKey;
  }
  public Block getLocation(){
    return location;
  }
  public boolean getMove(){
    return move;
  }
  public boolean getSkill(){
    return skill;
  }
  public boolean getAlive(){
    return alive;
  }
  public int getColor(){
    return color;
  }
}//end of Unit class
