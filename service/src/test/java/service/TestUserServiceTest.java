package service;

import com.wangli.service.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/dubbo-provider.xml")
public class TestUserServiceTest {

    @Autowired
    private TestService testService;


    @Test
    public void testUser1(){

        int id = testService.createUser("liz", 88);
        Assert.assertTrue(id>0);

    }

}
