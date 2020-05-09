import java.util.Arrays;

public class Parameters {
    static int MAXGENERATIONS = 7;
    static int MAXBRANCHES = 5000;
    static int MAXTOTALSEGMENTS = 20000;

    static float[] BRANCHLEANNESS =             {10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};
    static float[] SEGMENTLENGTH =              {15.0f, 12.0f, 10.0f, 9.0f, 8.0f, 7.0f, 6.0f, 5.0f};
    static float[] SEGMENTLENGTHRANDOMNESS =    {2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f};
    static float[] WAVINESS =                   {1.0f, 10.0f, 10.0f, 10.0f, 3.0f, 3.0f, 3.0f, 3.0f};
    static float[] CHILDANGLES =                {60.0f, 55.0f, 50.0f, 45.0f, 45.0f, 45.0f, 45.0f, 45.0f};
    static float[] CHILDANGLESRANDOMNESS =      {3.0f, 6.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    static double[] WAVINESSRANDOMNESS =         {1.0f, 30.0f, 18.0f, 16.0f, 16.0f, 16.0f, 16.0f, 16.0f};

    static int[] PHYLLOTAXIS =      {5, 5, 5, 3, 3, 3, 3, 3};
    static int[] CHILDDENSITY =     {3, 3, 2, 2, 2, 2, 2, 2};
    static int[] FIRSTCHILDOFFSET = {15, 5, 4, 3, 3, 2, 2, 2};
    static int[] MAXBRANCHLENGTH =  {1400, 300, 100, 50, 30, 20, 10, 10};
    static int[] MAXSEGMENTS =      {40, 35, 30, 25, 20, 20, 20, 20};

    static int treeColor = 0x00FF0000;
//    static int fogColor = 0x00000000;


    @Override
    public String toString() {
        String parametersString = "PARAMETERS:";
        parametersString += "\n MAX GENERATIONS:    " + MAXGENERATIONS;
        parametersString += "\n CHILD DENSITY:      " + Arrays.toString(CHILDDENSITY);
        parametersString += "\n FIRST CHILD OFFSET: " + Arrays.toString(FIRSTCHILDOFFSET);
        return parametersString;
    }

    public static void main(String[] args) {
        Parameters testParameters = new Parameters();
        System.out.println(testParameters);
    }
}
