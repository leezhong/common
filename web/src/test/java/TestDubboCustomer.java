import com.wangli.service.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDubboCustomer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dubbo-customer.xml");
        TestService testService = (TestService)applicationContext.getBean("testService");
        System.out.println(testService.testSum(1,2));

    }
}
