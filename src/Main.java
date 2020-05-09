import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet p;
    private Tree tree = new Tree();
    private Fog fog = new Fog(40);
    private ArrayList<Sound> sounds;

    float displayAngle = 0;

    public static void main(String[] args) {
        PApplet.main("Main", args);
//        System.out.println("Main applet: " + Main.p);
    }

    public void setup() {
        p = this;
        frameRate(25);
        background(0);
        hint(ENABLE_DEPTH_SORT);
        colorMode(RGB, 255, 255, 255, 1.0f);
        tree.grow(10000);
        System.out.println(tree);
//        TreeSaver ts = new TreeSaver();
//        ts.saveTree(tree);
    }

    public void settings() {
        size(1000, 1000, "processing.opengl.PGraphics3D");
    }

    public void draw() {
//        tree.grow(4);
        displayAngle += 0.03;
        background(0);
        tree.drawTree(displayAngle);
        fog.draw();
    }


}