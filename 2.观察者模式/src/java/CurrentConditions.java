public class CurrentConditions implements Observer{
    private Subject weatherData;
    public CurrentConditions(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(double temperature, double humidity) {
        System.out.println(temperature);
        System.out.println(humidity);
    }
}
