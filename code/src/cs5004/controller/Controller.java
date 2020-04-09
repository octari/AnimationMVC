package cs5004.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cs5004.EasyAnimator;
import cs5004.model.AnimatorModel;
import cs5004.view.IPlayBack;
import cs5004.view.IView;
import cs5004.view.JFrameView;
import cs5004.view.PlaybackView;
import cs5004.view.SVG;
import cs5004.view.TextualView;

import javax.swing.Timer;

/**
 *
 */
public class Controller implements Features {
  private AnimatorModel model;
  private IView view;
  private Timer timer;
  private int speed;
  private boolean loopFlag;

  /**
   * @param m
   * @param v
   */
  public Controller(AnimatorModel m, IView v, int speed, String viewType) throws IOException {
    model = m;
    view = v;
    this.speed = speed;
    switch (viewType) {
      case "visual":
        ActionListener myListener = new Controller.TickActionListener((JFrameView) view);
        int delay = 1000 / this.speed;
        this.timer = new Timer(delay, myListener);
        break;
      case "svg":
        ((SVG) view).outputFile();
        break;
      case "text":
        ((TextualView) view).outputFile();
        break;
      case "playback":
        ActionListener myListenerPB = new Controller.TickActionListener((PlaybackView) view);
        int delayPB = 1000 / this.speed;
        this.timer = new Timer(delayPB, myListenerPB);
        break;
      default:
        throw new IllegalStateException("not a valid view");
    }
  }

  @Override
  public void play() {
//    this.timer.start();
    ((IPlayBack) view).addFeatures(this);
    while(loopFlag){
      ((IPlayBack) view).addFeatures(this);
    }
    // check action performed in timer reset/stop?
  }

  @Override
  public void start() {
    timer.start();
  }

  @Override
  public void pause() {
    timer.stop();
  }

//  @Override
//  public void resume() {
//
//  }

  @Override
  public void restart() {
    timer.restart();
  }

  @Override
  public void increaseSpeed() {
    speed *= 2;
    timer.setDelay(1000/speed);
  }

  @Override
  public void decreaseSpeed() {
    speed /= 2;
    timer.setDelay(1000/speed);
  }

  @Override
  public void loop() {
//    timer.start();???
    loopFlag = !loopFlag;
  }

  /**
   * TickActionListener implements ActionListener to carry out the JFrameView results.
   */
  private static class TickActionListener implements ActionListener {

    private int currentTick = 0;
    private IView view;

    TickActionListener(IView view) {
      this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(view instanceof JFrameView){
        ((JFrameView)view).setCurrentTick(currentTick);
        ((JFrameView)view).refresh();
      }else if(view instanceof PlaybackView){
        ((PlaybackView)view).setCurrentTick(currentTick);
        ((PlaybackView)view).refresh();
      }
      currentTick++;
    }

//    /**
//     * TickActionListener implements ActionListener to carry out the JFrameView results.
//     */
//    private static class TickActionListenerPB implements ActionListener {
//
//      private int currentTick = 0;
//      private PlaybackView view;
//
//      TickActionListenerPB(PlaybackView view) {
//        this.view = view;
//      }
//
//      @Override
//      public void actionPerformed(ActionEvent e) {
//
//        view.setCurrentTick(currentTick);
//        view.refresh();
//        currentTick++;
//      }
//    }
  }
}
