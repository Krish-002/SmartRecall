package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.studyguide.ComparePath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class ComparePathTest {

  ComparePath cp1;
  ComparePath cp2;
  ComparePath cp3;
  ComparePath invalid;
  Path path1;
  Path path2;
  LocalDateTime before;
  LocalDateTime after;

  Instant instantB;

  Instant instantA;
  BasicFileAttributes attr1;
  BasicFileAttributes attr2;


  /**
   * Tests the compare method in ComparePath
   */
  @Test
  public void testCompare() {

    cp1 = new ComparePath("filename");
    cp2 = new ComparePath("created");
    cp3 = new ComparePath("modified");

    invalid = new ComparePath("wrong");

    Path path1 = Path.of("testFiles/arrays.md");
    Path path2 = Path.of("testFiles/vectors.md");

    before = LocalDateTime.of(2022, 6, 20, 11, 29, 24);
    after = LocalDateTime.of(2023, 2, 3, 6, 23, 03);
    instantB = before.toInstant(ZoneOffset.UTC);
    instantA = after.toInstant(ZoneOffset.UTC);

    try {
      attr1 = Files.readAttributes(path1, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      attr2 = Files.readAttributes(path2, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    //invalid
    assertThrows(IllegalArgumentException.class, () -> invalid.compare(path1, path2));

    //filename
    assertTrue(cp1.compare(path1, path2) < 0);
    assertFalse(cp1.compare(path1, path2) > 0);
    assertNotEquals(0, cp1.compare(path1, path2));

    BasicFileAttributeView a1 = Files.getFileAttributeView(path1, BasicFileAttributeView.class);
    FileTime t1 = FileTime.fromMillis(1928374651);
    try {
      a1.setTimes(t1, t1, t1);
    } catch (IOException e) {
      System.out.println("IOException");
    }
    BasicFileAttributeView a2 = Files.getFileAttributeView(path2, BasicFileAttributeView.class);
    FileTime t2 = FileTime.fromMillis(0);
    try {
      a2.setTimes(t2, t2, t2);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertFalse(cp2.compare(path1, path2) < 0);
    assertTrue(cp2.compare(path1, path2) > 0);
    assertNotEquals(0, cp2.compare(path1, path2));

    try {
      Files.setLastModifiedTime(path1, FileTime.from(instantB));
    } catch (IOException e) {
      System.out.println("IOException");
    }
    try {
      Files.setLastModifiedTime(path2, FileTime.from(instantA));
    } catch (IOException e) {
      System.out.println("IOException");
    }
    assertTrue(cp3.compare(path1, path2) < 0);
    assertFalse(cp3.compare(path1, path2) > 0);
    assertNotEquals(0, cp3.compare(path1, path2));
  }
}