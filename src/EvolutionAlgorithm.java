public class EvolutionAlgorithm {

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(Main.POP_SIZE, false);

        int elitismOffset = 0;
        if (Main.ELITISM) {
            newPopulation.saveIndividual(pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < Main.POP_SIZE; i++) {
            Individual parent1 = tournamentSelection(pop);
            Individual parent2 = tournamentSelection(pop);
            Individual child = crossover(parent1, parent2);
            newPopulation.saveIndividual(child);
        }

        for (int i = elitismOffset; i < Main.POP_SIZE; i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private static Individual crossover(Individual parent1, Individual parent2) {
        Individual child = new Individual();

        // Generate a random subset to crossover
        int subsetStart = (int) Math.round((Math.random() * Main.NUM_TOWNS ));
        int subsetEnd = (int) Math.round((Math.random() * Main.NUM_TOWNS ));

        // Crossover genes from first parent
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            if (subsetStart < subsetEnd && i > subsetStart && i < subsetEnd) {
                child.setGenomeTown(i, parent1.getGenomeTown(i));
            } else if (subsetStart > subsetEnd && ( i < subsetStart || i > subsetEnd)) {
                child.setGenomeTown(i, parent1.getGenomeTown(i));
            }
        }

        // Crossover genes from second parent
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            if (!child.containsTown(parent2.getGenomeTown(i))) {
                for (int j = 0; j < Main.NUM_TOWNS; j++) {
                    if (child.getGenomeTown(j) == null) {
                        child.setGenomeTown(j, parent2.getGenomeTown(j));
                        break;
                    }
                }
            }
        }
        return child;
    }

    private static void mutate(Individual individual) {
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            if (Math.random() < Main.MUTATE_RATE) {
                int j = (int) Math.random() * Main.NUM_TOWNS;
                Town town1 = individual.getGenomeTown(i);
                Town town2 = individual.getGenomeTown(j);

                individual.setGenomeTown(j, town1);
                individual.setGenomeTown(i, town2);
            }
        }
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(Main.TOURNAMENT_SIZE, false);
        for (int i = 0; i < Main.TOURNAMENT_SIZE; i++) {
            int randomIndex = (int) Math.random() * Main.POP_SIZE;
            tournament.saveIndividual(pop.getIndividual(randomIndex));
        }
        return tournament.getFittest();
    }

}
