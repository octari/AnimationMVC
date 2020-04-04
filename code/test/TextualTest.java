
import org.junit.Before;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.model.AnimatorImpl;
import cs5004.model.ColorChange;
import cs5004.model.Oval;
import cs5004.model.Rectangle;
import cs5004.model.PosChange;
import cs5004.model.Position;
import cs5004.model.ReadOnlyModel;
import cs5004.model.ScaleChange;
import cs5004.model.Shape;
import cs5004.model.ShapeType;

import static org.junit.Assert.assertEquals;
/**
 *
 */
public class TextualTest {
  ReadOnlyModel model;
  Rectangle rectangle;
  Oval oval;
  Position p1;
  Position p2;
  Position p3;
  Position p4;
  PosChange changeP1;
  PosChange changeP2;
  PosChange changeP3;
  ColorChange changeColor1;
  ScaleChange changeSize1;
  TextualTest textualView;

  ArrayList<Shape> shapesList;

  @Before
  public void setup() {
    model = new ReadOnlyModel;
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

    model.addShape(rectangle);
    model.addShape(oval);
  }



}
