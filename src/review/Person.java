package review;

/**
 * @Author lishaohui
 * @Date 2023/4/28 20:48
 */
public class Person {

    private String password;
    private int age;
    private String name;

    public Person(String name, String password, int age) {
        this.password = password;
        this.age = age;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person(" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ')';
    }

}
