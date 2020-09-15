public class WeaterStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditions(weatherData);
        weatherData.measurementChange();
    }
}
