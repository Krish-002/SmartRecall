package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.studyguide.FileWalker;
import cs3500.pa01.studyguide.InfoBlock;
import cs3500.pa01.studyguide.Utils;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class UtilsTest {
  Utils util = new Utils();

  Path p0 = Path.of("testFiles");
  Path p1 = Path.of("testFiles/arrays.md");
  Path p2 = Path.of("testFiles/vectors.md");
  Path p3 = Path.of("testFiles/lists.md");


  //tests for pathToInfoBlocks
  @Test
  void pathToInfoBlocks() {
    ArrayList<InfoBlock> listofib = util.pathToInfoBlocks(p1);
    assertEquals(3, listofib.size());
    ArrayList<InfoBlock> listofib2 = util.pathToInfoBlocks(p2);
    assertEquals(3, listofib2.size());
    ArrayList<InfoBlock> listofib3 = util.pathToInfoBlocks(p3);
    assertEquals(1, listofib3.size());
    ArrayList<String> impInfoList1 = new ArrayList<>();
    impInfoList1.add("An **array** is a collection of variables of the same type");
    InfoBlock ib = new InfoBlock("# Java Arrays ", impInfoList1);
    assertEquals(ib.toString(), listofib.get(0).toString());
  }

  //tests for impInfoList
  @Test
  void impInfoList() {
    ArrayList<String> list = new ArrayList<>();
    list.add("collection of variables");
    list.add("type");
    list.add("collection of");

    StringBuilder sb = new StringBuilder();
    String s = "- An **array** is a [[collection of variables]] of the same [[type]]";
    sb.append(s);
    String s2 = "- A **vector** is a [[collection of]] of the same type";
    sb.append(s2);
    assertEquals(list, util.impInfoList(sb.toString()));
  }

  @Test
  void testFilesToInfoBlocks() {
    FileWalker fw = new FileWalker("filename");
    ArrayList<InfoBlock> listofib = null;
    listofib = util.filesToInfoBlocks(fw, p0);
    assertEquals(7, listofib.size());
  }

}