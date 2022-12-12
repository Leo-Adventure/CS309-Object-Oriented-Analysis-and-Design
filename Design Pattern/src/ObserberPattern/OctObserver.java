package ObserberPattern;

public class OctObserver extends Observer{
    public OctObserver(Subject subject){
        this.subject = subject;
        this.subject.attachObserver(this);
    }
    @Override
    public void update() {
        System.out.println("Oct String" + Integer.toOctalString(subject.getState()));
    }
}
