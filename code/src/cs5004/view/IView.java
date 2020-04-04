package cs5004.view;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import cs5004.model.AnimatorModel;
import cs5004.model.ReadOnlyModel;

/**
 * This Iview interface represents a View. A View receives inputs from the Controller and generates
 * an output description of the animation that includes the Shapes in the animation and the Actions
 * performed by the Shapes.
 */
public interface IView {

  /**
   * Display this view of the animation that includes the Shapes that are in the animation and the
   * Actions of each Shape.
   */
  void displayOutPut();

  /**
   * setModel passes the read-only AnimatorModel into view.
   * @param m the read-only AnimatorModel
   */
  void setModel(ReadOnlyModel m);

  /**
   *
   * @return String
   */
  public String getCurrentState();

  void outputFile() throws FileNotFoundException, UnsupportedEncodingException;
}
