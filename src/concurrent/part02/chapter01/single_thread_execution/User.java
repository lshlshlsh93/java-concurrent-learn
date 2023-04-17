package concurrent.part02.chapter01.single_thread_execution;

import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/17 22:53
 */
public class User extends Thread {

    private final String name;

    private final String address;

    private final Gate gate;

    public User(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        Optional.of(name + "Begin").ifPresent(System.out::println);
        while (true){
            this.gate.pass(name,address);
        }
    }
}
