//import processing.core.PApplet;
import processing.core.PMatrix3D;

public class Segment {
    private int branchNumber;
    private int branchGeneration;
    private int segmentNumber;
    private int childBranch = -1;       // unique id of child branch
    private int absSegmentNumber;

    private float length;               // in pixels
    private float diameter = 1.0f;             // in pixels

    Sound sound;

    boolean hasLeave;
    Leave leave;
    private PMatrix3D matrix;
    // FIXME: 28.03.2020

    Tree tree;
    Branch currentBranch;

    public Segment(Tree tree, Branch currentBranch) {
        this.branchNumber = currentBranch.getBranchNumber();
        this.branchGeneration = currentBranch.getBranchGeneration();
        this.segmentNumber = currentBranch.segments.size() + 1;
        this.absSegmentNumber = tree.getSegmentsGrown();
        this.tree = tree;
        this.currentBranch = currentBranch;
        this.length = Parameters.SEGMENTLENGTH[branchGeneration] +
                (float) (Math.random() * Parameters.SEGMENTLENGTHRANDOMNESS[branchGeneration]);
        this.tree.incrementSegmentsGrown();
        this.matrix = new PMatrix3D();
        this.matrix.set(currentBranch.getGrowingMatrix());
        this.matrix.rotateX(((float) (2.0f * Math.PI) /
                Parameters.PHYLLOTAXIS[branchGeneration]) /
                Parameters.CHILDDENSITY[branchGeneration]);
        this.matrix.rotateZ((float) Math.toRadians(Parameters.WAVINESS[branchGeneration] +
                Math.random() * Parameters.WAVINESSRANDOMNESS[branchGeneration]));
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

    public int getSegmentNumber() {
        return segmentNumber;
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

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void drawSegment() {
        Main.p.pushMatrix();
        Main.p.applyMatrix(this.matrix);
        Main.p.strokeWeight(this.diameter);
        Main.p.line(0, 0, 0, this.length, 0, 0);
        if (this.diameter > 10.0f)
            Main.p.line(this.length * 0.5f, 0, 0, this.length * 1.5f, 0, 0);
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
        String segmentString = "\n  ";
        for (int i = 0; i < this.branchGeneration; i++)
            segmentString += "  |";

        // sformatować wyświetlanie nr segmentu FIXME
        segmentString += "  |__ sg: " + (String.format("%5d", segmentNumber)) +
                " length: " + String.format("%4.2f", length) +
                " diameter: " + String.format("%4.2f", diameter)
        /* + " sound: " + soundFile + " sample: " + soundFilePosition*/;

//        segmentString += " " + matrix.m00 + " " + matrix.m01 + " " + matrix.m02 + " " + matrix.m03;

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
