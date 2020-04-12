package cs5004.model;

import java.util.List;
import java.util.Map;

/**
 *  animator.ReadOnlyModel is the the model for a readonly Animator made in the MVC structure for
 *  connecting model and view.
 */
public interface ReadOnlyModel {
  /**
   * Prints the shapes and animations in string format.
   *
   * @return a string listing out the shapes and animations
   */
  String getState();

  /**
   * Returns a list of Shapes in the AnimationModel at a certain time.
   *
   * @param tick the specific time
   * @return listOfShapes a list of Shapes in the AnimationModel
   */
  List<Shape> getShapesAt(double tick);

  /**
   * Returns a Shape in the AnimationModel at a certain time.
   *
   * @param s the shape to get the status
   * @param tick the specific time
   * @return Shape an shape object
   */
  Shape getShapeAt(Shape s, double tick);

  /**
   * get the existing shapes as a list.
   *
   * @return a list of shape
   */
  List<Shape> getShapes();

  /**
   * get the canvas of the animator
   * @return a canvas object
   */
  Canvas getCanvas();

  /**
   * get the pairing of shape and all its chagnes
   * @return a map whose key is Shape and value is list of Changes corresponding to that shape
   */
  Map<Shape, List<Change>> getMap();

  /**
   * get the end time for the whole animation
   * @return a double represents the final time of the animation
   */
  double getFinalTime();

  /**
   * get a list of change from the given shape id.
   *
   * @param id the id of the requested shape
   */
  List<Change> getChange(String id);
}

