package cs5004.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cs5004.model.AnimatorModel;
import cs5004.view.IPlayBack;
import cs5004.view.IView;
import cs5004.view.JFrameView;
import cs5004.view.PlaybackView;
import cs5004.view.SVG;
import cs5004.view.TextualView;

import javax.swing.Timer;

/**
 * Controller class implements Features and all the corresponding methods.
 */
public class Controller implements Features {
  private AnimatorModel model;
  private IView view;
  private Timer timer;
  private int speed;
  private boolean loopFlag;

  /**
   * A Controller for a visual view that the user can interact with.
   *
   * @param m        the animation model
   * @param v        a view interface
   * @param speed    the given speed
   * @param viewType the type of the required output view
   * @throws IOException throes when file not found
   */
  public Controller(AnimatorModel m, IView v, int speed, String viewType) throws IOException {
    model = m;
    view = v;
    this.speed = speed;
    switch (viewType) {
      case "visual":
      case "playback":
        int delay = 1000 / this.speed;
        this.timer = new Timer(delay, new Controller.TickActionListener(view, loopFlag,
                model.getFinalTime()));
        break;
      case "svg":
        ((SVG) view).outputFile();
        break;
      case "text":
        ((TextualView) view).outputFile();
        break;
      default:
        throw new IllegalStateException("not a valid view");
    }
  }

  @Override
  public int play() {
    if (view instanceof IPlayBack) {
      ((IPlayBack) view).addFeatures(this);
      while (loopFlag) {
        ((IPlayBack) view).addFeatures(this);
      }
    } else if (view instanceof JFrameView) {
      this.timer.start();
    }
    return 0;
  }

  @Override
  public int resume() {
    timer.start();
    return 0;
  }

  @Override
  public int pause() {
    timer.stop();
    return 0;
  }

  @Override
  public int restart() {
    timer.stop();
    ActionListener myListenerPB = new Controller.TickActionListener(view, loopFlag,
            model.getFinalTime());
    int delayPB = 1000 / this.speed;
    this.timer = new Timer(delayPB, myListenerPB);
    timer.start();
    return 0;
  }

  @Override
  public int increaseSpeed() {
    speed *= 2;
    timer.setDelay(1000 / speed);
    return 0;
  }

  @Override
  public int decreaseSpeed() {
    speed /= 2;
    timer.setDelay(1000 / speed);
    return 0;
  }

  @Override
  public int loop() {
    loopFlag = true;
    timer.stop();
    ActionListener myListenerPB = new Controller.TickActionListener(view, loopFlag,
            model.getFinalTime());
    int delayPB = 1000 / this.speed;
    this.timer = new Timer(delayPB, myListenerPB);
    timer.start();
    return 0;
  }

  @Override
  public int unloop() {
    loopFlag = false;
    timer.stop();
    ActionListener myListenerPB = new Controller.TickActionListener(view, loopFlag,
            model.getFinalTime());
    int delayPB = 1000 / this.speed;
    this.timer = new Timer(delayPB, myListenerPB);
    timer.start();
    return 0;
  }

  /**
   * TickActionListener implements ActionListener to carry out the JFrameView results.
   */
  private static class TickActionListener implements ActionListener {

    private int currentTick = 0;
    private IView view;
    private boolean loopFlag;
    private double finalTime;

    TickActionListener(IView view, boolean loopFlag, double finalTime) {
      this.view = view;
      this.loopFlag = loopFlag;
      this.finalTime = finalTime;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (view instanceof JFrameView) {
        ((JFrameView) view).setCurrentTick(currentTick);
        ((JFrameView) view).refresh();
      } else if (view instanceof PlaybackView) {
        ((PlaybackView) view).setCurrentTick(currentTick);
        ((PlaybackView) view).refresh();
      }
      if (loopFlag && currentTick == (int) finalTime) {
        currentTick = 0;
      } else {
        currentTick++;
      }
    }

  }
}
