package cs5004.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import cs5004.model.Change;
import cs5004.model.ColorChange;
import cs5004.model.Oval;
import cs5004.model.PosChange;
import cs5004.model.ReadOnlyModel;
import cs5004.model.Rectangle;
import cs5004.model.ScaleChange;
import cs5004.model.Shape;

/**
 * This class represent the XML style output of the model. It has a ReadOnly Model and it can output
 * a file or just output to the terminal.
 */
public class SVG implements IView {
  private ReadOnlyModel m;
  private String out;
  private int speed;

  /**
   * This is the constructor for SVG view.
   *
   * @param m     ReadOnly model
   * @param out   the way of outputing the model content
   * @param speed the speed of rendering the animation
   */
  SVG(ReadOnlyModel m, String out, int speed) {
    this.m = m;
    this.out = out;
    this.speed = speed;
  }

  @Override
  public String render() throws IllegalArgumentException {
    StringBuilder sb = new StringBuilder();
    sb.append("<svg ")
//            .append("viewBox=\"").append(m.getCanvas().getX()).append(" ")
//            .append(m.getCanvas().getY()).append(" ").append(m.getCanvas().getWidth())
//            .append(" ").append(m.getCanvas().getHeight())
//            .append("\" ")
            .append("width=\"")
            .append(m.getCanvas().getWidth()+m.getCanvas().getX())
            .append("\" height=\"")
            .append(m.getCanvas().getHeight()+m.getCanvas().getY())
            .append("\" version=\"1.1\"\n")
            .append("xmlns=\"http://www.w3.org/2000/svg\">\n");
    for (Shape s : m.getShapes()) {
      switch (s.getType()) {
        case RECTANGLE:
          sb.append("  <rect id=\"").append(s.getId()).append("\" x=\"").append(s.getPos().getX())
                  .append("\" y=\"").append(s.getPos().getY()).append("\" width=\"")
                  .append((int) ((Rectangle) s).getWidth()).append("\" height=\"")
                  .append((int) ((Rectangle) s).getHeight()).append("\" fill=\"rgb(")
                  .append(s.getR()).append(", ").append(s.getG()).append(", ").append(s.getB())
                  .append(")\" visibility=\"hidden\" >\n");
          sb.append(String.format("    <set attributeType=\"xml\" attributeName=\"" + "visibility\" " +
                  "to=\"visible\" begin=\"%dms\" dur=\"%dms\" fill=\"remove\" />\n",
                  (int) s.getAppear() * 1000 / speed,
                  (int) (s.getDisappear()-s.getAppear() * 1000 /speed)));
          for (Change c : m.getMap().get(s)) {
            switch (c.getMotion()) {
              case MOVE:
                if (Double.compare(m.getShapeAt(s, c.getStartTime()).getPos().getX(),
                        ((PosChange) c).getEndPos().getX()) != 0) {
                  sb.append("    <animate attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime())) / speed)
                          .append("ms\" attributeName=\"x\" from=\"")
                          .append((int) m.getShapeAt(s, c.getStartTime()).getPos().getX())
                          .append("\" to=\"").append((int) ((PosChange) c).getEndPos().getX())
                          .append("\" fill=\"freeze\" />\n");
                }
                if (Double.compare(m.getShapeAt(s, c.getStartTime()).getPos().getY(),
                        ((PosChange) c).getEndPos().getY()) != 0) {
                  sb.append("    <animate attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime()) / speed))
                          .append("ms\" attributeName=\"y\" from=\"")
                          .append((int) m.getShapeAt(s, c.getStartTime()).getPos().getY())
                          .append("\" to=\"").append((int) ((PosChange) c).getEndPos().getY())
                          .append("\" fill=\"freeze\" />\n");
                }
                break;
              case SCALE:
                if (Double.compare(((Rectangle) m.getShapeAt(s, c.getStartTime())).getWidth(),
                        ((ScaleChange) c).getEndIndex1()) != 0) {
                  sb.append("    <animate attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime()) / speed))
                          .append("ms\" attributeName=\"width\" from=\"")
                          .append((int) ((Rectangle) m.getShapeAt(s, c.getStartTime())).getWidth())
                          .append("\" to=\"").append(((ScaleChange) c).getEndIndex1())
                          .append("\" fill=\"freeze\" />\n");
                }
                if (Double.compare(((Rectangle) m.getShapeAt(s, c.getStartTime())).getHeight(),
                        ((ScaleChange) c).getEndIndex2()) != 0) {
                  sb.append("    <animate attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime())) / speed)
                          .append("ms\" attributeName=\"height\" from=\"")
                          .append(((Rectangle) m.getShapeAt(s, c.getStartTime())).getHeight())
                          .append("\" to=\"").append(((ScaleChange) c).getEndIndex2())
                          .append("\" fill=\"freeze\" />\n");
                }
                break;
              case COLOR:
                sb.append("    <animate attributeType=\"CSS\"  attributeName=\"fill\"")
                        .append(" from=\" rgb(")
                        .append(s.getR()).append(", ").append(s.getG()).append(", ")
                        .append(s.getB()).append(")\" to=\"").append("rgb(")
                        .append(((ColorChange) c).getEndR()).append(", ").append(((ColorChange) c)
                        .getEndG()).append(", ").append(((ColorChange) c).getEndB())
                        .append(")\" begin=\"").append((int) (1000 * c.getStartTime() / speed))
                        .append("ms\" dur=\"")
                        .append((int) (1000 * (c.getEndTime() - c.getStartTime()) / speed))
                        .append("ms\" fill=\"freeze\" />\n");
                break;
              default:
                throw new IllegalArgumentException("invalid motion");
            }
          }
          sb.append("  </rect>\n\n");
          break;
        case OVAL:
          sb.append("  <ellipse id=\"").append(s.getId()).append("\" cx=\"")
                  .append(s.getPos().getX()).append("\" cy=\"").append(s.getPos()
                  .getY()).append("\" rx=\"").append((int) ((Oval) s).getRadius1())
                  .append("\" ry=\"").append((int) ((Oval) s).getRadius2()).append("\" fill=\"rgb(")
                  .append(s.getR()).append(", ").append(s.getG()).append(", ").append(s.getB())
                  .append(")\" visibility=\"hidden\" >\n");
          sb.append(String.format("    <set attributeType=\"xml\" attributeName=\"" + "visibility\" " +
                          "to=\"visible\" begin=\"%dms\" dur=\"%dms\" fill=\"remove\" />\n",
                  (int) s.getAppear() * 1000 / speed,
                  (int) (s.getDisappear()-s.getAppear() * 1000 /speed)));
          for (Change c : m.getMap().get(s)) {
            switch (c.getMotion()) {
              case MOVE:
                if (Double.compare(m.getShapeAt(s, c.getStartTime()).getPos().getX(),
                        ((PosChange) c).getEndPos().getX()) != 0) {
                  sb.append("    <animate attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime())) / speed)
                          .append("ms\" attributeName=\"cx\" from=\"")
                          .append((int) m.getShapeAt(s, c.getStartTime()).getPos().getX())
                          .append("\" to=\"").append(((PosChange) c).getEndPos().getX())
                          .append("\" fill=\"freeze\" />\n");
                }
                if (Double.compare(m.getShapeAt(s, c.getStartTime()).getPos().getY(),
                        ((PosChange) c).getEndPos().getY()) != 0) {
                  sb.append("    <animate attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime()) / speed))
                          .append("ms\" attributeName=\"cy\" from=\"")
                          .append((int) m.getShapeAt(s, c.getStartTime()).getPos().getY())
                          .append("\" to=\"").append(((PosChange) c).getEndPos().getY())
                          .append("\" fill=\"freeze\" />\n");
                }
                break;
              case SCALE:
                if (Double.compare(((Oval) m.getShapeAt(s, c.getStartTime())).getRadius1(),
                        ((ScaleChange) c).getEndIndex1()) != 0) {
                  sb.append("    <animate attributeName=\"rx\" attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime()) / speed))
                          .append("ms\" from=\"")
                          .append((int) ((Oval) m.getShapeAt(s, c.getStartTime())).getRadius1())
                          .append("\" to=\"").append(((ScaleChange) c).getEndIndex1())
                          .append("\" fill=\"freeze\" />\n");
                }
                if (Double.compare(((Oval) m.getShapeAt(s, c.getStartTime())).getRadius2(),
                        ((ScaleChange) c).getEndIndex2()) != 0) {
                  sb.append("    <animate attributeName=\"ry\" attributeType=\"xml\" begin=\"")
                          .append((int) (1000 * c.getStartTime() / speed)).append("ms\" dur=\"")
                          .append((int) (1000 * (c.getEndTime() - c.getStartTime())) / speed)
                          .append("ms\" from=\"")
                          .append((int) ((Oval) m.getShapeAt(s, c.getStartTime())).getRadius2())
                          .append("\" to=\"").append(((ScaleChange) c).getEndIndex2())
                          .append("\" fill=\"freeze\" />\n");
                }
                break;
              case COLOR:
                sb.append("    <animate attributeName=\"fill\" attributeType=\"CSS\" from=\"")
                        .append("rgb(")
                        .append(s.getR()).append(", ").append(s.getG()).append(", ")
                        .append(s.getB()).append(")\" to=\"").append("rgb(")
                        .append(((ColorChange) c).getEndR()).append(", ").append(((ColorChange) c)
                        .getEndG()).append(", ").append(((ColorChange) c).getEndB())
                        .append(")\" begin=\"").append((int) (1000 * c.getStartTime() / speed))
                        .append("ms\" dur=\"")
                        .append((int) (1000 * (c.getEndTime() - c.getStartTime()) / speed))
                        .append("ms\" fill=\"freeze\" />\n");
                break;
              default:
                throw new IllegalArgumentException("invalid movement");
            }
          }
          sb.append("  </ellipse>\n\n");
          break;
        default:
          throw new IllegalArgumentException("invalid shape.");
      }
    }
    sb.append("</svg>\n");
    return String.valueOf(sb);
  }

  @Override
  public String getCurrentState() {
    return null;
  }

  @Override
  public void outputFile() throws IOException {
    String content = this.render();
    if (!out.equals("SysOut")) {
      PrintWriter writer = new PrintWriter(out, StandardCharsets.UTF_8);
      writer.append(content);
      writer.close();
    } else {
      System.out.print(content);
    }
  }
}

