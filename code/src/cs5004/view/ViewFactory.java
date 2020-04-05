package cs5004.view;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import cs5004.model.ReadOnlyModel;

/**
 * Implement a factory of views, with a single static method that takes in a String name for a view
 * —“text”, “svg”, or “visual”— and constructs an instance of the appropriate concrete view.
 */
public class ViewFactory {

  /**
   * makeView takes in the type of view the client want and throws exception if type invalid.
   * @param type a string represent the type of view
   * @return a view
   * @throws IllegalArgumentException if type invalid.
   */
  public static IView makeView (String type, ReadOnlyModel am, String out, int speed)
          throws IllegalArgumentException, FileNotFoundException, UnsupportedEncodingException {

    switch (type) {
      case "JFrame":
        return new JFrameView("JFrameView", am);
      case "SVG":
        return new SVG(am, out, speed);
      case "textView":
        return new TextualView(am, out);
      default:
        throw new IllegalArgumentException("Invalid view type.");
    }

  }

}
