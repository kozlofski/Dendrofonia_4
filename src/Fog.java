public class Fog {
    int quality;

    public Fog(int quality) {
        this.quality = quality;
    }

    void draw()
    {
        Main.p.pushMatrix();
        Main.p.rectMode(Main.p.CENTER);
        Main.p.noStroke();
        Main.p.colorMode(Main.p.RGB, 255, 255, 255, 1.0f);
        Main.p.fill(20, 20, 20, 3.0f / quality);
//        Main.p.stroke(0,0,0,0.1f);

        Main.p.translate(0, 0, -Main.p.width);
        for (int i=0; i < this.quality; i++)
        {
            Main.p.rect((int) (Main.p.width * 0.5), (int) (Main.p.height * 0.5), Main.p.width, Main.p.height);
            Main.p.translate(0, 0, 2*Main.p.width/quality);
        }
        Main.p.popMatrix();
    }
}
