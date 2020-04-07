package cs5004.model;

/**
 * This class represents a rectangle, which has width and height other than all AbstractShape's
 * fields, its method also have the corresponding getters.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * This is the constructor for rectangle object.
   *
   * @param id        a String that each shape has as a identification
   * @param type      the type of shape created
   * @param appear    appearance time
   * @param disappear disappearance time
   * @param r         red parameter for color
   * @param g         green parameter for color
   * @param b         blue parameter for color
   * @param p         a position object represents the position of the shape
   * @param width     a double represents the width of rectangle
   * @param height    a double represents teh height of the rectangle
   * @exception IllegalArgumentException throws if invalid input is given.
   */
  public Rectangle(String id, ShapeType type, double appear, double disappear, int r, int g, int b,
                   Position p, double width, double height) throws IllegalArgumentException {
    super(id, type, appear, disappear, r, g, b, p);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid radius. Need to be positive.");
    }
    this.height = height;
    this.width = width;
  }

  /**
   * This is the getter for width.
   *
   * @return a double represents the width
   */
  public double getWidth() {
    return width;
  }

  /**
   * This is the getter for height.
   *
   * @return a double represents the height
   */
  public double getHeight() {
    return height;
  }

  @Override
  public String toString() {
    return "(" + width + ", " + height+ ")";

  }

  @Override
  public Shape copy() {
    Rectangle newR = new Rectangle(this.getId(), this.getType(), this.getAppear(),
            this.getDisappear(), this.getR(), this.getG(), this.getB(), this.getPos(),
            width, height);
    return newR;
  }
}
