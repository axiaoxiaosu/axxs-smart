package com.smart.demo.functioninterface;

public class TestMethods implements Eat {

    public TestMethods(Eat eat) {
        eat.eatFood();
    }

    @Override
    public void eatFood() {
        System.out.println("你好啊");
    }
}
