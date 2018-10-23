/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tsp.City;
import tsp.TourManager;

/**
 *
 * @author Slayer
 */
public class TestFitnessChamiya {
    public static void main(String[] args) {
        
        City c0 = new City(0, 2, 2, 0);
        City c1 = new City(1, 3, 3, 50);
        City c2 = new City(2, 4, 4, 50);
        City c3 = new City(3, 5, 5, 50);
        City c4 = new City(4, 6, 6, 50);
        
        TourManager.addCity(c0);
        TourManager.addCity(c1);
        TourManager.addCity(c2);
        TourManager.addCity(c3);
        TourManager.addCity(c4);
        
        
        int demand=0;
        int vehiile_capacity = 120;
        int capacity = 0;
        int noOfTrips = 1;
        
        City fromCity = TourManager.getCity(0);
        City toCity = TourManager.getCity(1);
        
        double distance = 0;
        
        double energy = 0;
        double totalEnergy = 0;
        
        //capacity = vehiile_capacity;
        
        for(int i=0;i<TourManager.numberOfCities();i++){
            
            demand = demand + TourManager.getCity(i).getDemand();
            
        }
        
        System.out.println("Demand is :"+ demand);
        
        int i=1;
        int j=0;
        //initial loading
       
        while((capacity + TourManager.getCity(j).getDemand())<vehiile_capacity){
              capacity = capacity + TourManager.getCity(j).getDemand();
              j++;        
        }
        
        j=0;
        
        while(demand>0){

            System.out.println("Demand left to be served " + demand);
            System.out.println("Capacity left is " + capacity);
            
            if(capacity >= TourManager.getCity(i).getDemand()){
                
//                if(capacity==vehiile_capacity && noOfTrips > 1){
//                    fromCity = TourManager.getCity(0);  
//                }else{
//                    fromCity = TourManager.getCity(i-1);
//                }
//
//                toCity = TourManager.getCity(i);
                System.out.println("From : "+ fromCity.getName());
                System.out.println("To : "+toCity.getName());
                distance = fromCity.distanceTo(toCity);
                
                
                
                //calculating energy
                energy = distance * capacity;
                totalEnergy = totalEnergy + energy;
                
                //Dropping off load
                capacity = capacity - TourManager.getCity(i).getDemand();
                demand = demand - TourManager.getCity(i).getDemand();
                
                i=i+1;
                //distance calculation
                //fromCity = TourManager.getCity(i);

                

                //System.out.println("City " + TourManager.getCity(i).getName() + " Served");
                fromCity = toCity;
                if(TourManager.numberOfCities()>i){
                    toCity = TourManager.getCity(i);
                }
                System.out.println("==========================");
                
                
            }else{
                //i = i-1;
                fromCity = TourManager.getCity(i-1);
                toCity = TourManager.getCity(0);
                
                System.out.println("From : "+ fromCity.getName());
                System.out.println("To : "+toCity.getName());
                
                distance = fromCity.distanceTo(toCity);
                
                energy = distance * capacity;
                totalEnergy = totalEnergy + energy ;
                
                //Relaoding only the required capacity
                j=i;

                while((capacity<vehiile_capacity) && (TourManager.numberOfCities()>j)){
                    capacity = capacity + TourManager.getCity(j).getDemand();
                    j++;
               }
                System.out.println("Reloaded");
                
                
                noOfTrips = noOfTrips + 1;
//                fromCity = TourManager.getCity(0);  
//                toCity = TourManager.getCity(i);
                fromCity = toCity;
                if(TourManager.numberOfCities()>i){
                    toCity = TourManager.getCity(i);
                }
               
                System.out.println("=+=+=+====================");
            }
            
            
            System.out.println("energy consumption : " + energy);
            System.out.println("***********************************");
            
            
            
        }
        //Going back trip
        fromCity = toCity;
        toCity = TourManager.getCity(0);
        
        distance = fromCity.distanceTo(toCity);
        
        energy = capacity * distance;
        totalEnergy = totalEnergy + energy;
        System.out.println("Going back from : "+ fromCity.getName());
        System.out.println("To : "+toCity.getName());
        
        System.out.println("No of trips are " + noOfTrips);
        System.out.println("Total energy consumption is " + totalEnergy);
        
        
        
    }
}
