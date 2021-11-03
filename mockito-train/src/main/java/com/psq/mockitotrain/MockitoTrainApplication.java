package com.psq.mockitotrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * com.psq.mockitotrain.MockitoTrainApplication.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/11/3
 */
@SpringBootApplication
public class MockitoTrainApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MockitoTrainApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
