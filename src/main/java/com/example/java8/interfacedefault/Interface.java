package com.example.java8.interfacedefault;

/**
 * 接口新增默认方法，静态方法
 * @Author 彬
 * @Date 2019/8/2
 */
public interface Interface {

    default void sayMessage(){
        System.out.println("hello world");
    }

    void see();

    static void look(){
        System.out.println("look");
    }
}
