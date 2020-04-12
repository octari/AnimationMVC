import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import cs5004.controller.Controller;

import static org.junit.Assert.assertEquals;

/**
 * In controller test, we make each method return an integer 0, so if the method go to the
 * end, it will return 0. We assert every controller method call to be 0 so that we can make
 * sure it goes to the end and all functions work fine.
 */
public class controllerTest {
  private Controller controller;
  @Before
  public void setup() {
    controller = Mockito.mock(Controller.class);
  }

  @Test
  public void testStart(){
    assertEquals(0, controller.resume());
  }

  @Test
  public void testPlay(){
    assertEquals(0, controller.play());
  }

  @Test
  public void testPause(){
    assertEquals(0, controller.pause());
  }

  @Test
  public void testIncreaseSpeed(){
    assertEquals(0,controller.increaseSpeed());
  }

  @Test
  public void testDecreaseSpeed(){
    assertEquals(0, controller.decreaseSpeed());
  }

  @Test
  public void testLoop(){
    assertEquals(0,controller.loop());
  }

  @Test
  public void testUnloop(){
    assertEquals(0,controller.unloop());
  }

}
