package com.wangli.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangli.service.TestService;
import org.springframework.stereotype.Component;

@Component
@Service
public class TestServiceImpl implements TestService{

    public int testSum(int num1, int num2) {
        return num1+num2;
    }

}
