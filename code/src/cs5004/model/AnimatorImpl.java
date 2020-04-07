package cs5004.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cs5004.util.AnimationBuilder;


/**
 * animator.AnimatorImpl implements the Animator.AnimatorModel. An AnimatorModelImpl has a list of
 * shapes that modifies and executes commands of shapes.
 */
public class AnimatorImpl implements AnimatorModel {
  private List<Shape> shapes;
  private Map<Shape, List<Change>> map;
  private Canvas c;
  private List<Shape> lastEndState;


  /**
   * Animator.construct an AnimatorModelImpl which has a list of shapes that modifies and executes
   * commands of shapes.
   */
  public AnimatorImpl() {
    this.shapes = new ArrayList<>();
    this.map = new HashMap<>();
    c = new Canvas(0, 0, 0, 0);

  }


  @Override
  public void addShape(String id, ShapeType type, double appear, double disappear, Position p,
                       double index1, double index2, int r, int g, int b)
          throws IllegalArgumentException {
    List<String> listOfId = new ArrayList<>();
    for (Shape s : shapes) {
      listOfId.add(s.getId());
    }
    if (listOfId.contains(id)) {
      throw new IllegalArgumentException("Repeated id.");
    }
    if (type == null) {
      throw new IllegalArgumentException("Invalid type.");
    }
    switch (type) {
      case OVAL:
        Shape o = new Oval(id, type, appear, disappear, r, g, b, p, index1, index2);
        shapes.add(o);
        map.put(o, new ArrayList<>());
        break;
      case RECTANGLE:
        Shape rec = new Rectangle(id, type, appear, disappear, r, g, b, p, index1, index2);
        shapes.add(rec);
        map.put(rec, new ArrayList<>());
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + type);
    }

  }

  @Override
  public void addMove(String id, double startTime, double endTime, Position endPos)
          throws IllegalArgumentException {
    Change c = new PosChange(id, startTime, endTime, endPos);
    for (Shape s : shapes) {
      List<Change> listOfAllChanges = new ArrayList<>(map.get(s));
      List<Change> listOfAllMoves = listOfAllChanges.stream()
              .filter(f -> f.getMotion().equals(Motion.MOVE))
              .collect(Collectors.toList());
      for (Change move : listOfAllMoves) {
        if (move.getId().equals(id)) {
//          if (move.getClass().equals(c.getClass())) {
          if (!(endTime <= move.getStartTime() || move.getEndTime() <= startTime)) {
            throw new IllegalArgumentException("Conflicting time slot.");
          }
//          }
        }
      }
      if (s.getId().equals(id)) {
        if (startTime < s.getAppear() || endTime > s.getDisappear()) {
          throw new IllegalArgumentException("Start/end time should be later/before " +
                  "than appear/disappear time.");
        }
        // move can have the same position
//        if (s.getPos().getX() == endPos.getX() && s.getPos().getY() == endPos.getY()) {
//          throw new IllegalArgumentException("Start Position should be " +
//                  "different from end Position.");
//        }
        map.get(s).add(c);
        break;
      } else {
        if (shapes.get(shapes.size() - 1).equals(s)) {
          throw new IllegalArgumentException("Does not have such Id in existing Shape ids.");
        }
      }
    }
  }

  @Override
  public void addColor(String id, double startTime, double endTime, int r, int g, int b)
          throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("RGB factor out of range.");
    }
    Change c = new ColorChange(id, startTime, endTime, r, g, b);
    for (Shape s : shapes) {
      if (s.getId().equals(id)) {
        if (startTime < s.getAppear() || endTime > s.getDisappear()) {
          throw new IllegalArgumentException("Start/end time should be later/before " +
                  "than appear/disappear time.");
        }
        if (s.getR() == r && s.getG() == g && s.getB() == b) {
          throw new IllegalArgumentException("Color change should be different.");
        }
        map.get(s).add(c);
        break;
      } else {
        if (shapes.get(shapes.size() - 1).equals(s)) {
          throw new IllegalArgumentException("Does not have such Id in existing Shape ids.");
        }
      }
    }
  }

  @Override
  public void addScale(String id, double startTime, double endTime, double index1,
                       double index2) throws IllegalArgumentException {
    Change c = new ScaleChange(id, startTime, endTime, index1, index2);
    for (Shape s : shapes) {
      if (s.getId().equals(id)) {
        if (startTime < s.getAppear() || endTime > s.getDisappear()) {
          throw new IllegalArgumentException("Start/end time should be later/before " +
                  "than appear/disappear time.");
        }
        switch (s.getType()) {
          case RECTANGLE:
            if (Double.compare(((Rectangle) s).getWidth(), index1) == 0
                    && Double.compare(((Rectangle) s).getHeight(), index2) == 0) {
              throw new IllegalArgumentException("Scale change should be different.");
            }
            break;
          case OVAL:
            if (Double.compare(((Oval) s).getRadius1(), index1) == 0
                    && Double.compare(((Oval) s).getRadius2(), index2) == 0) {
              throw new IllegalArgumentException("Scale change should be different.");
            }
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + s.getType());
        }
        map.get(s).add(c);
        break;
      } else {
        if (shapes.get(shapes.size() - 1).equals(s)) {
          throw new IllegalArgumentException("Does not have such Id in existing Shape ids.");
        }
      }
    }
  }

  @Override
  public void addCanvas(int x, int y, int width, int height) {
    this.c.setX(x);
    this.c.setY(y);
    this.c.setWidth(width);
    this.c.setHeight(height);
  }

  /**
   * Builder class implements AnimationBuilder interface. Build the final document for the project.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {
    AnimatorModel IModel;
    Map<String, ShapeType> shp;
    Map<String, List<Transform>> shpTrans;
    //    List<Transform> l;
    Map<String, Integer> minTicks;
    Map<String, Integer> maxTicks;

    /**
     * Construct a document based on given model.
     */
    public Builder() {
      IModel = new AnimatorImpl();
      shp = new HashMap<>();
      shpTrans = new HashMap<>();
//      l = new ArrayList<>();
      minTicks = new HashMap<>();
      maxTicks = new HashMap<>();
    }

    @Override
    public AnimatorModel build() {
      for (String shapeId : shp.keySet()) {
        // use first Transform Start state as its original state
        IModel.addShape(shapeId, shp.get(shapeId), minTicks.get(shapeId), maxTicks.get(shapeId),
                new Position(shpTrans.get(shapeId).get(0).x1, shpTrans.get(shapeId).get(0).y1),
                shpTrans.get(shapeId).get(0).w1, shpTrans.get(shapeId).get(0).h1,
                shpTrans.get(shapeId).get(0).r1, shpTrans.get(shapeId).get(0).g1,
                shpTrans.get(shapeId).get(0).b1);
        // iterate through the List<Transform>
        for (Transform t : shpTrans.get(shapeId)) {
          // move
          if (t.x1 - t.x2 != 0 || t.y1 - t.y2 != 0) {
            IModel.addMove(shapeId, t.t1, t.t2, new Position(t.x2, t.y2));
          }
          // scale
          if (t.w1 - t.w2 != 0 || t.h1 - t.h2 != 0) {
            IModel.addScale(shapeId, t.t1, t.t2, t.w2, t.h2);
          }
          // color
          if (t.r1 - t.r2 != 0 || t.g1 - t.g2 != 0 || t.b1 - t.b2 != 0) {
            IModel.addColor(shapeId, t.t1, t.t2, t.r2, t.g2, t.b2);
          }
        }

      }
      return IModel;
    }

    @Override
    public AnimationBuilder setBounds(int x, int y, int width, int height) {
      IModel.addCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder declareShape(String name, String type) throws IllegalArgumentException {
      switch (type) {
        case "rectangle":
          shp.put(name, ShapeType.RECTANGLE);
          shpTrans.put(name, new ArrayList<>());
          break;
        case "ellipse":
          shp.put(name, ShapeType.OVAL);
          shpTrans.put(name, new ArrayList<>());
          break;
        default:
          throw new IllegalArgumentException("invalid shape.");
      }
      return this;
    }

    @Override
    public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
                                      int g1, int b1, int t2, int x2, int y2, int w2, int h2,
                                      int r2, int g2, int b2) {
      // check t1(appear), t2(disappear). if is null, then put
      minTicks.putIfAbsent(name, t1);
      maxTicks.putIfAbsent(name, t2);
      // check if time is earlier/ later than appear/disappear time
      if (t1 < minTicks.get(name)) {
        minTicks.put(name, t1);
      }
      if (t2 > maxTicks.get(name)) {
        maxTicks.put(name, t2);
      }
      shpTrans.get(name).add(new Transform(t1, t2, x1, x2, y1, y2, r1, r2, g1, g2, b1, b2,
              w1, w2, h1, h2));
      return null;
    }

    static class Transform {
      int t1, t2;
      int x1, x2, y1, y2;
      int r1, r2, g1, g2, b1, b2;
      int w1, w2, h1, h2;

      Transform(int t1, int t2, int x1, int x2, int y1, int y2, int r1, int r2, int g1, int g2,
                int b1, int b2, int w1, int w2, int h1, int h2) {
        this.t1 = t1;
        this.t2 = t2;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.r1 = r1;
        this.r2 = r2;
        this.g1 = g1;
        this.g2 = g2;
        this.b1 = b1;
        this.b2 = b2;
        this.w1 = w1;
        this.w2 = w2;
        this.h1 = h1;
        this.h2 = h2;
      }
    }
  }

  @Override
  public String getState() {
    StringBuilder output = new StringBuilder("Shapes:\n");
    List<Change> listOfAllChanges = new ArrayList<>();
    shapes.sort(Comparator.comparing(Shape::getAppear));
    for (Shape s : shapes) {
      output.append("Name:").append(s.getId()).append("\n");
      output.append("Type:").append(s.getType()).append("\n");
      switch (s.getType()) {
        case RECTANGLE:
          output.append("Min Corner: ").append(s.getPos().toString()).append(", Width: ")
                  .append(((Rectangle) s).getWidth()).append(", Height: ")
                  .append(((Rectangle) s).getHeight());
          break;
        case OVAL:
          output.append("Center: ").append(s.getPos().toString()).append(", radius1: ")
                  .append(((Oval) s).getRadius1()).append(", radius2: ")
                  .append(((Oval) s).getAppear());
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + s.getType());
      }
      output.append(", Color: (").append(s.getR()).append(", ").append(s.getG())
              .append(", ").append(s.getB()).append(")\n");
      output.append("Appears at t = ").append(s.getAppear()).append("\n");
      output.append("Disappears at t = ").append(s.getDisappear()).append("\n");
      // get list of changes, sort by happen time
      listOfAllChanges.addAll(map.get(s));
    }

    listOfAllChanges.sort(Comparator.comparing(Change::getStartTime));

    for (Change c : listOfAllChanges) {
      output.append("shape ").append(c.getId());
      switch (c.getMotion()) {
        case MOVE:
          output.append(" moves from ");
          for (Shape s : shapes) {
            output.append(s.getPos().toString());
          }
          output.append(" to ").append(((PosChange) c).getEndPos().toString());
          break;
        case SCALE:
          output.append(" scales from ");
          for (Shape s : shapes) {
            output.append(((ScaleChange) c).toString());
          }
          output.append(" to ").append(((ScaleChange) c).toString());
          break;
        case COLOR:
          output.append(" changes color from ");
          for (Shape s : shapes) {
            output.append("(").append(s.getR()).append(",").append(s.getG())
                    .append(",").append(s.getB()).append(")");
          }
          output.append(" to " + "(").append(((ColorChange) c).getEndR()).append(",")
                  .append(((ColorChange) c).getEndG()).append(",").append(((ColorChange) c)
                  .getEndB()).append(")");
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + c.getMotion());
      }
      output.append(" from t = ").append(c.getStartTime()).append("s to t = ").
              append(c.getEndTime()).append("s").append("\n");
    }
    return output.toString();
  }

  @Override
  public List<Shape> getShapesAt(double tick) {
    List<Shape> res = new ArrayList<>();
    for (Shape s : shapes) {
      res.add(this.getShapeAt(s, tick));
    }
    return res;
  }

  @Override
  public Shape getShapeAt(Shape s, double tick) throws IllegalArgumentException {
    Shape res = s.copy();
    for (Change c : map.get(s)) {
      if (c.getStartTime() > tick) {
        break;
      }
      if (c.getEndTime() < tick) {
        switch (s.getType()) {
          case RECTANGLE:
            switch (c.getMotion()) {
              case COLOR:
                res = new Rectangle(s.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        ((ColorChange) c).endR, ((ColorChange) c).endG, ((ColorChange) c).endB,
                        res.getPos(), ((Rectangle) res).getWidth(), ((Rectangle) res).getHeight());
                break;
              case MOVE:
                res = new Rectangle(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), ((PosChange) c).getEndPos(),
                        ((Rectangle) res).getWidth(), ((Rectangle) res).getHeight());
                break;
              case SCALE:
                res = new Rectangle(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), res.getPos(),
                        ((ScaleChange) c).getEndIndex1(), ((ScaleChange) c).getEndIndex2());
                break;
            }
            break;
          case OVAL:
            switch (c.getMotion()) {
              case COLOR:
                res = new Oval(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        ((ColorChange) c).endR, ((ColorChange) c).endG, ((ColorChange) c).endB,
                        res.getPos(), ((Oval) res).getRadius1(), ((Oval) res).getRadius2());
                break;
              case MOVE:
                res = new Oval(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), ((PosChange) c).getEndPos(),
                        ((Oval) res).getRadius1(), ((Oval) res).getRadius2());
                break;
              case SCALE:
                res = new Oval(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), res.getPos(),
                        ((ScaleChange) c).getEndIndex1(), ((ScaleChange) c).getEndIndex2());
                break;
              default:
                throw new IllegalStateException("Unexpected value: " + c.getMotion());
            }
            break;
        }
      } else if (c.getStartTime() <= tick && c.getEndTime() >= tick) {
        double timeElapse = c.getEndTime() - c.getStartTime();
        switch (res.getType()) {
          case RECTANGLE:
            switch (c.getMotion()) {
              case MOVE:
                double endX = ((PosChange) c).getEndPos().getX();
                double endY = ((PosChange) c).getEndPos().getY();
                double xChange = endX - res.getPos().getX();
                double yChange = endY - res.getPos().getY();
                double x = res.getPos().getX() + (tick - c.getStartTime()) / timeElapse * xChange;
                double y = res.getPos().getY() + (tick - c.getStartTime()) / timeElapse * yChange;
                res = new Rectangle(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), new Position(x, y),
                        ((Rectangle) res).getWidth(), ((Rectangle) res).getHeight());
                break;
              case COLOR:
                int endR = ((ColorChange) c).getEndR();
                int endG = ((ColorChange) c).getEndG();
                int endB = ((ColorChange) c).getEndB();
                double rChange = endR - res.getR();
                double gChange = endG - res.getG();
                double bChange = endB - res.getB();
                int newR = (int) (((ColorChange) c).getEndR()
                        + (tick - c.getStartTime()) / timeElapse * rChange);
                int newG = (int) (((ColorChange) c).getEndG()
                        + (tick - c.getStartTime()) / timeElapse * gChange);
                int newB = (int) (((ColorChange) c).getEndB()
                        + (tick - c.getStartTime()) / timeElapse * bChange);
                res = new Rectangle(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        newR, newG, newB, res.getPos(),
                        ((Rectangle) res).getWidth(), ((Rectangle) res).getHeight());
                break;
              case SCALE:
                double endIndex1 = ((ScaleChange) c).getEndIndex1();
                double endIndex2 = ((ScaleChange) c).getEndIndex2();
                double wChange = endIndex1 - ((Rectangle) res).getWidth();
                double hChange = endIndex2 - ((Rectangle) res).getHeight();
                double newW = ((Rectangle) res).getWidth()
                        + (tick - c.getStartTime()) / timeElapse * wChange;
                double newH = ((Rectangle) res).getHeight()
                        + (tick - c.getStartTime()) / timeElapse * hChange;
                res = new Rectangle(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), res.getPos(), newW, newH);
                break;
            }
            break;
          case OVAL:
            switch (c.getMotion()) {
              case MOVE:
                double endX = ((PosChange) c).getEndPos().getX();
                double endY = ((PosChange) c).getEndPos().getY();
                double xChange = endX - res.getPos().getX();
                double yChange = endY - res.getPos().getY();
                double x = res.getPos().getX() + (tick - c.getStartTime()) / timeElapse * xChange;
                double y = res.getPos().getY() + (tick - c.getStartTime()) / timeElapse * yChange;
                res = new Oval(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), new Position(x, y),
                        ((Oval) res).getRadius1(), ((Oval) res).getRadius2());
                break;
              case COLOR:
                int endR = ((ColorChange) c).getEndR();
                int endG = ((ColorChange) c).getEndG();
                int endB = ((ColorChange) c).getEndB();
                double rChange = endR - res.getR();
                double gChange = endG - res.getG();
                double bChange = endB - res.getB();
                int newR = (int) (((ColorChange) c).getEndR()
                        + (tick - c.getStartTime()) / timeElapse * rChange);
                int newG = (int) (((ColorChange) c).getEndG()
                        + (tick - c.getStartTime()) / timeElapse * gChange);
                int newB = (int) (((ColorChange) c).getEndB()
                        + (tick - c.getStartTime()) / timeElapse * bChange);
                res = new Oval(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        newR, newG, newB, res.getPos(),
                        ((Oval) res).getRadius1(), ((Oval) res).getRadius2());
                break;
              case SCALE:
                double endIndex1 = ((ScaleChange) c).getEndIndex1();
                double endIndex2 = ((ScaleChange) c).getEndIndex2();
                double wChange = endIndex1 - ((Oval) res).getRadius1();
                double hChange = endIndex2 - ((Oval) res).getRadius2();
                double newW = ((Oval) res).getRadius1()
                        + (tick - c.getStartTime()) / timeElapse * wChange;
                double newH = ((Oval) res).getRadius2()
                        + (tick - c.getStartTime()) / timeElapse * hChange;
                res = new Oval(res.getId(), res.getType(), res.getAppear(), res.getDisappear(),
                        res.getR(), res.getG(), res.getB(), res.getPos(), newW, newH);
                break;
            }
            break;
        }
      }
    }
    return res;
  }

  @Override
  public Canvas getCanvas() {
    return this.c;
  }

  @Override
  public List<Shape> getShapes() {
    return this.shapes;
  }

  @Override
  public Map<Shape, List<Change>> getMap() {
    return this.map;
  }

}
