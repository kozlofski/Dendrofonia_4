import processing.core.PApplet;
import processing.core.PMatrix3D;

public class Segment {
    int branchNumber;
    int branchGeneration;
    int segmentNumber;
    int childBranch = -1;       // unique id of child branch
    int absSegmentNumber;

    float length;               // in pixels
    float diameter = 1;             // in pixels

    private String soundFile = "";
    int soundFilePosition;      // in samples

    boolean hasLeave;
    Leave leave;
    private PMatrix3D matrix;
    // FIXME: 28.03.2020

    Tree tree;
    Branch currentBranch;

    public Segment(Tree tree, Branch currentBranch) {
        this.branchNumber = currentBranch.branchNumber;
        this.branchGeneration = currentBranch.branchGeneration;
        this.segmentNumber = currentBranch.segments.size() + 1;
        this.absSegmentNumber = tree.getSegmentsGrown();
        this.tree = tree;
        this.currentBranch = currentBranch;
        this.length = Parameters.SEGMENTLENGTH[branchGeneration];
        this.tree.incrementSegmentsGrown();
        this.matrix = new PMatrix3D();
        this.matrix.set(currentBranch.getGrowingMatrix());
        this.matrix.rotateX((float) Math.toRadians(15.0f));
        this.matrix.rotateZ((float) Math.toRadians(3.0f));
        this.childBranch = makeChild();
        // FIXME: 28.03.2020 pofalowanie gałęzi i filotaksja tutaj
        currentBranch.updateGrowingMatrix(this.matrix, this.length);
//        this.matrix.translate(this.length, 0, 0);
    }

    public int getChildBranch() {
        return childBranch;
    }

    public float getDiameter() {
        return diameter;
    }

    public float getLength() {
        return length;
    }

    public PMatrix3D getMatrix() {
        return this.matrix;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public void setSoundFile(String soundFile, int soundFilePosition) {
        this.soundFile = soundFile;
        this.soundFilePosition = soundFilePosition;
    }

    public void setSoundFilePosition(int soundFilePosition) {
        this.soundFilePosition = soundFilePosition;
    }

    public void drawSegment() {
        Main.p.pushMatrix();
        Main.p.applyMatrix(this.matrix);
//        weight = segmentDiameter''// * stokeWievModifier;
//        //if (weight < 1.5) smooth();
//        //else noSmooth();
        Main.p.strokeWeight(this.diameter);
//        Main.p.strokeWeight(50);
        Main.p.stroke(255 - absSegmentNumber, 120 + absSegmentNumber, 25 + absSegmentNumber);
//        //stroke(50, 0, 0);//128 - 1.25 * matrix.m23);
//        //    stroke(70);
//
        Main.p.line(0, 0, 0, 100, 0, 0);
        //if (weight > 10) // dla wypełnienia szpar w najgrubszych gałęziach rysuje drugą linię, trochę przesuniętą
//        //  line(5, 0, 0, segmentLength, 0, 0);
//
//        if (hasLeave == true)
//        {
//            drawLeave();
//        }//
        Main.p.popMatrix();
    }

    private void setHasLeave(boolean hasLeave) { // FIXME
        this.hasLeave = hasLeave;
        Leave leave = new Leave();
    }

    private int makeChild() {
        if (childPossible()) {
            this.tree.branches.add(new Branch(this.tree, this.currentBranch, this));
            return tree.getBranchesGrown();
        } else return -1;
    }

    private boolean childPossible() {
        if ((segmentNumber % Parameters.CHILDDENSITY[branchGeneration]) != 0 ||
                (segmentNumber < Parameters.FIRSTCHILDOFFSET[branchGeneration]) ||
                (branchGeneration == Parameters.MAXGENERATIONS))
            return false;
        else return true;
    }

    @Override
    public String toString() {
        String segmentString = "\n";
        for (int i = 0; i < this.branchGeneration; i++)
            segmentString += "  ";

        // sformatować wyświetlanie nr segmentu FIXME
        segmentString += "  -seg: " + segmentNumber +
                " from br: " + branchNumber + " length: " + length +
                " diameter: " + diameter/* +
                " sound: " + soundFile + " sample: " + soundFilePosition*/;

        segmentString += " " + matrix.m00 + " " + matrix.m01 + " " + matrix.m02 + " " + matrix.m03;

        if (childBranch != -1)
            segmentString += tree.branches.get(childBranch).toString();
        return segmentString;
    }

    public static void main(String[] args) {
        Tree testTree = new Tree();
        Branch testBranch = new Branch(testTree, null, null);
        Segment testSegment = new Segment(testTree, testBranch);
        System.out.println(testTree);
    }
}
