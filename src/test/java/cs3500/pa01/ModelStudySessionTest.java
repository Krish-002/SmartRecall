package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa01.model.Difficulty;
import cs3500.pa01.model.ModelStudySession;
import cs3500.pa01.model.QuestionAndAnswer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelStudySessionTest {
  ModelStudySession mss1;
  ModelStudySession mss2;
  ModelStudySession mss3;

  @BeforeEach
  void setUp() {
    mss1 = new ModelStudySession(Path.of("testFiles/StudyGuide.sr"), 10, new Random());
    mss2 = new ModelStudySession(Path.of("testFiles/StudyGuide.sr"), 29, new Random());
    mss3 = new ModelStudySession(Path.of("testFiles/StudyGuide.sr"), 35, new Random());
  }

  @Test
  void getSessionList() {
    assertEquals(10, mss1.getSessionList().size());
    assertEquals(29, mss2.getSessionList().size());
    assertEquals(29, mss3.getSessionList().size());
  }

  @Test
  void getListOfAllQuestions() {
    assertEquals(29, mss1.getListOfAllQuestions().size());
    assertEquals(29, mss2.getListOfAllQuestions().size());
    assertEquals(29, mss3.getListOfAllQuestions().size());
  }

  @Test
  void getCountHardQues() {
    assertEquals(29, mss1.getCountHardQues());
    assertEquals(29, mss2.getCountHardQues());
  }

  @Test
  void getCountEasyQues() {
    assertEquals(0, mss1.getCountEasyQues());
    assertEquals(0, mss2.getCountEasyQues());
  }

  @Test
  void setListOfAllQuestions() {
    ArrayList<QuestionAndAnswer> qaList1 = mss1.getListOfAllQuestions();
    assertEquals(qaList1, mss1.getListOfAllQuestions());
    ArrayList<QuestionAndAnswer> qaList2 = new ArrayList<>();
    QuestionAndAnswer qa = new QuestionAndAnswer("What is the capital of MA?", "Boston");
    qaList2.add(qa);
    mss1.setListOfAllQuestions(qaList2);
    assertEquals(qaList2, mss1.getListOfAllQuestions());
  }

  @Test
  void writeToFile() {
    ArrayList<QuestionAndAnswer> qaList1 = mss1.getListOfAllQuestions();
    mss1.writeToFile();
    try {
      final List<String> l1 = Files.readAllLines(Path.of("testFiles/StudyGuide.sr"));
      ArrayList<QuestionAndAnswer> qaList2 = new ArrayList<>();
      QuestionAndAnswer qa = new QuestionAndAnswer("What is the capital of MA?", "Boston");
      qaList2.add(qa);
      mss1.setListOfAllQuestions(qaList2);
      mss1.writeToFile();
      final List<String> l2 = Files.readAllLines(Path.of("testFiles/StudyGuide.sr"));
      assertNotEquals(l1, l2);
      mss1.setListOfAllQuestions(qaList1);
      mss1.writeToFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void updateChanges() {
    ArrayList<QuestionAndAnswer> qaList1 = mss1.getListOfAllQuestions();
    ArrayList<QuestionAndAnswer> qaList2 = new ArrayList<>();
    QuestionAndAnswer qa1 = mss1.stringToQuestion(
        "[[5]][[What is the longest river in South America?]]"
            + "[[The longest river is the Amazon River.]][[EASY]][[2023-05-27T00:24:16.968553Z]]");
    qaList2.add(qa1);
    mss1.updateChanges(qaList2);
    assertEquals(mss1.getListOfAllQuestions().get(5).getDifficulty(), Difficulty.EASY);
    ArrayList<QuestionAndAnswer> qaList3 = new ArrayList<>();
    QuestionAndAnswer qa2 = mss1.stringToQuestion(
        "[[5]][[What is the longest river in South America?]]"
            + "[[The longest river is the Amazon River.]][[HARD]][[2023-05-27T00:24:16.968553Z]]");
    qaList2.add(qa2);
    mss1.updateChanges(qaList2);
  }

  @Test
  void generateSessionList() {
    ArrayList<QuestionAndAnswer> qaList1 = mss1.getListOfAllQuestions();
    assertEquals(10, mss1.getSessionList().size());
    assertEquals(29, mss3.getSessionList().size());
  }

  @Test
  void getAllQues() {
    ArrayList<QuestionAndAnswer> qaList1 = mss1.getListOfAllQuestions();
    assertEquals(29, mss1.getListOfAllQuestions().size());
    assertEquals(29, mss2.getListOfAllQuestions().size());
    assertEquals(29, mss3.getListOfAllQuestions().size());
  }

  @Test
  void stringToQuestion() {
    QuestionAndAnswer qa1 = mss1.stringToQuestion(
        "[[5]][[What is the longest river in South America?]]"
            + "[[The longest river is the Amazon River.]][[EASY]][[2023-05-27T00:24:16.968553Z]]");
    assertEquals(5, qa1.getQuestionNumber());
    assertEquals("What is the longest river in South America?", qa1.getQuestion());
    assertEquals("The longest river is the Amazon River.", qa1.getAnswer());
    assertEquals(Difficulty.EASY, qa1.getDifficulty());
    assertEquals("2023-05-27T00:24:16.968553Z", qa1.getLastAccessed().toString());
  }
}