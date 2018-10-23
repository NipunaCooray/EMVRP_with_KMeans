/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author Slayer
 */
public class Tour {
     // Holds our tour of cities
    private ArrayList tour = new ArrayList<City>();
    //private List dummyTour = null;
    
    // Cache
    private double fitness = 0;
    private double distance=0;
    private double energy=0;
    private double totalEnergy=0 ;
    private int demand = 0;
    private int noOfTrips = 1;
    int capacity = 0;
    int vehicle_capacity =0;
   
    
//    int z=1;
//    int j=0;
    
    // Constructs a blank tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
        
        
    }
    
    public Tour(ArrayList tour){
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, TourManager.getCity(cityIndex));
        }

        // Randomly reorder the tour
        //dummyTour = tour.subList(1, tour.size());
        ArrayList <City> dummyTour = new ArrayList(tour.subList(1,tour.size()));

        Collections.shuffle(dummyTour);
        //Removing cities in dummyTOur from tour
        for(int i=tour.size()-1;i>0;i--){
            tour.remove(i);
        }

        tour.addAll(1, dummyTour);
        //System.out.println(tour);
        
        
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            //fitness = 1/totalEnergy;
            fitness = 1/getEnergy();
        }
        return fitness;
    }
    
    
    
    public double getEnergy(){
        int z=1;
        int j=0;
        //generateIndividual();
        demand = getDemand();
        vehicle_capacity = TourManager.getVehicleCapacity();
        City fromCity = getCity(0);
        City toCity = getCity(1);
        
        
        //initial loading
       
        while((capacity + getCity(j).getDemand())<vehicle_capacity){
              capacity = capacity + getCity(j).getDemand();
              
              j++;        
        }
        //System.out.println("Initial load "  + capacity);
        
        j=0;
        
        while(demand>0){

//                System.out.println("Demand left to be served " + demand);
//                System.out.println("Capacity left is " + capacity);

                if(capacity >= getCity(z).getDemand()){
//                    System.out.println("From : "+ fromCity.getName());
//                    System.out.println("To : "+toCity.getName());
                    distance = fromCity.distanceTo(toCity);
                    
                    //calculating energy
                    energy = distance * capacity;
                    totalEnergy = totalEnergy + energy;

                    //Dropping off load
                    capacity = capacity - getCity(z).getDemand();
                    demand = demand - getCity(z).getDemand();
                    
                    z=z+1;
                    
                    fromCity = toCity;
                    if(TourManager.numberOfCities()>z){
                        //toCity = TourManager.getCity(z);
                        
                        toCity = getCity(z);
                    }
//                    System.out.println("==========================");
                    
                }else{
                    fromCity = getCity(z-1);
                    toCity = getCity(0);
                
//                    System.out.println("From : "+ fromCity.getName());
//                    System.out.println("To : "+toCity.getName());

                    distance = fromCity.distanceTo(toCity);
                
                    energy = distance * capacity;
                    totalEnergy = totalEnergy + energy ;
                
                    //Relaoding only the required capacity
                    j=z;

                    while((capacity<vehicle_capacity) && (TourManager.numberOfCities()>j)){
                        capacity = capacity + getCity(j).getDemand();
                        j++;
                   }
//                    System.out.println("Reloaded");
                
                
                    noOfTrips = noOfTrips + 1;
    //                fromCity = TourManager.getCity(0);  
    //                toCity = TourManager.getCity(i);
                    fromCity = toCity;
                    if(TourManager.numberOfCities()>z){
                        toCity = getCity(z);
                    }
               
//                    System.out.println("=+=+=+====================");
                
                }
//                System.out.println("energy consumption : " + energy);
//                System.out.println("***********************************");
            
            }
        
            //Going back trip
            fromCity = toCity;
            toCity = getCity(0);

            distance = fromCity.distanceTo(toCity);

            energy = capacity * distance;
            totalEnergy = totalEnergy + energy;
//            System.out.println("Going back from : "+ fromCity.getName());
//            System.out.println("To : "+toCity.getName());

//            System.out.println("No of trips are " + noOfTrips);
//            System.out.println("Total energy consumption is " + totalEnergy);
            
        return totalEnergy;
    }
    
    //calculating the demand of the tour
    public int getDemand(){
        
        for(int x=0;x<TourManager.numberOfCities();x++){
            demand = demand + TourManager.getCity(x).getDemand();
        }
//        System.out.println("Demand is :"+ demand);
        
        return demand;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }
    
    // Check if the tour contains a city
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    public int getNoOfTrips(){
        return noOfTrips;
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"||";
        }
        return geneString;
    }
}
