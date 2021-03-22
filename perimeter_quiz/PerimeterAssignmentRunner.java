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

    // Assignment 2 task 1
    public double getLargestPerimeterMultipleFiles() {

        double largestPerimeterMultipleFiles = 0.0;
        DirectoryResource dr = new DirectoryResource();

        for (File currentFile : dr.selectedFiles()) {
                FileResource currentFileResource = new FileResource(currentFile);
                Shape currentShape = new Shape(currentFileResource);

                double currentShapePerimeter = getPerimeter(currentShape);
                if(currentShapePerimeter > largestPerimeterMultipleFiles)
                        largestPerimeterMultipleFiles = currentShapePerimeter;
        }
        return largestPerimeterMultipleFiles;
    }

    // Assignment 2 task 2
    public File getFileWithLargestPerimeter() {
        File largestPerimeterFile = null;
        DirectoryResource dr = new DirectoryResource();

        double largestPerimeter = 0.0;
        for (File currentFile : dr.selectedFiles()) {

            FileResource currentFileResource = new FileResource(currentFile);
            Shape currentShape = new Shape(currentFileResource);
            double currentShapePerimeter = getPerimeter(currentShape);

            if(currentShapePerimeter > largestPerimeter) {
                largestPerimeter = currentShapePerimeter;
                largestPerimeterFile = currentFile;
            }
        }
        return largestPerimeterFile;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape shape = new Shape(fr);
        double length = getPerimeter(shape);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points: "+ getNumberOfPoints(shape));
        System.out.println("Average Length of Shape: "+getAverageLength(shape));
        System.out.println("Largest Side of Shape: "+getLargestSide(shape));
        System.out.println("Largest X Value Of All Points: "+getLargestX(shape));
    }

    // Assignment 2 task 1
    public void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    // Assignment 2 task 2
    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter().getName());
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
        // pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
         pr.testFileWithLargestPerimeter();
    }
}
