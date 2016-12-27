/**********************************************
* Zachary Heth 2016
**********************************************
* GFighter
*     Extends GUnit
*     Graphics for Fighter type unit
**********************************************/
import java.awt.Color;

public class GFighter extends GUnit{

    GFighter(Fighter f, GBlock gb){
        unit = f;
        gBlock = gb;
        gBlock.setGUnit(this);
    }//end of constructor

    /*********************************************
    * Creates the fighter shape in the GBlock
    **********************************************/
    public void createGFighter(){
      int[] xp = new int[4];
      int[] yp = new int[4];
      //Top Point of Triangle
      xp[0] = gBlock.LineE() - (gBlock.LineNLength()/2);
      yp[0] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.1);
      //Bottom Left Point of Triangle
      xp[1] = gBlock.LineE() - (int)(gBlock.LineELength()*0.1);
      yp[1] = gBlock.LineS() - (int)(gBlock.LineSLength()*0.1);
      //Center Bottom thing that caves in
      xp[2] = gBlock.blockCenter()[0];
      yp[2] = gBlock.blockCenter()[1] + gBlock.LineELength()/4;
      //Bottom Right Point of Triangle
      xp[3] = gBlock.LineW() + (int)(gBlock.LineWLength()*0.1);
      yp[3] = gBlock.LineS() - (int)(gBlock.LineSLength()*0.1);

      if(unit.getColor() == 66 || unit.getColor() == 77){
        image = new EZPolygon(xp,yp,new Color(0,0,0),false);
      }
      else{
        image = new EZPolygon(xp,yp,color,false);
        //image = new EZPolygon(xp,yp,color,true);
      }
      EZ.app.elements.add(image); //makes the image show up on the screen. very important
    }//end of createGFighter

     /*********************************************
     * doesn't work, maybe it happens to quickly to see? but not too important
     * Animation for the fight skill
     * Skill being done on the GBlock dest
     * Rotates towards dest, and moves forward and back
     **********************************************/
     public void gFight(GBlock dest){
          if(gBlock.getBlock().hasN() && gBlock.getBlock() == dest.getBlock().getN()){
              image.rotateTo(180);
          }
          else if(gBlock.getBlock().hasE() && gBlock.getBlock() == dest.getBlock().getE()){
              image.rotateTo(270);
          }
          else if(gBlock.getBlock().hasS() && gBlock.getBlock() == dest.getBlock().getS()){
              image.rotateTo(0);
          }
          else if(gBlock.getBlock().hasW() && gBlock.getBlock() == dest.getBlock().getW()){
              image.rotateTo(90);
          }
    }//end of gFight
}//end of GFighter class
