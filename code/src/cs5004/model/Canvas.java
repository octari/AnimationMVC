package cs5004.model;

/**
 * This the the Canvas of the output. It has a point and size.
 */
public class Canvas {
  private int x, y, width, height;

  /**
   * This is the contructor for canvas, it initialize the property of the canvas.
   * @param x
   * @param y
   * @param width
   * @param height
   */
  public Canvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  void setX(int x) {
    this.x = x;
  }

  void setY(int y) {
    this.y = y;
  }

  void setWidth(int width) {
    this.width = width;
  }

  void setHeight(int height) {
    this.height = height;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}

