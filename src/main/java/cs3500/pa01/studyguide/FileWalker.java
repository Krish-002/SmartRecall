package cs3500.pa01.studyguide;

// ... relevant imports ...

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * FileWalker class that walks through the file tree
 */

public class FileWalker implements FileVisitor<Path> {
  private ArrayList<Path> listOfFiles;
  private final String orderingFlag;


  /**
   * constructor for a file walker
   *
   * @param orderingFlag the flag that determines the order of the files
   */
  public FileWalker(String orderingFlag) {
    this.listOfFiles = new ArrayList<>();
    this.orderingFlag = orderingFlag;
  }

  /**
   * gets the list of files
   *
   * @return the list of files
   * @throws IllegalStateException if the list of files has not been created yet
   */
  public ArrayList<Path> getListOfFiles() {

    sortFiles();
    return this.listOfFiles;

  }

  /**
   * sorts the files based on the ordering flag by mutating the list of files
   */
  private void sortFiles() {
    listOfFiles.sort(new ComparePath(this.orderingFlag));
  }

  /**
   * visits a file and adds it to the list of files
   *
   * @param file the path of the file to be visited
   * @param attr the attributes of the file using the basic file attributes class
   * @return the file visit result
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (file.toString().endsWith(".md")) {
      this.listOfFiles.add(file);
    }

    return CONTINUE;
  }

  /**
   * visits a directory and continues to look for files
   *
   * @param dir  the path of the directory to be visited
   * @param exec the IOException
   * @return the file visit result
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {

    return CONTINUE;
  }

  /**
   * visits a directory and continues to look for files
   *
   * @param dir   the path of the directory to be visited
   * @param attrs the attributes of the file using the basic file attributes class
   * @return the file visit result
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

    return CONTINUE;
  }

  /**
   * prints an error message if the file visit fails
   *
   * @param file the path of the file to be visited
   * @param exc  the IOException
   * @return the file visit result
   */

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.print(exc);
    return CONTINUE;
  }
}
