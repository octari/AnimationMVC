package cs5004.model;

import java.util.List;


/**
 * animator.AnimatorModel is the the model for the Animator made in the MVC structure. Animator is
 * the canvas for shapes; it contains shapes and make changes to them based on given commands.
 */
public interface AnimatorModel extends ReadOnlyModel{

  /**
   * Get a shape to add to the animation model list.
   *
   * @param id        a String that each shape has as a identification
   * @param type      the type of shape created
   * @param appear    appearance time
   * @param disappear disappearance time
   * @param p         a position object represents the position of the shape
   * @param index1    width
   * @param index2    height
   * @param r         red parameter for color
   * @param g         green parameter for color
   * @param b         blue parameter for color
   * @throws IllegalArgumentException throws if invalid input is given.
   */
  void addShape(String id, ShapeType type, double appear, double disappear, Position p,
                double index1, double index2, int r, int g, int b) throws IllegalArgumentException;

  /**
   * add the given motion (change position) to the specified shape according to its id.
   *
   * @param id        a String that each shape has as a identification
   * @param startTime the start time of the motion
   * @param endTime   the end time of the motion
   * @param endPos the end position of the shape
   * @throws IllegalArgumentException throws if invalid input is given.
   */
  void addMove(String id, double startTime, double endTime, Position endPos)
          throws IllegalArgumentException;

  /**
   * add the given motion (change color) to the specified shape according to its id.
   *
   * @param id        a String that each shape has as a identification
   * @param startTime the start time of the motion
   * @param endTime   the end time of the motion
   * @param r         red parameter for color
   * @param g         green parameter for color
   * @param b         blue parameter for color
   * @throws IllegalArgumentException throws if invalid input is given.
   */
  void addColor(String id, double startTime, double endTime, int r, int g, int b)
          throws IllegalArgumentException;

  /**
   * add the given motion (change size) to the specified shape according to its id.
   *
   * @param id        a String that each shape has as a identification
   * @param startTime the start time of the motion
   * @param endTime   the end time of the motion
   * @param index1    the first index for the new position
   * @param index2    the second index for the new position
   * @throws IllegalArgumentException throws if invalid input is given.
   */
  void addScale(String id, double startTime, double endTime, double index1, double index2)
          throws IllegalArgumentException;
}


