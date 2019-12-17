package Demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @data 2019/12/16-19:55
 * 获取线程的方式
 */
public class Demo07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new MyThread());
        new Thread(task,"A").start();
        task.get();
        System.out.println(123);
    }
}
class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("come to call******");
        return 1024;
    }
}
