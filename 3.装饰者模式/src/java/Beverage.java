public abstract class Beverage {
  String description = "Unkow Beverage";

  public String getDescription() {
    return description;
  }

  public abstract double cost();
}
