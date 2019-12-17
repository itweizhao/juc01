import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shkstart
 * @data 2019/12/14-11:09
 */
public class Demo01 {
    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        demo.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        demo.del();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        demo.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"C").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        demo.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"D").start();
    }
}
class Demo{
    private int num=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    void add() throws InterruptedException {
        lock.lock();
        while(num != 0){
            condition.await();
        }
        ++num;
        System.out.println(Thread.currentThread().getName()+"   "+num);
        condition.signalAll();
    }
    void del() throws InterruptedException {
        lock.lock();
        while (num==0){
            condition.await();
        }
        --num;
        System.out.println(Thread.currentThread().getName()+"   "+num);
        condition.signalAll();
    }
}