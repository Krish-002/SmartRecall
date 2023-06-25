package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.Difficulty;
import cs3500.pa01.model.QuestionAndAnswer;
import java.time.Instant;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionAndAnswerTest {

  QuestionAndAnswer qa1;
  QuestionAndAnswer qa2;
  QuestionAndAnswer qa3;

  @BeforeEach
  void setUp() {
    qa1 = new QuestionAndAnswer("What is the capital of MA?", "Boston");
    qa2 = new QuestionAndAnswer(1, "What is the capital India?", "Delhi", Difficulty.EASY);
    qa3 = new QuestionAndAnswer(4, "Who Are You?", "Krish", Difficulty.HARD,
        Instant.parse("2023-05-27T00:24:16.968553Z"));
  }


  @Test
  void getQuestionNumber() {
    assertEquals(qa1.getQuestionNumber(), 0);
    assertEquals(qa2.getQuestionNumber(), 1);
    assertEquals(qa3.getQuestionNumber(), 4);
  }

  @Test
  void getQuestion() {
    assertEquals(qa1.getQuestion(), "What is the capital of MA?");
    assertEquals(qa2.getQuestion(), "What is the capital India?");
    assertEquals(qa3.getQuestion(), "Who Are You?");
  }

  @Test
  void getAnswer() {
    assertEquals(qa1.getAnswer(), "Boston");
    assertEquals(qa2.getAnswer(), "Delhi");
    assertEquals(qa3.getAnswer(), "Krish");
  }

  @Test
  void getDifficulty() {
    assertEquals(qa1.getDifficulty(), Difficulty.HARD);
    assertEquals(qa2.getDifficulty(), Difficulty.EASY);
  }

  @Test
  void getLastAccessed() {
    assertEquals(qa3.getLastAccessed(), Instant.parse("2023-05-27T00:24:16.968553Z"));
  }

  @Test
  void setQuestionNumber() {
    qa1.setQuestionNumber(5);
    assertEquals(qa1.getQuestionNumber(), 5);
    qa2.setQuestionNumber(10);
    assertEquals(qa2.getQuestionNumber(), 10);
  }

  @Test
  void setDifficulty() {
    qa1.setDifficulty(Difficulty.EASY);
    assertEquals(qa1.getDifficulty(), Difficulty.EASY);
    qa2.setDifficulty(Difficulty.HARD);
    assertEquals(qa2.getDifficulty(), Difficulty.HARD);
  }

  @Test
  void setLastAccessed() {
    qa1.setLastAccessed(Instant.parse("2023-05-27T00:24:16.968553Z"));
    assertEquals(qa1.getLastAccessed(), Instant.parse("2023-05-27T00:24:16.968553Z"));
    qa2.setLastAccessed(Instant.parse("2023-05-27T00:24:16.968553Z"));
    assertEquals(qa2.getLastAccessed(), Instant.parse("2023-05-27T00:24:16.968553Z"));
  }

  @Test
  void testToString() {
    assertEquals(qa3.toString(),
        "[[4]][[Who Are You?]][[Krish]][[HARD]][[2023-05-27T00:24:16.968553Z]]");
  }
}