import processing.core.PApplet;

public class Main extends PApplet {
    public static PApplet p;
    private Tree tree = new Tree();

    public void setup() {
        p = this;
        frameRate(1.0f);
        background(0);
        for (int i = 0; i < 300; i++)
            if (!tree.grow()) {
                System.err.println("  Tree generating stopped");
                break;
            }
        System.out.println(tree);
    }

    public void settings() {
        size(1000, 1000, "processing.opengl.PGraphics3D");
    }

    public static void main(String[] args) {
        PApplet.main("Main", args);
//        p = this;
        System.out.println("Main applet: " + Main.p);
    }

    public void draw() {
        background(0);
        tree.drawTree();
//        pushMatrix();
//        translate(width/2, height/2, 0);
//        fog();
//        popMatrix();
    }

    void fog()
    {
        pushMatrix();
        rectMode(CENTER);
        noStroke();
        //fill(255, 255, 255, 10);
        fill(0, 0, 0, 20);
        translate(0, 0, -100);
        for (int z=0; z<20; z++)
        {
            rect(0, 50, 2*width, 2*height);
            translate(0, 0, 10);
        }
        popMatrix();
    }

}
