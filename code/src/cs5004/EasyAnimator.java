package cs5004;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



import cs5004.controller.Controller;
import cs5004.controller.Features;
import cs5004.model.AnimatorImpl;
import cs5004.model.AnimatorModel;
import cs5004.model.ReadOnlyModel;
import cs5004.util.AnimationReader;
import cs5004.view.IView;
import cs5004.view.ViewFactory;

/**
 * EasyAnimator class takes the main body of executing the program.
 *
 * @author Furong Tian, Yuchen Ji
 */
public class EasyAnimator {

  /**
   * Main function is for actually executing the program and get the expected view results.
   *
   * @param args inout arguments including input filename, view type, and output filename.
   * @throws FileNotFoundException        throws when file not found.
   * @throws IllegalArgumentException     throws when command line argument is invalid.
   * @throws UnsupportedEncodingException throws when Encoding code unsupported.
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException {
    int i = 0;
    String nameOfFile = "";
    String view = "";
    String out = "";
    int speed = 0;
    while (i < args.length) {
      switch (args[i]) {
        case "-in":
          nameOfFile = args[i + 1];
          break;
        case "-view":
          view = args[i + 1];
          break;
        case "-out":
          out = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;
        default:
          throw new IllegalArgumentException("invalid command line arguments.");
      }
      i += 2;
    }
    if (out.equals("")) {
      out = "SysOut";
    }
    if (speed == 0) {
      speed = 1;
    }
    if ((nameOfFile == null) || (view == null)) {
      throw new IllegalArgumentException("name of animation file and view can't be null");
    }
    File f = new File(nameOfFile);
    FileReader fr = new FileReader(f);
    ReadOnlyModel am = AnimationReader.parseFile(fr, new AnimatorImpl.Builder());

    IView viewM = ViewFactory.makeView(view, am, out, speed);
    Features controller = new Controller((AnimatorModel) am, viewM, speed, view);
    controller.play();
  }


}

