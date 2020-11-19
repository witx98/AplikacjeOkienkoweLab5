package Cars;

import java.util.*;

public class CarShowroom implements Comparable<CarShowroom>{
    private String showroomName;
    private Map<Vehicle, Integer> carList ;
    private int maxCapacity;
    private int currentSize;

    public CarShowroom(String showroomName, int maxCapacity) {
        this.showroomName = showroomName;
        this.carList = new LinkedHashMap<Vehicle, Integer>();
        this.maxCapacity = maxCapacity;
        this.currentSize = 0;
    }

    public  void addProduct(Vehicle car){
        if(this.currentSize < this.maxCapacity){

            if(carList.isEmpty()){
                carList.put(car, 1);
                currentSize++;
            }
            else {
                boolean isInList = false;
                for(Vehicle vehicle: carList.keySet()){
                    if (vehicle.compareTo(car)== 1){
                        int i =  carList.get(vehicle) + 1;
                        carList.replace(vehicle, i);
                        currentSize++;
                        isInList = true;
                    }
                }
                if(!isInList) {
                    carList.put(car, 1);
                    currentSize++;
                }
            }

        }
        else {
            System.err.println("Blad! Nie mozna dodac klejnego samochodu. Przekroczono limit.");
        }
    }
    public void getProduct(Vehicle car){
        for(Vehicle vehicle: carList.keySet()) {
            if (vehicle.compareTo(car) == 1) {
                carList.replace(vehicle, carList.get(vehicle), carList.get(vehicle)-1);
                currentSize--;
                if(carList.get(vehicle) == 0){
                    carList.remove(vehicle);
                }
            }
        }
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void removeProduct(Vehicle car){
        Vehicle toRemove = null;
        int amount = 0;
        for(Vehicle vehicle: carList.keySet()) {

            if (vehicle.compareTo(car) == 1) {
                System.out.println(vehicle.getBrand());
                amount = carList.get(vehicle);
                //carList.remove(vehicle);
                toRemove = vehicle;


            }
        }
        carList.remove(toRemove);
        currentSize = currentSize - amount;
    }
    public void search(String carName){
        for(Vehicle vehicle: carList.keySet()){
            if((vehicle.getBrand().compareToIgnoreCase(carName) == 0 || vehicle.getModel().compareToIgnoreCase(carName) == 0 )){
                vehicle.print();
            }
        }
    }
    public void searchPartial(String partialCarName){
        for (Vehicle vehicle: carList.keySet()){
            if((vehicle.getBrand() + " " + vehicle.getModel()).contains(partialCarName)){
                vehicle.print();
            }
        }
    }
    public void countByCondition(){

        for(ItemCondition condition: ItemCondition.values()){
            int conditionCount = 0;
            for (Vehicle vehicle: carList.keySet()){
                if(vehicle.getCondition().equals(condition)){
                    conditionCount++;
                }
            }
            System.out.println("Samochdow w stanie: " + condition + " jest: " + conditionCount);
        }

    }
    public  void  summary(){
        for (Vehicle vehicle: carList.keySet()){
            vehicle.print();
            System.out.println("Liczba modeli w salonie: " + carList.get(vehicle));
            System.out.println();
        }
    }
    public void sortByName(){
        List<String> carNameList = new ArrayList<String>();
        for (Vehicle vehicle: carList.keySet()){
            carNameList.add(vehicle.getBrand() + " " + vehicle.getModel());
        }
        Collections.sort(carNameList);
        for (String names: carNameList){
            System.out.println(names);
        }
    }
    public void sortByAmount(){
        Set<Map.Entry<Vehicle, Integer>> entries = carList.entrySet();
        Comparator<Map.Entry<Vehicle, Integer>> valueComparator = new Comparator<Map.Entry<Vehicle, Integer>>() {
            @Override
            public int compare(Map.Entry<Vehicle, Integer> o1, Map.Entry<Vehicle, Integer> o2) {
                Integer val1 = o1.getValue();
                Integer val2 = o2.getValue();
                return val1.compareTo(val2);
            }
        };
        List<Map.Entry<Vehicle, Integer>> listOfEntries = new ArrayList<Map.Entry<Vehicle, Integer>>(entries);
        Collections.sort(listOfEntries, valueComparator);
        LinkedHashMap<Vehicle, Integer> sortedByValue = new LinkedHashMap<Vehicle, Integer>(listOfEntries.size());

        for (Map.Entry<Vehicle, Integer> entry: listOfEntries){
            sortedByValue.put(entry.getKey(),entry.getValue());
        }
        System.out.println("HashMap after sorting entries by value ");
        Set<Map.Entry<Vehicle,Integer>> entrySetSortedByValue = sortedByValue.entrySet();
        for (Map.Entry<Vehicle, Integer> mapping: entrySetSortedByValue){
            for (Vehicle vehicle: carList.keySet()){
                if(vehicle.equals(mapping.getKey())){
                    System.out.println(vehicle.getBrand()  + " " + vehicle.getModel() + " ==> " + mapping.getValue());
                }
            }

        }
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void max(){

        System.out.println("Najwiecej jest: ");
        for (Map.Entry<Vehicle,Integer> entry : carList.entrySet()){
                if (entry.getValue().equals(Collections.max(carList.values()))){
                    for (Vehicle vehicle : carList.keySet()){
                        if(vehicle.equals(entry.getKey())){
                            System.out.println(vehicle.getBrand() + " " + vehicle.getModel() + ": " + entry.getValue());
                        }
                    }
                }
        }
    }

    public Map<Vehicle, Integer> getCarList() {
        return carList;
    }

    public void setCarList(Map<Vehicle, Integer> carList) {
        this.carList = carList;
    }
    public List<Vehicle> mapToArrayList(){
        List<Vehicle> carArrayList = new ArrayList<Vehicle>();
        for (Vehicle vehicle: carList.keySet()){
            carArrayList.add(vehicle);
        }
        return  carArrayList;
    }

    @Override
    public int compareTo(CarShowroom o) {
        return Integer.compare(this.getMaxCapacity(), o.getMaxCapacity());
    }


}
