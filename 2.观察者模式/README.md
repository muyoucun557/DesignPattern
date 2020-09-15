# 观察者模式

对象与对象之间会有依赖关系，当某一个对象的状态发生变化时，需要通知所有依赖与该对象的对象。

被依赖者称之为``主体``，依赖者称之为``观察者``。

实现的功能如下：

1. 主体的状态发生变化需要通知所有的观察者。
2. 观察者可以``订阅``和``取消订阅``。

## 案例

气象站实现如下功能：监控天气情况，天气数据随时发生变化，面板上需要显示天气数据，会有多种形式的面板。

策略模式中阐述了两个设计原则。1. 针对接口编程 2. 针对变化的部分，使用组合。

定义接口

```java
public interface  Subject {
  public void registerObserver(Observer o);     // 订阅
  public void removeObserver(Observer o);       // 取消订阅
  public void notifyObserver();                 // 通知观察者
}
```

气象数据实现主体接口

```java
import java.util.ArrayList;

public class WeatherData implements  Subject {
  public double temperature;
  public double humidity;
  public ArrayList<Observer> observers; // 这里用到的设计原则是将变化的东西归类，使用组合原则。

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

    // 调用这个方法模拟气象数据发生变化
  public void measurementChange() {
    temperature = 1;
    humidity = 2;
    notifyObserver();
  }
}
```

观察者接口

```java
public interface Observer {
  public void update(double temperature, double humidity);
}
```

面板实现观察者接口

```java
public class CurrentConditions implements Observer{
    private Subject weatherData;
    public CurrentConditions(Subject weatherData) {
        this.weatherData = weatherData;
        //订阅
        weatherData.registerObserver(this);
    }

    public void update(double temperature, double humidity) {
        System.out.println(temperature);
        System.out.println(humidity);
    }
}
```

测试代码

```java
public class WeaterStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditions(weatherData);
        weatherData.measurementChange();
    }
}
```

如果是使用Nodejs实现的话，使用EventEmitter可以非常方便的实现。
