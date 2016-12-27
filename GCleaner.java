/**********************************************
* Zachary Heth 2016
**********************************************
* GCleaner Class
*     Extends GUnit
*     Graphics for Cleaner type unit
**********************************************/
import java.awt.Color;

public class GCleaner extends GUnit{

  GCleaner(Cleaner c, GBlock gb){
      unit = c;
      gBlock = gb;
      gBlock.setGUnit(this);
  }//end of constructor

  public void createGCleaner(){
      int[] xp = new int[11];
      int[] yp = new int[11];
      //Top point of outer square
      xp[0] = gBlock.blockCenter()[0];
      yp[0] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.1);
      //Left Point of outer square
      xp[1] = gBlock.LineW() + (int)(gBlock.LineWLength()*0.1);
      yp[1] = gBlock.blockCenter()[1];
      //Bottom point of outer square
      xp[2] = gBlock.blockCenter()[0];
      yp[2] = gBlock.LineS() - (int)(gBlock.LineSLength()*0.1);
      //Right Point of outer square
      xp[3] = gBlock.LineE() - (int)(gBlock.LineELength()*0.1);
      yp[3] = gBlock.blockCenter()[1];
      //Back to Top of outer square
      xp[4] = gBlock.blockCenter()[0];
      yp[4] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.1);
      //Top point of inner square
      xp[5] = gBlock.blockCenter()[0];
      yp[5] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.3);
      //Left point of inner square
      xp[6] = gBlock.LineW() + (int)(gBlock.LineWLength()*0.3);
      yp[6] = gBlock.blockCenter()[1];
      //Bottom point of inner square
      xp[7] = gBlock.blockCenter()[0];
      yp[7] = gBlock.LineS() - (int)(gBlock.LineSLength()*0.3);
      //Right point of inner square
      xp[8] = gBlock.LineE() - (int)(gBlock.LineELength()*0.3);
      yp[8] = gBlock.blockCenter()[1];
      //Back to Top point of inner square
      xp[9] = gBlock.blockCenter()[0];
      yp[9] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.3);
      //Back to Top of outer square
      xp[10] = gBlock.blockCenter()[0];
      yp[10] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.1);

      if(unit.getColor() == 66 || unit.getColor() == 77){
        image = new EZPolygon(xp,yp,new Color(0,0,0),false);
      }
      else{
        image = new EZPolygon(xp,yp,color,false);
      //  image = new EZPolygon(xp,yp,color,true);
      }
      //image = new EZPolygon(xp,yp,color,true);
      EZ.app.elements.add(image);

  }

  public void gClean(GBlock dest,Color color){
    dest.changeBlockColor(color);
  }

}
