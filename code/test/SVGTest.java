import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cs5004.model.AnimatorImpl;
import cs5004.model.ColorChange;
import cs5004.model.Oval;
import cs5004.model.PosChange;
import cs5004.model.Position;
import cs5004.model.Rectangle;
import cs5004.model.ScaleChange;
import cs5004.model.Shape;
import cs5004.model.ShapeType;
import cs5004.view.IView;
import cs5004.view.TextualView;
import cs5004.view.ViewFactory;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SVGTest {
  AnimatorImpl model;
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

  @Test
  public void testEmptySVG(){
    IView svg =
  }

  @Test
  public void testSVG() throws FileNotFoundException, UnsupportedEncodingException {
    IView svg = ViewFactory.makeView("svg", model, "", 20);
    assertEquals("", svg.render());
  }
}
