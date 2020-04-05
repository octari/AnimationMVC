package cs5004.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import cs5004.model.ReadOnlyModel;

public class TextualView implements IView {
  private ReadOnlyModel m;

  public TextualView(ReadOnlyModel m, String out) {
    this.m = m;
  }

  @Override
  public void displayOutPut() {

  }

  @Override
  public void setModel(ReadOnlyModel m) {

  }

  @Override
  public String getCurrentState() {
    return m.getState();
  }

  @Override
  public void outputFile() throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter("TextualView.txt", "UTF-8");
    writer.print(m.getState());
    writer.close();
  }
}