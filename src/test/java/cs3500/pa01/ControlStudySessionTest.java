package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.controller.ControlStudySession;
import cs3500.pa01.model.ModelStudySession;
import cs3500.pa01.view.ViewStudySession;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ControlStudySessionTest {

  ModelStudySession mss;
  ViewStudySession vss;
  ControlStudySession css1;
  ControlStudySession css2;
  ControlStudySession css3;
  ControlStudySession css4;
  ControlStudySession css5;


  String correct = "testFiles/StudyGuide.sr\n10\n1\n4\n";
  String wrongPath = "testFiles/doesntExist.sr\ntestFiles/StudyGuide.sr\n10\n1\n4\n";
  String wrongNumq = "testFiles/StudyGuide.sr\napple\n10\n1\n4\n";
  String showAns = "testFiles/StudyGuide.sr\n10\n1\n3\n4\n";

  String easyQues = "testFiles/easyQues.sr\n10\n2\n4\n";
  StringBuilder sb = new StringBuilder();

  String correctAns1 = "\n\nWelcome to your Study Session! \n"
      + "Please input the path your QuestionBank File\n"
      + "Please Enter the Number of Questions\n"
      + "What is the capital of Canada?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: " + Instant.now().toString().substring(0, 10) + "\n"
      + "What is the largest lake in North America?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: 2023-05-27\n"
      + "TOTAL QUESTIONS ANSWERED: 1\n"
      + "TOTAL QUESTIONS CHANGED FROM EASY TO HARD: 0\n"
      + "TOTAL QUESTIONS CHANGED FROM HARD TO EASY: 0\n"
      + "TOTAL HARD QUESTIONS: 29\n"
      + "TOTAL EASY QUESTIONS: 0\n";

  String correctAns2 = "\n\nWelcome to your Study Session! \n"
      + "Please input the path your QuestionBank File\n"
      + "Invalid Input. Please Try Again\n"
      + "Please input the path your QuestionBank File\n"
      + "Please Enter the Number of Questions\n"
      + "What is the capital of Canada?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: " + Instant.now().toString().substring(0, 10) + "\n"
      + "What is the largest lake in North America?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: 2023-05-27\n"
      + "TOTAL QUESTIONS ANSWERED: 1\n"
      + "TOTAL QUESTIONS CHANGED FROM EASY TO HARD: 0\n"
      + "TOTAL QUESTIONS CHANGED FROM HARD TO EASY: 0\n"
      + "TOTAL HARD QUESTIONS: 29\n"
      + "TOTAL EASY QUESTIONS: 0\n";

  String correctAns3 = "\n\nWelcome to your Study Session! \n"
      + "Please input the path your QuestionBank File\n"
      + "Please Enter the Number of Questions\n"
      + "Invalid Input. Please Try Again\n"
      + "Please Enter the Number of Questions\n"
      + "What is the capital of Canada?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: " + Instant.now().toString().substring(0, 10) + "\n"
      + "What is the largest lake in North America?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: 2023-05-27\n"
      + "TOTAL QUESTIONS ANSWERED: 1\n"
      + "TOTAL QUESTIONS CHANGED FROM EASY TO HARD: 0\n"
      + "TOTAL QUESTIONS CHANGED FROM HARD TO EASY: 0\n"
      + "TOTAL HARD QUESTIONS: 29\n"
      + "TOTAL EASY QUESTIONS: 0\n";

  String correctAns4 = "\n\nWelcome to your Study Session! \n"
      + "Please input the path your QuestionBank File\n"
      + "Please Enter the Number of Questions\n"
      + "What is the capital of Canada?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: " + Instant.now().toString().substring(0, 10) + "\n"
      + "What is the largest lake in North America?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: 2023-05-27\n"
      + "The largest lake is Lake Superior.\n"
      + "TOTAL QUESTIONS ANSWERED: 1\n"
      + "TOTAL QUESTIONS CHANGED FROM EASY TO HARD: 0\n"
      + "TOTAL QUESTIONS CHANGED FROM HARD TO EASY: 0\n"
      + "TOTAL HARD QUESTIONS: 29\n"
      + "TOTAL EASY QUESTIONS: 0\n";

  String correctAns5 = "\n\nWelcome to your Study Session! \n"
      + "Please input the path your QuestionBank File\n"
      + "Please Enter the Number of Questions\n"
      + "What is the capital of Mexico?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: " + Instant.now().toString().substring(0, 10) + "\n"
      + "What is the capital of Mexico?\n"
      + "1. Mark Hard, 2. Mark Easy, 3. Show Answer 4. Exit\n"
      + "Last Accessed On: 2023-05-27\n"
      + "TOTAL QUESTIONS ANSWERED: 1\n"
      + "TOTAL QUESTIONS CHANGED FROM EASY TO HARD: 0\n"
      + "TOTAL QUESTIONS CHANGED FROM HARD TO EASY: 0\n"
      + "TOTAL HARD QUESTIONS: 0\n"
      + "TOTAL EASY QUESTIONS: 2\n";


  @BeforeEach
  void setUp() throws IOException {
    css1 = new ControlStudySession(new StringReader(correct), sb, new Random(1));
    css2 = new ControlStudySession(new StringReader(wrongPath), sb, new Random(1));
    css3 = new ControlStudySession(new StringReader(wrongNumq), sb, new Random(1));
    css4 = new ControlStudySession(new StringReader(showAns), sb, new Random(1));
    css5 = new ControlStudySession(new StringReader(easyQues), sb, new Random(1));
  }

  @Test
  void run() throws IOException {
    System.out.println(LocalDate.now().toString());
    css1.run();
    assertEquals(correctAns1, sb.toString());
    sb.setLength(0);
    css2.run();
    assertEquals(correctAns2, sb.toString());
    sb.setLength(0);
    css3.run();
    assertEquals(correctAns3, sb.toString());
    sb.setLength(0);
    css4.run();
    assertEquals(correctAns4, sb.toString());
    sb.setLength(0);
    css5.run();
    assertEquals(correctAns5, sb.toString());

  }
}