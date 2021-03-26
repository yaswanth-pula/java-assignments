package makeGrayBatch;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class ImageInverter {
    public ImageResource makeInvertedImage(ImageResource inputImage) {
        ImageResource outImage = new ImageResource(inputImage.getWidth(), inputImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inputPixel = inputImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inputPixel.getRed());
            pixel.setGreen(255 - inputPixel.getGreen());
            pixel.setBlue(255 - inputPixel.getBlue());
        }
        return outImage;
    }

    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            ImageResource inputImage = new ImageResource(file);
            ImageResource grayImage = makeInvertedImage(inputImage);
            grayImage.setFileName("inverted-"+inputImage.getFileName());
            grayImage.save();
        }
    }

    public static void main(String[] args) {
        ImageInverter inverter = new ImageInverter();
        inverter.selectAndConvert();
    }


}
