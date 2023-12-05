package com.psq.springwebexercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 * Description:
 *
 * @author PengShiquan
 * @version 1.0
 * @date 2023年10月31日
 */
@RequestMapping(value = {"/test"})
@RestController
@Validated
@Slf4j
public class TestController {
}
