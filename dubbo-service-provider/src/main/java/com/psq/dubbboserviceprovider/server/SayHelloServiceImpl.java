package com.psq.dubbboserviceprovider.server;

import com.psq.dubboserviceapi.service.SayHelloService;
import org.apache.dubbo.config.annotation.Service;

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
