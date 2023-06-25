package cs3500.pa01.model;

// ... relevant imports ...

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Model for the study session
 */
public class ModelStudySession implements ModelSs {
  ArrayList<QuestionAndAnswer> listOfAllQuestions; // list of all questions in the question bank
  ArrayList<QuestionAndAnswer> sessionList; // list of questions to be asked in the study session

  int numOfQues; // number of questions to be asked in the study session
  Path pathOfQuestionBank; // path to the question bank

  int countHardQues; // number of hard questions in the question bank

  int countEasyQues; // number of easy questions in the question bank

  Random rand;

  /**
   * Constructor for the model of the study session
   *
   * @param pathOfQuestionBank path to the question bank
   * @param numOfQues          number of questions to be asked in the study session
   * @param rand               random number generator
   */
  public ModelStudySession(Path pathOfQuestionBank, int numOfQues, Random rand) {
    this.pathOfQuestionBank = pathOfQuestionBank;
    this.listOfAllQuestions = getAllQues(pathOfQuestionBank);
    this.numOfQues = Math.min(numOfQues, listOfAllQuestions.size());
    this.sessionList = new ArrayList<QuestionAndAnswer>();
    this.countHardQues = 0;
    this.countEasyQues = 0;
    this.rand = rand;
    generateSessionList();
  }

  /**
   * gets the list of questions to be asked in the study session
   *
   * @return list of questions to be asked in the study session
   */
  public ArrayList<QuestionAndAnswer> getSessionList() {
    return sessionList;
  }

  /**
   * gets the list of all questions in the question bank
   *
   * @return list of all questions in the question bank
   */
  public ArrayList<QuestionAndAnswer> getListOfAllQuestions() {
    return listOfAllQuestions;
  }

  /**
   * gets the number of hard questions in the question bank
   *
   * @return number of hard questions in the question bank
   */
  public int getCountHardQues() {
    return countHardQues;
  }

  /**
   * gets the number of easy questions in the question bank
   *
   * @return number of easy questions in the question bank
   */
  public int getCountEasyQues() {
    return countEasyQues;
  }


  /**
   * mutates the questions in the question bank
   *
   * @param listOfAllQuestions list of all questions in the question bank
   */
  public void setListOfAllQuestions(ArrayList<QuestionAndAnswer> listOfAllQuestions) {
    this.listOfAllQuestions = listOfAllQuestions;
  }

  /**
   * writes the changes made to the question bank to the file
   */
  public void writeToFile() {
    File updatedQb = new File(pathOfQuestionBank.toString());
    try (FileWriter fw = new FileWriter(updatedQb, false)) {
      for (QuestionAndAnswer qa : listOfAllQuestions) {
        fw.write(qa.toString().concat("\n"));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * updates the changes made to the question bank
   *
   * @param qaList list of questions and answers that have been answered with the updated changes.
   */
  public void updateChanges(ArrayList<QuestionAndAnswer> qaList) {
    for (QuestionAndAnswer qa : qaList) {
      QuestionAndAnswer toBeChanged = listOfAllQuestions.get(qa.getQuestionNumber());
      toBeChanged.setDifficulty(qa.getDifficulty());
      toBeChanged.setLastAccessed(qa.getLastAccessed());
    }
    writeToFile();
  }

  /**
   * generates a list of all questions to be asked in the study session by mutating the sessionList.
   */
  public void generateSessionList() {
    ArrayList<ArrayList<QuestionAndAnswer>> listArray = splitList();
    ArrayList<QuestionAndAnswer> listHard = listArray.get(0);
    ArrayList<QuestionAndAnswer> listEasy = listArray.get(1);
    Collections.shuffle(listHard, this.rand);
    Collections.shuffle(listEasy, this.rand);
    listHard.addAll(listEasy);

    for (int i = 0; i < numOfQues; i++) {
      sessionList.add(listHard.get(i));
    }
  }

  /**
   * splits the list of all questions into two lists, one with hard questions and the other
   * with easy questions
   * mutates the countHardQues and countEasyQues fields using the size of the two lists
   *
   * @return an arrayList of two lists, one with hard questions and the other with easy questions
   */

  private ArrayList<ArrayList<QuestionAndAnswer>> splitList() {
    ArrayList<QuestionAndAnswer> listHard = new ArrayList<>();
    ArrayList<QuestionAndAnswer> listEasy = new ArrayList<>();
    for (QuestionAndAnswer qa : listOfAllQuestions) {
      if (qa.getDifficulty().equals(Difficulty.HARD)) {
        listHard.add(qa);
      } else {
        listEasy.add(qa);
      }
    }
    this.countHardQues = listHard.size();
    this.countEasyQues = listEasy.size();
    ArrayList<ArrayList<QuestionAndAnswer>> full = new ArrayList<ArrayList<QuestionAndAnswer>>();
    full.add(listHard);
    full.add(listEasy);
    return full;
  }

  /**
   * gets all the questions from the question bank
   *
   * @return list of all questions from the question bank
   */
  public ArrayList<QuestionAndAnswer> getAllQues(Path pathOfQuestionBank) {
    ArrayList<QuestionAndAnswer> list = new ArrayList<QuestionAndAnswer>();
    try {
      try (Scanner fileReader = new Scanner(pathOfQuestionBank)) {
        while (fileReader.hasNextLine()) {
          String currQuestion = fileReader.nextLine();
          QuestionAndAnswer ques = stringToQuestion(currQuestion);
          list.add(ques);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  /**
   * converts a properly formatted string to a question
   *
   * @param ques string to be converted to a question
   * @return QuestionAndAnswer converted from the string
   */
  public QuestionAndAnswer stringToQuestion(String ques) {
    String[] attrArr = questionAttrToArray(ques);
    int quesNum = Integer.parseInt(attrArr[0]);
    String question = attrArr[1];
    String ans = attrArr[2];
    Difficulty diff = Difficulty.valueOf(attrArr[3]);
    Instant lastAccess = Instant.parse(attrArr[4]);

    return new QuestionAndAnswer(quesNum, question, ans, diff, lastAccess);
  }

  /**
   * takes a question and converts it to an array of strings
   *
   * @param ques question to be converted to an array of strings
   * @return array of strings converted from the question with attributes of the question at
   *         specific indices
   */
  private String[] questionAttrToArray(String ques) {
    String[] strArr = new String[5];
    Scanner s2 = new Scanner(ques);
    Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
    Matcher match = p.matcher(ques);
    int index = 0;
    while (match.find()) {
      String attribute = match.group(1);
      strArr[index] = attribute;
      index++;
    }
    return strArr;
  }
}

