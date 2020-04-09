package cs5004.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import cs5004.controller.Controller;
import cs5004.controller.Features;
import cs5004.model.ReadOnlyModel;

public class PlaybackView extends JFrame implements IPlayBack {
  private ViewPanel p;
  private ReadOnlyModel m;
  private JLabel display;
  private JButton startButton, pauseButton, resumeButton, restartButton, loopButton,
          increaseSpeed, decreaseSpeed;

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

    //start button
    startButton = new JButton("start");
    startButton.setActionCommand("start Button");
    this.add(startButton);

    //pause button
    pauseButton = new JButton("pause");
    pauseButton.setActionCommand("pause");
    this.add(pauseButton);

    //resume button
    resumeButton = new JButton("Exit");
    resumeButton.setActionCommand("Exit Button");
    this.add(resumeButton);

    //restart button
    restartButton = new JButton("Exit");
    restartButton.setActionCommand("Exit Button");
    this.add(restartButton);

    //loop button
    loopButton = new JButton("Toggle color");
    loopButton.setActionCommand("Toggle color");
    this.add(loopButton);

    //resume button
    increaseSpeed = new JButton("Exit");
    increaseSpeed.setActionCommand("Exit Button");
    this.add(increaseSpeed);

    //restart button
    decreaseSpeed = new JButton("Exit");
    decreaseSpeed.setActionCommand("Exit Button");
    this.add(decreaseSpeed);

    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
  public void addFeatures(Features features) {
    // wiring btw controller and view
    startButton.addActionListener(evt -> features.start());
    pauseButton.addActionListener(evt -> features.pause());
    resumeButton.addActionListener(evt -> features.start());
    restartButton.addActionListener(evt -> features.restart());
    loopButton.addActionListener(evt -> features.loop());
    increaseSpeed.addActionListener(evt -> features.increaseSpeed());
    decreaseSpeed.addActionListener(evt -> features.decreaseSpeed());
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

  public void setCurrentTick(int currentTick) {
    p.setCurrentFrame(m.getShapesAt(currentTick));
  }
}
