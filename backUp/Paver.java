/**********************************************
* Zachary Heth 2016
**********************************************
* Paver Class
*     Extends Unit | Logic of pave method 
*     Pavers change a block from a neutral or opposing color
*          into its own color
**********************************************/
public class Paver extends Unit{
  public Paver(Block loc, int clr){
    location = loc;
    color = clr;
    unitKey = 2;
    location.setVacant(false);
  }
  //Do you need to set prevStatus? Is prevStatus only used for GuardBlocks?
  public boolean pave(Block block){
    boolean paved = false;
    if(block.getStatus() == 1 || (block.getStatus() == 2 && block.getVacant())){
      if(getLocation().checkAdjacent(block) && getSkill()){
        block.setColor(color);
        block.setStatus(2);
        useSkill();
        paved = true;
      }
    }
    return paved;
  }
}//end of Paver class
