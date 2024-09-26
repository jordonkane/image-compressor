# ImageCompressor - JPEG Image Compression Library 🖼️
**ImageCompressor** 🖼️ is a Java library that allows users to compress images using the **JPEG** compression algorithm, including color space conversion, Discrete Cosine Transform (DCT), quantization, and Huffman encoding.

## Features
- **RGB to YCbCr Color Conversion**: Converts image colors from RGB to YCbCr, separating brightness and color information.
- **Discrete Cosine Transform (DCT)**: Converts 8x8 blocks of image data into frequency components.
- **Quantization**: Reduces the precision of DCT coefficients for efficient compression.
- **Huffman Encoding**: Compresses quantized data for reduced file size.
- **Support for Multiple Image Formats**: Supports PNG, BMP, and other input formats, converting them to JPEG.

## How It Works
The **ImageCompressor** processes images step-by-step, applying several key transformations and encoding techniques to achieve JPEG compression. This involves converting images to the YCbCr color space, performing the **Discrete Cosine Transform (DCT)** on 8x8 blocks of image data, **quantizing** the coefficients to reduce precision, and applying **Huffman encoding** to the resulting data.

#### Key Functionalities:
- **Color Space Conversion**: Converts RGB images to YCbCr, separating brightness from color information.
- **Discrete Cosine Transform**: Transforms pixel intensity values into frequency domain data.
- **Quantization**: Reduces DCT coefficients' precision, discarding insignificant high-frequency components.
- **Huffman Encoding**: Encodes quantized coefficients using Huffman encoding for compression.
  
## Prerequisites
1. **Java 11 or later** installed.
2. A basic understanding of image compression algorithms (for contributing).

## Installation
1. Clone this repository to your local machine:
   ```
   git clone https://github.com/jordonkane/ImageCompressor.git
   ```
2. Navigate into the project directory:
   ```
   cd ImageCompressor
   ```
3. Build the project using Maven:
   ```
   mvn clean install
   ```

## Usage
Once the project is built, you can use the `JpegCompressor` class to compress any image into a JPEG file.

```
import com.kanej.imagecompressor.JpegCompressor;

public class Main {
    public static void main(String[] args) throws Exception {
        JpegCompressor compressor = new JpegCompressor();
        compressor.compress("input_image.png", "output_image.jpg");
    }
}
```
- **Input Image**: Specify the path to the image you want to compress (e.g., PNG or BMP format).
- **Output Image**: Specify the path where the compressed JPEG image will be saved.

## Compression Steps
1. **Load Image**: The input image is loaded from the file system.
2. **Color Space Conversion**: The image is converted from RGB to YCbCr.
3. **Block Division**: The image is divided into 8x8 blocks for further processing.
4. **DCT**: The Discrete Cosine Transform is applied to each 8x8 block.
5. **Quantization**: The resulting DCT coefficients are quantized using a standard quantization table.
6. **Huffman Encoding**: The quantized data is compressed using Huffman encoding.
7. **Save Output**: The compressed image data is saved as a JPEG file.

## How to Contribute
Contributions are welcome! If you would like to contribute to ImageCompressor, feel free to fork the repository and submit a pull request. Below is the process to follow:

1. Fork the repository.
2. Create a new feature branch.
3. Implement your changes.
4. Submit a pull request.

### Future Features:
- Support for more image formats (e.g., GIF).
- Implement lossy compression optimization to improve file size while maintaining quality.
- Add more configurable compression options (e.g., different quality levels).

### Known Issues
- Current version does not fully implement the saving of compressed data in JPEG format (this will be improved in future versions).
- Not optimized for very large image sizes—may require performance tuning for large datasets.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Credits
Developed by Jordon Kane
