import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cs5004.model.AnimatorImpl;
import cs5004.model.AnimatorModel;
import cs5004.model.Rectangle;
import cs5004.model.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class BuilderTest {

  Map<String, ShapeType> shp;


  @Test
  public void setBound() {
    AnimatorImpl.Builder builder = new AnimatorImpl.Builder();
    builder.setBounds(100, 100,500, 600);
    AnimatorModel m = builder.build();
    assertEquals(100, m.getCanvas().getX());
    assertEquals(100, m.getCanvas().getY());
    assertEquals(500, m.getCanvas().getWidth());
    assertEquals(600, m.getCanvas().getHeight());

  }
  @Test
  public void declareShape() {
    AnimatorImpl.Builder builder = new AnimatorImpl.Builder();
    builder.declareShape("R", "rectangle");
    assertTrue(ShapeType.RECTANGLE.toString().equalsIgnoreCase("rectangle"));
    builder.declareShape("R", "oval");
    assertTrue(ShapeType.OVAL.toString().equalsIgnoreCase("oval"));
  }

  @Test
  public void build() {
    AnimatorImpl.Builder builder = new AnimatorImpl.Builder();
    shp = new HashMap<>();
    builder.declareShape("R", "rectangle");
    builder.declareShape("R", "oval");
    shp.put("R", ShapeType.RECTANGLE);
    shp.put("R", ShapeType.OVAL);
    builder.addMotion("R", 2, 3, 4, 5,6 ,7, 8, 9, 1,
            2, 3, 4, 5, 6, 7, 8);

  }

}
