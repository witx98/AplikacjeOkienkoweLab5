package Cars;

public class Vehicle implements Comparable<Vehicle> {
    private String brand;
    private String model;
    private ItemCondition condition;
    private int yearOfProduction;
    private double price;
    private double mileage;
    private double engineCapacity;

    public Vehicle(String brand, String model, ItemCondition condition, int yearOfProduction, double price, double mileage, double engineCapacity) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.yearOfProduction = yearOfProduction;
        this.price = price;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void print(){
        System.out.println("Marka pojazdu: " + brand);
        System.out.println("Model pojazdu: " + model);
        System.out.println("Stan pojazdu: " + condition);
        System.out.println("Cena pojazdu: " + price);
        System.out.println("Rok produkcji pojazdu: " + yearOfProduction);
        System.out.println("Przebieg pojazdu: " + mileage);
        System.out.println("Pojemnosc silnika: " + engineCapacity);
    }


    @Override
    public int compareTo(Vehicle o) {
        if(this.brand.equalsIgnoreCase(o.brand) && this.model.equalsIgnoreCase(o.model))
            return 1;
        else {
            return 0;
        }
    }

}
