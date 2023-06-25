package cs3500.pa01.studyguide;

// ... relevant imports ...

import cs3500.pa01.model.QuestionAndAnswer;
import java.util.ArrayList;

/**
 * this class represents an info block which is a header and a list of important points
 */
public class InfoBlock {

  private String header; // the header of the info block
  private ArrayList<String> impPoints; // the list of important points
  private ArrayList<QuestionAndAnswer> qaList; // the list of questions and answers

  /**
   * constructor for an info block
   *
   * @param header    the header of the info block
   * @param impPoints the list of important points
   */
  public InfoBlock(String header, ArrayList<String> impPoints) {
    this.header = header;
    this.impPoints = impPoints;
    this.qaList = new ArrayList<QuestionAndAnswer>();
  }

  public void setImpPoints(ArrayList<String> impPoints) {
    this.impPoints = impPoints;
    separateInfoAndQues();
  }

  /**
   * gets the Q and A list
   *
   * @return the Q and A list
   */
  public ArrayList<QuestionAndAnswer> getQaList() {
    return qaList;
  }

  /**
   * gets the list of all important points and mutates the fields to seperate the questions and
   * important points.
   */
  private void separateInfoAndQues() {
    String[] tempArr = this.impPoints.toArray(new String[0]);
    for (String s : tempArr) {
      if (s.contains(":::")) {
        String[] tempArr2 = s.split(":::");
        QuestionAndAnswer qa = new QuestionAndAnswer(tempArr2[0].trim(), tempArr2[1].trim());
        this.qaList.add(qa);
        this.impPoints.remove(s);
      }
    }

  }

  /**
   * returns the string representation of an info block
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.header.concat("\n"));
    for (String s : this.impPoints) {
      sb.append("- ".concat(s.concat("\n")));
    }
    return sb.toString();
  }


}
