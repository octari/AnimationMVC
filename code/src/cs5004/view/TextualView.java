package cs5004.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import cs5004.model.ReadOnlyModel;

public class TextualView implements IView {
  private ReadOnlyModel m;
  private String out;

  public TextualView(ReadOnlyModel m, String out) {
    this.m = m;
    this.out = out;
  }

  @Override
  public String render() {
    return m.getState();
  }

  @Override
  public String getCurrentState() {
    return null;
  }

  @Override
  public void outputFile() throws FileNotFoundException, UnsupportedEncodingException {
    if (!out.equals("SysOut")) {
      PrintWriter writer = new PrintWriter(out, "UTF-8");
      writer.print(m.getState());
      writer.close();
    }else{
      System.out.print(this.render());
    }
  }
}