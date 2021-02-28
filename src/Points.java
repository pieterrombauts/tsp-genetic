import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Points extends JPanel {

    private static class Line {
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;
        final int strokeWidth;

        public Line(int x1, int y1, int x2, int y2, Color color, int strokeWidth) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            this.strokeWidth = strokeWidth;
        }
    }

    private final ArrayList<Line> lines = new ArrayList<>();
    private int genNumber = 0;

    public void addIndividualPaths(Individual individual) {
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            Town origin = individual.getGenomeTown(i);
            Town dest = individual.getNextTown(i);
            this.lines.add(new Line(origin.getX(), origin.getY(), dest.getX(), dest.getY(), Color.BLACK, 2));
        }
        repaint();
    }

    public void addTowns(Individual individual) {
        for (int i = 0; i < Main.NUM_TOWNS; i++) {
            Town town = individual.getGenomeTown(i);
            this.lines.add(new Line(town.getX(), town.getY(), town.getX(), town.getY(), Color.RED, 10));
        }
        repaint();
    }

    public void setGenNumber(int genNumber) {
        this.genNumber = genNumber;
    }

    public void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Line line : lines) {
            g2.setColor(line.color);
            g2.setStroke(new BasicStroke(line.strokeWidth));
            g2.drawLine(line.x1, line.y1, line.x2, line.y2);
            if (genNumber != 0) g2.drawString("Generation: " + genNumber, Main.DIMENSIONS-75, 10);
        }
    }
}
