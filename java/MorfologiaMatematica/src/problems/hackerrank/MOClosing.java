package problems.hackerrank;
/**
 * https://www.hackerrank.com/challenges/dip-morphological-operations-closing/problem
 * DONE
 * */
public class MOClosing {

    public static final int [][] BIN_IMAGE = {
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

    public static final int [][] E = {
         {1,1,1}
        ,{1,1,1}
        ,{1,1,1}
    };

    public static int [][] addPadding() {
        int [][] copy = new int[BIN_IMAGE.length+2][BIN_IMAGE[0].length+2];
        for(int i=0; i<BIN_IMAGE.length; i++) {
            for (int j = 0; j <BIN_IMAGE[i].length ; j++) {
                copy[i+1][j+1] = BIN_IMAGE[i][j];
            }
        }
        return copy;
    }


    public static int [][] dilation(int matrix [][], int [][] e) {
        int [][] copy = new int[matrix.length][matrix[0].length];
        int limitI = copy.length;
        int limitJ = copy[0].length;
        int limitK = e.length;
        int limitL = e[0].length;
        for (int i = 0; i < limitI-limitK; i++) {
            for (int j = 0; j < limitJ-limitL; j++) {
                for (int k = 0; k < limitK ; k++) {
                    for (int l = 0; l < limitL ; l++) {
                        if(matrix[i+k][j+l] == 1)
                            copy[i+e.length/2][j+e[0].length/2] = 1;
                    }
                }
            }
        }
        return copy;
    }

    public static int [][] erosion(int matrix[][], int [][] e) {
        int [][] copy = new int[matrix.length][matrix[0].length];
        for(int i=0; i<copy.length; i++)
            System.arraycopy(matrix[i], 0, copy[i], 0, copy[i].length);
        int limitI = copy.length;
        int limitJ = copy[0].length;
        int limitK = e.length;
        int limitL = e[0].length;
        for (int i = 0; i < limitI-limitK; i++) {
            for (int j = 0; j < limitJ-limitL; j++) {
                for (int k = 0; k < limitK; k++) {
                    for (int l = 0; l < limitL ; l++) {
                        if(matrix[i+k][j+l] == 0)
                            copy[i+e.length/2][j+e[0].length/2] = 0;
                    }
                }
            }
        }
        return copy;
    }

    public static int solver() {
        int matrix [][] = erosion(dilation(addPadding(), E), E);
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
        System.out.println(solver());
    }

}
