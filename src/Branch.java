import processing.core.PMatrix3D;

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Branch implements Serializable {
    private int branchNumber = 1;           // unique identifier
    private int branchGeneration = 0;
    private int parentBranch = -1;           // identifier of parent branch ...
    private int parentSegmentNumber = -1;          // ... and it's segment
//    int totalSegments = 0;
    private float length;         // in pixels

    private boolean reachedMaxLength = false;
    private boolean reachedMaxSegments = false;
    
    private PMatrix3D growingMatrix;

    Tree tree;
    Segment parentSegment;

    ArrayList<Segment> segments;

    public Branch(Tree tree, Branch parentBranch, Segment parentSegment) {
        this.growingMatrix = new PMatrix3D();

        if(parentBranch != null) {
            this.branchGeneration = parentBranch.branchGeneration + 1;
            this.parentBranch = parentBranch.branchNumber;
            this.parentSegmentNumber = parentSegment.getSegmentNumber();
            this.parentSegment = parentSegment;
            this.growingMatrix.set(parentSegment.getMatrix()); // fixed!
            this.growingMatrix.rotateZ((float) (Math.toRadians(Parameters.CHILDANGLES[branchGeneration] +
                    Math.random() * Parameters.CHILDANGLESRANDOMNESS[branchGeneration])));
        } else {
            this.growingMatrix.set(tree.rootMatrix.get());
        }
        
        this.tree = tree;
        tree.incrementBranchesGrown();
        this.branchNumber = tree.getBranchesGrown();
        segments = new ArrayList<Segment>();
    }

    public boolean grow() {
        if (growthPossible()) {
            segments.add(new Segment(tree, this));

            if(segments.size() > Parameters.MAXSEGMENTS[branchGeneration])
                reachedMaxSegments = true;
            recalculateLength();
            recalculateDiameters();
            return true;
        }
        else return false;
    }


    public void updateGrowingMatrix(PMatrix3D endOfLastSegmentMatrix, float lastSegmentLength) {
        this.growingMatrix.set(endOfLastSegmentMatrix);
        this.growingMatrix.translate(lastSegmentLength, 0, 0);
    }

    private void recalculateLength() {
        length += segments.get(segments.size() - 1).getLength();
        if(length > Parameters.MAXBRANCHLENGTH[branchGeneration])
            reachedMaxLength = true;
    }

    private void recalculateDiameters() {
        float maxBaseDiameter = branchGeneration > 0 ?
            this.parentSegment.getDiameter() : 200;

        float baseDiameter = Math.min(maxBaseDiameter, length/Parameters.BRANCHLEANNESS[branchGeneration]);

        for(Segment s : segments) {
            float newDiameter;
            newDiameter = Math.max(1.0f, (1.0f-( s.getSegmentNumber()/(float)segments.size() ) ) * baseDiameter);       // FIXME div/0?
            s.setDiameter(newDiameter);
        }
    }

    public void drawBranch() {
        for (Segment s : segments) {
            s.drawSegment();
        }
    }

    private boolean growthPossible() {
        if(reachedMaxLength || reachedMaxSegments) return false;
        else return true;
    }

    public PMatrix3D getGrowingMatrix() {
        return growingMatrix;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public int getBranchGeneration() {
        return branchGeneration;
    }

    public int getParentBranch() {
        return parentBranch;
    }

    public int getParentSegmentNumber() {
        return parentSegmentNumber;
    }

    public float getLength() {
        return length;
    }

    public boolean isReachedMaxLength() {
        return reachedMaxLength;
    }

    public boolean isReachedMaxSegments() {
        return reachedMaxSegments;
    }

    public Segment getParentSegment() {
        return parentSegment;
    }



    @Override
    public String toString() {
        String branchString = "\n  ";
        for(int i = 0; i < branchGeneration; i++)
            branchString += "  |";
        // sformatować wyświetlanie nr gałęzi FIXME
        branchString += "  \\__ BR: " + String.format("%5d", branchNumber) +
                " length: " + String.format("%4.2f", length) + " (" + segments.size() + " segments)";
        for(Segment s : segments) {
            branchString += s.toString();
        }
        branchString += "\n  ";
        for(int i = 0; i < branchGeneration; i++)
            branchString += "  |";


        return branchString;
    }

    public static void main(String[] args) {
        Tree testTree = new Tree();
        Branch testBranch = new Branch(testTree, null, null);
        testBranch.grow();
        System.out.println(testTree);
    }
}
