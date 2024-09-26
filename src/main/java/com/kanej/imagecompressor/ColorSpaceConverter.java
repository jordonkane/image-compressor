package com.kanej.imagecompressor;

import java.awt.image.BufferedImage;

public class ColorSpaceConverter {

    public static double[][][] convertRGBtoYCbCr(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double[][][] yCbCr = new double[3][height][width];  // 3 channels: Y, Cb, Cr

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb) & 0xff;

                // Convert RGB to YCbCr
                double Y  =  0.299 * r + 0.587 * g + 0.114 * b;
                double Cb = 128 - 0.168736 * r - 0.331264 * g + 0.5 * b;
                double Cr = 128 + 0.5 * r - 0.418688 * g - 0.081312 * b;

                yCbCr[0][y][x] = Y;
                yCbCr[1][y][x] = Cb;
                yCbCr[2][y][x] = Cr;
            }
        }

        return yCbCr;
    }
}
