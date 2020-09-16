public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage b = new Espresso();
        Beverage mocha = new Mocha(b);
        mocha = new Mocha(mocha);
        mocha = new Mocha(mocha);
        System.out.println(mocha.cost());
        System.out.println(mocha.getDescription());
    }
}
