package com.mainserver.client;

import com.mainserver.calculator.Calculator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {
        try {
            Calculator calculator =
                    (Calculator) Naming.lookup("rmi://127.0.0.1/valodik");
            System.out.println(calculator.multiply(5, 4));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
