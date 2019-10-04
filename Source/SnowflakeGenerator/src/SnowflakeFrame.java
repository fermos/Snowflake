
import java.awt.Graphics;
import javax.swing.JFrame;

public class SnowflakeFrame extends JFrame implements TriangleListener {

    /**
     * Array di lampadine.
     */
    private double uX = 80;
    private double uY = 60;
    private Triangle triangle = new Triangle((int)(1 * uX),(int)(3*uY),(int)(3*uX));

    public SnowflakeFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.addMouseListener(triangle);
        this.addMouseMotionListener(triangle);
        triangle.addTriangleListener(this);
    }

    public static void main(String[] args) {
        SnowflakeFrame sf = new SnowflakeFrame("Snowflake");
        sf.setVisible(true);
    }

    /**
     * Disegna sul frame.
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        triangle.setAll((int)(1 * uX),(int)(3*uY),(int)(3*uX));
        uX = this.getWidth()/10;
        uY = this.getHeight()/10;
        triangle.paint(g);
    }

    @Override
    public void triangleEvent() {
        repaint();
    }
}
