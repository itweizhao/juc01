import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author shkstart
 * @data 2019/12/16-16:22
 */
public class Call {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        FutureTask task = new FutureTask(thread);
        new Thread(task,"A").start();
        System.out.println(task.get());
    }
}
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName());
        System.out.println("123***************");
        return "122";
    }
}