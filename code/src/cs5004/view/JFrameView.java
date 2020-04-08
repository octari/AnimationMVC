package cs5004.view;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.model.ReadOnlyModel;


/**
 * JFrameView represents a view for a simple Visual Animation model for this project. It extends
 * JFrame and implements all methods listed in the iView interface.
 */
public class JFrameView extends JFrame implements IView {

  private ViewPanel p;
  private ReadOnlyModel m;

  /**
   * Construct a JFrameView object.
   *
   * @param caption the caption of the view window
   */
  public JFrameView(String caption, ReadOnlyModel m) {
    super(caption);
    this.m = m;
    setSize(900, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.p = new ViewPanel();
    this.add(p);

    p.setPreferredSize(new Dimension(700, 700));


    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);
    setVisible(true);
  }

  /**
   * setCurrentTick takes the tick and get the shapes status to se the current frame.
   *
   * @param tick the time component
   */
  public void setCurrentTick(int tick) {
    p.setCurrentFrame(m.getShapesAt(tick));
  }


  @Override
  public String render() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }


  @Override
  public String getCurrentState() {
    return m.getState();
  }

  @Override
  public void outputFile() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  /**
   * This method refresh the paint on the canvas every some time.
   */
  public void refresh() {
    this.repaint();
  }
}
