package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.studyguide.InfoBlock;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class InfoBlockTest {

  //tests for toString method
  @Test
  void testToString() {
    ArrayList<String> alist = new ArrayList<String>();
    alist.add("hello");
    alist.add("world");
    alist.add("!");
    InfoBlock ib = new InfoBlock("header", alist);
    assertEquals("header\n- hello\n- world\n- !\n", ib.toString());
  }
}