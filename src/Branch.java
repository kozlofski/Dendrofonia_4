import processing.core.PMatrix3D;

import java.util.ArrayList;

public class Branch {
    int branchNumber = 1;           // unique identifier
    int branchGeneration = 0;
    int parentBranch = -1;           // identifier of parent branch ...
    int parentSegmentNumber = -1;          // ... and it's segment
    float length;         // in pixels

    boolean reachedMaxLength = false;
    boolean reachedMaxSegments = false;
    
    private PMatrix3D growingMatrix;

    Tree tree;
    Segment parentSegment;

    ArrayList<Segment> segments;

    public Branch(Tree tree, Branch parentBranch, Segment parentSegment) {
        this.growingMatrix = new PMatrix3D();

        if(parentBranch != null) {
            this.branchGeneration = parentBranch.branchGeneration + 1;
            this.parentBranch = parentBranch.branchNumber;
            this.parentSegmentNumber = parentSegment.segmentNumber;
            this.parentSegment = parentSegment;
            this.growingMatrix.set(parentSegment.getMatrix()); // fixed!
            this.growingMatrix.rotateZ(Parameters.CHILDANGLES[branchGeneration]);
            // FIXME: 28.03.2020 
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
            // sounds.get najnowszy
            // segments-najnowszy załaduj doń te dźwięki ;) FIXME
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
        length += Parameters.SEGMENTLENGTH[branchGeneration];
        if(length > Parameters.MAXBRANCHLENGTH[branchGeneration])
            reachedMaxLength = true;
    }

    private void recalculateDiameters() {
        float maxBaseDiameter = branchGeneration > 0 ?
            this.parentSegment.getDiameter() : 200;

        float baseDiameter = Math.min(maxBaseDiameter, length/Parameters.BRANCHLEANNESS[branchGeneration]);

        for(Segment s : segments) {
            float newDiameter;
            newDiameter = Math.max(1.0f, (1.0f-( s.segmentNumber/(float)segments.size() ) ) * baseDiameter);       // FIXME div/0?
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

    //    public int howManySegments() {
//        return segments.size();
//    }

    @Override
    public String toString() {
        String branchString = "\n";
        for(int i = 0; i < branchGeneration; i++)
            branchString += "  ";
        // sformatować wyświetlanie nr gałęzi FIXME
        branchString += " |- br " + branchNumber + " from seg " + parentSegmentNumber + " gen " +
                branchGeneration + " length " + length + " px (" +
                segments.size() + " segments)";
        for(Segment s : segments) {
            branchString += s.toString();
        }
        return branchString;
    }

    public static void main(String[] args) {
        Tree testTree = new Tree();
        Branch testBranch = new Branch(testTree, null, null);
        testBranch.grow();
        System.out.println(testTree);
    }
}
