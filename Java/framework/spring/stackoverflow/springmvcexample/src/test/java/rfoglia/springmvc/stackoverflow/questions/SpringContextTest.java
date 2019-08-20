package rfoglia.springmvc.stackoverflow.questions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
public class SpringContextTest {
  @Autowired
  public MyPrototype myPrototype;

  @Autowired
  public ApplicationContext applicationContext;

  @Test
  public void test() {
    // https://stackoverflow.com/questions/57529937/spring-java-injected-singleton-into-prototype-bean
    // @@@ Spring dependencies injections
    System.out.println("The first call - @Autowired");
    myPrototype.callMySingleton();

    MyPrototype myPrototype2 = (MyPrototype) applicationContext.getBean("my.prototypeBean");
    System.out.println("The second call - getBean(id)");
    myPrototype2.callMySingleton();

    MyPrototype myPrototype3 = (MyPrototype) applicationContext.getBean(MyPrototype.class);
    System.out.println("The third call - getBean(class)");
    myPrototype3.callMySingleton();
  }
}
