package cs5004.model;

/**
 * This is the abstract class for all shapes it has all the common fields and methods of shape.
 */
public abstract class AbstractShape implements Shape {
  private String id;
  private ShapeType type;
  private double appear;
  private double disappear;
  private int r;
  private int g;
  private int b;
  private Position p;

  /**
   * This is the constructor for abstract shape.
   *
   * @param id        a String that each shape has as a identification.
   * @param type      the type of shape created
   * @param appear    appearance time
   * @param disappear disappearance time
   * @param r         red parameter for color
   * @param g         green parameter for color
   * @param b         blue parameter for color
   * @param p         a position object represents the position of the shape
   * @throws IllegalArgumentException when invalid time or invalid id.
   */
  AbstractShape(String id, ShapeType type, double appear, double disappear,
                int r, int g, int b, Position p) throws IllegalArgumentException {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("invalid id.");
    }
    if (appear >= disappear || appear < 0) {
      throw new IllegalArgumentException("Appear should before disappear and be positive num.");
    }
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("RGB factor out of range.");
    }
    this.type = type;
    this.appear = appear;
    this.disappear = disappear;
    this.r = r;
    this.g = g;
    this.b = b;
    this.p = p;
    this.id = id;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public Position getPos() {
    return p;
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public int getB() {
    return b;
  }

  @Override
  public double getAppear() {
    return appear;
  }

  @Override
  public double getDisappear() {
    return disappear;
  }

  @Override
  public ShapeType getType() {
    return type;
  }
}
