package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.studyguide.FileWalker;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class FileWalkerTest {

  // your FileVisitor implementation test class
  private final String dir = "testFiles";

  // tests building a list of all valid Markdown paths in a directory
  @Test
  public void getListOfFiles() throws IOException {
    FileWalker fv1 = new FileWalker("filename");
    Files.walkFileTree(Path.of(dir), fv1);


    // build list of expected file paths
    ArrayList<Path> expectedFiles = new ArrayList<>();
    expectedFiles.add(Path.of(dir + "/arrays.md"));
    expectedFiles.add(Path.of(dir + "/empty.md"));
    expectedFiles.add(Path.of(dir + "/lists.md"));
    expectedFiles.add(Path.of(dir + "/vectors.md"));


    // get list of traversed Markdown file paths
    ArrayList<Path> actualFiles1 = fv1.getListOfFiles();

    // compare both lists
    assertEquals(4, actualFiles1.size());
    assertArrayEquals(expectedFiles.toArray(), actualFiles1.toArray());
  }

  @Test
  public void testFileFailed() {
    FileWalker fv1 = new FileWalker("filename");
    assertEquals(
        FileVisitResult.CONTINUE,
        fv1.visitFileFailed(Path.of("testFiles"), new IOException()));
  }


}