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
public class Triangle extends AbstractShape
{

    /** color to use for drawing a particular triangle */
    private Color drawColor = null;

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
        anchor.setLocation(x1,y1);      // First point , anchor
        vertices.set(0,anchor);
        vertices.set(1,anchor);         // Second point
        vertices.set(2,anchor);         // Thrid point
        counter++;
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
        int numerator = vertices.get(0).x * (vertices.get(1).y - vertices.get(2).y);
        numerator += vertices.get(1).x * (vertices.get(2).y - vertices.get(0).y);
        numerator += vertices.get(2).x * (vertices.get(0).y - vertices.get(1).y);
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
           len = Math.sqrt(Math.pow(vertices.get(index1).x - vertices.get(index2).x, 2) +
                            Math.pow(vertices.get(index1).y - vertices.get(index2).y, 2));
	   }
	return len;
    }

    /** static method to draw all the triangles that have been
     * created so far.
     * @param  graphics   Graphics context for drawing.
     */
    /*public static void drawAll(Graphics2D graphics)
    {
        for (int i=0; i < counter; i++)
        {
            Triangle triangle = allFigures.get(i);
            triangle.draw(graphics);
        }
    }*/

}
