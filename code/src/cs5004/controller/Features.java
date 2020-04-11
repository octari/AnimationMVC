package cs5004.controller;


/**
 * Features is the interface of a Controller for AnimatorModel.
 */
public interface Features {

  /**
   * Execute a play of valid shapes on canvas.
   */
  void play();

  /**
   * start to display the animation.
   */
  void start();

  /**
   * pause the current showing animation.
   */
  void pause();

  /**
   * stop and cancel the current play and restart a new one.
   */
  void restart();

  /**
   * Increase the speed by double the current speed.
   */
  void increaseSpeed();

  /**
   * decrease the speed by subtract the current speed by 2.
   */
  void decreaseSpeed();

  /**
   * restart the animation when its end to show the animation as looping till uncheck this choice.
   */
  void loop();

  /**
   * stop the current happening looping and start a one-time play.
   */
  void unloop();


}
