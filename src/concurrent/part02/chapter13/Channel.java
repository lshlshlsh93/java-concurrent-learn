package concurrent.part02.chapter13;


import java.util.Arrays;

/**
 * @Author lishaohui
 * @Date 2023/4/24 22:15
 */
public class Channel {

    public static final int MAX_REQUEST = 100;

    private final Request[] requestQueue;

    private int head;

    private int tail;

    private int count;

    private final Worker[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new Worker[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new Worker("Worker-" + i, this);
        }
    }

    public void startWork() {
        Arrays.asList(workerPool).forEach(Worker::start);
    }

    public synchronized void put(Request request) {
        while (count >= requestQueue.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        this.requestQueue[tail] = request;
        this.tail = (this.tail + 1) % this.requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request poll() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requestQueue[head];
        this.head = (this.head + 1) % this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }

}
