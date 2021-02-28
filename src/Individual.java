import java.util.ArrayList;
import java.util.Collections;

public class Individual {

    private ArrayList<Town> genome = new ArrayList<>(Main.NUM_TOWNS);
    private double fitness = 0;
    private double distance = 0;

    public Individual() {
        // Initialise empty individual with null genome
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            this.genome.add(null);
        }
    }

    public Individual(ArrayList<Town> genome) {
        this.genome = genome;
    }

    public void generateGenome() {
        // Randomly generate a new genome
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            setGenomeTown(i, Township.getTown(i));
        }
        Collections.shuffle(this.genome);
    }

    public Town getGenomeTown(int index) {
        return this.genome.get(index);
    }

    public void setGenomeTown(int index, Town town) {
        this.genome.set(index, town);
        this.fitness = 0;
        this.distance = 0;
    }

    public double getFitness() {
        if (this.fitness == 0) {
            this.fitness = 1 / this.getDistance();
        }
        return this.fitness;
    }

    public double getDistance() {
        if (this.distance == 0) {
            double genomeDistance = 0;
            for (int i = 0; i < Main.NUM_TOWNS; i++) {
                Town originTown = getGenomeTown(i);
                Town destTown = getNextTown(i);
                genomeDistance += originTown.distanceTo(destTown);
            }
            this.distance = genomeDistance;
        }
        return this.distance;
    }

    public Town getNextTown(int index) {
        if (index + 1 < Main.NUM_TOWNS) return this.getGenomeTown(index + 1);
        else return getGenomeTown(0);
    }

    public boolean containsTown(Town town) {
        return genome.contains(town);
    }

    public String toString() {
        String genomeString = "| ";
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            genomeString += this.getGenomeTown(i) + " | ";
        }
        return genomeString;
    }

}
