package com.br.xplore;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * http://www.sunshine2k.de/coding/java/Houghtransformation/HoughTransform.html#step1
 * */

public class HoughTransformation {

    private static final int [][] SOBEL_X = {
         {1,0,-1}
        ,{2,0,-2}
        ,{1,0,-1}
    };

    private static final int [][] SOBEL_Y = {
         {1,2,1}
        ,{0,0,0}
        ,{-1,-2,-1}
    };


    private static final int MAX_THETA = 180;

    private static int [][] houghSpace;
    private static double [] cacheCos, cacheSin;
    private static int centerW, centerH, houghHeight, points = 0;


    private static BufferedImage readImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    /**
     *  Luminance (relative)
     *  https://en.wikipedia.org/wiki/Relative_luminance
     * */
    private static double toGrayScale(int r, int g, int b) {
        return 0.2126 * r + 0.7152 * g + 0.0722 * b;
    }


    private static double luminance(int r, int g, int b) {
        return .299 * r + .587 * g + .114 * b;
    }

    /**
     * HSP Color Model
     * http://alienryderflex.com/hsp.html
     * Math.sqrt(.299 * (r*r) + .587 * (g*g) + .114 * (b*b))
     * */
    private static double sqrtLuminance(int r, int g, int b) {
        return Math.sqrt(0.2126 * (r*r) + 0.7152 * (g*g) + 0.0722 * (b*b));
    }

    private static BufferedImage copyInGrayScale(BufferedImage bufferedImage) {
        int h = bufferedImage.getHeight(), w = bufferedImage.getWidth();
        BufferedImage copy = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < h ; i++) {
            for (int j = 0; j < w; j++) {
                int c = bufferedImage.getRGB(j, i);
                Color color = new Color(c);
                /*
                int mid = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                mid = mid > 255 ? 255 : mid < 0 ? 0 : mid;
                */
                double gray = sqrtLuminance(color.getRed(), color.getGreen(), color.getBlue());
                int mid =(int) Math.round(gray);
                copy.setRGB(j, i, new Color(mid, mid, mid).getRGB());
            }
        }
        return copy;
    }

    private static int [][] createRBGMatrix(String path) throws IOException {
        BufferedImage bufferedImage = readImage(path);
        int h = bufferedImage.getHeight(), w = bufferedImage.getWidth();
        int [][] matrix = new int[h][w];
        for (int i = 0; i < h ; i++) {
            for (int j = 0; j < w; j++) {
                int c = bufferedImage.getRGB(j, i);
                Color color = new Color(c);
                matrix[i][j] = color.getRGB();
            }
        }
        return matrix;
    }

    private static int [][] createIntegerMatrixRGB(BufferedImage bufferedImage) {
        int h = bufferedImage.getHeight(), w = bufferedImage.getWidth();
        int [][] matrix = new int[h][w];
        for (int i = 0; i < h ; i++) {
            for (int j = 0; j < w; j++) {
                int c = bufferedImage.getRGB(j, i);
                Color color = new Color(c);
                double gray = toGrayScale(color.getRed(), color.getGreen(), color.getBlue());;
                matrix[i][j] = (int) Math.round(gray);
            }
        }
        return matrix;
    }

    private static BufferedImage createBufferedImage(int [][] matrix, int type) {
        int h = matrix.length, w = matrix[0].length;
        BufferedImage bufferedImage = new BufferedImage(w, h, type);
        for (int i = 0; i < h ; i++) {
            for (int j = 0; j < w ; j++) {
                int c = matrix[i][j];
                Color color = new Color(c, c, c);
                bufferedImage.setRGB(j, i, color.getRGB());
            }
        }
        return bufferedImage;
    }

    private static boolean writeImage(BufferedImage buffer, String pathfile, String format) {
        File outputFile = new File(pathfile);
        String path = outputFile.getParent();
        File folder = new File(path);
        boolean create = false;
        if( ! folder.exists() ) {
            System.out.printf("%s", folder.mkdir() ? "Pasta Criada" : "Não foi possível criar a pasta");
        }
        try {
            create = ImageIO.write(buffer, format, outputFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return create;
    }

    private static void copyMatrix(int [][] matrix, int [][] copy, int startFill, int destPos, int lines) {
        for (int i = 0, j = startFill; i <lines; i++, j++) {
            System.arraycopy(matrix[i], 0, copy[j], destPos, lines);
        }
    }

    private static int mean(int [][] mat) {
        int li = mat.length, lj = mat[0].length;
        int acc = 0;
        for(int i=0; i<li; i++)
            for(int j=0; j<lj; j++)
                acc += mat[i][j];
        return acc / (li * lj);
    }

    private static int [][] applyMask(int [][] mask, int [][] matrixImage) {
        // adicionando bordas a imagem
        int h = matrixImage.length + 2, w = matrixImage[0].length + 2;
        int hh = mask.length, ww = mask[0].length;
        int [][] copy = new int[h][w];
        copyMatrix(matrixImage, copy, 1,1, h-2);
        int centerH = hh / 2, centerW = ww / 2;
        for (int i = 0; i < h - 2 - hh + 1 ; i++) {
            for (int j = 0; j < w - 2 - ww + 1 ; j++) {
                int acc = 0;
                for (int k = 0; k < hh ; k++) {
                    for (int l = 0; l < ww; l++) {
                        int c = matrixImage[i+k][j+l];
                        acc += c * mask[k][l];
                    }
                }
                acc = acc < 0 ? 0 : acc > 255 ? 255 : acc;
                copy[i+centerH][j+centerW] = acc;
            }
        }
        return copy;
    }


    private static void addPoints(int [][] matrix) {
        int h = matrix.length, w = matrix[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w ; j++) {
                // Acma de 127 eh um pixel claro
                if(matrix[i][j] > 127) {
                    addPoint(i, j);
                }
            }
        }
    }

    private static void addPoint(int x, int y) {
        for (int i = 0; i < MAX_THETA ; i++) {
            int r = (int) ((x - centerW) * cacheCos[i] + (y - centerH) * cacheSin[i]);
            r += houghHeight;
            if (r < 0 || r >= houghHeight*2)
                continue;
            houghSpace[i][r]++;
        }
    }

    private static void initialize(int h, int w) {
        houghHeight = Math.round((float)Math.sqrt(2)*Math.max(h, w)/2);
        houghSpace = new int[MAX_THETA][2*houghHeight];
        cacheCos = new double[MAX_THETA];
        cacheSin = new double[MAX_THETA];
        for (int i = 0; i < MAX_THETA ; i++) {
            cacheSin[i] = Math.sin(i * Math.PI / 180);
            cacheCos[i] = Math.cos(i * Math.PI / 180);
        }
        centerW = w/2;
        centerH = h/2;
    }

    public static void main(String[] args) {
        try {
            String [] imageNames = {"wp4.png", "wp8.jpeg", "Koala.jpg", "wp10.png", "wp11.png"};
            String path = String.format("raw/imgs/%s", imageNames[4]);
            BufferedImage imageInGrayScale = copyInGrayScale(readImage(path));
            int [][] matrix = createIntegerMatrixRGB(imageInGrayScale);
            int [][] cp = applyMask(SOBEL_X, matrix);
            cp = applyMask(SOBEL_Y, cp);
            initialize(cp.length, cp[0].length);

            addPoints(cp);


            BufferedImage result = createBufferedImage(cp, BufferedImage.TYPE_BYTE_GRAY);
            boolean flag = writeImage(result, "raw/out/out.jpg", "jpg");
            if (flag) {
                System.out.println("Imagem criada");
            }
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }

}
