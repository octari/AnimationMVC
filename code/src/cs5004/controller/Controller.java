package cs5004.controller;

import cs5004.model.AnimatorModel;
import cs5004.view.IPlayBack;
import cs5004.view.IView;
import javax.swing.Timer;

/**
 *
 */
public class Controller implements Features {

  private AnimatorModel model;
  private IView view;
  private Timer timer;

  /**
   *
   * @param m
   * @param v
   */
  public Controller(AnimatorModel m, IView v) {
    model = m;
    view = v;
    ((IPlayBack) view).addFeatures(this);


  }

  @Override
  public void play(AnimatorModel model) {
//    Timer timer = new Timer(500, m);
    timer.start();

  }

}
