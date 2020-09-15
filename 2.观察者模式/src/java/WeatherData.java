import java.util.ArrayList;

public class WeatherData implements  Subject {
  public double temperature;
  public double humidity;
  public ArrayList<Observer> observers;

  public WeatherData() {
    observers = new ArrayList<Observer>();
  }

  public void registerObserver(Observer o) {
      observers.add(o);
  }

  public void removeObserver(Observer o) {
    int index = observers.indexOf(o);
    observers.remove(index);
  }

  public void notifyObserver() {
    for (int i = 0; i < observers.size(); i++) {
      Observer o = (Observer) observers.get(i);
      o.update(temperature, humidity);
    }
  }

  public void measurementChange() {
    temperature = 1;
    humidity = 2;
    notifyObserver();
  }
}