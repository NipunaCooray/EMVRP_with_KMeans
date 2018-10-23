/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tsp.City;
import tsp.GA;
import tsp.Tour;
import tsp.TourManager;

/**
 *
 * @author Slayer
 */
public class TestMutate {
    
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

        TourManager.setVehicleCapacity(120);
        
        Tour t1 = new Tour();
        t1.generateIndividual();
        
        GA.mutate(t1);
        System.out.println(t1);
        
    }
    
}
