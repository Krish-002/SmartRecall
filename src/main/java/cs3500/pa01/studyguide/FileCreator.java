package cs3500.pa01.studyguide;

//relevant imports

import cs3500.pa01.model.QuestionAndAnswer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 */
public class FileCreator {
  private ArrayList<InfoBlock> sortedInfo;
  private String outputPath;

  /**
   * constructor for a file creator
   *
   * @param sortedInfo the sorted info blocks
   * @param opath      the output path location for the file created
   */

  public FileCreator(ArrayList<InfoBlock> sortedInfo, String opath) {
    this.sortedInfo = sortedInfo;
    this.outputPath = opath;
  }

  /**
   * creates a new file with the sorted info blocks called StudyGuide.md
   * if it already exists, it overwrites it
   */
  public void studyGuideMaker() {
    try {
      File studyGuide = new File(this.outputPath);
      try (FileWriter fw = new FileWriter(studyGuide, false)) {
        for (InfoBlock i : this.sortedInfo) {
          fw.write(i.toString().concat("\n"));
        }
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * creates a new file with the sorted info blocks called QuestionBank.sr
   * if it already exists, it overwrites it
   */
  public void questionBankMaker() {
    try {
      File questionBank = new File(changeExtension(this.outputPath));
      try (FileWriter fw = new FileWriter(questionBank, false)) {
        int quesNumAssigner = 0;
        for (InfoBlock i : this.sortedInfo) {
          for (QuestionAndAnswer qa : i.getQaList()) {
            qa.setQuestionNumber(quesNumAssigner);
            fw.write(qa.toString().concat("\n"));
            quesNumAssigner++;
          }
        }
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static String changeExtension(String s) {
    StringBuilder sb = new StringBuilder();
    sb.append(s);
    int c = sb.length() - 1;
    while (sb.charAt(c) != '.') {
      sb.deleteCharAt(c);
      c--;
    }
    sb.append("sr");
    return sb.toString();
  }
}

