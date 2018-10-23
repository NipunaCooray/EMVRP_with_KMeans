
package tsp;

import java.util.ArrayList;

/**
 *
 * @author Slayer
 */
public class GA {
    /* GA parameters */
    private static final double mutationRate = 0.001;
    //private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), true);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Tour crossover(Tour parent1, Tour parent2) {
        
        //Removing first element
        City firstCity = new City();
        firstCity = parent1.getCity(0);
            
        Tour dummyParent1 = new Tour(generateCityList(parent1));
        Tour dummyParent2 = new Tour(generateCityList(parent2));
        
        // Create new child tour
        Tour child = new Tour();
        child.setCity(0, firstCity);
        
        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * dummyParent1.tourSize());
        int endPos = (int) (Math.random() * dummyParent2.tourSize());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 1; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, dummyParent1.getCity(i-1));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, dummyParent1.getCity(i-1));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < dummyParent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(dummyParent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 1; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, dummyParent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }
    
    //We need to generate parent without first element before we cross over
    private static ArrayList  generateCityList(Tour parent){
        
        ArrayList <City> cityList = new ArrayList();
        
        for(int i=0;i<TourManager.numberOfCities()-1;i++){
            cityList.add(i, parent.getCity(i+1)); 
        }
        
        return cityList;
    }
    
    // Mutate a tour using swap mutation
    public static void mutate(Tour tour) {
        

        // Loop through tour cities
        for(int tourPos1=1; tourPos1 < tour.tourSize(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                
                
                int tourPos2 = (int) (tour.tourSize() * Math.random());
                 
                
                if(tourPos1 > 0 && tourPos2 > 0) {
                    // Get the cities at target position in tour
                    City city1 = tour.getCity(tourPos1);
                    City city2 = tour.getCity(tourPos2);

                    // Swap them around
                    tour.setCity(tourPos2, city1);
                    tour.setCity(tourPos1, city2);
                }
            }
        }
    }

    // Selects candidate tour for crossover
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}
