package cs5004.model;

/**
 * Shape interface represents all the possible shapes. It includes the type of shape, the position,
 * the index for color(r,g,b) , and the appear and disappear time.
 */

public interface Shape {

  /**
   * Get the appearance of the shape.
   */

  double getAppear();

  /**
   * Get the disappearance time of the shape.
   */

  double getDisappear();

  /**
   * Get the position of the shape.
   */

  Position getPos();

  /**
   * Get the r factor of the shape’s color..
   */

  int getR();

  /**
   * Get the g factor of the shape’s color..
   */

  int getG();

  /**
   * Get the b factor of the shape’s color..
   */

  int getB();

  /**
   * Get the id of the shape.
   */

  String getId();

  /**
   * Get the type of the shape...
   */

  ShapeType getType();

  /**
   * return a copy of the current shape.
   * @return a copy of the current shape.
   */
  Shape copy();

}
