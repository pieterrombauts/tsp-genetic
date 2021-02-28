import java.util.ArrayList;

public class Township {

    private static ArrayList<Town> towns = new ArrayList<>();

    public static void addTown(Town town) {
        towns.add(town);
    }

    public static Town getTown(int index) {
        return towns.get(index);
    }
}
