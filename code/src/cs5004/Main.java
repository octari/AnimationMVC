package cs5004;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.Timer;

import cs5004.model.AnimatorModel;
import cs5004.model.AnimatorImpl;
import cs5004.util.AnimationReader;
import cs5004.view.IView;
import cs5004.view.JFrameView;
import cs5004.view.ViewFactory;

/**
 * This is the main body
 */
public class Main  {

  public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
//    -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"
    int i = 0;
    String nameOfFile = null;
    String view = null;
    String out = null;
    int tick = 0;
    while(i < args.length){
      if(args[i].equals("-in")){
        nameOfFile = args[i+1];
      }else if(args[i].equals("-view")){
        view = args[i+1];
      }else if(args[i].equals("-out")){
        out = args[i+1];
      }else if(args[i].equals("-speed")){
        tick = Integer.parseInt(args[i+1]);
      }
      i+=2;
    }
    if(out == null){
      out = "SysOut";
    } else if(tick == 0){
      tick = 1;
    } else if(nameOfFile == null || view == null){
      throw new IllegalArgumentException("name of animation file and view can't be null");
    }
    File f = new File(nameOfFile);
    FileReader fr = new FileReader(f);
    AnimatorModel am = AnimationReader.parseFile(fr, new AnimatorImpl.Builder());
    // user command line arg: text, svg, visual
    IView viewM = ViewFactory.makeView(view, am, out);

    ActionListener myListener = new TickActionListener((JFrameView) viewM);
    Timer t = new Timer(1000/tick, myListener);
    t.start();
  }
}

class TickActionListener implements ActionListener {

  private int currentTick = 0;
  private JFrameView view;

  TickActionListener(JFrameView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    view.displayOutPut();
    view.setCurrentFrame(view.getM().getShapesAt(currentTick));
    view.refresh();


    currentTick++;
  }
}