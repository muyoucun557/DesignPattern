import java.util.ArrayList;
import java.util.Observer;

public class WeatherData implements  Subject {
  private float temperature;
  private float humidity;
  private ArrayList<Observer> observers;

  public WeatherData() {

  }

  public void registerObserver(Observer o) {
    int index = observers.indexOf(o);
    observers.add(o);
  }

  public void removeObserver(Observer o) {
    int index = observers.indexOf(o);
    observers.remove(index);
  }
}