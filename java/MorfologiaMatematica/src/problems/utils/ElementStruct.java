package problems.utils;

public class ElementStruct {

    public static final Struct e = new Struct(0,0
        , new int[][] {{1,1},{1,1}}
    );

    public static final Struct  e1 = new Struct(1,0,
        new int[][] {{1},{1},{1},{1}}
    );

    public static final Struct e2 = new Struct(0,2, new int[][] {{1,1,1,1}});

    public static final Struct e3 = new Struct(1,1
        , new int[][] {
            {1,1,1},{1,1,1},{1,1,1}
        }
    );


    public static final Struct e4 = new Struct(1,1
            , new int[][] {
            {0,1,0},{1,1,1},{0,1,0}
        }
    );

    public static final Struct e5 = new Struct(0,5
        , new int[][] {
            {1,0,1}
        }
    );

    public static final Struct e6 = new Struct(7,10
        , new int[][] {
             {0,1,0,0,0,0,0,0,0,0,0,0,0,0}
            ,{1,1,1,0,1,1,1,1,1,0,0,0,0,0}
            ,{0,1,0,0,0,0,1,1,1,1,1,1,1,1}
            ,{1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            ,{1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            ,{1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            ,{1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            ,{1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        }
    );

    public static final Struct e7 = new Struct(1,1
        , new int[][] {
             {1}
            ,{1,1}
            ,{1,0,1}
        }
    );

}
