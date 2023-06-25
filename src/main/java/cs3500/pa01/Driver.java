package cs3500.pa01;

//relevant imports

import cs3500.pa01.controller.ControlStudySession;
import cs3500.pa01.studyguide.FileCreator;
import cs3500.pa01.studyguide.FileWalker;
import cs3500.pa01.studyguide.InfoBlock;
import cs3500.pa01.studyguide.Utils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * main method of the driver
   *
   * @param args command line arguments
   * @throws IllegalArgumentException if the number of arguments is not 3
   * @throws IllegalArgumentException if the flag is not valid
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      String startPath = args[0];
      String orderingFlag = args[1];
      if (!orderingFlag.equals("created")
          && !orderingFlag.equals("modified")
          && !orderingFlag.equals("filename")) {
        throw new IllegalArgumentException("Invalid flag");
      }
      String location = args[2];
      ArrayList<InfoBlock> iblist = new ArrayList<InfoBlock>();
      Utils u = new Utils();
      Path p = Path.of(startPath);
      FileWalker fw = new FileWalker(orderingFlag);
      iblist.addAll(u.filesToInfoBlocks(fw, p));

      //creates the new file
      FileCreator fc = new FileCreator(iblist, location);
      fc.studyGuideMaker();
      fc.questionBankMaker(); //creates the question bank
    } else if (args.length == 0) {
      ControlStudySession css = null;
      try {
        css = new ControlStudySession(new InputStreamReader(System.in), System.out, new Random());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      css.run();  //runs the study session
    } else {
      throw new IllegalArgumentException("number of arguments are wrong");
    }
  }
}