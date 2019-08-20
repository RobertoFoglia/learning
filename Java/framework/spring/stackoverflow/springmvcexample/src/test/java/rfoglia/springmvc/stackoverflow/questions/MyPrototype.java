package rfoglia.springmvc.stackoverflow.questions;

import org.springframework.beans.factory.annotation.Autowired;

public class MyPrototype {
  @Autowired
  public MySingleton mySingleton;

  public MySingleton getMySingleton() {
    return mySingleton;
  }

  public void setMySingleton(MySingleton mySingleton) {
    this.mySingleton = mySingleton;
  }

  public void callMySingleton() {
    System.out.println(mySingleton.myName());
  }
}
