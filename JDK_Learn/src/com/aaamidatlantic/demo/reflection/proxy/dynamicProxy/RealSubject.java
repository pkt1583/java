package com.aaamidatlantic.demo.reflection.proxy.dynamicProxy;

//�����ɫ

public class RealSubject implements Subject
{

    public RealSubject()
    {
    }

    public void request()
    {
        System.out.println("From real subject.");
    }

}