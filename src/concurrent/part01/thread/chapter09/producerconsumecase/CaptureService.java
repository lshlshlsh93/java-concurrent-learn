package concurrent.part01.thread.chapter09.producerconsumecase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author lishaohui
 * @Date 2023/4/11 12:42
 */
public class CaptureService {

    private static final LinkedList<Control> CONTROL_LINKED_LIST = new LinkedList<>();

    public static final int MAX_WORKER_COUNT = 8;

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        Stream.of("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8")
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });
        worker
                .stream()
                .forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }


    private static Thread createCaptureThread(String threadName) {
        return new Thread(() -> {
            Optional.of("The worker [" + Thread.currentThread().getName() + "] BEGIN capture data.").ifPresent(System.out::println);
            synchronized (CONTROL_LINKED_LIST) {
                while (CONTROL_LINKED_LIST.size() > MAX_WORKER_COUNT) {
                    try {
                        CONTROL_LINKED_LIST.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROL_LINKED_LIST.addLast(new Control());
            }
            Optional.of("The worker [" + Thread.currentThread().getName() + "] is working.").ifPresent(System.out::println);
            try {
                Thread.sleep(10_000L); // 假设采集需要10秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROL_LINKED_LIST) {
                Optional.of("The worker [" + Thread.currentThread().getName() + "] END capture work.").ifPresent(System.out::println);
                CONTROL_LINKED_LIST.removeFirst();
                CONTROL_LINKED_LIST.notifyAll();
            }
        }, threadName);
    }

    private static class Control {
    }

}
