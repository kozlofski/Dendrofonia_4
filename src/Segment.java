public class Segment {
    int branchNumber;
    int branchGeneration;
    int segmentNumber;
    int childBranch = -1;       // unique id of child branch

    float length;               // in pixels
    float diameter = 1;             // in pixels

    String soundFile = "";
    int soundFilePosition;      // in samples

    boolean hasLeave;
    Leave leave;
    // Matrix3D FIXME

    Tree tree;
    Branch currentBranch;

    public Segment(Tree tree, Branch currentBranch) {
        this.branchNumber = currentBranch.branchNumber;
        this.branchGeneration = currentBranch.branchGeneration;
        this.segmentNumber = currentBranch.segments.size() + 1;
        this.tree = tree;
        this.currentBranch = currentBranch;
        this.length = Parameters.SEGMENTLENGTH[branchGeneration];
        this.tree.incrementSegmentsGrown();
        this.childBranch = makeChild();
    }

    public int getChildBranch() {
        return childBranch;
    }

    public float getDiameter() {
        return diameter;
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
    }

    private void setHasLeave(boolean hasLeave) { // FIXME
        this.hasLeave = hasLeave;
        Leave leave = new Leave();
    }

    private int makeChild() {
        if(childPossible()) {
            this.tree.branches.add(new Branch(this.tree, this.currentBranch, this));
            return tree.getBranchesGrown();
        }
        else return -1;
    }

    private boolean childPossible() {
        if((segmentNumber % Parameters.CHILDDENSITY[branchGeneration]) != 0 ||
                (segmentNumber < Parameters.FIRSTCHILDOFFSET[branchGeneration]) ||
                (branchGeneration == Parameters.MAXGENERATIONS))
            return false;
        else return true;
    }

    @Override
    public String toString() {
        String segmentString = "\n";
        for(int i = 0; i < this.branchGeneration; i++)
            segmentString += "  ";

            // sformatować wyświetlanie nr segmentu FIXME
        segmentString += "  -seg: " + segmentNumber +
                    " from br: " + branchNumber + " length: " + length +
                " diameter: " + diameter +
                " sound: " + soundFile + " sample: " + soundFilePosition;

        if(childBranch != -1)
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
