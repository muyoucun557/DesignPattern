public class StarbuzzCoffee {
  public static void main(String[] args) {
    Beverage coffee = new Espresso();
    coffee = new Mocha(coffee);
    coffee = new Mocha(coffee);
    System.out.println(coffee.getDescription());
    System.out.println(coffee.cost());
  }
}