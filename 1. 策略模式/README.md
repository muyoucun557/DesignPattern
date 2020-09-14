# 策略模式

## OO基础

抽象、封装、多态、继承

### 继承有哪些缺点

子类继承父类所有的属性和方法，极大的复用了代码，但同时缺乏灵活性。因为：

1. 我们很难知道所有字类的所有行为，继承并不能解决这个问题`
2. 运行时的行为不容易改变
3. 有些字类可能并不需要某些来自于父类的行为
4. 如果改变了父类的某一行为，会带来所有子类这一行为的改变，这可能并不是想要的

### 设计原则

1. 找出应用中可能需要变化的地方，把他们独立出来，不要和那些不需要变化的代码混合在一起。
2. 针对接口编程，而不是针对实现编程。
3. 多用组合，少用继承。

### 案例1

鸭子的故事

鸭子都会有``外观``，在父类中可以设计一个``display``方法，每个鸭子的外观都不同，因此``display``需要抽象，具体由子类实现。该方法适合由父类继承给子类。

有些鸭子会叫，有些鸭子不会叫。会叫的鸭子，有些是呱呱叫，有些是喵喵叫。
那么对于叫这个行为，就不适合用继承了。那么该怎么来实现呢？

针对叫这个行为，设计一个接口
```JAVA
public interface QuackBehavior {
    public void quack();
}
```
针对不同的叫，提供多种实现
吱吱叫
```java
public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("Squeak");
    }
}
```
嘎嘎叫
```java
public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack");
    }
}
```

鸭子类中怎么是用上面的设计
鸭子父类，鸭子父类中定义一个QuackBehavior变量，``performQuack`调用QuackBehavior变量的quack方法
```java
public abstract class Duck {
    QuackBehavior quackBehavior;
    public Duck() {

    }
    public void performQuack() {
        quackBehavior.quack();
    }
}
```

鸭子子类：绿头鸭，在子类的构造方法中给QuackBehavior变量赋值了``Quack``对象（多态）
```java
public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
    }
}
```

鸭子子类的测试代码
```java
public class MiniDuckSimultor {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
    }
}
```

总结上面的案例：
对于鸭子叫的行为，将其抽象出来，不使用继承，而是使用组合。
对于变化的部分，继承不是一个好的方法，继承更适用于不变的东西，组合更适用于变化的东西。

### 案例2

在应用程序中，配置文件是必不可少的部分。配置文件有多种格式，有json、ini、yaml、xml。如何实现一个库来管理配置文件。

config.js
```js
const objectPath = require('object-path')
function Config(Strategy) {
  if (this.constructor !== Config) {
    throw new Error(`Config structor can't invoke!`)
  }
  this.data = {}
  this.Strategy = Strategy
  this.flag = false

  this.get = function(path) {
    if (!this.flag) {
      throw new Error('尚未读取配置文件，请先读取配置文件')
    }
    return objectPath(this.data, path)
  }

  this.readFile = async function(path) {
    this.flag = true
    this.data = await Strategy.Deserialization(path)
  }

  this.saveFile = async function(path) {
    await Strategy.Serialization(path, JSON.stringify(this.data))
  }
}
```

Strategy.js

```js
const fsPromise = require('fs').promises

const json = {
  Deserialization: function(path) {
    return require(path)
  },
  Serialization: function(path, text) {
    await fsPromise.writeFile(path, text)
  }
}

module.exports = {
  json
}
```
