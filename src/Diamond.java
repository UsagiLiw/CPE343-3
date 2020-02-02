import java.awt.*;

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
    private double vertical = 0;
    private double horizontal = 0;

    /**
     * Constructor creates a new Diamond by setting the
     * values of the sets of vertex coordinates.
     * Also increments the counter and sets the figureNumber.
     * @param x                 X axis coordinate of the anchor
     * @param y                 Y axis coordinate of the anchor
     * @param verticalLength    Length of vertical axis
     * @param horizontalLength  Length of horizontal axis
     */
    public Diamond(int x, int y, int verticalLength, int horizontalLength)
    {
        vertical = verticalLength;
        horizontal = horizontalLength;
        anchor.x = x;
        anchor.y = y;
        vertices.add(anchor);                                   // First, Top point, anchor
        vertices.add(new Point(x + (horizontalLength / 2), y + (verticalLength / 2)));  // Second, Right point
        vertices.add(new Point(x, y + verticalLength));     // Third, Bottom point
        vertices.add(new Point(x - (horizontalLength / 2), y + (verticalLength / 2)));  // Forth, Left point
        oneside = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        counter++;
        figureNumber = counter;
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
        return vertical * horizontal;
    }
}
