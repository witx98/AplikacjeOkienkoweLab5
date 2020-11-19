package Cars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarShowroomContainer {
    private Map<String, CarShowroom> salons;
    public CarShowroomContainer(){
        this.salons = new HashMap<String, CarShowroom>();
    }
    public void addCenter(String showroomName, int maxCapacity){
        CarShowroom showroom = new CarShowroom(showroomName,maxCapacity);
        this.salons.put(showroomName, showroom);
    }
    public void addCenter(CarShowroom carShowroom){
        this.salons.put(carShowroom.getShowroomName(), carShowroom);
    }
    public void removeCenter(String showroomName){
        if(salons.containsKey(showroomName)){
            salons.remove(showroomName);
        }
    }

    public Map<String, CarShowroom> getSalons() {
        return salons;
    }

    public void setSalons(Map<String, CarShowroom> salons) {
        this.salons = salons;
    }

    public void findEmpty(){
        for (CarShowroom showroom: salons.values()){
            if(showroom.getCurrentSize() == 0){
                System.out.println(showroom.getShowroomName());
            }
        }
    }
    public void summary(){
        System.out.println("LISTA SALONOW");
        for (CarShowroom showroom : salons.values()){
            double percent = (double)showroom.getCurrentSize()/(double)showroom.getMaxCapacity()*100;
            System.out.println("\t" + showroom.getShowroomName() + " - procentowe zapelnienie: " + percent +"%");
        }
    }
    public List<CarShowroom> getCarShowroomsList() {
        List<CarShowroom> carShowroomsList = new ArrayList<>();
        this.salons.forEach((k, v) ->
                carShowroomsList.add(v));
        return carShowroomsList;
    }
}
