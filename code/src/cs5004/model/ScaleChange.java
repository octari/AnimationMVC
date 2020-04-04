package cs5004.model;

/**
 * This class represents the change in scale by two indexs, it has getters for all its attributes.
 */
public class ScaleChange extends AbstractChange {
  private double endIndex1;
  private double endIndex2;

  /**
   * This is the constructor for scale change, it has all parameter an abstract change has and tow
   * more index represent the changed scale.
   * @param id        the Shape perform the change
   * @param startTime the starting time of the motion.
   * @param endTime   the ending time of the motion.
   * @param endIndex1 the end index1
   * @param endIndex2 the end index2
   */
  public ScaleChange(String id, double startTime, double endTime,
                     double endIndex1, double endIndex2) throws IllegalArgumentException {
    super(id, startTime, endTime);
    this.m = Motion.SCALE;
    if (endIndex1 < 0 || endIndex2 < 0) {
      throw new IllegalArgumentException("Invalid position. Indexes need to be positive.");
    }
    this.endIndex1 = endIndex1;
    this.endIndex2 = endIndex2;
  }

  /**
   * The getter for End index1.
   *
   * @return a double represents the end index
   */
  public double getEndIndex1() {
    return endIndex1;
  }

  /**
   * The getter for End index2.
   *
   * @return a double represents the end index
   */
  public double getEndIndex2() {
    return endIndex2;
  }

  @Override
  public String toString() {
    return "(" + endIndex1 + ", " + endIndex2 + ")";
  }
}
