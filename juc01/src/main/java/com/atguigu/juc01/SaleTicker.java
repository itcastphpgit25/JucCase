package com.atguigu.juc01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class TicKet{
    //操作
    private int number=30;
    //定义锁
    public Lock lock=new ReentrantLock();
//    public synchronized void sale(){
//        if(number>0){
//            System.out.println(Thread.currentThread().getName()+"卖出第："+(number)+"还剩"+(--number));
//        }
//        this.notifyAll();
//    }
    public void sale(){

        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出第："+(number)+"还剩"+(--number));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}

public class SaleTicker {
    public static void main(String[] args) {
        //线程
        TicKet ticKet = new TicKet();

        //lambda表达式:拷贝小括号，写死右箭头，落地大括号
        new Thread(()->{ for(int i=0;i<40;i++)ticKet.sale();},"A").start();
        new Thread(()->{ for(int i=0;i<40;i++)ticKet.sale();},"B").start();
        new Thread(()->{ for(int i=0;i<40;i++)ticKet.sale();},"C").start();
        new Thread(()->{ for(int i=0;i<40;i++)ticKet.sale();},"D").start();

//        //线程1
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<40;i++){
//                    ticKet.sale();
//                }
//            }
//        },"AA").start();
//       //线程2
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<40;i++){
//                    ticKet.sale();
//                }
//            }
//        },"BB").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<40;i++){
//                    ticKet.sale();
//                }
//            }
//        },"CC").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<40;i++){
//                    ticKet.sale();
//                }
//            }
//        },"DD").start();
    }

}
