package cs3500.pa01.model;

// ... relevant imports ...

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Model interface for the study session
 */
public interface ModelSs {
  /**
   * writes the changes made to the question bank to the file
   */
  public void writeToFile();

  /**
   * updates the changes made to the question bank
   *
   * @param qaList list of questions to be updated
   */
  public void updateChanges(ArrayList<QuestionAndAnswer> qaList);

  /**
   * generates the list of questions to be asked in the study session
   */
  public void generateSessionList();

  /**
   * gets all the questions from the question bank
   *
   * @param pathOfQuestionBank path to the question bank
   * @return list of all questions in the question bank
   */
  public ArrayList<QuestionAndAnswer> getAllQues(Path pathOfQuestionBank);
}
