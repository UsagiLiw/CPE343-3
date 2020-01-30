import java.awt.*;
import java.util.ArrayList;

/**
 * Simple class representing a triangle object. Designed 
 * to show the idea of visibility, methods, class data, etc.
 *
 *   Created by Sally Goldin, 9 December 2011
 *   Modified 16 Dec 2011 to fix triangle area calculations
 *   Modified 19 Aug 2017 to draw itself, keep collection of
 *   all triangles  
 *
 */
public class Triangle
{
   /** X coordinates of three points defining the triangle */
   private int xcoord[] = new int[3];

   /** Y coordinates of three points defining the triangle */
   private int ycoord[] = new int[3];

    /** figure number for a particular square */
    private int figureNumber = -1; 
    
    /** color to use for drawing a particular square */
    private Color drawColor = null;

    /** used to cycle through display colors */    
    private Color colors[] = {Color.PINK, Color.BLACK, Color.GREEN,
			      Color.CYAN, Color.YELLOW};
    
    /* static data */
    /** so we can count and label figures */ 
    private static int counter = 0;
    
    /** collection of all squares */
    private static ArrayList<Triangle> allFigures = new ArrayList<Triangle>();
    

   /**
    * Constructor creates a new Triangle by setting the
    * values of the sets of vertext coordinates.
    * Also increments counter, sets drawing color adn stores in allFigures.
    * @param     x1        X coord of first vertex
    * @param     y1        Y coord of first vertex
    * @param     x2        X coord of second vertex
    * @param     y2        Y coord of second vertex
    * @param     x3        X coord of third vertex
    * @param     y3        Y coord of third vertex
    */
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3)
    {
       xcoord[0] = x1;
       xcoord[1] = x2;
       xcoord[2] = x3;
       ycoord[0] = y1;
       ycoord[1] = y2;
       ycoord[2] = y3;
       counter++;
       figureNumber = counter;
       allFigures.add(this);
       drawColor = colors[counter % 5]; // set so will always be same color
    }

    /**
     * calculate the perimeter of this triangle
     * @return perimeter value
     */
    public double calcPerimeter()
    {
	double perimeter = 0;
        for (int i = 1; i < 4; i++)
	    {
	    perimeter = perimeter + calcLength(i);
	    } 
        return perimeter;
    }

    /**
     * calculate the area of this triangle
     * @return area value
     */
    public double calcArea()
    {
        /* area formula is  
        |Ax(By - Cy) + Bx(Cy - Ay) + Cx(Ay - By)|/2
        */
        int numerator = xcoord[0] * (ycoord[1] - ycoord[2]);
        numerator += xcoord[1] * (ycoord[2] - ycoord[0]);
        numerator += xcoord[2] * (ycoord[0] - ycoord[1]);
        return (double) Math.abs(numerator) / 2;
    }


    /**
     * Calculate the length of one side of the triangle.
     * This is private method used by calcPerimeter and calcArea.
     * @param  which    1,2 or 3, for which side
     * @return length of side, or -1 if 'which' is out of range.
     */
    private double calcLength(int which)
    {
	double len = -1;
        int index1 = -1;
        int index2 = -1;
        switch (which)
           {
           case 1:  
                index1 = 0;
                index2 = 1;
                break;
           case 2:  
                index1 = 1;
                index2 = 2;
                break;
           case 3:  
                index1 = 0;
                index2 = 2;
                break;
	   default:
                System.out.println("Invalid argument to calcLength!");
	   }
        if (index1 >= 0)
	   {
           len = Math.sqrt(Math.pow(xcoord[index1] - xcoord[index2],2) +
                               Math.pow(ycoord[index1] - ycoord[index2],2)); 
	   }
	return len;
    }

    /**
     * Draw the triangle. The passed graphics2D contains
     * the information necessary for this.
     * @param graphics Class with info to do the drawing
     */
    public void draw(Graphics2D graphics)
    {
	graphics.setPaint(drawColor);
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	for (int i = 0; i < 3; i++)
	{  
	    int pt1 = i;
	    int pt2 = (i+1) % 3;
	    x1 = xcoord[pt1];
	    y1 = ycoord[pt1];
	    x2 = xcoord[pt2];
	    y2 = ycoord[pt2];
	    x1 *=10;    /* multiply by 10 so we can use small numbers for coords*/
	    y1 *=10;
	    x2 *=10;
	    y2 *=10;
	    graphics.drawLine(x1,y1,x2,y2);
	}
	/* label in the center */
	graphics.setColor(Color.BLACK);
	graphics.drawString(new String(" " + figureNumber),(x1 + x2)/2,
			    (y1 + y2)/2); 
    }

    /** static method to draw all the triangles that have been
     * created so far.
     * @param  graphics   Graphics context for drawing.
     */
    public static void drawAll(Graphics2D graphics)
    {
	for (int i=0; i < counter; i++)
	{
	    Triangle triangle = allFigures.get(i);
	    triangle.draw(graphics);
	}
    }

}
