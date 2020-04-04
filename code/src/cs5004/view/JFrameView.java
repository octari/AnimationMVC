package cs5004.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.model.AnimatorImpl;
import cs5004.model.ReadOnlyModel;
import cs5004.model.Shape;


/**
 * JFrameView represents a view for a simple Visual Animation model for this project. It extends
 * JFrame and implements all methods listed in the iView interface.
 */
public class JFrameView extends JFrame implements IView {

  private ViewPanel p;
  private ArrayList<Shape> shapes;
  private ReadOnlyModel m;

  /**
   * Construct a JFrameView object
   *
   * @param caption
   */
  public JFrameView(String caption, ReadOnlyModel m) {
    super(caption);
    this.m = m;
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ReadOnlyModel rm = new AnimatorImpl();
    this.p = new ViewPanel();
    add(p);

    ViewPanel p = new ViewPanel();
    p.setPreferredSize(new Dimension(700, 700));


    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();

  }

  /**
   * getM to get the Model.
   *
   * @return the readonly Model for use
   */
  public ReadOnlyModel getM() {
    return m;
  }

  @Override
  public void displayOutPut() {
    setVisible(true);
  }


  @Override
  public void setModel(ReadOnlyModel m) {
    //do nothing for this view
  }

  /**
   * setCurrentFrame get the existing shapes.
   *
   * @param shapes a list of existing shapes
   */
  public void setCurrentFrame(List<Shape> shapes) {
    p.setCurrentFrame(shapes);
  }

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  public List<Shape> getShapes() {
    return this.shapes;
  }


  @Override
  public String getCurrentState() {
    return null;
  }

  public void refresh() {
    this.repaint();
  }
}