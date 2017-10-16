import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestDubboProvider {

    public static void main(String[] args) {

        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
            applicationContext.start();
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
