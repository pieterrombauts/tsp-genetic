public class Town {

    private int x;
    private int y;

    public Town() {
        this.x = (int) (Math.random()*Main.DIMENSIONS);
        this.y = (int) (Math.random()*Main.DIMENSIONS);
    }

    public Town(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceTo(Town town) {
        int distanceX = this.getX() - town.getX();
        int distanceY = this.getY() - town.getY();
        return Math.hypot(distanceX, distanceY);
    }

    public String toString() {
        return getX() + "," + getY();
    }
}
