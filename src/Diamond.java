import java.awt.*;
import java.util.ArrayList;

/**
 *  Class representing diamond figure.
 *  Designed to show the idea of visibility, method, class data, etc.
 *
 *      Created by Nonthakorn Sukprom 60070503435,
 *      2 February 2020
 */
public class Diamond extends AbstractShape
{
    /** also keep the length of one side */
    private double oneside = 0;

    /** color to use for drawing a particular diamond */
    private Color drawColor = null;

    public void Diamond(int x, int y, int verticalLength, int horizontalLength)
    {
        anchor.setLocation(x, y);
        vertices.add(anchor);                                   // First, Top point, anchor
        vertices.add(new Point(x + (horizontalLength / 2), y - (verticalLength / 2)));  // Second, Right point
        vertices.add(new Point(x, y - verticalLength));     // Third, Bottom point
        vertices.add(new Point(x - (horizontalLength / 2), y - (verticalLength / 2)));  // Forth, Left point
        oneside = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        counter++;
        allFigures.add(this);
        drawColor = colors[counter % 5];

    }
    /**
     * Calculate and return the perimeter.
     *
     * @return Length of shape boundary
     */
    public double calcPerimeter()
    {
        return oneside * 4;
    }

    /**
     * Calculate and return the area of the shape.
     *
     * @return area
     */
    public double calcArea()
    {
        return oneside * oneside;
    }
}
