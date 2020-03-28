import java.util.Arrays;

public class Parameters {
    static int MAXGENERATIONS = 7;
    static int MAXBRANCHES = 15000;
    static int MAXTOTALSEGMENTS = 100000;

    static float[] BRANCHLEANNESS = {10.0f, 15.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f};
    static float[] SEGMENTLENGTH =    {10.0f, 7.0f, 5.0f, 3.0f, 2.0f, 1.0f, 1.0f, 1.0f};


    static int[] CHILDDENSITY =     {3, 3, 2, 2, 2, 2, 2, 2};
    static int[] FIRSTCHILDOFFSET = {20, 5, 5, 5, 5, 5, 5, 5};
    static int[] MAXBRANCHLENGTH =  {300, 60, 30, 20, 10, 10, 10, 10};
    static int[] MAXSEGMENTS =      {30, 35, 30, 25, 20, 20, 20, 20};
    static int[] CHILDANGLES =      {30, 30, 30, 30, 30 ,30, 30, 30};


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
