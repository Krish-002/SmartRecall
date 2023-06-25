package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.studyguide.FileCreator;
import cs3500.pa01.studyguide.InfoBlock;
import cs3500.pa01.studyguide.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class FileCreatorTest {

  /**
   * Tests the newFileMaker method in FileCreator
   */
  Utils util = new Utils();
  ArrayList<InfoBlock> listofib = util.pathToInfoBlocks(Path.of("testFiles/arrays.md"));
  FileCreator fc = new FileCreator(listofib, "output/StudyGuide.md");

  @Test
  void studyGuideMaker() {
    fc.studyGuideMaker();
    String filepath = "output/StudyGuide.md";
    File file = new File(filepath);
    File file2 = new File("testingFileCreator/testArray.md");
    Scanner s1 = null;
    try {
      s1 = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    Scanner s2 = null;
    try {
      s2 = new Scanner(file2);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
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

  }

  @Test
  void questionBankMaker() {
    fc.questionBankMaker();
    Files.exists(Path.of("output/StudyGuide.sr"));
  }
}