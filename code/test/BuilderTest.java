

import cs5004.model.AnimatorModel;
import cs5004.model.ShapeType;
import cs5004.model.AnimatorImpl.Builder;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * BuilderTest tests the builder class's methods.
 */
public class BuilderTest {
  Map<String, ShapeType> shp;


  @Test
  public void setBound() {
    Builder builder = new Builder();
    builder.setBounds(100, 100, 500, 600);
    AnimatorModel m = builder.build();
    Assert.assertEquals(100L, (long)m.getCanvas().getX());
    Assert.assertEquals(100L, (long)m.getCanvas().getY());
    Assert.assertEquals(500L, (long)m.getCanvas().getWidth());
    Assert.assertEquals(600L, (long)m.getCanvas().getHeight());
  }

  @Test
  public void declareShape() {
    Builder builder = new Builder();
    builder.declareShape("R", "rectangle");
    Assert.assertTrue(ShapeType.RECTANGLE.toString().equalsIgnoreCase("rectangle"));
    builder.declareShape("R", "oval");
    Assert.assertTrue(ShapeType.OVAL.toString().equalsIgnoreCase("oval"));
  }

  @Test
  public void build() {
    this.shp = new HashMap();
    this.shp.put("R", ShapeType.RECTANGLE);
    this.shp.put("O", ShapeType.OVAL);

    assertEquals("{R=RECTANGLE, O=OVAL}", shp.toString());
  }
}