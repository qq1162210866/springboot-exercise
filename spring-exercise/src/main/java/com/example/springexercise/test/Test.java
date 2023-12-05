package com.example.springexercise.test;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * Test
 * Description:
 *
 * @author PengShiquan
 * @version 1.0
 * @date 2023年09月20日
 */
public class Test {
    public static void main(String[] args) throws Exception {
        test7();
    }

    public static void test() throws Exception {
        //偏向所延迟偏向
        Object tmp = new Object();
        System.out.println(ClassLayout.parseInstance(tmp).toPrintable());
        System.err.println(tmp.hashCode());
        System.out.println(ClassLayout.parseInstance(tmp).toPrintable());
        Thread.sleep(6000);
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
    }

    public static void test2() throws Exception {
        Thread.sleep(5000);
        Object o = new Object();
        //偏向锁指定线程
        new Thread(() -> {
            System.out.println("线程1开始执行" + ClassLayout.parseInstance(o).toPrintable());
            synchronized (o) {
                System.out.println("线程1拿到锁" + ClassLayout.parseInstance(o).toPrintable());
            }
            System.out.println("线程1释放锁" + ClassLayout.parseInstance(o).toPrintable());
        }).start();
        Thread.sleep(1);
        new Thread(() -> {
            System.out.println("线程2开始执行" + ClassLayout.parseInstance(o).toPrintable());
            synchronized (o) {
                System.out.println("线程2拿到锁" + ClassLayout.parseInstance(o).toPrintable());
            }
            System.out.println("线程2释放锁" + ClassLayout.parseInstance(o).toPrintable());
        }).start();
        Thread.sleep(2000);
        System.out.println("主线程拿到锁" + ClassLayout.parseInstance(o).toPrintable());
    }

    public static void test4() throws Exception {
        Thread.sleep(5000);
        //偏向锁撤销
        System.out.println("偏向锁撤销");
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.err.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Thread.sleep(3000);

        //偏向锁转换为轻量级锁
        System.out.println("偏向锁转换为轻量级锁");
        Object o2 = new Object();
        System.out.println(ClassLayout.parseInstance(o2).toPrintable());
        new Thread(() -> {
            System.out.println("线程1开始执行" + ClassLayout.parseInstance(o2).toPrintable());
            synchronized (o2) {
                System.out.println("线程1拿到锁" + ClassLayout.parseInstance(o2).toPrintable());
            }
            System.out.println("线程1释放锁" + ClassLayout.parseInstance(o2).toPrintable());
        }).start();
        Thread.sleep(1);
        new Thread(() -> {
            System.out.println("线程2开始执行" + ClassLayout.parseInstance(o2).toPrintable());
            synchronized (o2) {
                System.out.println("线程2拿到锁" + ClassLayout.parseInstance(o2).toPrintable());
            }
            System.out.println("线程2释放锁" + ClassLayout.parseInstance(o2).toPrintable());
        }).start();
        Thread.sleep(3000);

        //偏向锁转换为重量级锁
        System.out.println("偏向锁转换为重量级锁");
        Object o3 = new Object();
        System.out.println(ClassLayout.parseInstance(o3).toPrintable());
        new Thread(() -> {
            System.out.println("线程3开始执行" + ClassLayout.parseInstance(o3).toPrintable());
            synchronized (o3) {
                o3.hashCode();
                System.out.println("线程3拿到锁" + ClassLayout.parseInstance(o3).toPrintable());
            }
            System.out.println("线程3释放锁" + ClassLayout.parseInstance(o3).toPrintable());
        }).start();
        Thread.sleep(3000);
        System.out.println("主线程拿到锁" + ClassLayout.parseInstance(o3).toPrintable());
    }

