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
public class TestFitnessCopy {
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
        int vehiile_capacity = 100;
        int capacity = 0;
        int noOfTrips = 1;
        
        City fromCity = null;
        City toCity = null;
        
        double distance = 0;
        
        double energy = 0;
        double totalEnergy = 0;
        
        capacity = vehiile_capacity;
        
        
        
        for(int i=0;i<TourManager.numberOfCities();i++){
            
            demand = demand + TourManager.getCity(i).getDemand();
            
        }
        
        System.out.println("Demand is :"+ demand);
        
        for(int i=1;i<TourManager.numberOfCities();i++){
            
            System.out.println("Demand left to be served " + demand);
            System.out.println("Capacity left is " + capacity);
            
            if(capacity >= TourManager.getCity(i).getDemand()){
                
                if(capacity==vehiile_capacity && noOfTrips > 1){
                
                    fromCity = TourManager.getCity(0);
                    
                    if(i < TourManager.numberOfCities()){
                        toCity = TourManager.getCity(i);
                    }
                    else{
                        toCity = TourManager.getCity(0);
                    }
                    
                    
                  
                }else{
                    fromCity = TourManager.getCity(i-1);
                    
                    if(i < TourManager.numberOfCities()){
                        toCity = TourManager.getCity(i);
                    }
                    else{
                        toCity = TourManager.getCity(0);
                    }
                }
                
                
                
                distance = fromCity.distanceTo(toCity);
                
                //calculating energy
                energy = distance * capacity;
                totalEnergy = totalEnergy + energy;
                
                //Dropping off load
                capacity = capacity - TourManager.getCity(i).getDemand();
                demand = demand - TourManager.getCity(i).getDemand();
                
                //distance calculation
                //fromCity = TourManager.getCity(i);

                System.out.println("From : "+ fromCity.getName());
                System.out.println("To : "+toCity.getName());

                //System.out.println("City " + TourManager.getCity(i).getName() + " Served");

                
                System.out.println("==========================");
                
                
            }else{
                i = i-1;
                fromCity = TourManager.getCity(i);
                toCity = TourManager.getCity(0);
                
                System.out.println("From : "+ fromCity.getName());
                System.out.println("To : "+toCity.getName());
                
                distance = fromCity.distanceTo(toCity);
                
                energy = distance * capacity;
                totalEnergy = totalEnergy + energy ;
 
                capacity = vehiile_capacity;
                System.out.println("Reloaded");
                noOfTrips = noOfTrips + 1;
             
               
                System.out.println("=+=+=+====================");
            }
            
            
            System.out.println("energy consumption : " + energy);
            System.out.println("***********************************");
            
            
            
        }
        System.out.println("No of trips are " + noOfTrips);
        System.out.println("Total energy consumption is " + totalEnergy);
        
        
    }
}
