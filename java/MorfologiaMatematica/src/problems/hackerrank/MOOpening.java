package problems.hackerrank;

/**
 * DONE
 * */

public class MOOpening {

    public static int [][] addPadding(int [][] image) {
        int [][] copy = new int[image.length+2][image[0].length+2];
        for(int i=0; i<image.length; i++) {
            for (int j = 0; j <image[i].length ; j++) {
                copy[i+1][j+1] = image[i][j];
            }
        }
        return copy;
    }

    public static int opening(int [][] binImage, int [][] e) {
        //print(addPadding(binImage));
        int [][] matrix = dilation(erosion(addPadding(binImage), e), e);
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length; j++) {
                if(matrix[i][j] == 1)
                    counter++;
            }
        }
        return counter;
    }

    public static int [][] dilation(int [][] binImage, int [][] e) {
        int matrix [][] = new int[binImage.length][binImage[0].length];
        for (int i = 0; i < binImage.length ; i++)
            System.arraycopy(binImage[i], 0, matrix[i], 0, matrix[i].length);
        int limitI = matrix.length;
        int limitJ = matrix[0].length;
        int limitK = e.length;
        int limitL = e[0].length;
        for (int i = 0; i < limitI - limitK; i++) {
            for (int j = 0; j < limitJ - limitL; j++) {
                for (int k = 0; k < limitK; k++) {
                    for (int l = 0; l < limitL; l++) {
                        if(binImage[i+k][j+l] == 1) {
                            matrix[i+e.length/2][j+e[0].length/2] = 1;
                        }
                    }
                }
            }
        }
        //print(matrix);
        return matrix;
    }

    public static int [][] erosion(int [][] binImage, int [][] e) {
        int matrix [][] = new int[binImage.length][binImage[0].length];
        for (int i = 0; i < binImage.length ; i++)
            System.arraycopy(binImage[i], 0, matrix[i], 0, matrix[i].length);
        int limitI = matrix.length;
        int limitJ = matrix[0].length;
        int limitK = e.length;
        int limitL = e[0].length;
        for (int i = 0; i < limitI - limitK ; i++) {
            for (int j = 0; j < limitJ - limitL; j++) {
                for (int k = 0; k < limitK; k++) {
                    for (int l = 0; l < limitL; l++) {
                        if(binImage[i+k][j+l] == 0) {
                            matrix[i+e.length/2][j+e[0].length/2] = 0;
                        }
                    }
                }
            }
        }
        //print(matrix);
        return matrix;
    }

    public static void print(int [][] matrix) {
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                System.out.printf("%d", matrix[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int [][] BIN_IMAGE = {
             {0,0,0,0,0,0,0,0,0,0}
            ,{0,1,1,1,1,1,1,1,0,0}
            ,{0,0,0,0,1,1,1,1,0,0}
            ,{0,0,0,0,1,1,1,1,0,0}
            ,{0,0,0,1,1,1,1,1,0,0}
            ,{0,0,0,0,1,1,1,1,0,0}
            ,{0,0,0,1,1,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
            ,{0,0,0,0,0,0,0,0,0,0}
        };
        int [][] E = {{1,1,1},{1,1,1},{1,1,1}};
        System.out.println(opening(BIN_IMAGE, E));
    }

}
