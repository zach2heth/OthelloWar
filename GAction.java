/**********************************************
* Zachary Heth 2016
**********************************************
* GAction Class
*     Graphics for the Action class
*     It displays all the different options for a unit
*     It gives the color change requests to the appropriate GBlocks
**********************************************/
import java.lang.NullPointerException;
import java.awt.Color;
public class GAction{

  GGrid gGrid;//This is not a 2d array, it is an instance of GGrid
  Action action;
  Block[][] moveOpt; //stores the moveOptions passed into  displayMoveOptions
  Block[] skillOpt;
  Block[] objOpt;
  int moveOptSize;

  public GAction(Map map, Action actn){
      action = actn;
      gGrid = new GGrid(action.getGrid(), map.getDimensions(), map.getGridSize(),
                        map.getShiftX(), map.getShiftY());
  }//end of constructor
  /*********************************************
  * When CC is called, the list of changed blocks must be saved
  * Used as input for CCUpdate, and if it returns true,
  * Call this method with that list as the parameter
  **********************************************/
  public void displayColorChange(SLL<Block> changed){
      while(changed.size() != 0){
          if(changed.get(0).getColumn() >= 0) {
            gGrid.getGGrid()[changed.get(0).getColumn()][changed.get(0).getRow()]
            .changeBlockColor(requestColor(changed.get(0).getColor()));
          }
          changed.removeIndex(0);
      }
  }//end of displayColorChange
  /****************************************************
  * Shows move options (and awaits for selection?)
  /****************************************************/
  public void displayMoveOptions(Block options[][]){
    if(action.getGrid().getColumns() > action.getGrid().getRows()){
      moveOptSize = action.getGrid().getColumns();
    }
    else{
      moveOptSize = action.getGrid().getRows();
    }
    moveOpt = new Block[moveOptSize][4];
    moveOpt = options;
    for(int y = 0; y < 4; y ++){
      for(int x = 0; x < moveOptSize; x++){
        try{
          if(options[x][y].getRow() != -1){
              gGrid.getGGrid()[options[x][y].getColumn()][options[x][y].getRow()]
                              .changeLineColor(requestColor(2));
          }
        }//end of try
        catch(NullPointerException e){}
      }
    }//end of for loops
  }//end of displayMoveOptions
  /*********************************************
  * Returns the block form move options, that
  **********************************************/
  public Block checkInsideMoveOptions(int posx ,int posy){
    Block block = new Block(-1,-1);
    for(int y = 0; y < 4; y++){
      for(int x = 0; x < moveOptSize; x++){
        try{
          if(moveOpt[x][y].getRow() != -1){
            if(gGrid.getGGrid()[moveOpt[x][y].getColumn()][moveOpt[x][y].getRow()].insideBlock(posx,posy)){
                block = moveOpt[x][y];
            }
          }
        }//end of try
        catch(NullPointerException e){}
      }
    }
    return block;
  }//end of checkInsideMoveOptions
  /*********************************************
  * Changes lines colors from displayMoveOptions back to normal
  **********************************************/
  public void cancelMoveOptions(){
    for(int y = 0; y < 4; y ++){
      for(int x = 0; x < moveOptSize; x++){
        try{
          if(moveOpt[x][y].getRow() != -1)
          gGrid.getGGrid()[moveOpt[x][y].getColumn()][moveOpt[x][y].getRow()]
                          .changeLineColor(requestColor(1));
        }
        catch(NullPointerException e){}
      }
    }
  }//end of cancelMoveOptions
  /*********************************************
  * Show adjacent blocks that can be affected by the skill
  **********************************************/
  public void displayUnitSkillOptions(GUnit gUnit){
    Block block = gUnit.getUnit().getLocation();
    Block adjacent[] = block.getAdjacent();
    Block nullBlock = new Block(-1,-1);
    skillOpt = new Block[4];
    for(int i = 0; i < 4; i++){
      if(adjacent[i].getRow() != -1){
        switch(gUnit.getUnit().getUnitKey()){
            case 1: // fighter
  //System.out.println(" adjacent["+i+"]:" +adjacent[i]);
                if(adjacent[i].hasUnit()){
                    gGrid.getGGrid()[adjacent[i].getColumn()][adjacent[i].getRow()]
                                    .changeLineColor(requestColor(3));
                    skillOpt[i] = adjacent[i];
                }
                else{ skillOpt[i] = nullBlock; }
  //System.out.println("skillOpt[i]: "+skillOpt[i]);
                break;
            case 2: // paver
                if(adjacent[i].getColor() != gUnit.getUnit().getColor() && adjacent[i].getVacant() &&
                  (adjacent[i].getStatus() == 1 || adjacent[i].getStatus() == 2)){
                      gGrid.getGGrid()[adjacent[i].getColumn()][adjacent[i].getRow()]
                                      .changeLineColor(requestColor(3));
                    skillOpt[i] = adjacent[i];
                }
                else{ skillOpt[i] = nullBlock; }
                break;
            case 3: // cleaner
                if(adjacent[i].getStatus() == 2 && adjacent[i].getVacant()){
                    gGrid.getGGrid()[adjacent[i].getColumn()][adjacent[i].getRow()]
                                    .changeLineColor(requestColor(3));
                    skillOpt[i] = adjacent[i];
                }
                else{ skillOpt[i] = nullBlock; }
                break;
        }//end of switch
      }//end of if != -1
    }//end of for loop
  }//end of displayUnitSkillOptions

