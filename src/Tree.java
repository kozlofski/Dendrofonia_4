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
//        this.rootMatrix.reset();
//        this.rootMatrix.translate(Main.p.width/2, Main.p.height, 0);
        this.rootMatrix.translate(500, 750, 0);
//        this.rootMatrix.rotateZ(Main.p.radians(-90));
        this.rootMatrix.rotateZ(-1.57f);
        this.branches.add(new Branch(this, null, null));
//        System.out.println(Main.p);
    }

    public void drawTree() {
        for (Branch b : branches) {
            b.drawBranch();
        }
    }

    @Override
    public String toString() {
        String treeString = "TREE:";
        treeString += "\n segments grown: " + segmentsGrown;
        treeString += "\nROOT";

//        for (Branch b : branches) {
//            treeString += b.toString();
//        }
        treeString += branches.get(0).toString();
        return treeString;
    }
}
