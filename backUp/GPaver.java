/**********************************************
* Zachary Heth 2016
**********************************************
* GPaver Class
*     Extends GUnit
*     Graphics for the Paver type unit
**********************************************/
import java.awt.Color;

public class GPaver extends GUnit{

  GPaver(Paver p, GBlock gb){
      unit = p;
      gBlock = gb;
      gBlock.setGUnit(this);
  }//end of constructor

  public void createGPaver(){
    int[] xp = new int[12];
    int[] yp = new int[12];
    //Upper Right Point of Hexagon
    xp[0] = gBlock.LineE() - (gBlock.LineNLength()/10);
    yp[0] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.2);
    // Top Point of hexgon
    xp[1] = gBlock.LineE() - (gBlock.LineNLength()/2);
    yp[1] = gBlock.LineN() + (int)(gBlock.LineELength()*0.1);
    //Upper Left Point of Hexagon
    xp[2] = gBlock.LineE() - gBlock.LineNLength() + (gBlock.LineNLength()/10);
    yp[2] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.2);
    //Lower Left Point of Hexagon
    xp[3] = gBlock.LineE() - gBlock.LineNLength() + (gBlock.LineNLength()/10);
    yp[3] = gBlock.LineS() - (int)(gBlock.LineSLength()*0.2);
    //Bottom Point of Hexagon
    xp[4] = gBlock.LineE() - (gBlock.LineNLength()/2);
    yp[4] = gBlock.LineS() - (int)(gBlock.LineELength()*0.1);
    //Lower Right Point of Hexagon
    xp[5] = gBlock.LineE() - (gBlock.LineNLength()/10);
    yp[5] = gBlock.LineS() - (int)(gBlock.LineSLength()*0.2);
    //Back to UpperRight Point of Hexagon
    xp[6] = gBlock.LineE() - (gBlock.LineNLength()/10);
    yp[6] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.2);
    //Line from Upper Right to Center
    xp[7] = gBlock.LineE() - (gBlock.LineNLength()/2);
    yp[7] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.3);
    //Line from Center to Botom
    xp[8] = gBlock.LineE() - (gBlock.LineNLength()/2);
    yp[8] = gBlock.LineS() - (int)(gBlock.LineELength()*0.1);
    //Back from Bottom to Center
    xp[9] = gBlock.LineE() - (gBlock.LineNLength()/2);
    yp[9] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.3);
    //Line from Center to Upper Left
    xp[10] = gBlock.LineE() - gBlock.LineNLength() + (gBlock.LineNLength()/10);
    yp[10] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.2);
    //Back from Upper Left to center
    xp[11] = gBlock.LineE() - (gBlock.LineNLength()/2);
    yp[11] = gBlock.LineN() + (int)(gBlock.LineNLength()*0.3);

  //  image = new EZPolygon(xp,yp,new Color(200,200,100),true);//take out xp[11] and yp[11]
    image = new EZPolygon(xp,yp,color,false);
    EZ.app.elements.add(image);
  }//end of createGFighter
  /****************************************************
  * Changes dest color to GUnit's color
  /****************************************************/
  public void gPave(GBlock dest,Color color){
      dest.changeBlockColor(color);
  }
}
