package com.atguigu.fzclass;
//题目：现在两个线程，可以操作初始值为0的一个变量，实现一个线程对该变量加1，一个线程对该变量-1，
//实现交替，来10轮，变量初始值为零

/*
* 1.高内聚低耦合前提下：线程 操作 资源类
* 2.判断+干活+通知
* 3.避免虚假唤醒
* **wait()和notify()为java.lang.Object里面的方法
* */
class  ShareResource{
    private int number=0;

    public synchronized void de() throws InterruptedException {
        while (number!=1){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+"买了第"+number+"张票，还剩："+(--number));
        this.notifyAll();
    }
    public synchronized void cr() throws InterruptedException {
        while (number!=0){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+"买了第"+number+"张票，还剩："+(++number));
        this.notifyAll();
    }
}
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

            new Thread(()->{
                for (int i = 0; i <10 ; i++) {
                    try {
                        shareResource.de();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"AA").start();

            new Thread(()->{
                for (int i = 0; i <10 ; i++) {
                    try {
                        shareResource.cr();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"BB").start();



        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    shareResource.de();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    shareResource.cr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();


    }
}
