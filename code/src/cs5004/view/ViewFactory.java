package cs5004.view;

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
  public static IView makeView (String type, ReadOnlyModel am, String out) throws IllegalArgumentException {

    switch (type) {
      case "JFrame":
        return new JFrameView("JFrameView", am);
      case "SVG":
        return new SVG();
      case "textView":
        return new TextualView();
      default:
        throw new IllegalArgumentException("Invalid view type.");
    }

  }

}
