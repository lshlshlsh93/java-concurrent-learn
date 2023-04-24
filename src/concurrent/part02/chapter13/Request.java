package concurrent.part02.chapter13;

/**
 * @Author lishaohui
 * @Date 2023/4/24 22:16
 */
public class Request {


    private final String name;

    private final int number;

    public Request(final String name, final int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
