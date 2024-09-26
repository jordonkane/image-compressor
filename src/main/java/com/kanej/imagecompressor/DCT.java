package com.kanej.imagecompressor;

public class DCT {

    public static double[][] applyDCT(double[][] block) {
        int N = 8;
        double[][] dctBlock = new double[N][N];

        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                double sum = 0;
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        sum += block[x][y] * Math.cos(((2 * x + 1) * u * Math.PI) / 16) * Math.cos(((2 * y + 1) * v * Math.PI) / 16);
                    }
                }
                dctBlock[u][v] = sum * alpha(u) * alpha(v) / 4.0;
            }
        }
        return dctBlock;
    }

    private static double alpha(int x) {
        return (x == 0) ? (1 / Math.sqrt(2)) : 1;
    }
}
