package com.psq.dubbboserviceprovider.server;

import com.alibaba.dubbo.config.annotation.Service;
import com.psq.dubboserviceapi.service.SayHelloService;

/**
 * com.psq.dubbboserviceprovider.server.SayHelloServiceImpl.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/8/15
 */
@Service
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello() {
        return "Hello World";
    }
}
