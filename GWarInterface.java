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

  boolean colorChange;
  boolean guardBlock;
  boolean spawn;
  /*********************************************
  * EZ objects for choose options
  * After assigning selectedBlock2, user must click on appropriate box?
  * Or have dual functionality | Double click, or click on box
  **********************************************/
  final int BoxW = 100; //width od the boxes
  final int BoxH = 30; //height of the boxes
  final int FontSize = 15;//font size
  final int endBoxX = EZ.getWindowWidth() - EZ.getWindowWidth()/8; //x coord the action box locations will be based on
  final int endBoxY = EZ.getWindowHeight()/2 + EZ.getWindowHeight()/3; //y coord the acction box locations will be based no
  final int actBoxX = EZ.getWindowWidth() - EZ.getWindowWidth()/12;
  final int actBoxY = EZ.getWindowHeight()/2 + EZ.getWindowHeight()/3 + BoxH;
  final int unitBoxX = EZ.getWindowWidth() - EZ.getWindowWidth()/8;
  final int unitBoxY = EZ.getWindowHeight()/2 + EZ.getWindowHeight()/8;
  final int objBoxX = unitBoxX;
  final int objBoxY = unitBoxY - EZ.getWindowHeight()/3;
  final int playBoxX = EZ.getWindowWidth()/5;
  final int playBoxY = EZ.getWindowHeight()/12;

  final Color unselectedBox = new Color(200,200,200);
  final Color actBoxColor = new Color(128,255,100);
  final Color unitBoxColor = new Color(100,255,255);
  final Color objBoxColor = new Color(255,128,100);
  final Color endBoxColor = new Color(255,100,255);
  EZGroup endTurnBox = new EZGroup();
  EZGroup colorChangeBox = new EZGroup();
  EZGroup moveGBBox = new EZGroup();
  EZGroup spawnBox = new EZGroup();
  EZGroup fighterBox = new EZGroup();
  EZGroup paverBox = new EZGroup();
  EZGroup cleanerBox = new EZGroup();
  EZGroup destroyBox = new EZGroup();
  EZGroup captureBox = new EZGroup();
  EZGroup play1Box = new EZGroup();
  EZGroup play2Box = new EZGroup();
  EZGroup play3Box = new EZGroup();
  EZGroup play4Box = new EZGroup();
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
    initSpawnBox();

    initFighterBox();
    initPaverBox();
    initCleanerBox();

    initDestroyObjectiveBox();
    initCaptureObjectiveBox();

    initPlay1();
    initPlay2();
    if(gwar.getWar().getnumPlayers() >= 3) initPlay3();
    if(gwar.getWar().getnumPlayers() == 4) initPlay4();
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
    initBox(endTurnBox,"End Turn",endBoxX,endBoxY+BoxH,BoxW + 1,BoxH + 1,
            endBoxX,endBoxY+BoxH,BoxW,BoxH,endBoxColor);
  }
  public void initCCBox(){
    initBox(colorChangeBox,"Color Change",actBoxX - 6*BoxW,actBoxY,BoxW,BoxH,
            actBoxX - 6*BoxW,actBoxY,BoxW,BoxH,actBoxColor);
    colorChange = true;
  }
  public void initGBBox(){
    initBox(moveGBBox,"Guard Block",actBoxX - 4*BoxW,actBoxY,BoxW,BoxH,
            actBoxX - 4*BoxW,actBoxY,BoxW,BoxH,actBoxColor);
    guardBlock = true;
  }
  public void initSpawnBox(){
    initBox(spawnBox,"Spawn Units",actBoxX - 2*BoxW,actBoxY,BoxW,BoxH,
            actBoxX - 2*BoxW,actBoxY,BoxW,BoxH,actBoxColor);
    spawn = true;
  }
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
  * EZGroup Player Boxes
  **********************************************/
  public void initPlay1(){
    int shift = EZ.getWindowWidth()/7;
   if(gwar.getWar().getnumPlayers() >= 3){
        shift = 0;
    }
    initBox(play1Box,"Player 1",playBoxX+shift,playBoxY,BoxW,BoxH,
            playBoxX+shift,playBoxY,BoxW,BoxH,
            gwar.getGAction().requestColor(gwar.getWar().getP1().getColor()));
  }
  public void initPlay2(){
      int shift = EZ.getWindowWidth()/2;
      if(gwar.getWar().getnumPlayers() == 3){
          shift = EZ.getWindowWidth()/3 - EZ.getWindowWidth()/25;
      }
      else if(gwar.getWar().getnumPlayers() == 4){
          shift = EZ.getWindowWidth()/5;
      }
      initBox(play2Box,"Player 2",playBoxX+shift,playBoxY,BoxW,BoxH,
              playBoxX+shift,playBoxY,BoxW,BoxH,unselectedBox);
  }
  public void initPlay3(){
    int shift = 2*(EZ.getWindowWidth()/3 - EZ.getWindowWidth()/25);
    if(gwar.getWar().getnumPlayers() == 4){
        shift = (int)(2*EZ.getWindowWidth()/5);
    }
      initBox(play3Box,"Player 3",playBoxX+shift,playBoxY,BoxW,BoxH,
              playBoxX+shift,playBoxY,BoxW,BoxH,unselectedBox);
  }
  public void initPlay4(){
   int shift = (int)(3*EZ.getWindowWidth()/5);

   initBox(play4Box,"Player 4",playBoxX+shift,playBoxY,BoxW,BoxH,
           playBoxX+shift,playBoxY,BoxW,BoxH,unselectedBox);

  }
  /*********************************************
  * Check what actions are still available
  **********************************************/
  public void checkActBox(){
    if(gwar.getWar().currTurn().getCC())
      colorChange = true;
    else
      colorChange = false;
    if(gwar.getWar().currTurn().getMoveGB())
      guardBlock = true;
    else
      guardBlock = false;
    if(gwar.getWar().currTurn().getSpawn() > 0)
      spawn = true;
    else
      spawn = false;
    turnOffActBox();
  }
  /*********************************************
  * Display that all actions are still available
  **********************************************/
  public void resetActBox(){
      colorChangeBox.getChildren().get(0).setColor(actBoxColor);
      moveGBBox.getChildren().get(0).setColor(actBoxColor);
      spawnBox.getChildren().get(0).setColor(actBoxColor);
  }
  /*********************************************
  * Change color of boxes corresponding to actions already done
  **********************************************/
  public void turnOffActBox(){
    if(!colorChange) colorChangeBox.getChildren().get(0).setColor(unselectedBox);
    if(!guardBlock) moveGBBox.getChildren().get(0).setColor(unselectedBox);
    if(!spawn) spawnBox.getChildren().get(0).setColor(unselectedBox);
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
  * Displays the player, whose turn it is
  **********************************************/
  public void displayPlayerBox(){
      SLL<GPlayer> player = gwar.allGPlayers();
      if(player.get(0) == gwar.currTurnG()){
        play1Box.getChildren().get(0).setColor(gwar.getGAction().requestColor(gwar.getWar().getP1().getColor()));
        play2Box.getChildren().get(0).setColor(unselectedBox);
        if(gwar.getWar().getnumPlayers() >= 3)play3Box.getChildren().get(0).setColor(unselectedBox);
        if(gwar.getWar().getnumPlayers() == 4)play4Box.getChildren().get(0).setColor(unselectedBox);
      }
      else if(player.get(1) == gwar.currTurnG()){
        play1Box.getChildren().get(0).setColor(unselectedBox);
        play2Box.getChildren().get(0).setColor(gwar.getGAction().requestColor(gwar.getWar().getP2().getColor()));
        if(gwar.getWar().getnumPlayers() >= 3)play3Box.getChildren().get(0).setColor(unselectedBox);
        if(gwar.getWar().getnumPlayers() == 4)play4Box.getChildren().get(0).setColor(unselectedBox);
      }
      else if(player.get(2) == gwar.currTurnG()){
        play1Box.getChildren().get(0).setColor(unselectedBox);
        play2Box.getChildren().get(0).setColor(unselectedBox);
        play3Box.getChildren().get(0).setColor(gwar.getGAction().requestColor(gwar.getWar().getP3().getColor()));
        if(gwar.getWar().getnumPlayers() == 4)play4Box.getChildren().get(0).setColor(unselectedBox);
      }
      else if(player.get(3) == gwar.currTurnG()){
        play1Box.getChildren().get(0).setColor(unselectedBox);
        play2Box.getChildren().get(0).setColor(unselectedBox);
        play3Box.getChildren().get(0).setColor(unselectedBox);
        play4Box.getChildren().get(0).setColor(gwar.getGAction().requestColor(gwar.getWar().getP4().getColor()));
      }
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
