package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage image = new APImage("image/cyberpunk2077.jpg");
        image.draw();
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        for (Pixel p : image) {
            int avg = getAverageColour(p);
            p.setRed(avg);
            p.setGreen(avg);
            p.setBlue(avg);
        }

        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        return (red + green + blue) / 3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        for (Pixel p : image) {
            int avg = getAverageColour(p);
            if (avg < 128) {
                p.setRed(0);
                p.setGreen(0);
                p.setBlue(0);
            } else {
                p.setRed(255);
                p.setGreen(255);
                p.setBlue(255);
            }
        }

        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel current = image.getPixel(x, y);
                Pixel left = x > 0 ? image.getPixel(x - 1, y) : current;
                Pixel below = y > 0 ? image.getPixel(x, y - 1) : current;

                int currAvg = getAverageColour(current);
                int leftAvg = getAverageColour(left);
                int belowAvg = getAverageColour(below);

                if (Math.abs(currAvg - leftAvg) > threshold || Math.abs(currAvg - belowAvg) > threshold) {
                    current.setRed(0);
                    current.setGreen(0);
                    current.setBlue(0);
                } else {
                    current.setRed(255);
                    current.setGreen(255);
                    current.setBlue(255);
                }
            }
        }

        image.draw();
    }


    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                Pixel leftPixel = image.getPixel(x, y);
                Pixel rightPixel = image.getPixel(width - x - 1, y);
                Pixel temp = leftPixel.clone();

                leftPixel.setRed(rightPixel.getRed());
                leftPixel.setGreen(rightPixel.getGreen());
                leftPixel.setBlue(rightPixel.getBlue());

                rightPixel.setRed(temp.getRed());
                rightPixel.setGreen(temp.getGreen());
                rightPixel.setBlue(temp.getBlue());
            }
        }

        image.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();

        APImage rotatedImage = new APImage(height, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel oldPixel = image.getPixel(x, y);
                Pixel newPixel =rotatedImage.getPixel(height - y - 1, x);

                newPixel.setRed(oldPixel.getRed());
                newPixel.setGreen(oldPixel.getGreen());
                newPixel.setBlue(oldPixel.getBlue());
            }
        }

        rotatedImage.draw();
    }

}
