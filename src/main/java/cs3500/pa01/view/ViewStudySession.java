package cs3500.pa01.view;

import cs3500.pa01.model.QuestionAndAnswer;
import java.io.IOException;
import java.util.Objects;

/**
 * View for the study session
 */
public class ViewStudySession {

  private Appendable appendable;

  public ViewStudySession(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  public Appendable getAppendable() {
    return appendable;
  }

  public void showWelcomeScreen() throws IOException {
    appendable.append(
        "\n\nWelcome to your Study Session! \n");
  }

  /**
   * asks the user for the number of questions they want to be asked
   */
  public void askPath() throws IOException {
    appendable.append(
        "Please input the path your QuestionBank File\n");
  }

  /**
   * asks the user for the number of questions they want to be asked
   */
  public void askNumOfQues() throws IOException {
    appendable.append("Please Enter the Number of Questions\n");
  }

  /**
   * shows the question to the user and the instructions and the last accessed date
   *
   * @param qa question and answer
   */
  public void showQuestion(QuestionAndAnswer qa) throws IOException {
    appendable.append(qa.getQuestion()).append("\n")
        .append("1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n").append("Last Accessed On: ")
        .append(qa.getLastAccessed().toString().substring(0, 10)).append("\n");
  }

  /**
   * shows the summary of the study session
   *
   * @param quesAnswered  number of questions answered
   * @param easyToHard    number of questions changed from easy to hard
   * @param hardToEasy    number of questions changed from hard to easy
   * @param totalHardQues number of hard questions in the question bank
   * @param totalEasyQues number of easy questions in the question bank
   */
  public void getSummary(int quesAnswered, int easyToHard, int hardToEasy, int totalHardQues,
                         int totalEasyQues) throws IOException {
    appendable.append("TOTAL QUESTIONS ANSWERED: ").append(String.valueOf(quesAnswered))
        .append("\n").append("TOTAL QUESTIONS CHANGED FROM EASY TO HARD: ")
        .append(String.valueOf(easyToHard)).append("\n")
        .append("TOTAL QUESTIONS CHANGED FROM HARD TO EASY: ").append(String.valueOf(hardToEasy))
        .append("\n").append("TOTAL HARD QUESTIONS: ").append(String.valueOf(totalHardQues))
        .append("\n").append("TOTAL EASY QUESTIONS: ").append(String.valueOf(totalEasyQues))
        .append("\n");
  }

  /**
   * shows invalid input
   */
  public void showInvalidInput() throws IOException {
    appendable.append("Invalid Input. Please Try Again\n");
  }

  public void showAns(QuestionAndAnswer currQa) throws IOException {
    appendable.append(currQa.getAnswer()).append("\n");
  }
}
