package problems.hackerrank;


/**
 * https://www.hackerrank.com/challenges/dip-morphological-operations-erosion-with-a-structuring-element/problem
 * */
public class ErosionWithElementStruct {

    public static int [][] addPadding(int [][] image) {
        int [][] copy = new int[image.length+2][image[0].length+2];
        for(int i=0; i<image.length; i++) {
            for (int j = 0; j <image[i].length ; j++) {
                copy[i+1][j+1] = image[i][j];
            }
        }
        return copy;
    }

    public static int [][] erosion(int [][] binImage, int e [][], int ie, int je) {
        int [][] binImagePadding = addPadding(binImage);
        int [][] copy = addPadding(binImage);
        print(binImagePadding, 1);
        int limitI = copy.length;
        int limitJ = copy[0].length;
        int limitK = e.length;
        int limitL = e[0].length;
        for (int i = 0; i < limitI - limitK ; i++) {
            for (int j = 0; j < limitJ - limitL; j++) {
                for (int k = 0; k < limitK ; k++) {
                    for (int l = 0; l < limitL; l++) {
                        if(binImagePadding[i+k][j+l] == 0)
                            copy[i+ie][j+je] = 0;
                    }
                }
            }
        }
        print(copy, 1);
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

    public static int count(int [][] matrix) {
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length; j++) {
                if(matrix[i][j] == 1)
                    counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        int binImage [][] = new int[][] {
             {0,0,1,1,0}
            ,{0,0,1,1,0}
            ,{0,0,1,1,0}
            ,{1,1,1,1,1}
        };
        int e [][] = new int[][] {{1,1,1}} ;
        System.out.println(count(erosion(binImage, e, 1, 0)));
    }
}
