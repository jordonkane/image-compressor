package com.kanej.imagecompressor;

public class ImageUtils {

    public static double[][] extractBlock(double[][] channel, int startX, int startY) {
        double[][] block = new double[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                block[i][j] = channel[startY + i][startX + j];
            }
        }
        return block;
    }
}
