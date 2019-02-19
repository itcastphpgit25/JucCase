package com.atguigu.fzclass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/*
* 占车位案例一抢一放
* */
public class SemaphoreTest{
    public static void main(String[] args) {
        //抢占车位为例，3个车位
        Semaphore semaphore = new Semaphore(3);
        //加入有6个车子
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                //抢占车位
                try {
                    //占车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t已站车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);//占车位3秒
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放车位
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
