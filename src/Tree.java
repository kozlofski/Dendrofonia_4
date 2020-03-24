import java.util.ArrayList;

public class Tree {
    private int branchesGrown = -1;
    private int currentBranch = -1;
    private int segmentsGrown = -1;

    ArrayList<Branch> branches;

    public Tree() {
        this.branches = new ArrayList<Branch>();
        makeRoot();
    }

    public int getBranchesGrown() {
        return branchesGrown;
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
            boolean branchGrown = false;
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
        if(branchesGrown < Parameters.MAXBRANCHES &&
        segmentsGrown < Parameters.MAXTOTALSEGMENTS)
            return true;
        else return false;
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
        for(int i = 0; i < 250; i++)
            if(!tree.grow()) {
                System.err.println("  Tree generating stopped");
                break;
            }
        System.out.println(tree);

    }

    private void makeRoot() {
        this.branches.add(new Branch(this, null, null));
    }

    @Override
    public String toString() {
        String treeString = "TREE:";
        treeString += "\n branches grown: " + branchesGrown + " (counting from 0)";
        treeString += "\n segments grown: " + segmentsGrown;
        treeString += "\nROOT";
//        for (Branch b : branches) {
//            treeString += b.toString();
//        }
        treeString += branches.get(0).toString();
        return treeString;
    }
}
