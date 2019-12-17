package Demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author shkstart
 * @data 2019/12/16-19:18
 */
public class Demo04 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                latch.countDown();
            },String.valueOf(i)).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+"班长要走人了");
    }
}
