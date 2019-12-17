package Demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shkstart
 * @data 2019/12/16-18:57
 */
public class Demo03 {
    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 1; i <= 10; i++) {
            final int tempI=i;
            new Thread(()->{
                        cache.put(tempI+"",tempI+"");
                    },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 100; i++) {
            final int tempI=i;
            new Thread(()->{
                cache.get(tempI+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCache{
    private volatile Map<String,String> map=new HashMap<>();
    private Lock lock=new ReentrantLock();
    private ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    public void put(String key,String value){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写入开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写入结束");
        }finally {
            rwl.writeLock().unlock();
        }

    }
    public void get(String key){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取开始");
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取结束:"+value);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }

    }
}
