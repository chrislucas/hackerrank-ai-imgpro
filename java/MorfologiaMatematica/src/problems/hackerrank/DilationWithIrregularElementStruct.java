package problems.hackerrank;

/**
 * https://www.hackerrank.com/challenges/dip-morphological-operations-dilation-with-an-irregular-structuring-element/problem
 * */

public class DilationWithIrregularElementStruct {
    public static int [][] addPadding(int [][] image) {
        int [][] copy = new int[image.length+2][image[0].length+2];
        for(int i=0; i<image.length; i++) {
            for (int j = 0; j <image[i].length ; j++) {
                copy[i+1][j+1] = image[i][j];
            }
        }
        return copy;
    }

    public static int [][] dilation(int [][] image, int e [][], int ie, int je) {
        int imagePad [][] = addPadding(image);
        int copy [][] = addPadding(image);
        int limitI = copy.length;
        int limitJ = copy[0].length;
        int limitK = e.length;
        int limitL = e[0].length;
        for (int i = 0; i < limitI - limitK ; i++) {
            for (int j = 0; j < limitJ - limitL ; j++) {
                for (int k = 0; k < limitK; k++) {
                    for (int l = 0; l < limitL ; l++) {
                        if(imagePad[i+k][j+l] == 1)
                            copy[i+ie][j+je] = 1;
                    }
                }
            }
        }
        return copy;
    }

    public static void print(int [][] matrix, int padding) {
        for (int i = padding; i < matrix.length-padding; i++) {
            for (int j = padding; j < matrix[i].length-padding; j++) {
                System.out.printf("%d", matrix[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int binImage [][] = new int[][] {{0,0,0,0},{0,1,1,0},{0,0,0,0}};
        int e [][] = new int[][] {{1,0},{1,1}} ;
        print(dilation(binImage, e, 1, 0), 1);
    }
}
