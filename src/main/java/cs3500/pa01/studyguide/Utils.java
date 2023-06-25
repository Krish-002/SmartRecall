package cs3500.pa01.studyguide;

// ... relevant imports ...

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils class for helper methods that allow for the program to work
 */
public class Utils {

  /**
   * breaks down a file into info blocks
   *
   * @param file the path to the file
   * @return an arraylist of info blocks
   */

  public ArrayList<InfoBlock> pathToInfoBlocks(Path file) {
    ArrayList<InfoBlock> ibList = new ArrayList<>();
    Scanner s = null;
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    InfoBlock currBlock = null;
    try {
      s = new Scanner(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    while (s.hasNextLine()) {
      String currLine = s.nextLine().trim() + " ";
      if (currLine.startsWith("#")) {
        if (first) {
          currBlock = new InfoBlock(currLine, new ArrayList<String>());
          ibList.add(currBlock);
          first = false;
        } else {
          currBlock.setImpPoints(impInfoList(sb.toString()));
          currBlock = new InfoBlock(currLine, new ArrayList<String>());
          ibList.add(currBlock);
          sb.setLength(0);
        }
      } else if (currBlock != null) {
        sb.append(currLine);
        if (!s.hasNextLine()) {
          currBlock.setImpPoints(impInfoList(sb.toString()));
        }
      }
    }
    return ibList;
  }

  /**
   * gets the important information from a bullet point
   *
   * @param bulletPoint bullet points
   * @return an arraylist of important information
   */

  public ArrayList<String> impInfoList(String bulletPoint) {
    ArrayList<String> impInfo = new ArrayList<>();
    Scanner s2 = new Scanner(bulletPoint);
    Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
    Matcher match = p.matcher(bulletPoint);
    while (match.find()) {
      String impPoint = match.group(1);
      impInfo.add(impPoint);
    }
    s2.close();
    return impInfo;
  }

  /**
   * Coverts the files in the file tree to info blocks
   *
   * @param fw Filewalker object which goes through the .md file
   * @param p  the path to the file
   * @return ArrayList of InfoBlocks
   */
  public ArrayList<InfoBlock> filesToInfoBlocks(FileWalker fw, Path p) {
    Utils u = new Utils();
    ArrayList<InfoBlock> iblist = new ArrayList<InfoBlock>();
    //walks through the file tree
    try {
      Files.walkFileTree(p, fw);
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }

    //gets the list of files
    ArrayList<Path> list = fw.getListOfFiles();

    // gets the info blocks from the files
    for (Path path : list) {
      ArrayList<InfoBlock> iblistTemp = u.pathToInfoBlocks(path);
      iblist.addAll(iblistTemp);
    }

    return iblist;
  }

}