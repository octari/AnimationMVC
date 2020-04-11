
import org.junit.Test;


import cs5004.model.AnimatorImpl;
import cs5004.model.AnimatorModel;
import cs5004.model.Position;
import cs5004.model.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Test for the animator model. Testing all the methods and exceptions of the model.
 */
public class AnimatorTest {
  AnimatorModel a = new AnimatorImpl();

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullId() {
    a.addShape(null, ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeEmpId() {
    a.addShape("", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeRepeatedId() {
    a.addShape("r", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addShape("r", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidAppear() {
    a.addShape("1", ShapeType.RECTANGLE, -2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidPeriod() {
    a.addShape("1", ShapeType.RECTANGLE, 2, 1,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidIndex1() {
    a.addShape("1", ShapeType.RECTANGLE, 2, 3,
            new Position(2, 4.5), -3, 4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidIndex2() {
    a.addShape("1", ShapeType.RECTANGLE, 2, 3,
            new Position(2, 4.5), 3, -4.5, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidR() {
    a.addShape("1", ShapeType.RECTANGLE, 2, 3,
            new Position(2, 4.5), 3, 4.5, -3, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidG() {
    a.addShape("1", ShapeType.RECTANGLE, 2, 3,
            new Position(2, 4.5), 3, 4.5, 0, 700, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidB() {
    a.addShape("1", ShapeType.RECTANGLE, 2, 3,
            new Position(2, 4.5), 3, 4.5, 0, 700, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidType() {
    a.addShape("1", null, 2, 3,
            new Position(2, 4.5), 3, 4.5, 0, 700, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveInvalidId() {
    a.addMove("", 0.5, 10, new Position(8.8, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveNullId() {
    a.addMove(null, 0.5, 10, new Position(8.8, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveInvalidStartTime() {
    a.addMove("1", -5, 1, new Position(8.8, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveInvalidPeriod() {
    a.addMove("1", 5, 1, new Position(8.8, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveNonExistId() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("1", 1, 10, new Position(2, 4.5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveSamePosition() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1, 10, new Position(2, 4.5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorInvalidId() {
    a.addColor("", 0.5, 10, 3, 50, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorNullId() {
    a.addColor(null, 0.5, 10, 3, 50, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorInvalidStartTime() {
    a.addColor("1", -5, 1, 3, 50, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorInvalidPeriod() {
    a.addColor("1", 5, 1, 3, 50, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorNonExistId() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addColor("1", 1, 10, 3, 50, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorSameColor() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addColor("rec1", 1, 10, 0, 0, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleInvalidId() {
    a.addScale("", 0.5, 10, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleNullId() {
    a.addScale(null, 0.5, 10, 3, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleInvalidStartTime() {
    a.addScale("1", -5, 1, 3, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleInvalidPeriod() {
    a.addScale("1", 5, 1, 3, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleNonExistId() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addScale("1", 1, 10, 3, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddSameScale() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addScale("rec1", 1, 10, 3, 4.5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictMoveBtw() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    a.addMove("rec1", 2, 2.5, new Position(6.6, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictMoveBeforeBefore() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    a.addMove("rec1", 1, 2.5, new Position(6.6, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictMoveAfterAfter() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    a.addMove("rec1", 1.6, 4.5, new Position(6.6, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictMoveCover() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    a.addMove("rec1", 1, 4.5, new Position(6.6, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveAfter() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 4, new Position(6.6, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveBefore() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1, 3, new Position(6.6, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorAfter() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addColor("rec1", 1.5, 4, 100, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorBefore() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addColor("rec1", 1, 3, 100, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleAfter() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addScale("rec1", 1.5, 5, 2, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleBefore() {
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addScale("rec1", 1, 2, 2, 9);
  }

  @Test
  public void testAddConflictScaleMove() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    a.addScale("rec1", 2, 3, 3, 5);
    assertEquals("Shapes:\n" +
                    "Name:rec1\n" +
                    "Type:RECTANGLE\n" +
                    "Min Corner: (2.0, 4.5), Width: 3.0, Height: 4.5, Color: (0, 0, 100)\n" +
                    "Appears at t = 1.2\n" +
                    "Disappears at t = 3.6\n" +
                    "shape rec1 moves from (2.0, 4.5) to (8.8, 0.0) from t = 1.5s to t = 3.0s\n" +
                    "shape rec1 scales from (3.0, 5.0) to (3.0, 5.0) from t = 2.0s to t = 3.0s\n",
            a.getState());
  }

  @Test
  public void testAddConflictColorMove() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    a.addScale("rec1", 2, 3, 3, 5);
    assertEquals("Shapes:\n" +
                    "Name:rec1\n" +
                    "Type:RECTANGLE\n" +
                    "Min Corner: (2.0, 4.5), Width: 3.0, Height: 4.5, Color: (0, 0, 100)\n" +
                    "Appears at t = 1.2\n" +
                    "Disappears at t = 3.6\n" +
                    "shape rec1 moves from (2.0, 4.5) to (8.8, 0.0) from t = 1.5s to t = 3.0s\n" +
                    "shape rec1 scales from (3.0, 5.0) to (3.0, 5.0) from t = 2.0s to t = 3.0s\n",
            a.getState());
  }

  @Test
  public void testAddShape() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec0", ShapeType.RECTANGLE, 2.5, 3.0,
            new Position(1, 2), 2, 5, 100, 0, 0);
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addShape("oval1", ShapeType.OVAL, 0, 5,
            new Position(3, 4), 3, 4, 0, 100, 0);
    assertEquals("Shapes:\n" +
            "Name:rec0\n" +
            "Type:RECTANGLE\n" +
            "Min Corner: (1.0, 2.0), Width: 2.0, Height: 5.0, Color: (100, 0, 0)\n" +
            "Appears at t = 2.5\n" +
            "Disappears at t = 3.0\n" +
            "Name:rec1\n" +
            "Type:RECTANGLE\n" +
            "Min Corner: (2.0, 4.5), Width: 3.0, Height: 4.5, Color: (0, 0, 100)\n" +
            "Appears at t = 1.2\n" +
            "Disappears at t = 3.6\n" +
            "Name:oval1\n" +
            "Type:OVAL\n" +
            "Center: (3.0, 4.0), radius1: 3.0, radius2: 4.0, Color: (0, 100, 0)\n" +
            "Appears at t = 0.0\n" +
            "Disappears at t = 5.0\n", a.getState());
  }

  @Test
  public void testAddColor() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec0", ShapeType.RECTANGLE, 2.5, 4.0,
            new Position(1, 2), 2, 5, 100, 0, 0);
    a.addColor("rec0", 2.5, 3, 100, 100, 0);
    assertEquals("Shapes:\n" +
            "Name:rec0\n" +
            "Type:RECTANGLE\n" +
            "Min Corner: (1.0, 2.0), Width: 2.0, Height: 5.0, Color: (100, 0, 0)\n" +
            "Appears at t = 2.5\n" +
            "Disappears at t = 4.0\n" +
            "shape rec0 changes color from (100,0,0) to (100,100,0) from t = 2.5s " +
            "to t = 3.0s\n", a.getState());
  }

  @Test
  public void testAddScale() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec0", ShapeType.RECTANGLE, 2.5, 4.0,
            new Position(1, 2), 2, 5, 100, 0, 0);
    a.addScale("rec0", 2.5, 3, 3, 5);
    assertEquals("Shapes:\n" +
                    "Name:rec0\n" +
                    "Type:RECTANGLE\n" +
                    "Min Corner: (1.0, 2.0), Width: 2.0, Height: 5.0, Color: (100, 0, 0)\n" +
                    "Appears at t = 2.5\n" +
                    "Disappears at t = 4.0\n" +
                    "shape rec0 scales from (3.0, 5.0) to (3.0, 5.0) from t = 2.5s to t = 3.0s\n",
            a.getState());
  }

  @Test
  public void testAddMove() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec1", ShapeType.RECTANGLE, 1.2, 3.6,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 1.5, 3, new Position(8.8, 0));
    assertEquals("Shapes:\n"
            + "Name:rec1\n"
            + "Type:RECTANGLE\n"
            + "Min Corner: (2.0, 4.5), Width: 3.0, Height: 4.5, Color: (0, 0, 100)\n"
            + "Appears at t = 1.2\n"
            + "Disappears at t = 3.6\n"
            + "shape rec1 moves from (2.0, 4.5) to (8.8, 0.0) from t = 1.5s "
            + "to t = 3.0s\n", a.getState());
  }

  @Test
  public void testGetState() {
    assertEquals("Shapes:\n", a.getState());
    a.addShape("rec0", ShapeType.RECTANGLE, 2, 6.0,
            new Position(1, 2), 2, 5, 100, 0, 0);
    a.addShape("rec1", ShapeType.RECTANGLE, 0, 12,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec1", 0.5, 10, new Position(8.8, 0));
    a.addColor("rec1", 10, 12, 3, 0, 10);
    a.addScale("rec0", 2.3, 5, 9, 4.5);
    assertEquals("Shapes:\n" +
            "Name:rec0\n" +
            "Type:RECTANGLE\n" +
            "Min Corner: (1.0, 2.0), Width: 2.0, Height: 5.0, Color: (100, 0, 0)\n" +
            "Appears at t = 2.0\n" +
            "Disappears at t = 6.0\n" +
            "Name:rec1\n" +
            "Type:RECTANGLE\n" +
            "Min Corner: (2.0, 4.5), Width: 3.0, Height: 4.5, Color: (0, 0, 100)\n" +
            "Appears at t = 0.0\n" +
            "Disappears at t = 12.0\n" +
            "shape rec1 moves from (1.0, 2.0)(2.0, 4.5) to " +
            "(8.8, 0.0) from t = 0.5s to t = 10.0s\n" +
            "shape rec0 scales from (9.0, 4.5)(9.0, 4.5) to " +
            "(9.0, 4.5) from t = 2.3s to t = 5.0s\n" +
            "shape rec1 changes color from (100,0,0)(0,0,100) " +
            "to (3,0,10) from t = 10.0s to t = 12.0s\n", a.getState());
  }

  @Test
  public void getShapeAt() {
    a.addShape("rec0", ShapeType.RECTANGLE, 2, 6.0,
            new Position(1, 2), 2, 5, 100, 0, 0);
    a.addMove("rec0", 3, 5, new Position(8.8, 0));
    assertEquals("(2.0, 5.0)", a.getShapeAt(a.getShapes().get(0), 4).toString());
  }

  @Test
  public void getShapesAt() {
    a.addShape("rec0", ShapeType.RECTANGLE, 2, 6.0,
            new Position(1, 2), 2, 5, 100, 0, 0);
    a.addShape("rec1", ShapeType.RECTANGLE, 0, 12,
            new Position(2, 4.5), 3, 4.5, 0, 0, 100);
    a.addMove("rec0", 3, 5, new Position(8.8, 0));
    a.addMove("rec1", 3, 5, new Position(1.8, 0));
    assertEquals("[(2.0, 5.0), (3.0, 4.5)]", a.getShapesAt(0).toString());
    assertEquals("[(2.0, 5.0), (3.0, 4.5)]", a.getShapesAt(4).toString());
    assertEquals("[(2.0, 5.0), (3.0, 4.5)]", a.getShapesAt(10).toString());
  }

  //

}
