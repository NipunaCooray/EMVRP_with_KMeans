/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tsp.City;
import tsp.TourManager;

/**
 *
 * @author Slayer
 */
public class SuffleArrayList {
    public static void main(String[] args) {
       
        
        List tour = new ArrayList<Integer>();
        
       tour.add(0);
       tour.add(1);
       tour.add(2);
       tour.add(3);
       tour.add(4);
       tour.add(5);
        
        for(int i =0;i <tour.size();i++){
            
            System.out.println(tour.get(i));
            
        }
        
        System.out.println("======");
        
        
         List<Integer> dtour = new ArrayList<Integer>(tour.subList(1,tour.size()));
        
         
         Collections.shuffle(dtour);
         
        for(int i =0;i <dtour.size();i++){
            
            System.out.println(dtour.get(i));
            
        }
         
        System.out.println("==++==");
        
        
        tour.remove(5);
        tour.remove(4);
        tour.remove(3);
        tour.remove(2);
        tour.remove(1);
                
        
       tour.addAll(dtour);
       
       
       
//         //List dummyTour = null;
//         
         for(int i =0;i <tour.size();i++){
            
             System.out.println(tour.get(i));
            
        }
        
    }
}
