package com.atguigu.fzclass;

import java.util.concurrent.TimeUnit;

/*
* 1 标准访问，先打印短信还是邮件:短信  此时锁的是资源类，而不是方法，必须等第一个线程执行完成后第二个线程才可以执行
 *2 停4秒在短信方法内，先打印短信还是邮件：短信
* 3 新增普通的phone方法，是先打短信还是phone:phone  相当于一个手机被两个线程玩，这次玩的是手机和手机壳
 4 现在有两部手机，先打印短信还是邮件:邮件  两部手机不相干，各干各的
 5 两个静态同步方法，1部手机，先打印短信还是邮件:短信  锁的是全局对象，抢的是全局对象
 6 两个静态同步方法，2部手机，先打印短信还是邮件：短信
 7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件：邮件
 8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件：

 总结：synchronized实现同步的基础：java中的每一个对象都可以作为锁
    具体表现三种形式：
    对于普通同步方法，锁是当前实例对象
    对于静态同步方法，锁是当前类的Class对象
    对于同步代码块来说，锁是Synchonized括号里配置的对象
* */
class Phone{ //手机
    public static synchronized  void sendSMS() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName()+"\tsendSMS");
    }
    public  synchronized  void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\tsendEmail");
    }

    public void getHello(){  //相当于手机壳，和加锁的不相干
        System.out.println("phone");
    }
}

public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();
        //保证AA用工在BB前面运行
        TimeUnit.SECONDS.sleep(2);

        new Thread(()->{
            phone2.sendEmail();
            //phone.getHello();
            //phone.getHello();
            //phone.sendEmail();
        },"BB").start();
    }
}
