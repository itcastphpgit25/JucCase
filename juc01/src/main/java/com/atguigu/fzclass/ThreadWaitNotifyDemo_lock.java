package com.atguigu.fzclass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResouce_lock{
    private  int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void cr(){
        lock.lock();
        try {
            while (number!=0) {
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    public void dr(){
        lock.lock();
        try {
            while (number!=1) {
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
public class ThreadWaitNotifyDemo_lock {
    public static void main(String[] args) {
        ShareResouce_lock shareResouce_lock = new ShareResouce_lock();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResouce_lock.dr();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResouce_lock.cr();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResouce_lock.dr();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResouce_lock.cr();
            }
        },"D").start();

    }
}
