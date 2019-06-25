package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming;

public class Test {
    public static void main(String[] args) {
        A a = A.getNewInstance();
        a.i = 10;
        A a1 = A.getNewInstance();
        System.out.println(a1.i);
    }
}

class A {

    private static A instance;

    int i = 0;

    static A getNewInstance() {
        if (instance == null) {
            instance = new A();
        }
        return instance;
    }
}
