package concurrent.part02.chapter03.immutable;

/**
 * @Author lishaohui
 * @Date 2023/4/23 23:13
 */
public class User extends Thread{

    private Person person;

    public User(Person person){
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + " print " + person.toString());
        }
    }
}
