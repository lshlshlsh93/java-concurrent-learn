package concurrent.part02.chapter06.guarded_suspension_design_pattern;

import java.util.LinkedList;

/**
 * @Author lishaohui
 * @Date 2023/4/24 0:46
 */
public class RequestQueue {

    private final LinkedList<Request> requestLinkedList = new LinkedList<>();

    public Request getRequest() {
        synchronized (requestLinkedList) {
            while (requestLinkedList.size() <= 0) {
                try {
                    requestLinkedList.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return requestLinkedList.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (requestLinkedList) {
            requestLinkedList.addLast(request);
            requestLinkedList.notifyAll();
        }
    }


}
