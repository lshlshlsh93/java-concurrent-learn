package patterns.decorator;


import java.util.Optional;

/**
 * @Author lishaohui
 * @Date 2023/4/12 0:43
 */
public class DecoratorPattern {

    public interface Sharp {
        void draw();
    }

    public abstract static class ShapeDecorator implements Sharp {

        protected Sharp decoratedShape;

        public ShapeDecorator(Sharp decoratedShape) {
            this.decoratedShape = decoratedShape;
        }

        @Override
        public void draw() {
            decoratedShape.draw();
        }
    }

    public static class RedShapeDecorator extends ShapeDecorator {
        public RedShapeDecorator(Sharp decoratedShape) {
            super(decoratedShape);
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Sharp decoratedShape) {
            Optional.of("Border Color: Red").ifPresent(System.out::println);
        }
    }

    private static class Rectangle implements Sharp {

        @Override
        public void draw() {
            Optional.of("Sharp: Rectangle.").ifPresent(System.out::println);
        }

    }

    private static class Circle implements Sharp {

        @Override
        public void draw() {
            Optional.of("Sharp: Circle.").ifPresent(System.out::println);
        }

    }


    public static void main(String[] args) {

        Sharp circle = new Circle();
        Sharp redCircle = new RedShapeDecorator(new Circle());
        Sharp redRectangle = new RedShapeDecorator(new Rectangle());
        Optional.of("Circle with normal border").ifPresent(System.out::println);
        circle.draw();

        Optional.of("\nCircle of red border").ifPresent(System.out::println);
        redCircle.draw();

        Optional.of("\nRectangle of red border").ifPresent(System.out::println);
        redRectangle.draw();
    }
}
