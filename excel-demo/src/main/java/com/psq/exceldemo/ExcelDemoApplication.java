package com.psq.exceldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * com.psq.mockitotrain.ExcelDemoApplication.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/11/3
 */
@SpringBootApplication
public class ExcelDemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ExcelDemoApplication.class);
        application.run(args);
    }
}
