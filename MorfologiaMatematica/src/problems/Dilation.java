package problems;


public class Dilation {

    public static class Point2d {
        int x, y;
        public Point2d(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Element {
        Point2d main;
        Point2d [] points;
        public Element(Point2d main, Point2d [] points) {
            this.main = main;
            this.points = points;
        }
    }



    public static int [][] dilation(int [][] píxels, Element element) {
        /**
         * TODO fazer uma copia da imagem
         * */
        int [][] imageCopy = new int[píxels.length][píxels[0].length];
        for(int i=0; i<píxels.length; i++)
            System.arraycopy(píxels[i], 0, imageCopy[i], 0, imageCopy[i].length);

        return imageCopy;
    }

    public static void main(String[] args) {
        int [][] matrix = new int[][] {
             {1,0,0,1,0,1,1}
            ,{1,1,0,1,1,1,1}
            ,{1,1,1,0,0,0,0}
        };
        dilation(matrix, null);
    }


}
