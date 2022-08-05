package com.example.springexercise;

import com.example.springexercise.bean.A;
import com.example.springexercise.bean.B;
import com.example.springexercise.service.OrderService;
import com.example.springexercise.service.StringToUserServiceConverter;
import com.example.springexercise.service.StringToUserServicePropertyEditor;
import com.example.springexercise.service.UserService;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.OrderComparator;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class SpringExerciseApplication {

    public static void main(String[] args) {
        test9();
    }


    //@Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> propertyEditorMap = new HashMap<>();
        // 表示StringToUserPropertyEditor可以将String转化成User类型，
        // 在Spring源码中，如果发现当前对象是String，而需要的类型是User，就会使用该PropertyEditor来做类型转化
        propertyEditorMap.put(UserService.class, StringToUserServicePropertyEditor.class);
        customEditorConfigurer.setCustomEditors(propertyEditorMap);
        return customEditorConfigurer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new
                ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Collections.singleton(new
                StringToUserServiceConverter()));
        return conversionServiceFactoryBean;
    }

    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringExerciseApplication.class);
        System.err.println("this is test");
        AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        definition.setBeanClass(UserService.class);
        context.registerBeanDefinition("user", definition);

        UserService user = (UserService) context.getBean("user");
        user.test();

    }

    public static void test2() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        definition.setBeanClass(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("user", definition);

        UserService user = (UserService) defaultListableBeanFactory.getBean("user");
        user.test();
    }

    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringExerciseApplication.class);
        Resource resource = context.getResource("file://D:\\work\\springboot-exercise\\spring-exercise\\src\\main\\java\\com\\example\\springexercise\\service\\UserService.java");
        System.err.println(resource.getFilename());
        try {
            System.err.println(resource.contentLength());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 不知道为什么无法拿到对应的页面数据，也不知道有没有请求接口
        Resource resource2 = context.getResource("http://office.gome.inc/#/portal-site/103/personal");
        try {
            System.err.println(resource2.contentLength());
            System.err.println(resource2.getFile());
            System.err.println(resource2.getURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringExerciseApplication.class);
        Map<String, Object> systemEnvironment = context.getEnvironment().getSystemEnvironment();
        System.err.println(systemEnvironment);
        System.err.println("=============");
        Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
        System.err.println(systemProperties);
    }

    public static void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringExerciseApplication.class);
        OrderService orderService = (OrderService) context.getBean("orderService");
        System.err.println(orderService.toString());
    }

    public static void test6() {
        //实际业务中可能比较有用
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToUserServiceConverter());
        UserService value = conversionService.convert(1, UserService.class);
        System.out.println(value);
    }

    public static void test7() {
        OrderComparator orderComparator = new OrderComparator();
        System.err.println(orderComparator.compare(new A(), new B()));

        List list = new ArrayList();
        list.add(new A());
        list.add(new B());
        list.sort(orderComparator);
        System.err.println(list);
    }

    public static void test8() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringExerciseApplication.class);
        UserService userService = (UserService) context.getBean("exerciseFactoryBean");
        userService.test2();
    }

    public static void test9() {
        SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader;
        try {
            metadataReader = simpleMetadataReaderFactory.getMetadataReader("com.example.springexercise.service.ExerciseFactoryBean");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        System.err.println(classMetadata.getClassName());
        for (String s : metadataReader.getAnnotationMetadata().getAnnotationTypes()) {
            System.err.println(s);
        }
    }


}
