import java.util.ArrayList;
import java.awt.*;
/**
 *  AbstractShape class. Intended to serve as a superclass (generalization) for
 *  individual shapes like Triangle, Square, etc.
 *
 *  V2 - Created by Sally Goldin, 21 August 2017
 */
public abstract class AbstractShape
{
    /** Anchor point X,Y */
    protected Point anchor = new Point();   /* determines the "position" of a shape */
    /* Point is a class in package java.awt that has a public x and y member */

    /** list of points */
    protected ArrayList<Point> vertices = new ArrayList<Point>();

    /** how many points? */
    protected int pointCount; 

    /** color */
    protected Color drawColor;

    /** so we can count and label figures */ 
    protected static int counter = 0;

    protected int figureNumber;
    
    /** collection of all squares */
    protected static ArrayList<AbstractShape> allFigures = new ArrayList<AbstractShape>();

    /** used to cycle through display colors */    
    protected static Color colors[] = {Color.RED, Color.GREEN, Color.BLUE,
			      Color.MAGENTA, Color.ORANGE};


    /**
     * Move the shape to a new location, specified by
     * the passed point.
     * @param  newAnchor    x,y coordinates of new reference/anchor point
     */
    public void move(Point newAnchor)
    {
        // Find different between two anchor and apply to all vertices
        int diffX = newAnchor.x - anchor.x;
        int diffY = newAnchor.y - anchor.y;
        for (int i = 0; i < vertices.size(); i++)
        {
            vertices.get(i).x += diffX;
            vertices.get(i).y += diffY;
        }
        anchor.setLocation(newAnchor);
    }

    /**
     * Draw the shape.
     * @param  graphics    Graphics context for drawing
     */
    public void draw(Graphics2D graphics)
    {
        graphics.setPaint(drawColor);
        pointCount = vertices.size();
        int x1,y1,x2,y2;
        /* cycle around the outside of the square
         * starting at the upper left. Get the current
         * corner and the next corner, then draw
         * a line between them.
         */
        for (int i = 0; i < pointCount; i++)
        {
            int pt1 = i;
            int pt2 = ((i+1) % pointCount);
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
        int anchorX = anchor.x * 10;
        int anchorY = anchor.y * 10;
        graphics.setColor(Color.BLACK);
        graphics.drawString(new String(" " + figureNumber),(anchorX + 10),(anchorY-10));
    }
    /**
     * Calculate and return the perimeter.
     * @return  Length of shape boundary
     */
    public abstract double calcPerimeter();

    /**
     * Calculate and return the area of the shape.
     * @return  area
     */
    public abstract double calcArea();

    /** static method to draw all the shapes of this category 
     * that have been created so far.
     * @param  graphics   Graphics context for drawing.
     */
    public static void drawAll(Graphics2D graphics)
    {
        for (int i=0; i < counter; i++)
        {
            AbstractShape shape = allFigures.get(i);
            shape.draw(graphics);
        }
    }


}