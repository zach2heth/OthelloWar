/**********************************************
* Zachary Heth 2016
**********************************************
* test Class
*     This class has been changes many times
*        for the puporse of testing various aspects of the Othello War Project
**********************************************/
import java.awt.Color;
public class test {
//To test Grid methods, use a text version with numbers (use key grid)
 public static void main(String args[]){

  EZ.initialize(750,650);

  Map map = new Map(5,5);
//  Map map = new Map(10,10);
  //  Map map = new Map(15,5);
//  Map map = new Map(13,13);
//  Map map = new Map(17,17);

//  War war= new War(map, 99,88,-1,-1,2,2,0,0);
// War war= new War(map, 99,77,88,-1,3,3,3,0);
  War war= new War(map, 99,77,88,66,3,3,3,3);

  GWar gwar = new GWar(war);

  map.textToGrid("TestPlay.txt");
  //map.textToGrid("10x10Basic2Player");
  //map.textToGrid("15x5_2Player");
    //map.textToGrid("13x13_3Player");
  //map.textToGrid("17x17_4Players");
  gwar.updateAfterInit();
  war.assignObjectiveBlocks();

  int clickX;
  int clickY;
  boolean display = true;
  boolean choose = false;

  while(true){
    //gwar.getGAction().getGGrid().flashyTest();
    //gf.gFight(gwar.getGAction().getGBlock()[0][0]);
    clickX = EZInteraction.getXMouse();
    clickY = EZInteraction.getYMouse();

    if(EZInteraction.wasMouseLeftButtonPressed()){
      gwar.chooseOption(clickX,clickY);
      display = true;
      choose = false;
    //  war.endTurn(war.currTurn());
    //  war.getAction().getGrid().printGrid();
    }
    if(EZInteraction.wasMouseLeftButtonPressed()){
      gwar.displayOptions(clickX,clickY);
      display = false;
      choose = true;
    }

    EZ.refreshScreen();

  }

  }//end of main
}//end of test
