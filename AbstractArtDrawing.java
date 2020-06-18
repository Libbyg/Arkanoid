/**
 * @author Libby Goldin 204566236
 *
 * @version 1.0
 *
 * @since 2020-04-22
 */
import java.util.Random;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;

public class AbstractArtDrawing {

    public Line generateRandomLine() {
        Random generator = new Random();
        //generate random four coordinates for  two points to create a line
        int x1 = generator.nextInt(400) + 1; // get integer in range 1-400
        int y1 = generator.nextInt(300) + 1; // get integer in range 1-300
        int x2 = generator.nextInt(400) + 1; // get integer in range 1-400
        int y2 = generator.nextInt(300) + 1; // get integer in range 1-300
        System.out.println("First point " + x1 + " " + y1);
        System.out.println("Second geometry.Point " + x2 + " " + y2);
        System.out.println(" ");
        return (new Line(x1, y1, x2, y2));
    }

    public void drawPoint(Point p,DrawSurface d) {
        d.fillCircle(((int) p.getX()), ((int) p.getY()),3);
    }

    public DrawSurface drawBluePoint(Point middle, DrawSurface d) {
        int r = 3;
        int x = (int) middle.getX();
        int y = (int) middle.getY();
        d.setColor(Color.BLUE);
        d.fillCircle(x, y, r);
        return d;
    }

    public DrawSurface drawRedPoint(Point intersectionP, DrawSurface d) {
        int r = 3;
        int x = (int) intersectionP.getX();
        int y = (int) intersectionP.getY();
        d.setColor(Color.RED);
        d.fillCircle(x, y, r);
        return d;
    }

    public DrawSurface drawLines(Line line, DrawSurface d) {
        int x1 = (int) line.start().getX();
        int y1 = (int) line.start().getY();
        int x2 = (int) line.end().getX();
        int y2 = (int) line.end().getY();
        d.drawLine(x1, y1, x2, y2);
        d.setColor(Color.BLACK);
        return d;
    }

    public void AbstractArtOfLines() {
        GUI gui = new GUI("AbstractArtDrawing", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[10];

        for (int i = 0; i < lines.length; i++) {
            //generate each line and place it in array
            lines[i] = generateRandomLine();
            drawLines(lines[i], d);
            System.out.println("Slope " + lines[i].getSlope());

        }

        for (Line line: lines){
            for (Line other: lines){
                if (!line.equals(other)){
                    if (line.isIntersecting(other)) {
                        d.setColor(Color.RED);
                        drawPoint(line.intersectionWith(other),d);
                    }
                }
            }
            d.setColor(Color.BLUE);
            drawPoint(line.middle(),d);
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        AbstractArtDrawing canvas = new AbstractArtDrawing();
        //create array of geometry.Line objects
        canvas.AbstractArtOfLines();
    }
}