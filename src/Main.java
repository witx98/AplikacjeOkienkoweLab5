import Cars.CarShowroomContainer;
import Cars.ItemCondition;
import Cars.Vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("Mazda", "3", ItemCondition.NEW,2003,10000,27000,1.5);
        Vehicle v2 = new Vehicle("Opel", "4", ItemCondition.NEW,2003,10000,27000,1.5);
        Vehicle v3 = new Vehicle("Audi", "5", ItemCondition.NEW,2003,10000,27000,1.5);
        Vehicle v4 = new Vehicle("Fiat", "6", ItemCondition.NEW,2003,10000,27000,1.5);
        Vehicle v5 = new Vehicle("Porsche", "7", ItemCondition.NEW,2003,10000,27000,1.5);
        Vehicle v6 = new Vehicle("Nissan", "8", ItemCondition.NEW,2003,10000,27000,1.5);

        CarShowroomContainer conteiner = new CarShowroomContainer();
        conteiner.addCenter("Salon 1", 8);
        conteiner.addCenter("Salon 2", 6);
        conteiner.addCenter("Salon 3", 10);
        conteiner.addCenter("Salon 4", 20);
        for (int i = 0; i<3; i++){
            conteiner.getSalons().get("Salon 1").addProduct(v1);
            conteiner.getSalons().get("Salon 2").addProduct(v2);
            conteiner.getSalons().get("Salon 3").addProduct(v3);
            conteiner.getSalons().get("Salon 4").addProduct(v4);
        }
        for (int i = 0; i<5; i++){
            conteiner.getSalons().get("Salon 3").addProduct(v1);
            conteiner.getSalons().get("Salon 4").addProduct(v1);
            conteiner.getSalons().get("Salon 4").addProduct(v2);
        }
        conteiner.getSalons().get("Salon 1").addProduct(v5);
        conteiner.getSalons().get("Salon 1").addProduct(v6);

        conteiner.getSalons().get("Salon 2").addProduct(v3);
        conteiner.getSalons().get("Salon 2").addProduct(v4);

        //conteiner.summary();
        //conteiner.getSalons().get("Salon 4").sortByAmount();

        GuiClass myApp = new GuiClass(conteiner);
        myApp.displayApplication();
    }
}
