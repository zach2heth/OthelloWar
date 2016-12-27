/**********************************************
* Zachary Heth 2016
**********************************************
* OthelloWar Class
*     This is the driver class
**********************************************/
import java.awt.Color;
public class OthelloWar {
//To test Grid methods, use a text version with numbers (use key grid)
 public static void main(String [] args){

  EZ.initialize(750,650);

  Map map;
  War war;
  GWar gwar;

  if(args[0].equals("10")){
      map = new Map(10,10);
      war= new War(map, 99,88,-1,-1,2,2,0,0);
      gwar = new GWar(war);
      map.textToGrid("10x10Basic2Player");
  }
  else if(args[0].equals("13")){
    map = new Map(13,13);
    war= new War(map, 99,88,77,-1,3,3,3,0);
    gwar = new GWar(war);
    map.textToGrid("13x13_3Player");
  }
  else if(args[0].equals("15")){
      map = new Map(15,5);
      war= new War(map, 99,88,-1,-1,2,2,0,0);
      gwar = new GWar(war);
      map.textToGrid("15x5_2Player");
  }

  else if(args[0].equals("17")){
    map = new Map(17,17);
    war= new War(map, 99,77,88,66,3,3,3,3);
    gwar = new GWar(war);
    map.textToGrid("17x17_4Players");
  }
  else{
      map = new Map(5,5);
      war= new War(map,99,77,88,66,3,3,3,3);
      gwar = new GWar(war);
      map.textToGrid("TestPlay.txt");
  }

  gwar.updateAfterInit();
  war.assignObjectiveBlocks();

  int clickX;
  int clickY;
  boolean display = true;
  boolean choose = false;

  while(true){

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
}//end of OthelloWar
