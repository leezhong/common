package mapper;

import com.wangli.dao.TestUserMapper;
import com.wangli.entity.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TestUserMapperTest {

    @Autowired
    private TestUserMapper testUserMapper;

    @Test
    public void testUserMapper(){

        TestUser testUser = testUserMapper.selectByPrimaryKey(1);
        System.out.println(testUser);

    }

}
