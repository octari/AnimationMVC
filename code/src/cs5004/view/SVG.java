package cs5004.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import cs5004.model.AnimatorModel;
import cs5004.model.Change;
import cs5004.model.ColorChange;
import cs5004.model.Motion;
import cs5004.model.Oval;
import cs5004.model.PosChange;
import cs5004.model.ReadOnlyModel;
import cs5004.model.Rectangle;
import cs5004.model.ScaleChange;
import cs5004.model.Shape;

/**
 *
 */
public class SVG implements IView {
  private ReadOnlyModel m;
  private String out;

  SVG(ReadOnlyModel m, String out) throws FileNotFoundException, UnsupportedEncodingException {
    this.m = m;
    this.out = out;
  }

  @Override
  public void outputFile() throws FileNotFoundException, UnsupportedEncodingException {
    //Is the background black?
    StringBuilder sb = new StringBuilder();
    sb.append("<svg width=\"").append(m.getCanvas().getWidth()).append("\" height=\"")
            .append(m.getCanvas().getHeight()).append("\" version=\"1.1\" style=\"fill:black\">\n");
    for (Shape s : m.getShapes()) {
      switch (s.getType()) {
        case RECTANGLE:
          sb.append("  <rect id=\"").append(s.getId()).append("\" x=\"").append(s.getPos().getX())
                  .append("\" y=\"").append(s.getPos().getY()).append("\" width=\"")
                  .append((int) ((Rectangle) s).getWidth()).append("\" height=\"")
                  .append((int) ((Rectangle) s).getHeight()).append("\">\n");
          for (Change c : m.getMap().get(s)) {
            switch (c.getMotion()) {
              case MOVE:
                sb.append("    <animate attributeName=\"x\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append((int) s.getPos().getX())
                        .append("\" to=\"").append(((PosChange) c).getEndPos().getX())
                        .append("\" />\n");
                sb.append("    <animate attributeName=\"y\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append((int) s.getPos().getY())
                        .append("\" to=\"").append(((PosChange) c).getEndPos().getY())
                        .append("\" />\n");
              case SCALE:
                sb.append("<animate attributeName=\"width\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append(((Rectangle) s)
                        .getWidth()).append("\" to=\"").append(((ScaleChange) c).getEndIndex1())
                        .append("\"/>\n");
                sb.append("<animate attributeName=\"height\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append(((Rectangle) s)
                        .getHeight()).append("\" to=\"").append(((ScaleChange) c).getEndIndex2())
                        .append("\"/>\n");
              case COLOR:
                sb.append("<animate attributeName=\"fill\" attributeType=\"CSS\" from=\"" + "rgb(")
                        .append(s.getR()).append(", ").append(s.getG()).append(", ")
                        .append(s.getB()).append(")\" to=\"").append("rgb(")
                        .append(((ColorChange) c).getEndR()).append(", ").append(((ColorChange) c)
                        .getEndG()).append(", ").append(((ColorChange) c).getEndB())
                        .append(")\" begin=\"").append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" />\n");
            }
          }
          sb.append("  </rect>\n");
        case OVAL:
          sb.append("  <ellipse id=\"").append(s.getId()).append("\" cx=\"")
                  .append(s.getPos().getX()).append("\" cy=\"").append(s.getPos().getY())
                  .append("\" rx=\"").append((int) ((Oval) s).getRadius1()).append("\" ry=\"")
                  .append((int) ((Oval) s).getRadius2()).append("\">\n");
          for (Change c : m.getMap().get(s)) {
            switch (c.getMotion()) {
              case MOVE:
                sb.append("    <animate attributeName=\"x\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append((int) s.getPos().getX())
                        .append("\" to=\"").append(((PosChange) c).getEndPos().getX())
                        .append("\" />\n");
                sb.append("    <animate attributeName=\"y\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append((int) s.getPos().getY())
                        .append("\" to=\"").append(((PosChange) c).getEndPos().getY())
                        .append("\" />\n");
              case SCALE:
                sb.append("<animate attributeName=\"rx\" attributeType=\"XML\" begin=\"")
                sb.append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append(((Rectangle) s).getWidth())
                        .append("\" to=\"").append(((ScaleChange) c).getEndIndex1())
                        .append("\" />\n");
                sb.append("<animate attributeName=\"ry\" attributeType=\"XML\" begin=\"")
                        .append((int) c.getStartTime()).append("s\" dur=\"")
                        .append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" from=\"").append(((Rectangle) s).getHeight())
                        .append("\" to=\"").append(((ScaleChange) c).getEndIndex2())
                        .append("\" />\n");
              case COLOR:
                sb.append("<animate attributeName=\"fill\" attributeType=\"CSS\" from=\"" + "rgb(")
                        .append(s.getR()).append(", ").append(s.getG()).append(", ")
                        .append(s.getB()).append(")\" to=\"")
                        .append("rgb(").append(((ColorChange) c).getEndR()).append(", ")
                        .append(((ColorChange) c).getEndG()).append(", ").append(((ColorChange) c)
                        .getEndB()).append(")\" begin=\"").append((int) c.getStartTime())
                        .append("s\" dur=\"").append((int) (c.getEndTime() - c.getStartTime()))
                        .append("s\" fill=\"freeze\" />");
            }
          }
          writer.println("</ellipse>");
      }
      writer.println("</svg>");

    }
    if(!out.equals("SysOut")){
      PrintWriter writer = new PrintWriter("SVGView.svg", "UTF-8");

      writer.close();
    }
  }
}

