import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket();



        new Thread(()->{for(int i = 0; i < 101; i++) saleTicket.sale();},"A").start();
        new Thread(()->{for(int i = 0; i < 101; i++) saleTicket.sale();},"B").start();
        new Thread(()->{for(int i = 0; i < 101; i++) saleTicket.sale();},"C").start();
        
    }
}

class SaleTicket{
    private int ticketNum=100;
    ReentrantLock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (ticketNum>0){
                System.out.println(Thread.currentThread().getName()+"真再卖第"+(ticketNum--)+"张票"+",换剩下"+ticketNum);
            }
        }finally {
            lock.unlock();
        }

    }
}
