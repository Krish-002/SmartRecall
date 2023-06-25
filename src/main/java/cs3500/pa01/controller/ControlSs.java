package cs3500.pa01.controller;

// ... relevant imports ...

import cs3500.pa01.model.QuestionAndAnswer;
import java.io.IOException;

/**
 * Control interface for the study session
 */
public interface ControlSs {
  /**
   * runs the study session
   *
   * @throws IOException if the input or output is invalid
   */
  void run() throws IOException;
}
