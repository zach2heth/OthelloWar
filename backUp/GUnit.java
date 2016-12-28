/**********************************************
* Zachary Heth 2016
**********************************************
* GUnit class
*     Parent Class of differen GUnits
*     Contains the methods that pertain to all GUnits
**********************************************/
import java.awt.Color;
public class GUnit{
    Unit unit;
    GBlock gBlock;
    EZPolygon image;
    Color color = new Color(225,225,225); //honestly I think i might just keep it transparent with white lines
    final int shrinkShift = 10;

    /*********************************************
    * To be used when unit moves
    * Graphics for unit movement
    **********************************************/
    public void moveImage(GBlock start,GBlock dest){
        gBlock = dest; //new location of Unit
        dest.setGUnit(start.getGUnit());//
        start.setGUnit(null);
        image.translateTo(dest.blockCenter()[0],dest.blockCenter()[1]);
     }
     public void destoyGUnit(){
       gBlock.setGUnit(null);
       image.hide();
       EZ.removeEZElement(image);
       //OR
       //image.hide();  ResearveList.add(image).
       //Then when a new unit is needed, search through the Researve first!
     }
    public void setColor(Color c){
      color = c;
    }
    public void setUnit(Unit unt){
        unit = unt;
    }
    public Unit getUnit(){
      return unit;
    }
}//end of class GUnit
