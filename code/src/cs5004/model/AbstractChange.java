package cs5004.model;

/**
 * This is the abstract class for changes, it has common fields and getters for all motions.
 */
public abstract class AbstractChange implements Change {
  private String id;
  Motion m;
  private double startTime;
  private double endTime;

  /**
   * This is the constructor for abstract change.
   * @param id        the Shape perform the change
   * @param startTime change start time
   * @param endTime   change end time
   * @throws  IllegalArgumentException when invalid time or invalid id.
   */
  AbstractChange(String id, double startTime, double endTime)
          throws IllegalArgumentException {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Invalid id.");
    }
    if (startTime >= endTime || startTime < 0) {
      throw new IllegalArgumentException("start time should before end time and be positive num.");
    }
    this.id = id;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Motion getMotion() {
    return m;
  }

  @Override
  public double getStartTime() {
    return startTime;
  }

  @Override
  public double getEndTime() {
    return endTime;
  }
}
