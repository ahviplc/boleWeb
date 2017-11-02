package com.baorenai.bole;

import java.lang.reflect.Field;

public class AnnotationInfo {

    static void getAnnotation(Class<?> clazz)
    {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field:fields)
        {
            if(field.isAnnotationPresent(AnnotationTest.class))
            {
                AnnotationTest a = field.getAnnotation(AnnotationTest.class);
                System.out.println(a.t());
                System.out.println(a.value());
                System.out.println("----------");
            }
        }
    }
}

