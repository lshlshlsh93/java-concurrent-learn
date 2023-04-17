package concurrent.part03;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * <p>
 * 无锁队列
 * </p>
 *
 * @Author lishaohui
 * @Date 2023/4/11 9:12
 */
public class LockFreeQueue<E> {

    private static class Node<E> {
        E element;
        volatile Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element == null ? "" : element.toString();
        }
    }

    private AtomicReference<Node<E>> head, tail;
    private AtomicInteger size = new AtomicInteger(0);

    public LockFreeQueue() {
        Node<E> node = new Node<>(null);
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    public boolean isEmpty() {
        synchronized (this) {
            return head.get().next == null;
        }
    }

    /**
     * <p>
     * 添加队尾元素
     * </p>
     *
     * @param e 元素
     */
    public void addLast(E e) {
        if (e == null) throw new NullPointerException("The NULL Element is not allow");
        // 无锁方式保证线程安全
        Node<E> newNode = new Node<>(e);
        Node<E> prevNode = tail.getAndSet(newNode);
        prevNode.next = newNode;
//        size.incrementAndGet();
    }

    /**
     * <p>
     * 删除队头元素
     * </p>
     *
     * @return E
     */
    public E removeFirst() {
        Node<E> headNode, valueNode;
        do {
            headNode = head.get();
            valueNode = headNode.next;
        } while (valueNode != null && !head.compareAndSet(headNode, valueNode));
        E value = (valueNode != null ? valueNode.element : null);
        if (valueNode != null) valueNode.element = null;
//        size.decrementAndGet();
        return value;
    }


    public static void main(String[] args) throws InterruptedException {

        final ConcurrentHashMap<Long, Object> data = new ConcurrentHashMap<>();
        final LockFreeQueue<Long> lockFreeQueue = new LockFreeQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 5个写线程
        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            int counter = 0;
            while ((counter++) <= 10) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockFreeQueue.addLast(System.nanoTime());
            }
        }).forEach(executorService::submit);
        // 5个读进程
        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            int counter = 10;
            while (counter >= 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Long value = lockFreeQueue.removeFirst();
                if (value == null) continue;
                counter--;
                System.out.println(value);
                data.put(value, new Object());
            }
        }).forEach(executorService::submit);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(data.size());
        System.out.println("====================");
        System.out.println(data.keys());

    }

}
