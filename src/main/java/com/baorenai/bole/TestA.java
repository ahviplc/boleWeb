package com.baorenai.bole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestA {

//    @AnnotationTest(value = "a")
    String text;

//    @AnnotationTest(t = "s")
    String t;

    @AnnotationTest(value = "value",t = "t")
    public void AnnotationTest()
    {
        System.out.println("value is :");
        System.out.println("t is :"+t);
    }


    public static void main(String[] args) {
        TestA a= new TestA();
        a.AnnotationTest();
    }
}
