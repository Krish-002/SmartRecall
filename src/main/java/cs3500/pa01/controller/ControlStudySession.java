package cs3500.pa01.controller;

// ... relevant imports ...

import cs3500.pa01.model.Difficulty;
import cs3500.pa01.model.ModelStudySession;
import cs3500.pa01.model.QuestionAndAnswer;
import cs3500.pa01.view.ViewStudySession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Control class for the study session
 */
public class ControlStudySession implements ControlSs {
  private ModelStudySession model; // model for the study session
  private ViewStudySession view; // view for the study session
  private Path path; // path to the question bank
  private int numQues; // number of questions to be asked
  private int count; // keeps track of the number of questions asked
  private ArrayList<QuestionAndAnswer> answeredQuestions; // keeps track of the questions asked
  private final Scanner sc; // scanner for the study session

  private final Random random; // random number generator

  /**
   * constructor for the study session
   */
  public ControlStudySession(Readable rd, Appendable ap, Random random) throws IOException {
    this.sc = new Scanner(rd);
    this.view = new ViewStudySession(ap);
    this.path = null;
    this.numQues = 0;
    this.model = null;
    this.count = 0;
    this.answeredQuestions = new ArrayList<QuestionAndAnswer>();
    this.random = random;
  }

  /**
   * runs the study session
   *
   * @throws IOException if the input or output is invalid
   */
  public void run() throws IOException {
    this.view.showWelcomeScreen(); //shows the welcome screen
    getPathFromUser(); //gets the path from the user
    getNumQuesFromUser(); //gets the number of questions from the user
    this.model = new ModelStudySession(this.path, numQues, this.random);
    studySessionRun();
  }

  /**
   * starts asking the questions
   *
   * @throws IOException if the input or output is invalid
   */
  private void studySessionRun() throws IOException {
    int countEnd = this.model.getSessionList().size();
    if (count == countEnd) {
      getSummaryStudySession();
      this.model.updateChanges(this.answeredQuestions);
    } else {
      QuestionAndAnswer currQa = this.model.getSessionList().get(count);
      this.view.showQuestion(currQa);
      processUserResponse(currQa);
    }
  }

  /**
   * initializes the study session
   * gets the path to the question bank from the user
   */
  private void getPathFromUser() throws IOException {
    this.view.askPath();
    Path p1 = Path.of(sc.nextLine());
    if (Files.exists(p1) && Files.isRegularFile(p1)) {
      this.path = p1;
    } else {
      this.view.showInvalidInput();
      getPathFromUser();
    }
  }

  /**
   * initializes the study session
   * gets the number of questions from the user
   */
  private void getNumQuesFromUser() throws IOException {
    this.view.askNumOfQues();
    try {
      this.numQues = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
      this.view.showInvalidInput();
      getNumQuesFromUser();
    }
  }

  /**
   * gets the summary of the study session
   * calls the view to display the summary
   */
  private void getSummaryStudySession() throws IOException {
    int quesAnswered = this.answeredQuestions.size();
    int easyToHard = countEasyToHard();
    int hardToEasy = countHardToEasy();
    int totalHardQues = this.model.getCountHardQues() + easyToHard - hardToEasy;
    int totalEasyQues = this.model.getCountEasyQues() - easyToHard + hardToEasy;
    this.view.getSummary(quesAnswered, easyToHard, hardToEasy, totalHardQues, totalEasyQues);
  }

  /**
   * counts the number of questions that were originally easy but were answered hard
   *
   * @return integer representing the number of questions that were easy but were hard
   */
  private int countEasyToHard() {
    int count = 0;
    for (QuestionAndAnswer qa : this.answeredQuestions) {
      QuestionAndAnswer original = this.model.getListOfAllQuestions().get(qa.getQuestionNumber());
      if (original.getDifficulty().equals(Difficulty.EASY)
          && qa.getDifficulty().equals(Difficulty.HARD)) {
        count++;
      }
    }
    return count;
  }

  /**
   * counts the number of questions that were originally hard but were answered easy
   *
   * @return integer representing the number of questions that were originally hard but were
   *         answered easy
   */

  private int countHardToEasy() {
    int count = 0;
    for (QuestionAndAnswer qa : this.answeredQuestions) {
      QuestionAndAnswer original = this.model.getListOfAllQuestions().get(qa.getQuestionNumber());
      if (original.getDifficulty().equals(Difficulty.HARD)
          && qa.getDifficulty().equals(Difficulty.EASY)) {
        count++;
      }
    }
    return count;
  }

  /**
   * processes the user's response to the question
   *
   * @param currQa the current question being asked.
   */
  private void processUserResponse(QuestionAndAnswer currQa) throws IOException {
    String response = sc.nextLine();
    switch (response) {
      case "1" -> { // if the user answers hard
        QuestionAndAnswer qa =
            new QuestionAndAnswer(currQa.getQuestionNumber(), currQa.getQuestion(),
                currQa.getAnswer(), Difficulty.HARD);
        this.answeredQuestions.add(qa);
        count++;
        studySessionRun();
      }
      case "2" -> { // if the user answers easy
        QuestionAndAnswer qa =
            new QuestionAndAnswer(currQa.getQuestionNumber(), currQa.getQuestion(),
                currQa.getAnswer(), Difficulty.EASY);
        this.answeredQuestions.add(qa);
        count++;
        studySessionRun();
      }
      case "3" -> { // if the user wants to see the answer
        this.view.showAns(currQa);
        processUserResponse(currQa);
      }
      case "4" -> { // if the user wants to quit
        getSummaryStudySession();
        this.model.updateChanges(this.answeredQuestions);
      }
      default -> { // if the user enters an invalid response
        System.out.println("Enter a valid response");
        processUserResponse(currQa);
      }
    }

  }
}
