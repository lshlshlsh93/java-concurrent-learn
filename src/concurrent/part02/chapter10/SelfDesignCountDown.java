package concurrent.part02.chapter10;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:14
 */
public class SelfDesignCountDown {

    private final int total;

    private int counter;


    public SelfDesignCountDown(int total) {
        this.total = total;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while (counter != total){
                this.wait();
            }
        }
    }



}
