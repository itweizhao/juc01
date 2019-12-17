package Demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shkstart
 * @data 2019/12/17-10:57
 */
public class MyThread01 {
    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool(3);
        Tacket tacket = new Tacket();


        try {
            for (int i = 0; i < 35; i++) {
                threadPool.execute(()->{tacket.sale();});
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
class Tacket{
    private int num=30;
    private Lock lock=new ReentrantLock();
    void sale(){
        lock.lock();
        if (num>0){
            System.out.println(Thread.currentThread().getName()+"\t 正在卖第:"+(num--)+"\t 张票还剩"+num+"张");
        }
        lock.unlock();
    }
}
