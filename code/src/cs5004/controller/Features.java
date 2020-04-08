package cs5004.controller;

import cs5004.model.AnimatorModel;


/**
 * a Controller for AnimatorModel.
 */
public interface Features {

  /**
   * Execute a play of valid shapes on canvas.
   *
   */
  void play(AnimatorModel model);

  void start();
  void pause();
  void resume();
  void restart();
  void increaseSpeed();
  void decreaseSpeed();
  void loop();




}
