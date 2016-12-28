/**********************************************
* Zachary Heth 2016
**********************************************
* GWarInterface Class
*     The boxes that can be clicked on are all created/checked here
*     The methods are all run in the GWar class
**********************************************/
import java.awt.Color;

public class GWarInterface{

  boolean spawnFighter;
  boolean spawnPaver;
  boolean spawnCleaner;

  boolean captureObjective;
  boolean destroyObjective;
  /*********************************************
  * EZ objects for choose options
  * After assigning selectedBlock2, user must click on appropriate box?
  * Or have dual functionality | Double click, or click on box
  **********************************************/
  final int actBoxX = EZ.getWindowWidth()/2; //x coord the action box locations will be based on
  final int actBoxY = EZ.getWindowHeight()/2 + EZ.getWindowHeight()/3; //y coord the acction box locations will be based no
  final int unitBoxX = EZ.getWindowWidth() - EZ.getWindowWidth()/8;
  final int unitBoxY = EZ.getWindowHeight()/2 + EZ.getWindowHeight()/8;
  final int objBoxX = EZ.getWindowWidth()/2 - EZ.getWindowWidth()/2 + EZ.getWindowWidth()/10;
  final int objBoxY = EZ.getWindowHeight()/2 + EZ.getWindowHeight()/8;
  final int BoxW = 100; //width od the boxes
  final int BoxH = 30; //height of the boxes
  final int FontSize = 15;//font size

  final Color unselectedBox = new Color(200,200,200);
  final Color actBoxColor = new Color(255,100,255);
  final Color unitBoxColor = new Color(100,255,255);
  final Color objBoxColor = new Color(255,128,100);
  EZGroup endTurnBox = new EZGroup();
  EZGroup colorChangeBox = new EZGroup();
  EZGroup moveGBBox = new EZGroup();
  EZGroup fighterBox = new EZGroup();
  EZGroup paverBox = new EZGroup();
  EZGroup cleanerBox = new EZGroup();
  EZGroup destroyBox = new EZGroup();
  EZGroup captureBox = new EZGroup();

  GWar gwar;

  GWarInterface(GWar gw){
    gwar = gw;
    initAllBox();
  }
  /*********************************************
  * EZGroup Text Box Creation methods
  **********************************************/
  public void initAllBox(){
    initEndTurnBox();
    initCCBox();
    initGBBox();

    initFighterBox();
    initPaverBox();
    initCleanerBox();

    initDestroyObjectiveBox();
    initCaptureObjectiveBox();
  }
  public void initBox(EZGroup group,String text,int posx1, int posy1,int w1,int h1,int posx2,int posy2,int w2,int h2,Color color){
    EZRectangle outlineRec = new EZRectangle(posx1,posy1,w1,h1,new Color(0,0,0),false);
    EZRectangle rec = new EZRectangle(posx2,posy2,w2,h2,color,true);
    EZText txt = new EZText(rec.getXCenter(),rec.getYCenter(),text,new Color(0,0,0),FontSize);
    group.addElement(rec);
    group.addElement(outlineRec);
    group.addElement(txt);
    EZ.app.elements.add(group);
  }
  /*********************************************
  * EZGroup Action Boxes
  **********************************************/
  public void initEndTurnBox(){
    initBox(endTurnBox,"End Turn",actBoxX,actBoxY+BoxH,BoxW + 1,BoxH + 1,
            actBoxX,actBoxY+BoxH,BoxW,BoxH,actBoxColor);
  }
  public void initCCBox(){  }
  public void initGBBox(){  }
  /*********************************************
  * EZGroup Unit Boxes
  **********************************************/
  public void initFighterBox(){
    initBox(fighterBox,"Fighter",unitBoxX,unitBoxY - 2*BoxH,BoxW+1,BoxH+1,
            unitBoxX,unitBoxY - 2*BoxH,BoxW,BoxH,unselectedBox);
    spawnFighter = false;
  }
  public void initPaverBox(){
    initBox(paverBox,"Paver",unitBoxX,unitBoxY,BoxW+1,BoxH+1,
            unitBoxX,unitBoxY,BoxW,BoxH,unitBoxColor);
    spawnPaver = true;
  }
  public void initCleanerBox(){
    initBox(cleanerBox,"Cleaner",unitBoxX,unitBoxY + 2*BoxH,BoxW+1,BoxH+1,
            unitBoxX,unitBoxY + 2*BoxH,BoxW,BoxH,unselectedBox);
    spawnCleaner = false;
  }
  /*********************************************
  * EZGroup Objective Boxes
  **********************************************/
  public void initDestroyObjectiveBox(){
    initBox(destroyBox,"Destroy",objBoxX,objBoxY,BoxW, BoxH,
            objBoxX,objBoxY,BoxW,BoxH,objBoxColor);
    destroyObjective = true;
  }

