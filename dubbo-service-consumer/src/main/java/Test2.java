import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Test2.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/8/16
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        // 获取远程服务代理
        SayHelloService demoService = (SayHelloService) context.getBean("SayHelloService");
        // 执行远程方法
        String hello = demoService.sayHello();
        System.err.println(hello);
        System.in.read();

    }
}
