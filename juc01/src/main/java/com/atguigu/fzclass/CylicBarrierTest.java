package com.atguigu.fzclass;

import javafx.scene.shape.Cylinder;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CylicBarrierTest {
    private static final int NUMBER=7;//定义七颗龙珠
    //龙珠案例，及其7可龙珠可集成
    public static void main(String[] args) {
        CyclicBarrier cylicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("龙珠集成，一统天下！");
        });

        for (int i = 1; i <=7 ; i++) {
            final int a=i;
            new Thread(()->{
                try {
                    System.out.println("龙珠"+a+"已经集成");
                    cylicBarrier.await();//只等最后一个线程收集完成后才可以合成最终
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
