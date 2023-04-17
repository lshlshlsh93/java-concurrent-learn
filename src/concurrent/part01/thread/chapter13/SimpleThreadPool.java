package concurrent.part01.thread.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author lishaohui
 * @Date 2023/4/11 19:21
 */
public class SimpleThreadPool extends Thread {

    /**
     * 默认线程池大小
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 默认最小线程池大小
     */
    private static final int DEFAULT_MIN_SIZE = 4;

    /**
     * 默认最大线程池大小
     */
    private static final int DEFAULT_MAX_SIZE = 12;

    /**
     * 默认活跃线程大小
     */
    private static final int DEFAULT_ACTIVE_SIZE = 8;

    /**
     * 默认任务队列大小
     */
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    /**
     * 默认拒绝策略
     */
    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("DISCARD THIS TASK!");
    };

    /**
     * 线程池大小
     */
    private int size;

    /**
     * 最大任务队列大小
     */
    private final int maxQueueSize;

    /**
     * 最小线程
     */
    private final int min;

    /**
     * 最大线程
     */
    private final int max;

    /**
     * 最大活跃线程
     */
    private final int active;

    /**
     * 线程序号
     */
    private static int THREAD_SEQUENCE = 0;

    /**
     * 是否为destroy状态
     */
    private volatile boolean isDestroy = false;

    /**
     * 拒绝策略
     */
    private final DiscardPolicy discardPolicy;

    /**
     * 线程名前缀
     */
    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    /**
     * 线程组名前缀
     */
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("GROUP_POOL");

    /**
     * 任务队列
     */
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    /**
     * 线程工作任务队列
     */
    private static final List<WorkTask> THREAD_QUEUE_LIST = new ArrayList<>();


    public SimpleThreadPool() {
        this(DEFAULT_MIN_SIZE, DEFAULT_MAX_SIZE, DEFAULT_ACTIVE_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int max, int active, int maxQueueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.max = max;
        this.active = active;
        this.maxQueueSize = maxQueueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public int getSize() {
        return size;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public boolean isDestroy() {
        return this.isDestroy;
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    private void createWorkTask() {
        WorkTask task = new WorkTask(THREAD_GROUP, THREAD_PREFIX + (THREAD_SEQUENCE++));
        task.start();
        THREAD_QUEUE_LIST.add(task);
    }

    @Override
    public void run() {
        while (!isDestroy) {
            System.out.printf("Thread Pool#MIN:%d,ACTIVE:%d,MAX:%d,CURRENT:%d,QUEUE_SIZE:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000L);
                // 扩充到当前active大小
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    Optional.of("The pool increment.").ifPresent(System.out::println);
                    size = active;
                }
                // 扩容到最大
                else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    Optional.of("The pool increment to max.").ifPresent(System.out::println);
                    size = max;
                }

                synchronized (THREAD_QUEUE_LIST) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        Optional.of("回收部分线程池...................").ifPresent(System.out::println);
                        int releaseSize = size - active;
                        for (Iterator<WorkTask> it = THREAD_QUEUE_LIST.listIterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交任务
     *
     * @param runnable 任务
     */
    public void submit(Runnable runnable) {
        if (isDestroy)
            throw new IllegalStateException("The thread pool is already destroy and not allowed to submit task.");
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > maxQueueSize) {
                discardPolicy.discard(); // 执行拒绝策略
            }
            TASK_QUEUE.addLast(runnable); // 添加到队尾
            TASK_QUEUE.notifyAll(); //唤醒所有等待进程
        }
    }

    /**
     * 停止
     */
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50L);
        }
        synchronized (THREAD_QUEUE_LIST) {
            int initialValue = THREAD_QUEUE_LIST.size();
            while (initialValue > 0) {
                for (WorkTask task : THREAD_QUEUE_LIST) {
                    if (task.getTaskState() == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close();
                        initialValue--;
                    } else {
                        Thread.sleep(10L);
                    }
                }
            }
        }
        this.isDestroy = true;
        Optional.of("The Thread Pool disposed.").ifPresent(System.out::println);
    }


    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    /**
     * DiscardException
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 拒绝策略
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private static class WorkTask extends Thread {

        /**
         * 默认任务状态
         */
        private volatile TaskState taskState = TaskState.FREE;

        public WorkTask(ThreadGroup groupName, String name) {
            super(groupName, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable = null;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            Optional.of("Closed..").ifPresent(System.out::println);
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        IntStream.rangeClosed(0, 40).forEach(i -> {
            threadPool.submit(() -> {
                Optional
                        .of("The runnable " + i + "  be serviced by " + Thread.currentThread() + " started.")
                        .ifPresent(System.out::println);
                try {
                    Thread.sleep(3_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Optional
                        .of("The runnable " + i + " be serviced by " + Thread.currentThread() + " finished.")
                        .ifPresent(System.out::println);
            });
        });

        Thread.sleep(10_000L);
        threadPool.shutdown();
        // threadPool.submit(() -> System.out.println(".......")); // throw exception
    }

}
