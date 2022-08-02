package com.example.nacosexericse.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * NacosTestController.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/7/6
 */
@Controller
public class NacosTestController {

    @NacosValue(value = "${email}", autoRefreshed = true)
    private String[] emailAddress;

    @NacosValue(value = "${context}", autoRefreshed = true)
    private String context;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        for (String address : emailAddress) {
            System.err.println(address);
        }
        return context;
    }
}
