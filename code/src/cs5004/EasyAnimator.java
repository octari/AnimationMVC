package cs5004;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.Timer;

import cs5004.model.AnimatorImpl;
import cs5004.model.ReadOnlyModel;
import cs5004.util.AnimationReader;
import cs5004.view.IView;
import cs5004.view.JFrameView;
import cs5004.view.SVG;
import cs5004.view.TextualView;
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
    // user command line arg: text, svg, visual
    IView viewM = ViewFactory.makeView(view, am, out, speed);
    switch (view) {
      case "visual":
        ActionListener myListener = new TickActionListener((JFrameView) viewM);
        int delay = 1000 / speed;
        Timer t = new Timer(delay, myListener);
        t.start();
        break;
      case "svg":
        ((SVG) viewM).outputFile();
        break;
      case "text":
        ((TextualView) viewM).outputFile();
        break;
      case "playback":
//        ((PlaybackView) viewM);
      default:
        throw new IllegalStateException("not a valid view");
    }
  }

  /**
   * TickActionListener implements ActionListener to carry out the JFrameView results.
   */
  private static class TickActionListener implements ActionListener {

    private int currentTick = 0;
    private JFrameView view;

    TickActionListener(JFrameView view) {
      this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      view.setCurrentTick(currentTick);
      view.refresh();


      currentTick++;


    }
  }
}

