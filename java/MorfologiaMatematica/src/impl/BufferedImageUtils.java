package impl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageUtils {

    public static BufferedImage openImage(String pathname) {
        BufferedImage bufferedImage =  null;
        try {
            bufferedImage = ImageIO.read(new File(pathname));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bufferedImage;
    }

    public static boolean createImage(BufferedImage bufferedImage, String pathFile, String format) {
        File output = new File(pathFile);
        File folder = new File(output.getParent());
        if( ! folder.exists() ) {
            if( ! folder.mkdir() ) {
                return false;
            }
        }
        try {
            return ImageIO.write(bufferedImage, format, output);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return false;
    }

    /**
     * Conveosao, pxiel de fundo == 255 pixel de objeto == 0
     * */
    public static int [][] toBinaryMatrix(BufferedImage bufferedImage) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int binaryImage [][] = new int[h][w];
        for (int i = 0; i <h ; i++) {
            for (int j = 0; j <w ; j++) {
                int c = bufferedImage.getRGB(j, i);
                Color color = new Color(c);
                int m = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                binaryImage[i][j] = m > 127 ? 255 : 0;
            }
        }
        return binaryImage;
    }

    public static BufferedImage toBinaryBufferedImage(int [][] matrix) {
        int w = matrix[0].length, h = matrix.length;
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < h ; i++) {
            for (int j = 0; j < w ; j++) {
                int c = matrix[i][j];
                Color color = new Color(c, c, c);
                bufferedImage.setRGB(j, i, color.getRGB());
            }
        }
        return bufferedImage;
    }
}
