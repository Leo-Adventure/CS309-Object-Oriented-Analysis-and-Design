package ObserberPattern;

public class HexObserver extends Observer{
    public HexObserver(Subject subject){
        this.subject = subject;
        this.subject.attachObserver(this);
    }
    @Override
    public void update() {
        System.out.println("Hex String" + Integer.toHexString(subject.getState()));
    }
}
