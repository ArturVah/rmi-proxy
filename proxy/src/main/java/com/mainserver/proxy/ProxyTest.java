package com.mainserver.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyTest {

    public static void main(String[] args) {
        Calculator calculator = getCalculator();

        System.out.println(calculator.add(4,5));
        System.out.println(calculator.subtract(4,5));
        System.out.println(calculator.multiply(4,5));
        System.out.println(calculator.divide(4,5));
    }

    private static Calculator getCalculator() {
        return (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[]{Calculator.class},
                new CalculatorProxyInvocationHandler()
        );
    }

}

interface Calculator {
    Integer add(Integer a, Integer b);
    Integer subtract(Integer a, Integer b);

    Integer multiply(Integer a, Integer b);
    double divide(Integer a, Integer b);
}

class CalculatorImpl implements Calculator {

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public double divide(Integer a, Integer b) {
        return a / b;
    }
}

class CalculatorProxyInvocationHandler implements InvocationHandler {

    private final Calculator calculator = new CalculatorImpl();

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>> " + method.getName());
        System.out.println("Args: " + Arrays.toString(args));
        
        Method targetMethod = calculator
                .getClass()
                .getMethod(
                        method.getName(),
                        args[0].getClass(),
                        args[1].getClass()
                );

        return targetMethod.invoke(calculator, args[0], args[1]);
    }
}