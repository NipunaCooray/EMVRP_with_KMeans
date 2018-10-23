/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;



/**
 *
 * @author Slayer
 */
public class TestGA {
    public static void main(String[] args) {
        
        //new MainForm().setVisible(true);
        
       
        
        // Create and add our cities
//        City city = new City("Panadura",60, 200);
//        TourManager.addCity(city);
//        City city2 = new City("Kalutara",180, 200);
//        TourManager.addCity(city2);
//        City city3 = new City("Monaragala",80, 180);
//        TourManager.addCity(city3);
//        City city4 = new City("Jaffna",140, 180);
//        TourManager.addCity(city4);
//        City city5 = new City("Kegalle",20, 160);
//        TourManager.addCity(city5);
//        City city6 = new City("Kurunegala",100, 160);
//         TourManager.addCity(city6);
//        City city7 = new City("Mahawa",200, 160);
//        TourManager.addCity(city7);
//        City city8 = new City("Galigamuwa",140, 140);
//        TourManager.addCity(city8);
//        City city9 = new City("Thampalawela",40, 120);
//        TourManager.addCity(city9);
//        City city10 = new City("Galle",100, 120);
//        TourManager.addCity(city10);
//        City city11 = new City("Matara",180, 100);
//        TourManager.addCity(city11);
//        City city12 = new City("Kandy",60, 80);
//        TourManager.addCity(city12);
//        City city13 = new City("Polgahawela",120, 80);
//        TourManager.addCity(city13);
//        City city14 = new City("Yatiyanthota",180, 60);
//        TourManager.addCity(city14);
//        City city15 = new City("Mipe",20, 40);
//        TourManager.addCity(city15);
//        City city16 = new City("Yala",100, 40);
//        TourManager.addCity(city16);
//        City city17 = new City("Moratuwa",200, 40);
//        TourManager.addCity(city17);
//        City city18 = new City("Matugama",20, 20);
//        TourManager.addCity(city18);
//        City city19 = new City("Beruwala",60, 20);
//        TourManager.addCity(city19);
//        City city20 = new City("Mahara",160, 20);
//        TourManager.addCity(city20);
//        
//
//        // Initialize population
//        Population pop = new Population(50, true);
//        System.out.println("Initial distance: " + pop.getFittest().getDistance());
//
//        // Evolve population for 100 generations
//        pop = GA.evolvePopulation(pop);
//        for (int i = 0; i < 100; i++) {
//            pop = GA.evolvePopulation(pop);
//        }
//
//        // Print final results
//        System.out.println("Finished");
//        System.out.println("Final distance: " + pop.getFittest().getDistance());
//        System.out.println("Solution:");
//        System.out.println(pop.getFittest());
            City c0 = new City(0, 2, 2, 0);
            City c1 = new City(1, 3, 3, 50);
            City c2 = new City(2, 4, 4, 50);
            City c3 = new City(3, 5, 5, 50);
            City c4 = new City(4, 6, 6, 50);

            TourManager.addCity(c0);
            TourManager.addCity(c4);
            TourManager.addCity(c1);
            TourManager.addCity(c3);
            TourManager.addCity(c2);
            
            TourManager.setVehicleCapacity(120);
            
//            // Initialize population
//            Population pop = new Population(2, true);
//            System.out.println("Initial energy consumption: " + pop.getFittest().getEnergy());

            
          
            
            // Initialize population
            Population pop = new Population(50, true);
            System.out.println("Initial energy: " + pop.getFittest().getEnergy());
            System.out.println(pop.getFittest());
            
            // Evolve population for 100 generations
            pop = GA.evolvePopulation(pop);
            for (int i = 0; i < 100; i++) {
               
                pop = GA.evolvePopulation(pop);
            }

            // Print final results
            System.out.println("Finished");
            System.out.println("Final energy: " + pop.getFittest().getEnergy());
            System.out.println("Solution:");
            System.out.println(pop.getFittest());
            
    }
}
