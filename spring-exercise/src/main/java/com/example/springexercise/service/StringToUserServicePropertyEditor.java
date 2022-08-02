package com.example.springexercise.service;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * StringToUserServicePropertyEditor.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/2
 */
public class StringToUserServicePropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("开始执行转换方法");
        UserService userService = new UserService();
        userService.setText(text);
        this.setValue(userService);
    }

    public static void main(String[] args) {
        StringToUserServicePropertyEditor propertyEditor = new StringToUserServicePropertyEditor();
        propertyEditor.setAsText("1");
        UserService value = (UserService) propertyEditor.getValue();
        value.test2();
        System.out.println(value);
    }
}
