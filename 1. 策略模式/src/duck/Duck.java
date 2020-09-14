public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public Duck() {

    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }
}