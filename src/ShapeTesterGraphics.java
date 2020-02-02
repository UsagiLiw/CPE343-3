import java.awt.*;

/**
 *  Test program to create and display selected shape.
 *  Offer draw all shape option to let user command system to
 *  draw every object created in program
 *
 *  Created by Nonthakorn Sukprom 60070503435,
 *  2 February 2020
 */
public class ShapeTesterGraphics
{
    /**
     * AbstractShape object to hold newly create shape object
     */
    protected static AbstractShape lastestShape = null;

    /**
     * boolean variable to get the program out of loop
     * if unexpect input occur.
     */
    private static boolean bContinue = true;

    /**
     *  Main method, First show list of available function
     *  and wait for user to select by input.
     *  The program wil ask user shape property then draw and calculates parameter and area.
     *  Ask if user want to move the created object or not.
     */
    public static void main(String[] arguments)
    {
        bContinue = true;
        FigureViewer viewer = new FigureViewer();
        viewer.pack();
        viewer.setVisible(true);

        while (bContinue)
        {
            System.out.println(" 1  -   Create and draw triangle");
            System.out.println(" 2  -   Create and draw square");
            System.out.println(" 3  -   Create and draw diamond");
            System.out.println(" 4  -   Draw all shapes");
            System.out.println(" 5  -   Exit");
            int input = IOUtils.getInteger("Enter your choice: ");

            switch (input)
            {
                case 1:             // Triangle
                    createTriangle();
                    if (bContinue == false)
                        continue;
                    break;
                case 2:             // Square
                    createSquare();
                    if (bContinue == false)
                        continue;
                    break;
                case 3:             // Diamond
                    createDiamond();
                    if (bContinue == false)
                        continue;
                    break;
                case 4:             // Draw all
                    AbstractShape.drawAll(viewer.getViewerGraphics());
                    System.out.println("Draw all complete!\n");
                    continue;
                case 5:             // Exit
                    System.exit(0);
                default:
                    System.out.println("Invalid input, Enter your choice again.");
            }
            /*Start Drawing and Calculation*/
            lastestShape.draw(viewer.getViewerGraphics());
            double perimeter = lastestShape.calcPerimeter();
            System.out.println("Perimeter is " + perimeter);
            double area = lastestShape.calcArea();
            System.out.println("Area is " + area + "\n\n");
            System.out.println("-----------------------------");
            String move = IOUtils.getString("Want to move the square (Y/N)?");
            if ((move.startsWith("Y")) || (move.startsWith("y")))
            {
                int x = IOUtils.getInteger("New X for anchor: ");
                int y = IOUtils.getInteger("New Y for anchor: ");
                lastestShape.move(new Point(x, y));
                viewer.clear();
                try
                {
                    Thread.sleep(1000); // Wait for clear to complete
                }
                catch (InterruptedException ie)
                {
                }
                lastestShape.draw(viewer.getViewerGraphics());
            }

        }
    }

    /**
     * Static for asking user a coordination of Triangle
     * and create Triangle object.
     */
    private static void createTriangle()
    {
        int x1 = IOUtils.getInteger("Enter X for first point");
        int y1 = IOUtils.getInteger("Enter Y for first point");
        int x2 = IOUtils.getInteger("Enter X for second point");
        int y2 = IOUtils.getInteger("Enter Y for second point");
        int x3 = IOUtils.getInteger("Enter X for third point");
        int y3 = IOUtils.getInteger("Enter Y for third point");
        /*Check if there is any negative value*/
        if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x3 < 0 || y3 < 0)
        {
            bContinue = false;
            return;
        }
        lastestShape = new Triangle(x1,y1,x2,y2,x3,y3);
    }

    /**
     * Static for asking user a coordination of Square
     * and create Square object.
     */
    private static void createSquare()
    {
        int x = IOUtils.getInteger("Enter X for upper left point: ");
        int y = IOUtils.getInteger("Enter Y for upper left point: ");
        int side = IOUtils.getInteger("Length of each side of square: ");
        /*Check if there is any negative value*/
        if(x < 0 || y < 0 || side < 0)
        {
            bContinue = false;
            return;
        }
        lastestShape = new Square(x, y, side);
    }

    /**
     * Static for asking user a coordination of Diamond
     * and create Diamond object.
     */
    private static void createDiamond()
    {
        int x = IOUtils.getInteger("Enter X for upper left point: ");
        int y = IOUtils.getInteger("Enter Y for upper left point: ");
        int vLength = IOUtils.getInteger("Length in vertical axis: ");
        int hLength = IOUtils.getInteger("Length in horizontal axis: ");
        /*Check if there is any negative value*/
        if(x < 0 || y < 0 || vLength < 0 || hLength  < 0)
        {
            bContinue = false;
            return;
        }
        lastestShape = new Diamond(x, y, vLength, hLength);
    }
}
