public interface Subject <T>{
    public void registerObserver(T ball);
    public void removeObserver(T ball);
    public void notifyObservers(char keyChar);
    public void notifyObservers();
}
