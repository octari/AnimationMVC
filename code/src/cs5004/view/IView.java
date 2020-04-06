package cs5004.view;


import java.io.FileNotFoundException;
import java.io.IOException;
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
   * This method gives the user textual output of desired form.
   *
   * @return String of the output
   */
  public String render();

  /**
   * getCurrentState gets the current status of all the shapes on canvas and returns
   * in form of string.
   *
   * @return String shows the current status of all the shapes on canvas and returns
   * in form of string.
   */
  public String getCurrentState();

  /**
   * outputFile takes the output into a given output file.
   *
   * @throws IOException when output file invalid or notfound.
   */
  void outputFile() throws IOException;
}
