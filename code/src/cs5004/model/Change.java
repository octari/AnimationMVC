package cs5004.model;

/**
 * Change interface represents all the possible changes on a shape. It includes the type of motion
 * and the start and end time.
 */

public interface Change {
  /**
   * Get id of corresponding Shape.
   * @return String represent the Shape perform the change
   */
  String getId();

  /**
   * Get the type of the motion .
   * @return Motion represent the kind of change
   */
  Motion getMotion();

  /**
   * Get the start time of the motion.
   * @return double represent the start time
   */
  double getStartTime();

  /**
   * Get the end time of the motion.
   * @return double represent the end time
   */
  double getEndTime();
}
