package problems.impl;


import impl.BufferedImageUtils;
import problems.utils.ElementStruct;
import problems.utils.Struct;

import java.awt.image.BufferedImage;

public class Dilation {

    public static int [][] dilation(int [][] pixels, Struct struct) {
        /**
         * TODO fazer uma copia da imagem
         * */
        int [][] copy = new int[pixels.length][pixels[0].length];
        for(int i=0; i<pixels.length; i++)
            System.arraycopy(pixels[i], 0, copy[i], 0, copy[i].length);
        int limitI = pixels.length;
        int limitJ = pixels[0].length;
        int limitK = struct.matrix.length;
        int limitL = struct.matrix[0].length;
        // dispensar as bordar da matriz
        int indI = struct.i;
        int indJ = struct.j;
        for (int i = 0; i < limitI-limitK; i++) {
            for (int j = 0; j < limitJ-limitL; j++) {
                // loop sobre o elemento estruturante
                for (int k = 0; k < limitK ; k++) {
                    for (int l = 0; l < limitL ; l++) {
                        if(struct.matrix[k][l] == 1) {
                            int ik = i+k;
                            int jl = j+l;
                            // 255 pixel do objeto
                            if(pixels[ik][jl] == 0) {
                                int ii = i + indI;
                                int jj = j + indJ;
                                copy[ii][jj] = 0;
                            }
                        }
                    }
                }
            }
        }
        return copy;
    }

    private static void test1() {
        BufferedImage image = BufferedImageUtils.openImage("raw/img-samples/wp5.png");
        System.out.printf("Dimensao da imagem: W: %d H %d.\n", image.getWidth(), image.getHeight());
        int [][] matrix = BufferedImageUtils.toBinaryMatrix(image);
        boolean created = BufferedImageUtils.createImage(
                BufferedImageUtils.toBinaryBufferedImage(matrix)
                , "raw/output/output_bin_dilatation_1.png", "png");
        System.out.println(created ? "Criado" : "Não Criado");

        int result [][] = Dilation.dilation(matrix, ElementStruct.e6);
        created = BufferedImageUtils.createImage(
                BufferedImageUtils.toBinaryBufferedImage(result)
                , "raw/output/output_bin_dilatation_2.png", "png");
        System.out.println(created ? "Criado" : "Não Criado");

    }

    public static void main(String[] args) {
        test1();
    }


}
