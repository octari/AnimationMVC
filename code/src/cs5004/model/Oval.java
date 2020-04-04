package cs5004.model;

/**
 * This class represents an Oval, which has radius1 and radius2 other than all AbstractShape's
 * fields, its method also have the corresponding getters.
 */
public class Oval extends AbstractShape {
  private double radius1;
  private double radius2;

  /**
   * This is the constructor for oval, which has two more parameters: radius1 and radius2.
   *
   * @param id        a String that each shape has as a identification
   * @param type      the type of shape created
   * @param appear    appearance time
   * @param disappear disappearance time
   * @param r         red parameter for color
   * @param g         green parameter for color
   * @param b         blue parameter for color
   * @param p         a position object represents the position of the shape
   * @param radius1   one radius of an oval
   * @param radius2   another radius of an oval
   * @exception IllegalArgumentException throws if invalid input is given.
   */
  public Oval(String id, ShapeType type, double appear, double disappear,
              int r, int g, int b, Position p, double radius1, double radius2)
          throws IllegalArgumentException {
    super(id, type, appear, disappear, r, g, b, p);
    if (radius1 < 0 || radius2 < 0) {
      throw new IllegalArgumentException("Invalid radius. Need to be positive.");
    }
    this.radius1 = radius1;
    this.radius2 = radius2;
  }

  /**
   * This is the getter for radius1.
   *
   * @return a String that oval has
   */
  public double getRadius1() {
    return radius1;
  }

  /**
   * This is the getter for radius2.
   *
   * @return a String that oval has
   */
  public double getRadius2() {
    return radius2;
  }

  @Override
  public String toString() {
    return "(" + radius1 + ", " + radius2 + ")";
  }

  @Override
  public Shape copy() {
    Oval newO = new Oval(this.getId(), this.getType(), this.getAppear(),
            this.getDisappear(), this.getR(), this.getG(), this.getB(), this.getPos(),
            radius1, radius2);
    return newO;
  }
}
