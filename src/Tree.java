import processing.core.PMatrix3D;

import java.util.ArrayList;

public class Tree {
    private int branchesGrown = -1;
    private int currentBranch = -1;
    private int segmentsGrown = -1;

    PMatrix3D rootMatrix;//, growingMatrix;

    ArrayList<Branch> branches;

    public Tree() {
        this.branches = new ArrayList<>();
        this.rootMatrix = new PMatrix3D();
        makeRoot();
    }

    public int getBranchesGrown() {
        return branchesGrown;
    }

    public int getSegmentsGrown() {
        return segmentsGrown;
    }

    public void incrementBranchesGrown() {
        branchesGrown++;
    }

    public void incrementSegmentsGrown() {
        segmentsGrown++;
    }

    public void grow(int times) {
        for (int i = 0; i < times; i++) {
            if(!grow()) break;
        }
    }

    public boolean grow() {
        if(growthPossible()) {
            int startBranch = currentBranch;
            boolean branchGrown;
            do {
                nextBranch();
//                System.out.println("Trying branch " + currentBranch);
                branchGrown = branches.get(currentBranch).grow();

            } while (!branchGrown && (currentBranch != startBranch));
            if(branchGrown)
                return true;
            else {
                System.err.println(" All branches full");
                return false;
            }
        }
        else return false;
    }

    public void nextBranch() {
        currentBranch ++;
        if (currentBranch > branchesGrown)
            currentBranch = 0;
    }

    private boolean growthPossible() {
        return branchesGrown < Parameters.MAXBRANCHES &&
                segmentsGrown < Parameters.MAXTOTALSEGMENTS;
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
        for(int i = 0; i < 20; i++)
            if(!tree.grow()) {
                System.err.println("  Tree generating stopped");
                break;
            }
        System.out.println(tree);

    }

    private void makeRoot() {
        this.rootMatrix.reset();
        this.rootMatrix.rotateZ(-1.57f);
        this.branches.add(new Branch(this, null, null));
    }

    // FIXME: 30.03.2020 rename displayMatrix()
    private PMatrix3D displayMatrix(float angle) {
        PMatrix3D displayMatrix = new PMatrix3D();
        displayMatrix.translate(Main.p.width * 0.5f, Main.p.width * 0.9f, 0);
        displayMatrix.rotateY(angle);
        return displayMatrix;
    }

    public void drawTree(float angle) {
        Main.p.pushMatrix();
        Main.p.applyMatrix(displayMatrix(angle));
        Main.p.stroke(255, 255, 255, 1.0f);
        for (Branch b : branches) {
            b.drawBranch();
        }
        Main.p.popMatrix();
    }

    @Override
    public String toString() {
        String treeString = "TREE:";
        treeString += "\nROOT";
        treeString += branches.get(0).toString();
        treeString += "\n branches grown: " + branchesGrown;
        treeString += "\n segments grown: " + segmentsGrown;
        return treeString;
    }
}
