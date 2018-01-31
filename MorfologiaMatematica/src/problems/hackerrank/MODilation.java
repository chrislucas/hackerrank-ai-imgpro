package problems.hackerrank;

/**
 * https://www.hackerrank.com/challenges/dip-morphological-operations-dilation/problem
 * DONE
 * */

public class MODilation {

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

/*
    // https://en.wikipedia.org/wiki/Dilation_(morphology)
    public static final int [][] BIN_IMAGE = {
         {0,0,0,0,0,0,0,0,0,0,0}
        ,{0,1,1,1,1,0,0,1,1,1,0}
        ,{0,1,1,1,1,0,0,1,1,1,0}
        ,{0,1,1,1,1,1,1,1,1,1,0}
        ,{0,1,1,1,1,1,1,1,1,1,0}
        ,{0,1,1,0,0,0,1,1,1,1,0}
        ,{0,1,1,0,0,0,1,1,1,1,0}
        ,{0,1,1,0,0,0,1,1,1,1,0}
        ,{0,1,1,1,1,1,1,1,0,0,0}
        ,{0,1,1,1,1,1,1,1,0,0,0}
        ,{0,0,0,0,0,0,0,0,0,0,0}
    };
*/
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


    public static int dilatation() {
        int [][] copyPadding = addPadding();
        int [][] copy = new int[copyPadding.length][copyPadding[0].length];
        for(int i=0; i<copy.length; i++)
            System.arraycopy(copyPadding[i], 0, copy[i], 0, copy[i].length);

        int limitI = copy.length;
        int limitJ = copy[0].length;
        int limitK = E.length;
        int limitL = E[0].length;
        for (int i = 0; i<limitI-limitK; i++) {
            for (int j = 0; j<limitJ-limitL; j++) {
                for (int k = 0; k < limitK; k++) {
                    for (int l = 0; l < limitL ; l++) {
                        if(copyPadding[i+k][j+l] == 1) {
                            copy[i+E.length/2][j+E[0].length/2] = 1;
                        }
                    }
                }
            }
        }

        int counter = 0;
        for (int i = 0; i < copy.length ; i++) {
            for (int j = 0; j < copy[i].length ; j++) {
                System.out.printf("%d", copy[i][j]);
                if(copy[i][j] == 1)
                    counter++;
            }
            System.out.println("");
        }
        return counter;
    }


    public static void main(String[] args) {
        System.out.println(dilatation());
    }
}
