package com.geekaca.thread.pool;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        //        使用给定的初始参数、默认线程工厂和被拒绝的执行处理程序创建一个新的ThreadPoolExecutor。使用executor工厂方法之一可能比使用这个通用构造函数更方便。
//        参数:
//        corePoolSize—要保留在池中的线程数，即使它们是空闲的，除非设置了allowCoreThreadTimeOut
//        maximumPoolSize—池中允许的最大线程数
//        keepAliveTime -当线程数大于内核时，这是多余空闲线程在终止前等待新任务的最大时间。
//        unit - keepAliveTime参数的时间单位
//        workQueue——用于在任务执行前保存任务的队列。此队列将仅保存由execute方法提交的可运行任务。
//        threadFactory——执行器创建新线程时使用的工厂
//        handler-当线程边界和队列容量达到时，执行被阻塞时使用的处理程序
//        抛出:
//        IllegalArgumentException -如果以下情况之一存在:corePoolSize < 0 keepAliveTime < 0 maximumPoolSize <= 0 maximumPoolSize < corePoolSize
//        NullPointerException -如果workQueue为空
        ArrayBlockingQueue watiQueue = new ArrayBlockingQueue(20);

        ExecutorService threadPool = new ThreadPoolExecutor(2, 3, 10,
                TimeUnit.SECONDS, watiQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    }
}
