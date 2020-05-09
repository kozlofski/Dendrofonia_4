//import processing.core.PMatrix3D;
//
//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class TreeSaver {
//
//    public void saveTree(Tree tree) {
//        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("./saved"))) {
//            dos.writeInt(tree.getBranchesGrown());
//            dos.writeInt(tree.getCurrentBranch());
//            dos.writeInt(tree.getSegmentsGrown());
//            writeMatrix(dos, tree.getRootMatrix());
//            for (Branch b : tree) {
//                dos.writeInt(b.getBranchNumber());
//                dos.writeInt(b.getBranchGeneration());
//                dos.writeInt(b.getParentBranch());
//                dos.writeInt(b.getParentBranch());
//                dos.writeInt(b.getParentSegmentNumber());
////    int totalSegments = 0;
//                dos.writeFloat(b.getLength());
//
//                dos.writeBoolean(b.isReachedMaxLength());
//                dos.writeBoolean(b.isReachedMaxSegments());
//
//                writeMatrix(dos, b.getGrowingMatrix());
//
////                dos.write
//                Tree tree;
//                Segment parentSegment;
//
//                ArrayList<Segment> segments;
//
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    private void writeMatrix(DataOutputStream dos, PMatrix3D m) {
//        try {
//            dos.writeFloat(m.m00);
//            dos.writeFloat(m.m01);
//            dos.writeFloat(m.m02);
//            dos.writeFloat(m.m03);
//            dos.writeFloat(m.m10);
//            dos.writeFloat(m.m11);
//            dos.writeFloat(m.m12);
//            dos.writeFloat(m.m13);
//            dos.writeFloat(m.m20);
//            dos.writeFloat(m.m21);
//            dos.writeFloat(m.m22);
//            dos.writeFloat(m.m23);
//            dos.writeFloat(m.m30);
//            dos.writeFloat(m.m31);
//            dos.writeFloat(m.m32);
//            dos.writeFloat(m.m33);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
