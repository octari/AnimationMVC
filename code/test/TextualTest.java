
import org.junit.Before;
import org.junit.Test;

import cs5004.model.AnimatorImpl;
import cs5004.model.ColorChange;
import cs5004.model.Oval;
import cs5004.model.Rectangle;
import cs5004.model.PosChange;
import cs5004.model.Position;
import cs5004.model.ScaleChange;
import cs5004.model.ShapeType;
import cs5004.view.TextualView;

import static org.junit.Assert.assertEquals;

/**
 * TextualTest lass tests the output of TextualView.
 */
public class TextualTest {
  AnimatorImpl model;
  AnimatorImpl model0;
  Rectangle rectangle;
  Oval oval;
  Position p1;
  Position p2;
  Position p3;
  Position p4;
  PosChange changeP1;
  PosChange changeP2;
  ColorChange changeColor1;
  ScaleChange changeSize1;
  TextualView textualView;


  @Before
  public void setup() {
    model = new AnimatorImpl();
    p1 = new Position(250, 270);
    p2 = new Position(390, 390);
    p3 = new Position(510, 115);
    p4 = new Position(5, 40);

    rectangle = new Rectangle("R", ShapeType.RECTANGLE, 1, 100, 50, 100,
            0, p1, 10, 5);
    oval = new Oval("O", ShapeType.OVAL, 6, 100, 60, 30,
            0, p2, 3, 4);

    changeP1 = new PosChange("R", 10, 50, p2);
    changeP2 = new PosChange("O", 10, 50, p1);

    changeColor1 = new ColorChange("O", 50, 80, 10, 0, 0);
    changeSize1 = new ScaleChange("O", 61, 70, 50, 100);

    model.addShape(rectangle.getId(), rectangle.getType(), rectangle.getAppear(),
            rectangle.getDisappear(), rectangle.getPos(), rectangle.getWidth(),
            rectangle.getHeight(), rectangle.getR(), rectangle.getG(), rectangle.getB());
    model.addShape(oval.getId(), oval.getType(), oval.getAppear(), oval.getDisappear(),
            oval.getPos(), oval.getRadius1(), oval.getRadius2(), oval.getR(), oval.getG(),
            oval.getB());

    model.addMove(rectangle.getId(), changeP1.getStartTime(), changeP1.getEndTime(),
            changeP1.getEndPos());
    model.addScale(rectangle.getId(), changeSize1.getStartTime(), changeSize1.getEndTime(),
            changeSize1.getEndIndex1(), changeSize1.getEndIndex2());
    model.addColor(rectangle.getId(), changeColor1.getStartTime(), changeColor1.getEndTime(),
            changeColor1.getEndR(), changeColor1.getEndG(), changeColor1.getEndB());

    textualView = new TextualView(model, "out.txt");


  }

  /**
   * Test getTextDescription() method for empty model.
   */
  @Test
  public void testEmptyOutputFile() {
    model0 = new AnimatorImpl();
    TextualView empty = new TextualView(model0, "emptyOut.txt");
    assertEquals("Shapes:\n", empty.render());
  }

  /**
   * Test getTextDescription() method for populated model.
   */
  @Test
  public void testOutputFile() {
    assertEquals("Shapes:\n" +
            "Name:R\n" +
            "Type:RECTANGLE\n" +
            "Min Corner: (250.0, 270.0), Width: 10.0, Height: 5.0, Color: (50, 100, 0)\n" +
            "Appears at t = 1.0\n" +
            "Disappears at t = 100.0\n" +
            "Name:O\n" +
            "Type:OVAL\n" +
            "Center: (390.0, 390.0), radius1: 3.0, radius2: 4.0, Color: (60, 30, 0)\n" +
            "Appears at t = 6.0\n" +
            "Disappears at t = 100.0\n" +
            "shape R moves from (250.0, 270.0)(390.0, 390.0) to (390.0, 390.0) from t = 10.0s " +
            "to t = 50.0s\n" +
            "shape R changes color from (50,100,0)(60,30,0) to (10,0,0) from t = 50.0s to " +
            "t = 80.0s\n" +
            "shape R scales from (50.0, 100.0)(50.0, 100.0) to (50.0, 100.0) from t = 61.0s " +
            "to t = 70.0s\n", this.textualView.render());
  }


}
