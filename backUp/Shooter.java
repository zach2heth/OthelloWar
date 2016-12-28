public class Shooter extends Unit{
/*Change the color of a tile that is exactly two spaces away (skips adjacent tiles)
/*Subsequently destroying enemy that is on the tile
/*This unit should balance the defensive nature of the game //unconfirmed statement right there
/*Honestly it seems OP. Should be nerfed.
/*Nerf Options: Decrease range options, add ammo limit(destroyed when empty), doesn't kill only CCs
*/
  public Shooter(Block loc, int clr){
    location  = loc;
    color = clr;
    unitKey = 4;
  }
//To implement the diagonal /two space shot
//  Get both blocks col/row and make sure difference is exactly 2
}

/*
T T R T T
T R T R T
R T S T R
T R T R T
T T R T T

S = Shooter
R = In range
T = not in range (tile)
*/
