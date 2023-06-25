package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.QuestionAndAnswer;
import cs3500.pa01.view.ViewStudySession;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewStudySessionTest {
  ViewStudySession vss1;
  QuestionAndAnswer qa1;

  @BeforeEach
  void setUp() {
    vss1 = new ViewStudySession(new StringBuilder());
    qa1 = new QuestionAndAnswer("What is the capital of MA?", "Boston");
  }

  @Test
  void getAppendable() throws IOException {
    vss1.showInvalidInput();
    assertEquals("Invalid Input. Please Try Again\n", vss1.getAppendable().toString());
  }

  @Test
  void showWelcomeScreen() throws IOException {
    vss1.showWelcomeScreen();
    assertEquals("\n\nWelcome to your Study Session! \n",
        vss1.getAppendable().toString());
  }

  @Test
  void askPath() throws IOException {
    vss1.askPath();
    assertEquals("Please input the path your QuestionBank File\n",
        vss1.getAppendable().toString());
  }

  @Test
  void askNumOfQues() throws IOException {
    vss1.askNumOfQues();
    assertEquals("Please Enter the Number of Questions\n",
        vss1.getAppendable().toString());
  }

  @Test
  void showQuestion() throws IOException {
    vss1.showQuestion(qa1);
    assertEquals("What is the capital of MA?\n"
        + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
        + "Last Accessed On: "
        + qa1.getLastAccessed().toString().substring(0, 10)
        + "\n", vss1.getAppendable().toString());
  }

  @Test
  void getSummary() throws IOException {
    vss1.getSummary(1, 1, 1, 1, 1);
    assertEquals("TOTAL QUESTIONS ANSWERED: 1\n"
        + "TOTAL QUESTIONS CHANGED FROM EASY TO HARD: 1\n"
        + "TOTAL QUESTIONS CHANGED FROM HARD TO EASY: 1\n"
        + "TOTAL HARD QUESTIONS: 1\n"
        + "TOTAL EASY QUESTIONS: 1\n", vss1.getAppendable().toString());
  }

  @Test
  void showInvalidInput() throws IOException {
    vss1.showInvalidInput();
    assertEquals("Invalid Input. Please Try Again\n", vss1.getAppendable().toString());
  }

  @Test
  void showAns() throws IOException {
    vss1.showAns(qa1);
    assertEquals("Boston\n", vss1.getAppendable().toString());
  }
}