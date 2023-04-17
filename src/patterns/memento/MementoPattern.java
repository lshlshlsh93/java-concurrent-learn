package patterns.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lishaohui
 * @Date 2023/4/12 23:15
 */
public class MementoPattern {

    private static class Memento {

        private final String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    public static class Originator {
        private String state;

        public void setState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }

        public Memento saveStateToMemento(){
            return new Memento(state);
        }

        public void getStateFromMemento(Memento memento){
            state = memento.getState();
        }
    }

    public static class CareTaker {
        private final List<Memento> mementoList = new ArrayList<Memento>();

        public void add(Memento state){
            mementoList.add(state);
        }

        public Memento get(int index){
            return mementoList.get(index);
        }
    }


    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }

}
