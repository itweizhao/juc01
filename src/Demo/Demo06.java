package Demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @data 2019/12/16-19:46
 */
public class Demo06 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                boolean flag=false;
                try {
                    semaphore.acquire();
                    flag=true;
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                   try{
                       TimeUnit.SECONDS.sleep(3);
                    }catch (Exception e){e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(flag){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
