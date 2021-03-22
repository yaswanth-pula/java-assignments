package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape shape) {
        // Start with totalPerimeter = 0
        double totalPerimeter = 0.0;
        // Start wth prevPt = the last point 
        Point previousPoint = shape.getLastPoint();
        // For each point currPt in the shape,
        for (Point currentPoint : shape.getPoints()) {
            // Find distance from previousPoint point to currentPoint
            double currentDistance = previousPoint.distance(currentPoint);
            // Update totalPerimeter by currentDistance
            totalPerimeter = totalPerimeter + currentDistance;
            // Update previousPoint to be currentPoint
            previousPoint = currentPoint;
        }
        // totalPerimeter is the answer
        return totalPerimeter;
    }

    // Assignment 1 task 1
    public int getNumberOfPoints(Shape shape) {
        int numberOfPoints = 0;

        for(Point point :shape.getPoints())
            numberOfPoints++;

        return numberOfPoints;
    }

    // Assignment 1 task 2
    public double getAverageLength(Shape shape) {
        double avgLengthOfShape = 0.0;

        avgLengthOfShape = getPerimeter(shape) / getNumberOfPoints(shape);

        return avgLengthOfShape;
    }

    // Assignment 1 task 3
    public double getLargestSide(Shape shape) {
        double largestSideOfShape = 0.0;
        Point previousPoint = shape.getLastPoint();

        for (Point currentPoint : shape.getPoints()) {
            double currentLengthOfSide = previousPoint.distance(currentPoint);
            if(currentLengthOfSide > largestSideOfShape)
                    largestSideOfShape = currentLengthOfSide;
            previousPoint = currentPoint;
        }

        return largestSideOfShape;
    }

    // Assignment 1 task 4
    public double getLargestX(Shape shape) {
        double largestXValue = 0.0;

        for(Point currentPoint : shape.getPoints()) {
            double currentPointXValue = currentPoint.getX();
            if ( currentPointXValue > largestXValue)
                largestXValue = currentPointXValue;
        }

        return largestXValue;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape shape = new Shape(fr);
        double length = getPerimeter(shape);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points: "+ getNumberOfPoints(shape));
        System.out.println("Average Length of Shape: "+getAverageLength(shape));
        System.out.println("Largest Side of Shape: "+getLargestSide(shape));
        System.out.println("Largest X Value Of All Points: "+getLargestX(shape) );
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