  /*********************************************
  * Returns the block using unit skill on
  * Use after running displayUnitSkillOptions
  **********************************************/
  public Block checkInsideUnitSkill(int posx, int posy){
    Block block = new Block(-1,-1);
    for(int i = 0; i < 4; i++){
      try {
        if(skillOpt[i].getRow() != -1){//skillOpt[i].getRow() != -1
          if(gGrid.getGGrid()[skillOpt[i].getColumn()][skillOpt[i].getRow()].insideBlock(posx, posy)){
            block = skillOpt[i];
          }
        }
      }//end of try
      catch(NullPointerException e){}
    }
    return block;
  }//end of checkInsideUnitSkill
  /*********************************************
  * Turn off display of blocks to use unit skill on
  **********************************************/
  public void cancelUnitSkill(){
      for(int i = 0; i < 4; i++){
        try {
          if(skillOpt[i].getRow() != -1)
          getGBlock()[skillOpt[i].getColumn()][skillOpt[i].getRow()]
                  .changeLineColor(requestColor(1));
        }
        catch(NullPointerException e){}
      }
      skillOpt = null;
  }
  /****************************************************
  * Shows what adjacent blocks can be captured by a unit
  /****************************************************/
  public void displayObjBlockCaptureOptions(GUnit gUnit){
    Block block = gUnit.getUnit().getLocation();
    Block adjacent[] = block.getAdjacent();
    objOpt = new Block[4];
    for(int i = 0; i < 4; i++){
        if(adjacent[i].getStatus() == 4
        && adjacent[i].getVacant()
        && adjacent[i].getColor()!= gUnit.getUnit().getColor()){
            objOpt[i] = adjacent[i];
            gGrid.getGGrid()[adjacent[i].getColumn()][adjacent[i].getRow()]
                            .changeLineColor(requestColor(4));
        }
    }//end of for i
  }//end of displayObjBlockCaptureOptions
  /****************************************************
  * Make sure selectedBlock is one of the objective block options
  /****************************************************/
  public Block checkInsideObjBlockCapture(int posx, int posy){
    Block block = new Block(-1,-1);
    for(int i = 0; i < 4; i++){
      try {
        if(objOpt[i].getRow() != -1){//objOpt[i].getRow() != -1
          if(gGrid.getGGrid()[objOpt[i].getColumn()][objOpt[i].getRow()].insideBlock(posx, posy)){
            block = objOpt[i];
          }
        }
      }//end of try
      catch(NullPointerException e){}
    }
    return block;
  }
  /****************************************************
  * turn off display for objective block capture options
  /****************************************************/
  public void cancelObjBlockCapture(){
    for(int i = 0; i < 4; i++){
      try {
        if(objOpt[i].getRow() != -1)
        getGBlock()[objOpt[i].getColumn()][objOpt[i].getRow()]
                .changeLineColor(requestColor(1));
      }
      catch(NullPointerException e){}
    }
    objOpt = null;
  }//end of cancelObjBlockCapture
  /****************************************************
  * Changes color of the captured Block
  /****************************************************/
  public void displayCaptureObjective(GBlock obj){
    obj.changeBlockColor(requestColor(500 - obj.getBlock().getColor()));
  }
  /****************************************************
  * Moves the Guardblock visually.
  /****************************************************/
  public void displayMoveGuardBlock(Player player,Block start, Block dest){
    if(dest.getRow() != -1 && dest.getStatus() == 5){
        getGBlock()[dest.getColumn()][dest.getRow()].changeBlockColor(requestColor(600 - (player.getColor())));
        getGBlock()[start.getColumn()][start.getRow()].changeBlockColor(requestColor(start.getColor()));
    }
  }//end of displayMoveGuardBlock
  /****************************************************
  * Used after initialization of the grid.
  * Updates all the graphics, based on color and status
  * It does not consider units
  * But it could be used elsewhere I suppose
  /****************************************************/
  public void updateEntireGrid(){
    Block block;
    for(int y = 0; y < getGGrid().getGrid().getRows();y++){
      for(int x = 0; x < getGGrid().getGrid().getColumns();x++){
        block = getGGrid().getGrid().getCell(x,y);
        if(block.getStatus() == 5){ //GuardBlock
            getGBlock()[x][y].changeBlockColor(requestColor(600 - block.getColor()));
        }
        else if(block.getStatus() == 4){//ObjectiveBlock
            getGBlock()[x][y].changeBlockColor(requestColor(500 - block.getColor()));
        }
        else if(block.getStatus() == 3){//SpawnBlock
            getGBlock()[x][y].changeBlockColor(requestColor(400 - block.getColor()));
        }
        else{
            getGBlock()[x][y].changeBlockColor(requestColor(block.getColor()));
        }
      }//end of x for  loop
    }//end of y for loop
  }//end of updateEntireGrid
  /****************************************************
  * Gives the color needed for different methods
  /****************************************************/
  public Color requestColor(int request){
    Color color = null;
    switch(request){
      case -1: color = new Color(10,10,10); break;//deadBlock

      case 0: color = new Color(255,0,255); break;//selected Block
      case 1: color = new Color(0,0,0); break;//color of the lines normaly, without modifications
      case 2: color = new Color(0,255,255); break;//moveOptions
      case 3: color = new Color(255,128,0); break; //unit skill options
      case 4: color = new Color(200,0,200); break; //ObjectiveBlock Capture options

      case 66: color = new Color(255,255,0); break; //yellow
      case 77: color = new Color(0,255,0); break; //green
      case 88: color = new Color(0,0,255); break; //blue
      case 99: color = new Color(255,0,0); break; //red

      case 100: color = new Color(220,220,220); break;//neutral Block

      case 301: color = new Color(200,100,100); break; //red spawn
      case 312: color = new Color(100,100,200); break; //blue spawn
      case 323: color = new Color(100,200,100); break; //green spawn
      case 334: color = new Color(200,200,100); break; //yellow spawn

      case 400: color = new Color(75,75,75); break; //Dead objective
      case 401: color = new Color(150,0,0); break; //red objective
      case 412: color = new Color(0,0,150); break; //blue objective
      case 423: color = new Color(0,150,0); break; //green objective
      case 434: color = new Color(150,150,0); break; //yellow objective

      case 501: color = new Color(255,200,200);break; //red GuardBlock
      case 512: color = new Color(200,200,255);break; //blue GuardBlock
      case 523: color = new Color(200,255,200);break; //green GuardBlock
      case 534: color = new Color(255,255,200); break; //yellow GuardBlock

      case 1000: color = new Color(200,0,200); break; //true neutral
    }//end of switch request
    return color;
  }//end of requestColor

  /*********************************************
  * Getter Methods
  **********************************************/
  public GGrid getGGrid(){
    return gGrid;
  }
  public GBlock[][] getGBlock(){
    return gGrid.getGGrid();
  }
  public Block[][] getMoveOpt(){
    return moveOpt;
  }
}//end of GAction Class
