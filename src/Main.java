import javax.swing.*;
import java.awt.*;

public class Main {

    // GUI config
    static final int DIMENSIONS = 600;
    private static final int BORDER_WIDTH = 50;
    private static final int DRAW_RATE = 10;

    // Simulation config
    static final int NUM_TOWNS = 20;

    // Genetics config
    private static final int NUM_GENS = 100000;
    static final int POP_SIZE = 1000;
    static final double MUTATE_RATE = 0.5;
    static final int TOURNAMENT_SIZE = 50;
    static final boolean ELITISM = true;

    public static void main(String[] args) {

        Points initial = new Points();
        Points optimised = new Points();
        Points towns = new Points();

        JFrame unoptimisedFrame = new JFrame("Unoptimised Route");
        unoptimisedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        unoptimisedFrame.setSize(DIMENSIONS+BORDER_WIDTH, DIMENSIONS+BORDER_WIDTH);
        unoptimisedFrame.add(initial);
        unoptimisedFrame.setLocationRelativeTo(null);
        unoptimisedFrame.setVisible(true);

        JFrame optimisedFrame = new JFrame("Optimised Route");
        optimisedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optimisedFrame.setSize(DIMENSIONS+BORDER_WIDTH, DIMENSIONS+BORDER_WIDTH);
        optimisedFrame.add(optimised);
        optimisedFrame.setLocation(unoptimisedFrame.getX() + unoptimisedFrame.getWidth() - 10, unoptimisedFrame.getY());
        optimisedFrame.setVisible(true);

        JFrame townsFrame = new JFrame("Town Locations");
        townsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        townsFrame.setSize(DIMENSIONS+BORDER_WIDTH, DIMENSIONS+BORDER_WIDTH);
        townsFrame.add(towns);
        townsFrame.setLocation(unoptimisedFrame.getX() - unoptimisedFrame.getWidth() + 10, unoptimisedFrame.getY());
        townsFrame.setVisible(true);

        for (int i = 0; i < NUM_TOWNS; i++) {
            Township.addTown(new Town());
        }

        Population pop = new Population(POP_SIZE, true);

        // Draw initial state to GUI
        System.out.println("\nInitial distance: " + pop.getFittest().getDistance());
        towns.addTowns(pop.getFittest());
        towns.validate();
        initial.addIndividualPaths(pop.getFittest());
        initial.addTowns(pop.getFittest());
        initial.validate();

        // Evolve population
        pop = EvolutionAlgorithm.evolvePopulation(pop);
        for (int i = 0; i < NUM_GENS; i++) {
            pop = EvolutionAlgorithm.evolvePopulation(pop);
            // Only draw every 10 generations
            if (i % DRAW_RATE == 0) {
                optimised.clearLines();
                optimised.addIndividualPaths(pop.getFittest());
                optimised.addTowns(pop.getFittest());
                optimised.setGenNumber(i);
                optimised.validate();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Solution found!");
        System.out.println("Distance: " + pop.getFittest().getDistance());

        optimised.addIndividualPaths(pop.getFittest());
        optimised.addTowns(pop.getFittest());
        optimised.validate();
    }
}
