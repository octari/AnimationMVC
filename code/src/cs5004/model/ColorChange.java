package cs5004.model;

/**
 * ColorChange represents the function of changing color of the shape by modifying the rgb factors.
 * It contains getters for each attributes.
 */
public class ColorChange extends AbstractChange {
  int endR;
  int endG;
  int endB;

  /**
   * ColorChange object is the function of changing color of the shape by modifying the rgb
   * factors.
   * @param id        the Shape perform the change
   * @param startTime the starting time of the motion.
   * @param endTime   the ending time of the motion.
   * @param endR      the resulting r factor.
   * @param endG      the resulting g factor.
   * @param endB      the resulting b factor.
   * @exception IllegalArgumentException throws when invalid input is given.
   */
  public ColorChange(String id, double startTime, double endTime, int endR, int endG, int endB)
          throws IllegalArgumentException {
    super(id, startTime, endTime);
    this.m = Motion.COLOR;
    if (endR < 0 || endG < 0 || endB < 0 || endR > 255 || endG > 255 || endB > 255) {
      throw new IllegalArgumentException("RGB factor out of range.");
    }
    this.endR = endR;
    this.endG = endG;
    this.endB = endB;
  }

  /**
   * getEndR gets the r factor.
   *
   * @return r factor
   */
  public int getEndR() {
    return endR;
  }

  /**
   * getEndR gets the g factor.
   *
   * @return g factor
   */
  public int getEndG() {
    return endG;
  }

  /**
   * getEndR gets the b factor.
   *
   * @return b factor
   */
  public int getEndB() {
    return endB;
  }

}
