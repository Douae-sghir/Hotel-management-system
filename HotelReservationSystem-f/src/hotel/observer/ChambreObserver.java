package hotel.observer;

public class ChambreObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("🔔 IMPORTANT: " + message);
    }
}
