package ObserberPattern;


import java.util.ArrayList;
import java.util.List;

public class Subject {
    private int state;
    private List<Observer> observers = new ArrayList<Observer>();

    public void setState(int state){
        this.state = state;
        notifyAllObservers();
    }
    public void notifyAllObservers(){
        for (Observer observer: observers){
            observer.update();
        }
    }
    public int getState(){
        return state;
    }

    public void attachObserver(Observer observer){
        observers.add(observer);
    }
}
