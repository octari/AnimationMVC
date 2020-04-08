package cs5004.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.model.ReadOnlyModel;

public class PlaybackView extends JFrame implements IView {

  private ViewPanel p;
  private ReadOnlyModel m;

  /**
   * Construct a JFrameView object.
   *
   * @param caption the caption of the view window
   */
  public PlaybackView(String caption, ReadOnlyModel m) {
    super(caption);
    this.m = m;
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.p = new ViewPanel();
    this.add(p);

    p.setPreferredSize(new Dimension(1500, 1500));


    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);
    setVisible(true);
  }
  @Override
  public String render() {
    return null;
  }

  @Override
  public void outputFile() {

  }

  @Override
  public String getCurrentState() {
    return m.getState();
  }

  /**
   * This method refresh the paint on the canvas every some time.
   */
  public void refresh() {
    this.repaint();
  }
}