  public void initCaptureObjectiveBox(){
    initBox(captureBox,"Capture",objBoxX,objBoxY + 2*BoxH,BoxW,BoxH,
            objBoxX,objBoxY + 2*BoxH,BoxW,BoxH,unselectedBox);
    captureObjective = false;
  }
  /*********************************************
  * Return true if clicked on one of the unit boxes
  **********************************************/
  public boolean checkInsideUnitBox(int posx, int posy){
    boolean inside = false;
    if(fighterBox.isPointInElement(posx,posy)){
        inside = true;
        spawnFighter = true;
        spawnPaver = false;
        spawnCleaner = false;
    }
    else if(paverBox.isPointInElement(posx, posy)){
        inside = true;
        spawnFighter = false;
        spawnPaver = true;
        spawnCleaner = false;
    }
    else if(cleanerBox.isPointInElement(posx,posy)){
        inside = true;
        spawnFighter = false;
        spawnPaver = false;
        spawnCleaner = true;
    }
    return inside;
  }//end checkInsideUnitBox
  /*********************************************
  * Changes color of unit box depending on what's selected
  **********************************************/
  public void displaySelectedUnitBox(){
    if(spawnFighter){
      fighterBox.getChildren().get(0).setColor(unitBoxColor);
      paverBox.getChildren().get(0).setColor(unselectedBox);
      cleanerBox.getChildren().get(0).setColor(unselectedBox);
    }
    else if(spawnPaver){
      fighterBox.getChildren().get(0).setColor(unselectedBox);
      paverBox.getChildren().get(0).setColor(unitBoxColor);
      cleanerBox.getChildren().get(0).setColor(unselectedBox);
    }
    else if(spawnCleaner){
      fighterBox.getChildren().get(0).setColor(unselectedBox);
      paverBox.getChildren().get(0).setColor(unselectedBox);
      cleanerBox.getChildren().get(0).setColor(unitBoxColor);
    }
  }//end of displaySelectedUnitBox
  /*********************************************
  *
  **********************************************/
  public boolean checkInsideObjectiveBox(int posx,int posy){
      boolean inside = false;
      if(captureBox.isPointInElement(posx,posy)){
          captureObjective = true;
          destroyObjective = false;
          inside = true;
      }
      else if(destroyBox.isPointInElement(posx,posy)){
          captureObjective = false;
          destroyObjective = true;
          inside = true;
      }
      return inside;
  }
  public void displaySelectedObjectiveBox(){
      if(captureObjective){
            captureBox.getChildren().get(0).setColor(objBoxColor);
            destroyBox.getChildren().get(0).setColor(unselectedBox);
      }
      else if(destroyObjective){
          captureBox.getChildren().get(0).setColor(unselectedBox);
          destroyBox.getChildren().get(0).setColor(objBoxColor);
      }
  }
  /*********************************************
  * True is inside the end box
  **********************************************/
  public boolean checkInsideEndTurnBox(int posx,int posy){
    boolean inside = false;
    if(endTurnBox.isPointInElement(posx,posy)){
      inside = true;
    }
    return inside;
  }
  /*********************************************
  * GETTERS
  **********************************************/

  public boolean getSpawnFighter(){
    return spawnFighter;
  }
  public boolean getSpawnPaver(){
    return spawnPaver;
  }
  public boolean getSpawnCleaner(){
    return spawnCleaner;
  }
  public boolean getCaptureObjective(){
    return captureObjective;
  }
  public boolean getDestroyObjective(){
    return destroyObjective;
  }
}//end of class GWarInterface
