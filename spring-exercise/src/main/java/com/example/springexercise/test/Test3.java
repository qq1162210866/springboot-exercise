package com.example.springexercise.test;

import com.example.springexercise.bean.TestEnum;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Test2
 * Description:
 *
 * @author PengShiquan
 * @version 1.0
 * @date 2023年09月27日
 */
public class Test3 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("test", TestEnum.TEST_ENUM);
        map.values().forEach(o -> {
            if (o instanceof String) {
                System.err.println("判断为String" + o);
            } else {
                Gson gson = new Gson();
                System.err.println("判断为其他" + gson.toJson(o));
            }
        });
    }
}
