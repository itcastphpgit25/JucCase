package com.atguigu.juc01;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*
* 举例说明集合类是不安全的
* */
public class NotSafeDemo {

    public static void main(String[] args) {

        List<String> list=new CopyOnWriteArrayList<>();//写时复制，防止修改并发异常发生
        for(int i=0;i<3;i++){ //30个线程
             new Thread(new Runnable() {
                         @Override
                         public void run() {
                             list.add(UUID.randomUUID().toString().substring(0,4));
                             System.out.println(list);  //集合默认重写了toStirng()方法
                         }
                     },String.valueOf(i)).start();//String.valueOf(i),将int数据转换成字符串
        }
    }

    public static void setNotSafe() {
        HashSet<Object> objects = new HashSet<>();
        Set<String> set=new CopyOnWriteArraySet<>();
        for (int i = 0; i <30 ; i++) {
             new Thread(new Runnable() {
                         @Override
                         public void run() {
                             set.add(UUID.randomUUID().toString().substring(0,4));
                             System.out.println(set);
                         }
                     },"ThreadName").start();
        }
    }

}
