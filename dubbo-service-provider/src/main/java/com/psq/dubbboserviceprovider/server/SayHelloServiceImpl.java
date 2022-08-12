package com.psq.dubbboserviceprovider.server;

import com.psq.dubboserviceapi.service.SayHelloService;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

/**
 * com.psq.dubbboserviceprovider.server.SayHelloServiceImpl.java
 * Description: SayHelloServiceImpl
 *
 * @author Peng Shiquan
 * @date 2021/8/15
 */
@Service
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello() {
        System.err.println("开始执行dubbo接口");
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "This dubbo test";
    }
}
