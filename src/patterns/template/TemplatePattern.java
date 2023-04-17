package patterns.template;

/**
 * @Author lishaohui
 * @Date 2023/4/12 22:15
 */
public class TemplatePattern {

    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

}
