package com.wangli.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wangli.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-customer.xml")
public class CustomerSpringAnno {
    @Reference
    private TestService testService;

    @Test
    public void testCus(){

        int sum = testService.testSum(10, 656);
        System.out.println(sum);
    }

}
