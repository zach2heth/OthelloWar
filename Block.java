/**********************************************
* Zachary Heth 2016
**********************************************
*  Block Class
*     The basis for everything in this project
*     A block is a position on this grid that has the following qualities
*       Color: What team is in possession of the block
*       Status: The type of block giving it different properties
*           See key.txt fro details
*     A block can hold units of the same color
**********************************************/
public class Block{
  boolean vacant; //true if the block is not occupied by a unit
  int status;
  int prevStatus; //important if block is "covered" by GuardBlock
  int color;
  int prevColor;
  int row;
  int column;
  Block N; //Block North of this one
  Block E; //Block East of this one
  Block S; //Block South of this one
  Block W; //Block of West this one
  Unit unit;
  public Block(int col,int rw){
    status = 1;
    prevStatus = 1;
    color = 100;
    row = rw;
    column = col;
    vacant = true;
  }
/*********************************************
* All the methods below are setters/getters for the member variables
**********************************************/
  public void setVacant(boolean vac){
    vacant = vac;
  }
  public void setStatus(int stat){
    status = stat;
  }
  public void setPrevStatus(int stat){
    prevStatus = stat;
  }
  public void setColor(int clr){
    color = clr;
  }
  public void setPrevColor(int clr){
    prevColor = clr;
  }
  public void setUnit(Unit unt){
    unit = unt;
    if(unit != null){
      vacant = false;
    }
    else{
      vacant = true;
    }
  }
  public void setRow(int rw){
    row = rw;
  }
  public void setColumn(int col){
    column = col;
  }
  public void setN(Block b){
    N = b;
  }
  public void setE(Block b){
    E = b;
  }
  public void setS(Block b){
    S = b;
  }
  public void setW(Block b){
    W = b;
  }
  public boolean getVacant(){
    return vacant;
  }
  public int getStatus(){
    return status;
  }
  public int getPrevStatus(){
    return prevStatus;
  }
  public int getColor(){
    return color;
  }
  public int getPrevColor(){
    return prevColor;
  }
  public int getRow(){
    return row;
  }
  public int getColumn(){
    return column;
  }
  public Unit getUnit(){
    return unit;
  }
  public Block getN(){
    return N;
  }
  public Block getE(){
    return E;
  }
  public Block getS(){
    return S;
  }
  public Block getW(){
    return W;
  }
  public Block[] getAdjacent(){
    Block[] adjacent = new Block[4];
    Block nullBlock = new Block(-1,-1);
    for(int i = 0; i < 4; i++){
      adjacent[i] = nullBlock;
    }
    if(hasN()){
      adjacent[0] = N;
    }
    if(hasE()){
      adjacent[1] = E;
    }
    if(hasS()){
      adjacent[2] = S;
    }
    if(hasW()){
      adjacent[3] = W;
    }
    return adjacent;
  }
  /*********************************************
  *True if adjblock is adjacent to this block
  **********************************************/
  public boolean checkAdjacent(Block adjblock){
    boolean adjacent = false;
    if(hasN()){
      if(N == adjblock)
        adjacent = true;
    }
    if(hasE()){
      if(E == adjblock)
        adjacent = true;
    }
    if(hasS()){
      if(S == adjblock)
        adjacent = true;
    }
    if(hasW()){
      if(W == adjblock)
        adjacent = true;
    }
    return adjacent;
  }
  public boolean hasN(){
    boolean hasBlock = true;
    if(N!=null){}
    else{hasBlock = false;}
    return hasBlock;
  }
  public boolean hasE(){
    boolean hasBlock = true;
    if(E!=null){}
    else{hasBlock = false;}
    return hasBlock;
  }
  public boolean hasS(){
    boolean hasBlock = true;
    if(S!=null){}
    else{hasBlock = false;}
    return hasBlock;
  }
  public boolean hasW(){
    boolean hasBlock = true;
    if(W!=null){}
    else{hasBlock = false;}
    return hasBlock;
  }
  public boolean hasUnit(){
    boolean hasunit = false;
    if(vacant == false){
      hasunit = true;
    }
    return hasunit;
  }
}
