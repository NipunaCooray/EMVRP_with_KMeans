/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import tsp.City;
import tsp.Tour;
import tsp.TourManager;

/**
 *
 * @author Slayer
 */
public class Crossover {
    
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
        
        Tour t1 = new Tour();
        t1.generateIndividual();
        
//        System.out.println(t1.getCity(0));
//        System.out.println(t1.getCity(1));
//        System.out.println(t1.getCity(2));
//        System.out.println(t1.getCity(3));
//        System.out.println(t1.getCity(4));
        
        Tour t2 = new Tour();
        t2.generateIndividual();
        
        Tour child = new Tour();
        
        //List<Integer> t1small = new ArrayList<Integer>(t1.subList(1,tour.size()));
        //List<Integer> t1small = new ArrayList<Integer>(t1.
        Tour dummyT1 = Crossover.generateParent(t1);
//       
//        
//        System.out.println(dummyT1.getCity(0));
//        System.out.println(dummyT1.getCity(1));
//        System.out.println(dummyT1.getCity(2));
//        System.out.println(dummyT1.getCity(3));
        
    }
    
     private static Tour  generateParent(Tour parent){
        
        Tour dummyParent = new Tour();
        for(int i=0;i<TourManager.numberOfCities()-1;i++){
            dummyParent.setCity(i, parent.getCity(i+1)); 
        }
        
        System.out.println(dummyParent.getCity(0));
        System.out.println(dummyParent.getCity(1));
        System.out.println(dummyParent.getCity(2));
        System.out.println(dummyParent.getCity(3));
        
        return dummyParent;
     }
}
