package cs5004.controller;

import cs5004.model.AnimatorModel;


/**
 * a Controller for AnimatorModel.
 */
public interface IController {

  /**
   * Execute a play of valid shapes on canvas.
   *
   */
  void play(AnimatorModel model);


}
