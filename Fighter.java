/**********************************************
* Zachary Heth 2016
**********************************************
* Fighter Class
*     Extends Unit
*     Logic for the Fighter type unit
**********************************************/
public class Fighter extends Unit{
  public Fighter(Block loc, int clr){
    location = loc;
    color = clr;
    unitKey = 1;
    location.setVacant(false);
  }
//Check the fight method. It assumes that if a unit's block is adjacent to the fighter's block
// the unit will be destoyed
//You may also have to setUnit(null); setVacant(true);
  public boolean fight(Block block){
    boolean fought = false;
    if(!block.getVacant()){
      if(getLocation().checkAdjacent(block) && getSkill()){
        block.getUnit().destroy();
        useSkill();
        fought = true;
      }
    }
    return fought;
  }
}//end of Fighter class
