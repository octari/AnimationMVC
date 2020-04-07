package cs5004.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JPanel;

import cs5004.model.Oval;
import cs5004.model.Position;
import cs5004.model.Rectangle;
import cs5004.model.Shape;

/**
 * ViewPanel extends JPanel.
 */
public class ViewPanel extends JPanel {
  private List<Shape> shapes;

  /**
   * Construct a viewPanel with list of shape.
   */
  public ViewPanel() {
    super();
    this.shapes = new ArrayList<>();
    this.setBackground(Color.WHITE);
  }

  /**
   * Set a list shapes inside the JPanel.
   *
   * @param shapes a list of shape objects
   */
  public void setCurrentFrame(List<Shape> shapes) {
    this.shapes = shapes;
  }


  /**
   * Overrides the paintComponent methods from the JPanel abstract class and iterates through the
   * shape to draw new objects onto the canvas.
   *
   * @param graphics a Graphics object
   */
  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics;

    g2d.setColor(Color.BLACK);

    for (Shape s : shapes) {
      Position pos = s.getPos();
      int x = (int) pos.getX();
      int y = (int) pos.getY();
      int r = s.getR();
      int g = s.getG();
      int b = s.getB();

      switch (s.getType()) {
        case RECTANGLE:
          int w = (int) ((Rectangle) s).getWidth();
          int h = (int) ((Rectangle) s).getHeight();
          g2d.setColor(new Color(r, g, b));
          g2d.fillRect(x, y, w, h);
          g2d.drawRect(x, y, w, h);
          break;
        case OVAL:
          int r1 = (int) ((Oval) s).getRadius1();
          int r2 = (int) ((Oval) s).getRadius2();
          g2d.setColor(new Color(r, g, b));
          g2d.fillOval(x, y, r1, r1);
          g2d.drawOval(x, y, r1, r2);
          break;
        default:
          break;
      }

    }

  }

}
