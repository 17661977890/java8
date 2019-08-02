package com.example.java8.lambda;

/**
 * lambda表达式:
 * (1)条件：接口或抽象类中只有一个抽象方法，（只创建一个对象，适合做匿名内部类，不用再定义类继承实现）
 * (2)目的： 简化只有一个抽象方法的匿名内部类的创建
 * (3)语法->基本组成部分：
 *      （[arg1,arg2.....]）->{语句...};------()里的参数可有可无，有的话类型也可以不用写
 *         若语句块只有一句，则{}可省略。
 *         注：在语法描述中，[]用表示里面的内容可有可无。
 * (4)变量作用域：
 *      lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。---------测试错误不用final也可以
 *
 * 函数式接口：
 *  函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 *  在接口上加@FunctionalInterface注解，很友好的支持lambda表达式，比如GreetingService接口上加此注解
 *
 * @Author 彬
 * @Date 2019/8/2
 */
public class LambdaTest {

    static String salutation = "Hello! ";

    public static void main(String args[]){
        LambdaTest tester = new LambdaTest();

        // 参数类型声明（相当于）
        MathOperation addition = (int a, int b) -> a + b;

        //匿名内部类写法
        MathOperation operation1 = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a+b;
            }
        };

        // 参数不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句（没有就都没有）
        MathOperation division = (int a, int b) -> a / b;

        //匿名内部类输出
        System.out.println("1 + 2 = "+tester.operate(1,2,operation1));
        //lambda输入
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));


        //传统写法
        GreetingService greetingService =new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println(message+"!!!!!!!!!!!!");
            }
        };

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        GreetingService greetService3 = (message) ->
                salutation = message;

        greetingService.sayMessage("=====");
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
        greetService3.sayMessage("binbin");
        System.out.println(salutation);
        salutation = "ss";
        System.out.println(salutation);
    }

    /**
     * 定义接口MathOperation,只有一个方法
     */
    interface MathOperation {
        int operation(int a, int b);
    }
    /**
     * 定义接口GreetingService，只有一个方法
     */
    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}


