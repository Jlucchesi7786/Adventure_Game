
/**
 * This class holds position data on the frame in terms of x and y. This class is meant to 
 * be used with pretty much everything except for individual tiles, which hold the symbol
 * that's supposed to be there. The two work hand in hand in the Compiler class to make
 * the frame print the way it's supposed to.
 * @author Dr. Cheese
 */
public class Position {
  public int x; // stores the x position
  public int y; // stores the y position

  /**
   * This constructor sets up the Position to store one point initially that can then be 
   * modified or changed.
   * @param x The x position to be stored initially (int).
   * @param y The y position to be stored initially (int).
   */
  public Position(int x, int y) {
    this.x = x; // stores the initial x position
    this.y = y; // stores the initial y position
  }

  /**
   * This method changes specifically the x position, but not the y for ease of use.
   * @param newX the x position that the old x will be updated to (int).
   */
  public void updateX(int newX) {
    this.x = newX; // changes the x instance variable to the new x
  }

  /**
   * This method changes specifically the y position, but not the x for ease of use.
   * @param newY the y position that the old y will be updated to (int).
   */
  public void updateY(int newY) {
	  this.y = newY; // changes the y instance variable to the new y
  }

  /**
   * This method changes both x and y, to store an entirely new position.
   * @param newX the x position that the old x will be updated to (int).
   * @param newY the y position that the old y will be updated to (int).
   */
  public void updatePosition(int newX, int newY) {
    this.x = newX; // updates the x instance variable with newX
    this.y = newY; // does the same for y and newY
  }
  
  /**
   * This method flips the values of the stored x and y, effectively flipping it over
   * a diagonal line through the graph.
   */
  public void flipPosition() {
	  int a = this.x; // holds the x value
	  this.x = this.y; // sets the x value to the old y value
	  this.y = a; // sets the y value to the old x value using the placeholder
  }
  
  public void init(Room[] rooms, int roomnum) {
	  if (roomnum > 0) {
		  this.x += rooms[roomnum-1].width+1;
		  this.y += 0;//rooms[roomnum].height;
	  }
  }
  
  /**
   * This method checks if two position objectss hold the same data, or in easier 
   * terms, if they are holding the same position.
   * @param other another Position object to compare with this one.
   * @return true if they hold the same position, or false if otherwise.
   */
  public boolean equals(Position other) {
	  if (this.x == other.x && this.y == other.y) {
		  return true;
	  }
	  return false;
  }
  
  /**
   * This toString() method puts the x and y in a readable format, then returns that.
   */
  public String toString() {
	  return "x: " + this.x + "   y: " + this.y;
  }
  
  /**
   * This method is there for if I ever want to make the x instance variable private.
   * @return the x position the object is holding at the time.
   */
  public int getX() {
	  return x;
  }
  
  /**
   * This method is there for if I ever want to make the y instance variable private.
   * @return the y position the object is holding at the time.
   */
  public int getY() {
	  return y;
  }
}
