/**********************************************
* Zachary Heth 2016
**********************************************
* Cleaner Class
*     Extends Unit class
*     Logic for the cleaner type unit
**********************************************/
public class Cleaner extends Unit{
  public Cleaner(Block loc, int clr){
    location = loc;
    color = clr;
    unitKey = 3;
    location.setVacant(false);
  }
  public boolean clean(Block block){
    boolean cleaned = false;
    if(block.getStatus() == 2 && block.getVacant()){
      if(getLocation().checkAdjacent(block) && getSkill()){
        block.setColor(100);
        block.setStatus(1);
        useSkill();
        cleaned = true;
      }
    }
    return cleaned;
  }
}//end of Cleaner class
