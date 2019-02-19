package com.atguigu.fzclass;

import java.util.concurrent.CountDownLatch;

/*
* 学生为例：
*    实现班长最后走案例
* */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println("第"+Thread.currentThread().getName()+"已经离开");
                countDownLatch.countDown();//减一
            },String.valueOf(i)).start();
            countDownLatch.await(); //班长最后走
            System.out.println(Thread.currentThread().getName()+"\t班长已经走");

        }

    }
}
