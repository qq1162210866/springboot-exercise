package com.psq.dubboserviceconsumer.controlle;

import com.psq.dubboserviceapi.service.SayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SayHelloController.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/8/21
 */
@RestController
public class SayHelloController {

    //默认检查生产者是否存在
    //retries为数字，不包含第一次调用，为失败后重试次数
    //@Reference(check = false, timeout = 3000, mock = "com.psq.dubboserviceconsumer.controlle.SayHelloServiceMock", cluster = "failfast")
    @Autowired
    private SayHelloService sayHelloService;

    @GetMapping("/sayHello")
    public String sayHello() {
        String result = sayHelloService.sayHello();
        return result;
    }
}
