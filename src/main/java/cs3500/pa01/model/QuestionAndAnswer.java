package cs3500.pa01.model;

// ... relevant imports ...

import cs3500.pa01.model.Difficulty;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * QuestionAndAnswer class for the question and answer
 */
public class QuestionAndAnswer {

  private int questionNumber; // question number
  private String question; // question
  private String answer; // answer
  private Difficulty difficulty; // difficulty of the question
  private Instant lastAccessed; // last accessed DATE of the question

  /**
   * Constructor for the question and answer
   *
   * @param question question
   * @param answer   answer
   */
  public QuestionAndAnswer(String question, String answer) {
    this.questionNumber = 0;
    this.question = question;
    this.answer = answer;
    this.difficulty = Difficulty.HARD;
    this.lastAccessed = Instant.now();
  }

  /**
   * Constructor for the question and answer
   *
   * @param questionNumber question number
   * @param question       question
   * @param answer         answer
   * @param dif            difficulty of the question
   */
  public QuestionAndAnswer(int questionNumber, String question, String answer, Difficulty dif) {
    this.questionNumber = questionNumber;
    this.question = question;
    this.answer = answer;
    this.difficulty = dif;
    this.lastAccessed = Instant.now();
  }

  /**
   * Constructor for the question and answer
   *
   * @param questionNumber question number
   * @param question       question
   * @param answer         answer
   * @param dif            difficulty of the question
   * @param lastAccessed   last accessed date of the question
   */
  public QuestionAndAnswer(int questionNumber, String question, String answer, Difficulty dif,
                           Instant lastAccessed) {
    this.questionNumber = questionNumber;
    this.question = question;
    this.answer = answer;
    this.difficulty = dif;
    this.lastAccessed = lastAccessed;
  }

  /**
   * gets the question number
   *
   * @return question number
   */
  public int getQuestionNumber() {
    return questionNumber;
  }

  /**
   * getter for the question
   *
   * @return question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * getter for the answer
   *
   * @return answer
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * getter for the difficulty
   *
   * @return difficulty
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * getter  for the last accessed date
   *
   * @return last accessed date
   */
  public Instant getLastAccessed() {
    return lastAccessed;
  }

  /**
   * setter for the question number
   *
   * @param questionNumber question number
   */
  public void setQuestionNumber(int questionNumber) {
    this.questionNumber = questionNumber;
  }

  /**
   * setter for the difficulty
   *
   * @param difficulty difficulty
   */
  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * setter for the last accessed date
   *
   * @param lastAccessed last accessed date
   */
  public void setLastAccessed(Instant lastAccessed) {
    this.lastAccessed = lastAccessed;
  }

  /**
   * convert the question and answer to string
   *
   * @return string of the question and answer
   */
  @Override
  public String toString() {
    return "[[" + this.questionNumber + "]]" + "[[" + this.question + "]]" + "[[" + this.answer
        + "]]" + "[[" + this.difficulty.toString() + "]]" + "[[" + this.lastAccessed.toString()
        + "]]";
  }

}

