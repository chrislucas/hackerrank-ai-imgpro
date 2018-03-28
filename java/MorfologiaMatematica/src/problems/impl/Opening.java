package problems.impl;

import impl.BufferedImageUtils;
import problems.utils.ElementStruct;
import problems.utils.Struct;

import java.awt.image.BufferedImage;

public class Opening {

    private static int [][] opening(int [][] pixels, Struct struct) {
        int [][] copy = new int[pixels.length][pixels[0].length];
        for(int i=0; i<pixels.length; i++)
            System.arraycopy(pixels[i], 0, copy[i], 0, copy[i].length);
        return Dilation.dilation(Erosion.erosion(copy, struct), struct);
    }

    private static void test() {
        BufferedImage image = BufferedImageUtils.openImage("raw/img-samples/wp6.png");
        System.out.printf("Dimensao da imagem: W: %d H %d.\n", image.getWidth(), image.getHeight());
        int [][] matrix = BufferedImageUtils.toBinaryMatrix(image);
        boolean created = BufferedImageUtils.createImage(
                BufferedImageUtils.toBinaryBufferedImage(matrix)
                , "raw/output/output_bin_opening_1.png", "png");
        System.out.println(created ? "Criado" : "Não Criado");

        int result [][] = opening(matrix, ElementStruct.e6);
        created = BufferedImageUtils.createImage(
                BufferedImageUtils.toBinaryBufferedImage(result)
                , "raw/output/output_bin_opening_2.png", "png");
        System.out.println(created ? "Criado" : "Não Criado");
    }

    public static void main(String[] args) {
        test();
    }
}
