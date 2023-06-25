package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import cs3500.pa01.controller.ControlStudySession;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DriverTest {

  /**
   * Tests the main method in Driver
   */
  @Test
  public void testMain() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Driver.main(new String[] {"only 1 this"});
    });

    Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
      Driver.main(new String[] {"testFiles", "wrong", "output/StudyGuide.md"});
    });

    try {
      Driver.main(new String[] {"testFiles", "filename", "output/StudyGuide.md"});
      String filepath = "output/StudyGuide.md";
      File file = new File(filepath);
      File file2 = new File("testingFileCreator/sg.md");
      Scanner s1 = new Scanner(file);
      Scanner s2 = new Scanner(file2);
      String file1Content = "";
      String file2Content = "";

      while (s1.hasNextLine()) {
        file1Content += s1.nextLine();
      }
      s1.close();
      while (s2.hasNextLine()) {
        file2Content += s2.nextLine();
      }
      s2.close();
      assertEquals(file1Content, file2Content);

      File f1 = new File("output/StudyGuide.sr");
      assertTrue(f1.exists());

    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }
}

