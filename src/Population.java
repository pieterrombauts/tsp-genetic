import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Population {

    ArrayList<Individual> population;

    public Population(int popSize, boolean initialise) {
        this.population = new ArrayList<>(popSize);

        if (initialise) {
            for (int i = 0; i < popSize; i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateGenome();
                this.population.add(newIndividual);
            }
        }
    }

    public Individual getIndividual(int index) {
        return this.population.get(index);
    }

    public Individual getFittest() {
        return this.population.stream().max(Comparator.comparing(Individual::getFitness)).orElseThrow(NoSuchElementException::new);
    }

    public void saveIndividual(Individual individual) {
        this.population.add(individual);
    }
}
