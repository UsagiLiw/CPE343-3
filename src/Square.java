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
    
    /* static data */
    /** so we can count and label figures */ 
    private static int counter = 0;
    
    /** collection of all squares */
    private static ArrayList<Square> allFigures = new ArrayList<Square>();

    /** used to cycle through display colors */    
    private static Color colors[] = {Color.RED, Color.GREEN, Color.BLUE,
			      Color.MAGENTA, Color.ORANGE};
    
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
        color = colors[counter % 5];                           // set so will always be same color
    }

    /**
     * Move the square somewhere else, determined by new
     * upper left x and y. 
     * The function re-initializes the other coordinates in the array
     * to keep the figure square.
     * @param   newAnchor    New upper left X coordinate
     */
    public void move(Point newAnchor)
    {
        anchor.setLocation(newAnchor);
        int newX = anchor.x;
        int newY = anchor.y;
        vertices.set(0, anchor);                                   // upper left
        vertices.set(1, new Point(newX + oneside, newY));       // upper right
        vertices.set(2, new Point(newX + oneside, newY + oneside));     // lower right
        vertices.set(3, new Point( newX, newY + oneside));      // lower left
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

    /**
     * Draw the square. The passed graphics2D contains
     * the information necessary for this.
     * @param graphics Class with info to do the drawing
     */
    public void draw(Graphics2D graphics)
    {
	graphics.setPaint(drawColor);
	int x1,y1,x2,y2;
	/* cycle around the outside of the square
	 * starting at the upper left. Get the current
	 * corner and the next corner, then draw
	 * a line between them.
	 */
	for (int i = 0; i < 4; i++)
	{  
	    int pt1 = i;
	    int pt2 = ((i+1) % 4);
	    x1 = vertices.get(pt1).x;
	    y1 = vertices.get(pt1).y;
	    x2 = vertices.get(pt2).x;
	    y2 = vertices.get(pt2).y;
	    x1 *=10;    /* multiply by 10 so we can use small numbers for coords*/
	    y1 *=10;
	    x2 *=10;
	    y2 *=10;
	    graphics.drawLine(x1,y1,x2,y2);
	}
	/* label in the center */
	int ulx = vertices.get(0).x * 10;
	int uly = vertices.get(0).y * 10;
	graphics.setColor(Color.BLACK);
	graphics.drawString(new String(" " + counter),(ulx + 10),(uly-10));
    }

    /** static method to draw all the squares that have been
     * created so far.
     * @param  graphics   Graphics context for drawing.
     */
    public static void drawAll(Graphics2D graphics)
    {
	for (int i=0; i < counter; i++)
	{
	    Square square = allFigures.get(i);
	    square.draw(graphics);
	}
    }
	
}
