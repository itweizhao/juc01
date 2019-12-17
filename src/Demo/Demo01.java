package Demo;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shkstart
 * @data 2019/12/16-18:05
 */
public class Demo01 {
    public static void main(String[] args) {
        //Set<String> set= new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());
        Map<String,String> map=new ConcurrentHashMap<>();//new HashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,6));
                System.out.println(map);
            }).start();
        }
    }
}
