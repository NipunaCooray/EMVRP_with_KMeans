/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import java.util.ArrayList;
/**
 *
 * @author Slayer
 */
public class TourManager {
    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<City>();
    private static int vehicle_capacity=0;
    // Adds a destination city
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    public static void removeCity(int index) {
        destinationCities.remove(index);
    }
    
    public static void removeAll() {
        destinationCities.clear();
    }
    
    // Get a city
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }
    
    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
    
    public static void setVehicleCapacity(int vehicleCapacity){
        vehicle_capacity = vehicleCapacity;
    }
    
    public static int getVehicleCapacity(){
        return vehicle_capacity;
    }
}
