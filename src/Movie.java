public class Movie implements Runnable {
    private int total=30;
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            if (total>0){
                System.out.println(Thread.currentThread().getName()+"第"+total+"张票");
                total--;
            }
        }
    }
}
