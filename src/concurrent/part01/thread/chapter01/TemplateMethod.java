package concurrent.part01.thread.chapter01;

/**
 * @Author lishaohui
 * @Date 2023/3/18 22:57
 */
public class TemplateMethod {

    public final void print(String msg){
        System.out.println("##########################");
        wrapPrint(msg);
        System.out.println("##########################");
    }
    protected void wrapPrint(String msg){ }

    //这是main方法,程序的入口
    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String msg) {
                System.out.println("*"+msg+"*");
            }
        };
        t1.print("hello thread");
        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String msg) {
                System.out.println("+"+msg+"+");
            }
        };
        t1.print("hello thread");
    }
}
