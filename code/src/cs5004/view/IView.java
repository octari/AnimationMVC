package cs5004.view;


import java.io.IOException;


/**
 * This Iview interface represents a View. A View receives inputs from the Controller and generates
 * an output description of the animation that includes the Shapes in the animation and the Actions
 * performed by the Shapes.
 */
public interface IView {


  /**
   * This method gives the user textual output of desired form.
   *
   * @return String of the output
   */
   String render();

  /**
   * getCurrentState gets the current status of all the shapes on canvas and returns
   * in form of string.
   *
   * @return String shows the current status of all the shapes on canvas and returns
   * in form of string.
   */
   String getCurrentState();

  /**
   * outputFile takes the output into a given output file.
   *
   * @throws IOException when output file invalid or notfound.
   */
  void outputFile() throws IOException;
}
