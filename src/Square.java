import java.awt.*;
import java.util.ArrayList;

/**
 * Simple class representing a square figure. Designed 
 * to show the idea of visibility, methods, class data, etc.
 *
 *   Created by Sally Goldin, 9 August 2017
 *   Modified 19 August 2017 to improve cohesion, reduce coupling
 *   by removing any need for any other class to see the data.
 *
 */
public class Square extends AbstractShape
{
   /* A square can be defined by an upper left corner point plus
    * the length of a side. However, for drawing purposes it is 
    * more convenient to have four corner points in order.
    */ 

    /** also keep the length of one side */
    private int oneside = 0;

    /** color to use for drawing a particular square */
    private Color drawColor = null;

    /**
     * Constructor creates a new Square by setting the
     * values of the sets of vertex coordinates.
     * Also increments the counter and sets the figureNumber. 
     * @param     x        Upper left corner X
     * @param     y        Upper left corner Y
     * @param     side     Length of one side
     */
    public Square(int x, int y, int side)
    {
        oneside = side;
        anchor.setLocation(x,y);
        vertices.add(anchor);                                  // upper left
        vertices.add(new Point(x + side, y));               // upper right
        vertices.add(new Point(x + side, y + side));     // lower right
        vertices.add(new Point(x, y + side));               // lower left
        counter++;
        allFigures.add(this);
        drawColor = colors[counter % 5];                           // set so will always be same color
    }

    /**
     * calculate the perimeter of this triangle
     * @return perimeter value
     */
    public double calcPerimeter()
    {
        return (double) oneside * 4;
    }
    
    
    /**
     * calculate the area of this triangle
     * @return area value
     */
    public double calcArea()
    {
        return (double) oneside * oneside;
    }

    /** static method to draw all the squares that have been
     * created so far.
     * @param  graphics   Graphics context for drawing.
     */
    /*public static void drawAll(Graphics2D graphics)
    {
        for (int i=0; i < counter; i++)
        {
            Square square = allFigures.get(i);
            square.draw(graphics);
        }
    }*/
	
}
