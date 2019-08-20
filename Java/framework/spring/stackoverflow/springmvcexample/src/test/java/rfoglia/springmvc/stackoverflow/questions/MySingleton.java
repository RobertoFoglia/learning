package rfoglia.springmvc.stackoverflow.questions;

public class MySingleton {
  public String myName() {
   return this.getClass().getName();
  }

  public void startYourEngines() {
    System.out.println("Start your engines.");
  }

  public void enoughOfThis() {
    System.out.println("enoughOfThis");
  }
}
