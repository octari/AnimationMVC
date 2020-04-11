package cs5004.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
   * Controller
   * @param m the animation model
   * @param v a view interface
   * @param speed the given speed
   * @param viewType the type of the required output view
   * @throws IOException
   */
  public Controller(AnimatorModel m, IView v, int speed, String viewType) throws IOException {
    model = m;
    view = v;
    this.speed = speed;
    switch (viewType) {
      case "visual":
      case "playback":
        int delay = 1000 / this.speed;
        this.timer = new Timer(delay, new Controller.TickActionListener(view));
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
  public void play() {
//    this.timer.start();
    if (view instanceof IPlayBack) {
      ((IPlayBack) view).addFeatures(this);
    while(loopFlag){
      ((IPlayBack) view).addFeatures(this);
      }
    } else if (view instanceof JFrameView) {
      this.timer.start();
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
    timer.stop();
    ActionListener myListenerPB = new Controller.TickActionListener(view);
    int delayPB = 1000 / this.speed;
    this.timer = new Timer(delayPB, myListenerPB);
    timer.start();
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
    loopFlag = true;
    timer.stop();
    ActionListener myListenerPB = new Controller.TickActionListener(view, loopFlag
            , model.getFinalTime());
    int delayPB = 1000 / this.speed;
    this.timer = new Timer(delayPB, myListenerPB);
    timer.start();
  }

  @Override
  public void unloop() {
    loopFlag = false;
    timer.stop();
    ActionListener myListenerPB = new Controller.TickActionListener(view, loopFlag
            , model.getFinalTime());
    int delayPB = 1000 / this.speed;
    this.timer = new Timer(delayPB, myListenerPB);
    timer.start();
  }

  /**
   * TickActionListener implements ActionListener to carry out the JFrameView results.
   */
  private static class TickActionListener implements ActionListener {

    private int currentTick = 0;
    private IView view;
    private boolean loopFlag;
    private double finalTime;

    TickActionListener(IView view) {
      this.view = view;
    }
    TickActionListener(IView view, boolean loopFlag, double finalTime){
      this.view = view;
      this.loopFlag = loopFlag;
      this.finalTime = finalTime;
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
      if(loopFlag && currentTick==(int)finalTime){
        currentTick = 0;
      }else{
        currentTick++;
      }
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
