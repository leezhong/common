package com.wangli.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wangli.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/testMethod1")
    public void testMethod1(){
        System.out.println("---testMethod1---");
        System.out.println(testService.testSum(2,3));
    }

}
