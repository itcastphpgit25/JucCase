package com.atguigu.fzclass;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("come back in");
        return 200;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //因为new Thread(...)参数里面不支持Callable类型,但是支持Runnable接口
        //又因为FutureTask实现了Runnable接口，又支持Callable类型接口，所以虚假成真
        //FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());

        //简化之
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            System.out.println("come back in");
            return 2000;
        });
        Thread thread = new Thread(futureTask);
        thread.start();

        //通过get获取任务值
        System.out.println("获得返回值为："+futureTask.get());
    }
}
