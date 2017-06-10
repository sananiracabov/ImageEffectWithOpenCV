package artificial.intelligence.algorithm;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Processing {

    public Image applyGrayscale(File file) throws IOException {

        BufferedImage image = ImageIO.read(file);

        byte[] rgbData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, rgbData);

        Mat rgbToGray = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
        Imgproc.cvtColor(mat, rgbToGray, Imgproc.COLOR_RGB2GRAY);

        byte[] grayData = new byte[rgbToGray.rows() * rgbToGray.cols() * (int) (rgbToGray.elemSize())];
        rgbToGray.get(0, 0, grayData);
        BufferedImage grayImage = new BufferedImage(rgbToGray.cols(), rgbToGray.rows(),
                BufferedImage.TYPE_BYTE_GRAY);
        grayImage.getRaster().setDataElements(0, 0, rgbToGray.cols(), rgbToGray.rows(), grayData);

        return grayImage;
    }

    public void applyBrightness(File file) throws Exception {

        Mat source = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        source.convertTo(destination, -1, 2.5, 50);

        Imgcodecs.imwrite("image.jpg", destination);
    }

    public void applySharpness(File file) throws Exception {
        Mat source = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.GaussianBlur(source, destination, new Size(0, 0), 20);
        Core.addWeighted(source, 1.5, destination, -0.5, 0, destination);

        Imgcodecs.imwrite("image.jpg", destination);
    }

    public void applyGaussianFilter(File file) throws Exception {
        Mat source = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.GaussianBlur(source, destination, new Size(45, 45), 0);

        Imgcodecs.imwrite("image.jpg", destination);
    }

    public void imageInfo(File file) throws Exception {

        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        int count = 0;

        System.out.println("Height: " + height + "\n"
                + "Width: " + width + "\n"
                + "Total pixels: " + (height * width));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                count++;
                Color c = new Color(image.getRGB(j, i));
                System.out.println("No: " + count
                        + "  Red: " + c.getRed()
                        + "  Green: " + c.getGreen()
                        + "  Blue: " + c.getBlue());
            }
        }

    }

}
