import com.kanej.imagecompressor.ColorSpaceConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorSpaceConverterTest {

    @Test
    public void testRGBtoYCbCrConversion() {
        // create a 1x1 pixel BufferedImage for testing
        BufferedImage testImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        // set the color of the single pixel to pure red (255, 0, 0)
        testImage.setRGB(0, 0, Color.RED.getRGB());

        // convert the image from RGB to YCbCr color space
        double[][][] yCbCr = ColorSpaceConverter.convertRGBtoYCbCr(testImage);

        // Y should be close to 76 (luminance for red in YCbCr)
        assertEquals(76, yCbCr[0][0][0], 1.0, "Y component for red pixel should be close to 76");

        // Cb should be close to 84 (Cb value for red)
        assertEquals(84, yCbCr[1][0][0], 1.0, "Cb component for red pixel should be close to 84");

        // Cr should be close to 255 (Cr value for red)
        assertEquals(255, yCbCr[2][0][0], 1.0, "Cr component for red pixel should be close to 255");
    }
}
