package problems.impl;

import impl.BufferedImageUtils;
import problems.utils.ElementStruct;
import problems.utils.Struct;

import java.awt.image.BufferedImage;

public class Erosion {

    public static int [][] erosion(int [][] pixels, Struct struct) {
        int [][] copy = new int[pixels.length][pixels[0].length];
        for(int i=0; i<pixels.length; i++)
            System.arraycopy(pixels[i], 0, copy[i], 0, copy[i].length);

        int limitI = pixels.length;
        int limitJ = pixels[0].length;
        int limitK = struct.matrix.length;
        int limitL = struct.matrix[0].length;

        for(int i=0; i<limitI-limitK; i++) {
            for (int j = 0; j < limitJ-limitL ; j++) {
                // passando o elemento estruturante sobre a imagem que se quer analizar
                // como se fosse uma MASCARA de filtragem
                for (int k = 0; k < limitK ; k++) {
                    for (int l = 0; l < limitL ; l++) {
                        if(struct.matrix[k][l] == 1) {
                            int ik = i+k;   // linha da natriz do elemento estruturante na matriz que representa a imagem
                            int jl = j+l;   // idem para coluna
                            /**
                             * Estando numa posicao ij na matriz que representa a imagem que esta sendo analizada
                             * passamos a matriz que representa o elemento estruturante por cima dela (como um filtro),
                             * Se a posicao kl na matriz estruturante sobrepoe uma posicao ij na imagem que eh um ´pixel
                             * de um objeto que nao seja o fundo, nos transformamos o pixel da imagem que esta sobreposto
                             * pela posicao central na matriz estruturante em pixel de fundo (se o pixel de fundo for branco
                             * transformamos o pixel sobreposto com o valor 255 se for preto com 0)
                             * */
                            if(pixels[ik][jl] > 0) {
                                int ii = i+struct.i;
                                int jj = j+struct.j;
                                copy[ii][jj] = 255;
                            }
                        }
                    }
                }
            }
        }
        return copy;
    }

    public static void test() {
        BufferedImage image = BufferedImageUtils.openImage("raw/img-samples/wp8.png");
        System.out.printf("Dimensao da imagem: W: %d H %d.\n", image.getWidth(), image.getHeight());
        int [][] matrix = BufferedImageUtils.toBinaryMatrix(image);
        boolean created = BufferedImageUtils.createImage(
                BufferedImageUtils.toBinaryBufferedImage(matrix)
                , "raw/output/output_bin_erosion_1.png", "png");
        System.out.println(created ? "Criado" : "Não Criado");


        int result [][] = erosion(matrix, ElementStruct.e6);
        created = BufferedImageUtils.createImage(
                BufferedImageUtils.toBinaryBufferedImage(result)
                , "raw/output/output_bin_erosion_2.png", "png");
        System.out.println(created ? "Criado" : "Não Criado");
    }

    public static void main(String[] args) {
        test();
    }
}
