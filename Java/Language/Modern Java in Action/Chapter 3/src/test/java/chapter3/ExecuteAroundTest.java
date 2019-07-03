package chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExecuteAroundTest {
  @Test
  public void classTest() throws IOException {
    // method we want to refactor to make more flexible
    String result = ExecuteAround.processFileLimited();
    System.out.println(result);

    System.out.println("---");

    String oneLine = ExecuteAround.processFile((BufferedReader b) -> b.readLine());
    System.out.println(oneLine);

    String twoLines = ExecuteAround.processFile((BufferedReader b) -> b.readLine() + b.readLine());
    System.out.println(twoLines);
  }
}