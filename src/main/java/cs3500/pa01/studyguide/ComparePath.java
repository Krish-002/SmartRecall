package cs3500.pa01.studyguide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

/**
 * This class compares two paths based on the ordering flag
 */
public class ComparePath implements Comparator<Path> {
  private  String flag;

  /**
   * constructor for a compare path
   *
   * @param flag the flag that determines the order of the files
   */
  public ComparePath(String flag) {
    this.flag = flag;
  }

  /**
   * compares two paths based on the ordering flag
   *
   * @param o1 the first path
   * @param o2 the second path
   * @return the result of the comparison
   */

  @Override
  public int compare(Path o1, Path o2) {
    BasicFileAttributes attr1 = null;
    try {
      attr1 = Files.readAttributes(o1, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    BasicFileAttributes attr2 = null;
    try {
      attr2 = Files.readAttributes(o2, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return switch (this.flag) {
      case "filename" -> o1.getFileName().compareTo(o2.getFileName());
      case "created" -> attr1.creationTime().compareTo(attr2.creationTime());
      case "modified" -> attr1.lastModifiedTime().compareTo(attr2.lastModifiedTime());
      default -> throw new IllegalArgumentException("Flag wrong");
    };
  }
}
