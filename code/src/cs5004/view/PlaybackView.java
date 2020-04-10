package cs5004.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.controller.Features;
import cs5004.model.ReadOnlyModel;

public class PlaybackView extends JFrame implements IPlayBack {
  private ViewPanel p;
  private ReadOnlyModel m;
  private JLabel display;
  private JButton startButton, pauseButton, resumeButton, restartButton,
          increaseSpeed, decreaseSpeed;
//  private JCheckBox looping;
  private JRadioButton loop;
  private JRadioButton unloop;

  /**
   * Construct a JFrameView object.
   *
   * @param caption the caption of the view window
   */
  public PlaybackView(String caption, ReadOnlyModel m) {
    super(caption);
    this.m = m;
    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());


    this.p = new ViewPanel();
    this.add(p);

    p.setPreferredSize(new Dimension(1200, 1200));

    //start button
    startButton = new JButton("Start");
    startButton.setActionCommand("Start Button");
//    this.add(startButton, BorderLayout.EAST);

    //pause button
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause Button");
//    this.add(pauseButton, BorderLayout.EAST);

    //resume button
    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("Resume Button");
//    this.add(resumeButton, BorderLayout.EAST);

    //restart button
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");
//    this.add(restartButton, BorderLayout.);

    //loop button
//    looping = new JCheckBox("Looping");
//    looping.setActionCommand("Looping Button");
//    JRadioButton[] loopOrNot = new JRadioButton[2];
    ButtonGroup rGroup = new ButtonGroup();
    loop = new JRadioButton("Loop");
    unloop = new JRadioButton("Unloop");
    loop.setActionCommand("Loop");
    unloop.setActionCommand("Un Loop");
    rGroup.add(loop);
    rGroup.add(unloop);


    //resume button
    increaseSpeed = new JButton("IncreaseSpeed");
    increaseSpeed.setActionCommand("IncreaseSpeed Button");
//    this.add(increaseSpeed, BorderLayout.EAST);

    //restart button
    decreaseSpeed = new JButton("DecreaseSpeed");
    decreaseSpeed.setActionCommand("DecreaseSpeed Button");
//    this.add(decreaseSpeed, BorderLayout.EAST);

    //This will be the main panel.
    //We are going to put several buttons only in the "EAST" part of it.

    this.setLayout( new BorderLayout() );
    //We create a sub-panel. Notice, that we don't use any layout-manager,
    //Because we want it to use the default FlowLayout
    JPanel subPanel = new JPanel();

    subPanel.add(startButton);
    subPanel.add(pauseButton);
    subPanel.add(resumeButton);
    subPanel.add(restartButton);
    subPanel.add(loop);
    subPanel.add(unloop);
    subPanel.add(increaseSpeed);
    subPanel.add(decreaseSpeed);


    //Now we simply add it to your main panel.
    this.add(subPanel, BorderLayout.NORTH);

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
//    startButton.addActionListener(evt -> features.start());
//    pauseButton.addActionListener(evt -> features.pause());
//    resumeButton.addActionListener(evt -> features.start());
//    restartButton.addActionListener(evt -> features.restart());
//    loopButton.addActionListener(evt -> features.loop());
//    increaseSpeed.addActionListener(evt -> features.increaseSpeed());
//    decreaseSpeed.addActionListener(evt -> features.decreaseSpeed());

    // wiring btw controller and view
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start Button")){
          features.start();
        }
      }
    });
    pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Pause Button")){
          features.pause();
        }
      }
    });
    resumeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Resume Button")){
          features.start();
        }
      }
    });
    restartButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Restart Button")){
          features.restart();
        }
      }
    });


    loop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Loop")){
          features.loop();
        }
      }
    });

    unloop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Un Loop")) {
          features.unloop();
        }
      }
    });


    this.increaseSpeed.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("IncreaseSpeed Button")){
          features.increaseSpeed();
        }
      }
    });
    this.decreaseSpeed.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DecreaseSpeed Button")){
          features.decreaseSpeed();
        }
      }
    });

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