    public static void test3() throws Exception {
        //jvm延迟偏向
        Thread.sleep(5000);
        Object obj = new Test3();
        //Object obj = new Integer[4];
        //obj.hashCode();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\n" + ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println(Thread.currentThread().getName() + "释放锁\n" + ClassLayout.parseInstance(obj).toPrintable());

            // jvm 优化
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread1").start();

        Thread.sleep(2000);
        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\n" + ClassLayout.parseInstance(obj).toPrintable());
            }
        }, "Thread2").start();
    }

    public static void test5() throws Exception {
        Thread.sleep(5000);
        Object o3 = new Object();
        System.out.println(ClassLayout.parseInstance(o3).toPrintable());
        new Thread(() -> {
            System.out.println("线程1开始执行" + ClassLayout.parseInstance(o3).toPrintable());
            synchronized (o3) {
                o3.hashCode();
                System.out.println("线程1拿到锁" + ClassLayout.parseInstance(o3).toPrintable());
            }
            System.out.println("线程1释放锁" + ClassLayout.parseInstance(o3).toPrintable());
        }).start();
        Thread.sleep(3000);
        System.out.println("主线程拿到锁" + ClassLayout.parseInstance(o3).toPrintable());
        new Thread(() -> {
            System.out.println("线程2开始执行" + ClassLayout.parseInstance(o3).toPrintable());
            synchronized (o3) {
                System.out.println("线程2拿到锁" + ClassLayout.parseInstance(o3).toPrintable());
            }
            System.out.println("线程2释放锁" + ClassLayout.parseInstance(o3).toPrintable());
        }).start();
        Thread.sleep(3000);
        System.out.println("主线程拿到锁" + ClassLayout.parseInstance(o3).toPrintable());
    }

    public static void test6() throws Exception {
        //延时产生可偏向对象
        Thread.sleep(5000);
        int size = 30;
        // 创建一个list，来存放锁对象
        List<Object> list = new ArrayList<>(size);

        // 线程1
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                // 新建锁对象
                Object lock = new Object();
                synchronized (lock) {
                    list.add(lock);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead1").start();

        //睡眠3s钟保证线程thead1创建对象完成
        Thread.sleep(3000);
        System.out.println("打印thead1，list中第1个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(0)).toPrintable());

        // 线程2
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                if ((i >= 17 && i <= 21) || i >= 23) {
                    System.out.println("thread2-第" + (i + 1) + "次加锁执行前\t" +
                            ClassLayout.parseInstance(obj).toPrintable());
                }
                synchronized (obj) {
                    if ((i >= 17 && i <= 21) || i >= 23) {
                        System.out.println("thread2-第" + (i + 1) + "次加锁执行中\t" +
                                ClassLayout.parseInstance(obj).toPrintable());
                    }
                }
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead2").start();
        System.out.println("代码执行完成");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //查看最终对象锁状态
        System.out.println("最终打印，list中第18个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(17)).toPrintable());
        System.out.println("最终打印，list中第19个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(18)).toPrintable());
        System.out.println("最终打印，list中第20个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(19)).toPrintable());
        LockSupport.park();

    }

    public static void test7() throws Exception {
        //延时产生可偏向对象
        Thread.sleep(5000);
        int size = 50;
        // 创建一个list，来存放锁对象
        List<Object> list = new ArrayList<>(size);

        // 线程1
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                // 新建锁对象
                Object lock = new Object();
                synchronized (lock) {
                    list.add(lock);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead1").start();

        //睡眠3s钟保证线程thead1创建对象完成
        Thread.sleep(3000);
        System.out.println("打印thead1，list中第1个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(0)).toPrintable());


        // 线程2
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                boolean printRange = (i >= 17 && i <= 21) || (i >= 23 && i <= 26) || i >= 45;
                if (printRange) {
                    System.out.println("thread2-第" + (i + 1) + "次加锁执行前\t" +
                            ClassLayout.parseInstance(obj).toPrintable());
                }
                synchronized (obj) {
                    if (printRange) {
                        System.out.println("thread2-第" + (i + 1) + "次加锁执行中\t" +
                                ClassLayout.parseInstance(obj).toPrintable());
                    }
                }
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead2").start();
        System.out.println("代码执行完成");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //查看最终对象锁状态
        System.out.println("最终打印，list中第18个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(17)).toPrintable());
        System.out.println("最终打印，list中第19个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(18)).toPrintable());
        System.out.println("最终打印，list中第20个对象的对象头：");
        System.out.println(ClassLayout.parseInstance(list.get(19)).toPrintable());
        Thread.sleep(3000);
        // 线程3
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                boolean printRange = (i >= 17 && i <= 21) || (i >= 23 && i <= 26) || i >= 45;
                if (printRange) {
                    System.out.println("thread3-第" + (i + 1) + "次加锁执行前\t" +
                            ClassLayout.parseInstance(obj).toPrintable());
                }
                synchronized (obj) {
                    if (printRange) {
                        System.out.println("thread3-第" + (i + 1) + "次加锁执行中\t" +
                                ClassLayout.parseInstance(obj).toPrintable());
                    }
                }
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead2").start();
        System.out.println("代码执行完成");

        Thread.sleep(3000);
        Object lock = new Object();
        System.out.println("最终打印，新lock对象的对象头：");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        LockSupport.park();

    }
}

class Test2 {
    private boolean flag;
    private long p;
}
