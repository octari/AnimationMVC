package cs5004.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.controller.Features;
import cs5004.model.ReadOnlyModel;

/**
 * PlaybackView extends JFrame implements IPlayBack.
 */
public class PlaybackView extends JFrame implements IPlayBack {
  private ViewPanel p;
  private ReadOnlyModel m;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton restartButton;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;
  private JRadioButton looping;
  private JRadioButton unlooping;

  /**
   * Construct a playback object.
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


    //pause button
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause Button");

    //resume button
    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("Resume Button");

    //restart button
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");

    //loop/unloop radiobutton
    ButtonGroup rGroup = new ButtonGroup();
    looping = new JRadioButton("Looping start");
    unlooping = new JRadioButton("Not looping start");
    looping.setActionCommand("Loop");
    unlooping.setActionCommand("Un Loop");
    rGroup.add(looping);
    rGroup.add(unlooping);


    //resume button
    increaseSpeed = new JButton("IncreaseSpeed");
    increaseSpeed.setActionCommand("IncreaseSpeed Button");

    //restart button
    decreaseSpeed = new JButton("DecreaseSpeed");
    decreaseSpeed.setActionCommand("DecreaseSpeed Button");

    //This will be the main panel.
    //We are going to put several buttons only in the "North" part of it.
    this.setLayout(new BorderLayout());
    //We create a sub-panel. Notice, that we don't use any layout-manager,
    //Because we want it to use the default FlowLayout
    JPanel subPanel = new JPanel();

    JLabel display = new JLabel("Choose a start which is either looping or not looping. " +
            "Press button to do what it indicates.");
    display.getHorizontalAlignment();
    subPanel.add(display);

    subPanel.add(looping);
    subPanel.add(unlooping);
    subPanel.add(pauseButton);
    subPanel.add(resumeButton);
    subPanel.add(restartButton);
    subPanel.add(increaseSpeed);
    subPanel.add(decreaseSpeed);

    this.add(subPanel, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane(p);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);
    this.pack();
    setVisible(true);
  }

  @Override
  public String render() {
    return null;
  }

  @Override
  public void outputFile() {
    return;
  }

  @Override
  public void addFeatures(Features features) {

    pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Pause Button")) {
          features.pause();
        }
      }
    });

    resumeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Resume Button")) {
          features.resume();
        }
      }
    });

    restartButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Restart Button")) {
          features.restart();
        }
      }
    });


    looping.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Loop")) {
          features.loop();
        }
      }
    });

    unlooping.addActionListener(new ActionListener() {
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
        if (e.getActionCommand().equals("IncreaseSpeed Button")) {
          features.increaseSpeed();
        }
      }
    });

    this.decreaseSpeed.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("DecreaseSpeed Button")) {
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

  /**
   * setCurrentTick set the current tick.
   *
   * @param currentTick the time reference
   */
  public void setCurrentTick(int currentTick) {
    p.setCurrentFrame(m.getShapesAt(currentTick));
  }
}
