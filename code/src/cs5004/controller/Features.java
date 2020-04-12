package cs5004.controller;


/**
 * Features is the interface of a Controller for AnimatorModel.
 */
public interface Features {

  /**
   * Execute a play of valid shapes on canvas.
   */
  int play();

  /**
   * start to display the animation.
   */
  int resume();

  /**
   * pause the current showing animation.
   */
  int pause();

  /**
   * stop and cancel the current play and restart a new one.
   */
  int restart();

  /**
   * Increase the speed by double the current speed.
   */
  int increaseSpeed();

  /**
   * decrease the speed by subtract the current speed by 2.
   */
  int decreaseSpeed();

  /**
   * restart the animation when its end to show the animation as looping till uncheck this choice.
   */
  int loop();

  /**
   * stop the current happening looping and start a one-time play.
   */
  int unloop();


}
