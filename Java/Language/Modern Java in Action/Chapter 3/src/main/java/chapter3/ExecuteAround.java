package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

  private static final String FILE = ExecuteAround.class.getResource("/data.txt").getFile().replace("%20", " ");

  public static String processFileLimited() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return p.process(br);
    }
  }
}
