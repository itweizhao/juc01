package Demo;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shkstart
 * @data 2019/12/16-18:27
 */
public class Demo02 {
    public static void main(String[] args) {
        ShareResource sh = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sh.print(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sh.print(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sh.print(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
class ShareResource{
    private int flag=1;
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (flag!=1){
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag =2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (flag!=2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag =3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (flag!=3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag =1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print(int num) throws InterruptedException {
        lock.lock();
        int nums=num/5;
        try {
            while(flag!=nums&&nums==1)
                c1.await();
            while(flag!=nums&&nums==2)
                c2.await();
            while(flag!=nums&&nums==3)
                c3.await();
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            switch (flag){
                case 1:
                    c2.signal();
                    flag=2;
                    break;
                case 2:
                    c3.signal();
                    flag=3;
                    break;
                default:
                    c1.signal();
                    flag=1;
                    break;
            }
        }finally {
            lock.unlock();
        }
    }
}
