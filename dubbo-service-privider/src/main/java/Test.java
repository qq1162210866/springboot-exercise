import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Test.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/8/16
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("privider.xml");
        classPathXmlApplicationContext.start();
        System.in.read();
    }
}
