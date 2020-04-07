import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import cs5004.model.AnimatorImpl;
import cs5004.model.ColorChange;
import cs5004.model.Oval;
import cs5004.model.PosChange;
import cs5004.model.Position;
import cs5004.model.Rectangle;
import cs5004.model.ScaleChange;

import cs5004.model.ShapeType;
import cs5004.view.IView;

import cs5004.view.ViewFactory;

import static org.junit.Assert.assertEquals;

/**
 * SVGTest tests the SVG output.
 */
public class SVGTest {
  private AnimatorImpl model;

  @Before
  public void setup() {
    model = new AnimatorImpl();
    Position p1 = new Position(250, 270);
    Position p2 = new Position(390, 390);

    Rectangle rectangle = new Rectangle("R", ShapeType.RECTANGLE, 1, 100, 50, 100,
            0, p1, 10, 5);
    Oval oval = new Oval("O", ShapeType.OVAL, 6, 100, 60, 30,
            0, p2, 3, 4);

    PosChange changeP1 = new PosChange("R", 10, 50, p2);

    ColorChange changeColor1 = new ColorChange("O", 50, 80, 10, 0, 0);
    ScaleChange changeSize1 = new ScaleChange("O", 61, 70, 50, 100);

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
  }

  @Test
  public void testEmptySVG() throws FileNotFoundException, UnsupportedEncodingException {
    IView svg = ViewFactory.makeView("svg", new AnimatorImpl(), "", 20);
    assertEquals("<svg viewBox=\"0 0 0 0\" width=\"0\" height=\"0\" version=\"1.1\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>\n", svg.render());
  }

  @Test
  public void testSVG() throws FileNotFoundException, UnsupportedEncodingException {
    IView svg = ViewFactory.makeView("svg", model, "", 20);
    assertEquals("<svg viewBox=\"0 0 0 0\" width=\"0\" height=\"0\" version=\"1.1\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "  <rect id=\"R\" x=\"250.0\" y=\"270.0\" width=\"10\" height=\"5\" fill=\"" +
            "rgb(50, 100, 0)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"2000ms\" " +
            "attributeName=\"x\" from=\"250\" to=\"390\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"500ms\" dur=\"2000ms\" " +
            "attributeName=\"y\" from=\"270\" to=\"390\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3050ms\" dur=\"450ms\" " +
            "attributeName=\"width\" from=\"10\" to=\"50.0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"3050ms\" dur=\"450ms\" " +
            "attributeName=\"height\" from=\"5.0\" to=\"100.0\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"CSS\"  attributeName=\"fill\" from=\" " +
            "rgb(50, 100, 0)\" to=\"rgb(10, 0, 0)\" begin=\"2500ms\" dur=\"1500ms\" " +
            "fill=\"freeze\" />\n" +
            "  </rect>\n" + "\n" +
            "  <ellipse id=\"O\" cx=\"390.0\" cy=\"390.0\" rx=\"3\" ry=\"4\" " +
            "fill=\"rgb(60, 30, 0)\" visibility=\"visible\" >\n" +
            "  </ellipse>\n" +
            "\n" +
            "</svg>\n", svg.render());
  }
}
