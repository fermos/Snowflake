
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Triangle implements MouseListener, MouseMotionListener {
    /**
     * Raggio dei punti.
     */
    private final int PRADIUS = 3;
    /**
     * Area di click vicino ai punti.
     */
    private final int CAREA = 12;
    /**
     * Coordinata X.
     */
    private int x;
    /**
     * Coordinata Y.
     */
    private int y;
    /**
     * Lunghezza del lato.
     */
    private int lato;
    /**
     * Lista degli ascoltatori.
     */
    private List<Point> points = new ArrayList<>();
    private List<Polygon> polygons = new ArrayList<>();
    private List<TriangleListener> listeners = new ArrayList<>();

    public Triangle(int x, int y, int lato) {
        this.x = x;
        this.y = y;
        this.lato = lato;
    }

    /**
     * Ritorna true se il punto passato è all'interno del riquadro.
     *
     * @param p punto da controllare
     * @return true se il punto è all'interno della lampadina
     */
    public boolean contains(Point p) {
        if (this.x < p.x && this.y < p.y && this.x + lato > p.x && this.y + lato > p.y) {
            return true;
        }
        return false;
    }

    /**
     * Aggiunge un ascoltatore.
     *
     * @param listener ascoltatore da aggiungere
     */
    public void addTriangleListener(TriangleListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Rimuove un ascoltatore.
     *
     * @param listener ascoltatore da rimuovere
     */
    public void removeTriangleListener(TriangleListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Stampa sul fframe.
     *
     * @param g
     */
    public void paint(Graphics g) {
        int u = (int) (lato) / 10;
        //Base
        g.setColor(Color.CYAN);
        g.fillRect(x, y, lato, lato);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, lato, lato);
        Polygon pol = new Polygon();
        pol.addPoint(x + 2 * u, y + 1 * u);
        pol.addPoint(x + 8*u, y+ 1 * u);
        pol.addPoint(x + 2*u, y + 9*u);
        g.setColor(Color.WHITE);
        g.fillPolygon(pol);
        
        //Poligoni
        if (polygons.size() > 0) {
            g.setColor(Color.CYAN);
            for (int i = 0; i < polygons.size(); i++) {
                g.fillPolygon(polygons.get(i));
            }
        }

        //Punti
        if (points.size() > 0) {
            for (int i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                g.setColor(Color.RED);
                g.fillOval(p.x - PRADIUS, p.y - PRADIUS, PRADIUS * 2, PRADIUS * 2);
                if (i > 0) {
                    Point p1 = points.get(i - 1);
                    Point p2 = points.get(i);
                    g.setColor(Color.BLACK);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Se il mouse viene schiacciato.
     *
     * @param e evento mouse
     */
    public void mouseClicked(MouseEvent e) {
        boolean flag = true;
        Point p = e.getPoint();
        if (contains(p)) {
            if (points.size() > 0) {
                Point a = points.get(0);
                if (p.x + CAREA/2>= a.x && a.x + PRADIUS*2>= p.x -CAREA/2 && p.y + CAREA/2 >= a.y && a.y + PRADIUS*2>= p.y - CAREA/2) {
                    if (points.size() > 1) {
                        this.polygons.add(new Polygon());
                        for (int i = 0; i < points.size(); i++) {
                            polygons.get(polygons.size() - 1).addPoint(points.get(i).x, points.get(i).y);
                        }
                        points.clear();
                        listeners.get(0).triangleEvent();
                    }

                } else {

                    for (int i = 0; i < points.size(); i++) {
                        if (p == points.get(i)) {
                            flag = false;
                        }
                        break;
                    }
                    if (flag) {
                        this.points.add(p);
                        listeners.get(0).triangleEvent();
                    }
                }
            } else {
                this.points.add(p);
                listeners.get(0).triangleEvent();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Se il mouse viene mosso.
     *
     * @param e evento del mouse.
     */
    public void mouseMoved(MouseEvent e) {

    }
    
    public void setAll(int x, int y, int lato) {
        this.x = x;
        this.y = y;
        this.lato = lato;
    }
}
