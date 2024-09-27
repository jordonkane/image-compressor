package com.kanej.imagecompressor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class JpegCompressor {

    public void compress(String inputPath, String outputPath) throws Exception {
        // load the input image
        BufferedImage image = ImageIO.read(new File(inputPath));

        // convert to YCbCr color space
        double[][][] yCbCr = ColorSpaceConverter.convertRGBtoYCbCr(image);

        // huffman encoder will be used for encoding each block
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        StringBuilder jpegData = new StringBuilder(); // hold encoded jpeg data in binary format

        // compress each 8x8 block
        for (int channel = 0; channel < 3; channel++) { // process Y, Cb, Cr channels
            for (int y = 0; y < yCbCr[channel].length; y += 8) {
                for (int x = 0; x < yCbCr[channel][0].length; x += 8) {
                    // extract 8x8 block
                    double[][] block = ImageUtils.extractBlock(yCbCr[channel], x, y);

                    // apply dct on the block
                    double[][] dctBlock = DCT.applyDCT(block);

                    // quantize the dct coefficients
                    double[][] quantizedBlock = Quantizer.quantize(dctBlock);

                    // create frequency map from quantized block
                    Map<Character, Integer> frequencyMap = createFrequencyMap(quantizedBlock);

                    // generate huffman codes based on frequency map
                    Map<Character, String> huffmanCodes = huffmanEncoder.generateCodes(frequencyMap);

                    // encode the block using huffman coding
                    String encodedBlock = huffmanEncoder.encodeBlock(quantizedBlock, huffmanCodes);
                    jpegData.append(encodedBlock);  // append encoded block to overall jpeg data
                }
            }
        }

        // write the jpeg header, encoded data, and the jpeg footer to the file
        saveCompressedImage(jpegData.toString(), outputPath);
    }

    // convert quantized dct block to frequency map for Huffman encoding.
    private Map<Character, Integer> createFrequencyMap(double[][] quantizedBlock) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (double[] row : quantizedBlock) {
            for (double value : row) {
                char symbol = (char) value; // conversion from double to char
                frequencyMap.put(symbol, frequencyMap.getOrDefault(symbol, 0) + 1);
            }
        }
        return frequencyMap;
    }

    // save the jpeg file with minimal headers and the compressed data.
    private void saveCompressedImage(String jpegData, String outputPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            // write the start of image marker
            fos.write(new byte[]{(byte) 0xFF, (byte) 0xD8});  // start of image marker

            // write the encoded jpeg data
            fos.write(jpegData.getBytes());

            // write the end of image marker
            fos.write(new byte[]{(byte) 0xFF, (byte) 0xD9});  // end of image marker
        }
    }
}
