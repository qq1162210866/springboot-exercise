package com.psq.dubboserviceconsumer.controlle;

import com.psq.dubboserviceapi.service.SayHelloService;

/**
 * SayHelloServiceMock.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/12
 */
public class SayHelloServiceMock implements SayHelloService {
    @Override
    public String sayHello() {
        return "这是本地模拟数据，服务降级";
    }
}
