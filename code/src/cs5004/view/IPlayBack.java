package cs5004.view;

import cs5004.controller.Features;

/**
 * IPlayBack interface extends IView. one method addFeatures has been added.
 */
public interface IPlayBack extends IView {

  void addFeatures(Features features);

}
