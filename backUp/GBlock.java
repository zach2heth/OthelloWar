/**********************************************
* Zachary Heth 2016
**********************************************
* GBlock Class
*     Graphics for the block class
**********************************************/
import java.awt.Color;
public class GBlock{

  final int LINE_SIZE = 2; //Thickness of the line
  EZLine NLine;
  EZLine ELine;
  EZLine SLine;
  EZLine WLine;
  EZRectangle Fill;//square that will fill the Block, used for color changing
  Block block;
  GUnit gUnit;

  /*********************************************
  * Dimentions is the fraction, i.e how many you want in total in a row/column
  * gridSize is the total length of the grid.
  * To ensure square shaped blocks, gridSize will be dealt with in the GGrid class(or the map creation class)
  * shiftX/Y used to move the grid position on the screen
  **********************************************/
  GBlock(int Dimensions,int gridSize,Block blck,int shiftX,int shiftY){
      block = blck;
      int x = block.getColumn() + shiftX;
      int y = block.getRow() + shiftY;
      NLine =	EZ.addLine(x*gridSize/Dimensions,y*gridSize/Dimensions,
            (x+1)*gridSize/Dimensions, y*gridSize/Dimensions,
            new Color(0,0,0), LINE_SIZE);
      SLine =	EZ.addLine(x*gridSize/Dimensions,(y+1)*gridSize/Dimensions,
            (x+1)*gridSize/Dimensions, (y+1)*gridSize/Dimensions,
            new Color(0,0,0), LINE_SIZE);
      WLine =	EZ.addLine(x*gridSize/Dimensions,y*gridSize/Dimensions,
            x*gridSize/Dimensions, (y+1)*gridSize/Dimensions,
            new Color(0,0,0), LINE_SIZE);
      ELine =	EZ.addLine((x+1)*gridSize/Dimensions,y*gridSize/Dimensions,
            (x+1)*gridSize/Dimensions, (y+1)*gridSize/Dimensions,
            new Color(0,0,0), LINE_SIZE);
      Fill =  EZ.addRectangle((LineE()+LineW())/2,(LineN()+LineS())/2,
              LineE()-LineW()-2, LineS()-LineN()-2,new Color(255,255,255), true);
  } //end of GBlock contrusctor
/*******************************************
/* Methods to change color of block/lines of block
/*******************************************/
  public void changeBlockColor(Color color){
    Fill.color = color;
  }
  public void changeLineColor(Color color){
    NLine.color = color; NLine.pullToFront();
    ELine.color = color; ELine.pullToFront();
    SLine.color = color; SLine.pullToFront();
    WLine.color = color; WLine.pullToFront();
  }

  /*******************************************
  * Methods to get line lengths
  ********************************************/
  public int LineNLength(){
    return NLine.getWidth();
  }
  public int LineELength(){
    return ELine.getHeight();
  }
  public int LineSLength(){
    return SLine.getWidth();
  }
  public int LineWLength(){
    return WLine.getHeight();
  }
/*******************************************
* Methods to get block location on the screen
********************************************/
  public int LineN(){
		  int NCenter = NLine.getYCenter();
	  	return NCenter;
	}
	public int LineE(){
  		int ECenter = ELine.getXCenter();
  		return ECenter;
	}
	public int LineS(){
  		int SCenter = SLine.getYCenter();
  		return SCenter;
	}
	public int LineW(){
  		int WCenter = WLine.getXCenter();
  		return WCenter;
	}

  public boolean insideBlock(int x, int y){
    //x and y is the current position of the mouse
  		boolean inside = false;
  		if(x>LineW() && x<LineE() && y<LineS() && y>LineN()){
  			    inside = true;
  		}
  		return inside;
	}
  public int[] blockCenter(){
    int[] center = new int[2];
    center[0] = (LineE() + LineW())/2;
    center[1] = (LineN() + LineS())/2;
    return center;
  }
  public void setGUnit(GUnit gU){
    gUnit = gU;
  }
  public GUnit getGUnit(){
    return gUnit;
  }
  public Block getBlock(){
    return block;
  }

  public void hideBlock(){
    NLine.hide();
    ELine.hide();
    SLine.hide();
    WLine.hide();
  }
  public void showBlock(){
    NLine.show(); NLine.pullToFront();
    ELine.show(); ELine.pullToFront();
    SLine.show(); SLine.pullToFront();
    WLine.show(); WLine.pullToFront();
  }
}//end of class
