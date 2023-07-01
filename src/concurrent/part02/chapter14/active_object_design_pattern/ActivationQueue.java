package concurrent.part02.chapter14.active_object_design_pattern;

import java.util.LinkedList;

/**
 * @Author lishaohui
 * @Date 2023/5/16 12:29
 */
public class ActivationQueue {

    private final static int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    /**
     * 放
     *
     * @param methodRequest 请求
     */
    public synchronized void put(MethodRequest methodRequest) {
        while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(methodRequest);
        this.notifyAll();
    }

    /**
     * 拿
     */
    public synchronized MethodRequest take() {
        // 队列为空就等待
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 不为空，从头取出request
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }

}
