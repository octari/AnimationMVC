package cs5004.model;


/**
 * PosChange represents the function of changing position of the shape. It contains getters for each
 * attributes.
 */
public class PosChange extends AbstractChange {
  Position endPos;

  /**
   * PosChange object is the function of changing position of the shape.
   *
   * @param id        the Shape perform the change
   * @param startTime the starting time of the motion.
   * @param endTime   the ending time of the motion.
   * @param endPos    the resulting position
   */
  public PosChange(String id, double startTime, double endTime, Position endPos) {
    super(id, startTime, endTime);
    this.m = Motion.MOVE;
    this.endPos = endPos;
  }

  /**
   * getEndPos get the resulting position.
   *
   * @return the resulting position
   */
  public Position getEndPos() {
    return endPos;
  }
}
