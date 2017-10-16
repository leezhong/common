package com.wangli.service.impl;

import com.wangli.dao.TestUserMapper;
import com.wangli.entity.TestUser;
import com.wangli.service.TestService;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TestServiceImpl implements TestService{

    @Autowired
    private TestUserMapper testUserMapper;


    public int testSum(int num1, int num2) {
        return num1+num2;
    }

    public int createUser(String name, int age){
            TestUser user = new TestUser();
            user.setName(name);
            user.setAge(age);
            user.setCreateTime(new Date());
            int insert = testUserMapper.insert(user);
            return insert;
    }


}
