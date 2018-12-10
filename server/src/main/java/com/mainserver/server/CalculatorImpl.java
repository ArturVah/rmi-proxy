package com.mainserver.server;

import com.mainserver.calculator.Calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.text.MessageFormat;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    CalculatorImpl() throws RemoteException {
    }

    public int sum(int a, int b) {
        System.out.println("Incoming request");
        System.out.println(MessageFormat.format("a = {0}, b = {1}", a, b));
        return a + b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

}
