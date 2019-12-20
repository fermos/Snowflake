
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 *
 * @author Mosè Ferrazzini
 * @version 20.12.2019
 */



public class SnowflakePanel extends JPanel implements MouseListener{
    /**
     * Raggio dei punti.
     */
    private final int POINTR = 5;
    /**
     * Lista contenente i punti del triangolo.
     */
    private List<Point> trianglePoints = new ArrayList<>();
    /**
     * Area del triangolo modificato.
     */
    private Area triangleArea = new Area();
    /**
     * Punti di taglio.
     */
    private List<Point> cutPoints = new ArrayList<>();
    /**
     * Ultima posizione del mouse.
     */
    private Point lastMousePosition = new Point(0, 0);
    /**
     * Se la modalità live è abilitata true = sì.
     */
    private boolean enableLive = false;
    /**
     * Se la darmode è abilitata true = sì.
     */
    private boolean darkmodeEnabled = false;
    /**
     * Se subtract è abilitato true = subtract, false = intersect.
     */
    private boolean subtractEnabled = true;
    /**
     * Se far vedere i punti true = sì
     */
    private boolean showPoints = true;
    /**
     * Il punto centrale della finestra.
     */
    private Point center;
    
    /**
     * Costruttore che aggiunge mouseListener e istanzia
     * il triangolo inziale.
     */
    public SnowflakePanel() {
        this.addMouseListener(this);
        trianglePoints.add(new Point(200, 300));
        trianglePoints.add(new Point(200, 500));
        trianglePoints.add(new Point(500, 500));
        repaint();
    }
    
    /**
     * Disegna sul panel.
     * @param g
     */
    public void paintComponent(Graphics g) {
        if(darkmodeEnabled) {
            g.setColor(Color.DARK_GRAY);           
        }else{
            g.setColor(Color.WHITE);
        }

        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(new Color(153, 204, 255));
        
        // *************** TAGLIO POLIGONO ******************
        
        Polygon triangle = getPolygon(trianglePoints);
        for(int i = 0; i < trianglePoints.size(); i++) {
            System.out.println(trianglePoints.get(i).x + ", " + trianglePoints.get(i).y);
        }
        Polygon cutPolygon = new Polygon();
        if(cutPoints.size() > 2) {
           cutPolygon = getPolygon(cutPoints);
        }else{
           cutPolygon = new Polygon();
        }
        triangleArea = new Area(triangle);
        Area a = new Area(cutPolygon);
        if(cutPoints.size() > 2) {
            if(subtractEnabled) {
                triangleArea.subtract(a);
            }else{
                triangleArea.intersect(a);
            }
        }else{
            triangleArea.subtract(a);
        }
        Polygon cuttedTriangle = convertToPolygon(triangleArea);
        for(int i = 0; i < trianglePoints.size(); i++) {
            System.out.println(trianglePoints.get(i).x + ", " + trianglePoints.get(i).y);
        }
        g.fillPolygon(cuttedTriangle);           

        g.setColor(Color.BLUE);
        
        
        //*************** FIOCCO DI NEVE ***************************************************
        
        Area responsiveTriangleArea = new Area(getPolygon(trianglePoints));
        a = new Area(getPolygon(cutPoints));
        if(cutPoints.size() > 2) {
            if(subtractEnabled) {
                responsiveTriangleArea.subtract(a);
            }else{
                responsiveTriangleArea.intersect(a);
            }
        }else{
            responsiveTriangleArea.subtract(a);
        }

        Polygon responsiveCuttedTriangle = convertToPolygon(responsiveTriangleArea);
        this.center = new Point(this.getWidth()/2, this.getHeight()/2);
        List<Point[]> flakePol = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            flakePol.add(convertToArray(responsiveCuttedTriangle));
        } 
        flakePol.add(convertToArray(responsiveCuttedTriangle));
        
        for (int i = 0; i < 5; i++) {
            rotatePointMatrix(convertToArray(responsiveCuttedTriangle), 60*(i+1), flakePol.get(i));
        }

        Point[] tempP = convertToArray(responsiveCuttedTriangle);
        
        Point[] mirroredTriangle = new Point[tempP.length];
        for(int i = 0; i < tempP.length; i++) {
            int pX = tempP[i].x;
            int pY = (center.y - tempP[i].y) + center.y;
            System.out.println(center.y + "- " + tempP[i].y + "= " + pY);
            mirroredTriangle[i] = new Point(pX, pY);
        }
        
        for(int i = 5; i <= 10; i++) {
            rotatePointMatrix(mirroredTriangle, 60*(i-5), flakePol.get(i));
        }

        if(enableLive) {
            g.setColor(new Color(153, 204, 255));
            for(int i = 0; i < 5; i++) {
                g.fillPolygon(getPolygon(flakePol.get(i)));
            }
            g.setColor(new Color(0, 102, 255));
            for(int i = 5; i <= 10; i++) {
            g.fillPolygon(getPolygon(flakePol.get(i)));                
            }          
        }
        // *************** CUT POINTS ************************
        
        g.setColor(Color.BLACK);
        if(cutPoints.size() > 1) {
            if(!enableLive && showPoints) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setStroke(new BasicStroke(2));
                cutPolygon = getResponsivePolygon(cutPoints);
                g2.drawPolygon(cutPolygon);        
            }
        }
        if(showPoints) {
            for(int i = 0; i < cutPoints.size(); i++) {
                g.fillOval(getResponsiveW(cutPoints.get(i).x)-POINTR, getResponsiveH(cutPoints.get(i).y)-POINTR, POINTR*2, POINTR*2);
            }            
        }
    }
    
    /**
     * Converte da poligono ad array di punti
     */
    public Point[] convertToArray(Polygon pol) {
        Point[] points = new Point[pol.npoints];
        for(int i = 0; i < pol.npoints; i++) {
            points[i] = new Point(pol.xpoints[i], pol.ypoints[i]);
        }
        return points;
    }
    
    /**
     * Ritorna la larghezza da proporzionale a pixel. 
     * @param a Larghezza proporzionale.
     * @return Larghezza in pixel.
     */
    public int getResponsiveW(int a) {
        return this.getWidth()*a/1000;
    }
    
    /**
     * Ritorna l'altezza da proporzionale a pixel.
     * @param a Altezza proporzionale.
     * @return Altezza in pixel.
     */
    public int getResponsiveH(int a) {
        return this.getHeight()*a/1000;
    }
    
    /**
     * Ritorna un punto da proporzionale a pixel.
     * @param p Punto proporzionale.
     * @return Punto in pixel.
     */
    public Point toResponsivePoint(Point p) {
        Point a = new Point(p.x*1000/this.getWidth(), p.y*1000/this.getHeight());
        return a;
    }
    
    /**
     * Ritorna un poligono da una lista di punti proporzionale a poligono
     * in pixel.
     * @param points Lista di punti del poligono proporzionale.
     * @return Poligono in pixel.
     */
    public Polygon getResponsivePolygon(List<Point> points) {
        Polygon pol = new Polygon();
        for(int i = 0; i < points.size(); i++) {
            pol.addPoint(getResponsiveW(points.get(i).x), getResponsiveH(points.get(i).y));
        }
        return pol;
    }
    
    /**
     * Ritorna un poligono un array proporzionale a poligono
     * @param points
     * @return 
     */
    public Polygon getResponsivePolygon(Point[] points) {
        Polygon pol = new Polygon();
        for (int i = 0; i < points.length; i++) {
            pol.addPoint(getResponsiveW(points[i].x), getResponsiveH(points[i].y));
        }
        return pol;
    }
    
    /**
     * Ritorna un poligono convertito da una lista di punti.
     * @param points
     * @return Poligono risultante.
     */
    public Polygon getPolygon(List<Point> points) {
        Polygon pol = new Polygon();
        for(int i = 0; i < points.size(); i++) {
            pol.addPoint((points.get(i).x), (points.get(i).y));
        }
        return pol;
    }
    
    /**
     * Ritorna un poligono da un array di punti.
     * @param points
     * @return Poligono risultante.
     */
    public Polygon getPolygon(Point[] points) {
        Polygon pol = new Polygon();
        for(int i = 0; i < points.length; i++) {
            pol.addPoint((points[i].x), (points[i].y));
        }
        return pol;
    }
    
    /**
     * Trasforma un area in un poligono.
     * @param a Area da trasformare.
     * @return Poligono risultante.
     */
    public Polygon convertToPolygon(Area a) {
        PathIterator iterator = a.getPathIterator(null);
        float[] floats = new float[6];
        Polygon p = new Polygon();
        while (!iterator.isDone()) {
            int type = iterator.currentSegment(floats);
            int x = (int) floats[0];
            int y = (int) floats[1];
            if(type != PathIterator.SEG_CLOSE) {
                p.addPoint(getResponsiveW(x),getResponsiveH(y));
                System.out.println("adding x = " + x + ", y = " + y);
            }
            iterator.next();
        }
        return p;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if(arg0.getPoint() != lastMousePosition) {
            if(arg0.getButton() == MouseEvent.BUTTON1) {
                cutPoints.add(toResponsivePoint(arg0.getPoint()));
                lastMousePosition = toResponsivePoint(arg0.getPoint());                
            }else if(arg0.getButton() == MouseEvent.BUTTON3) {
                Point p = toResponsivePoint(arg0.getPoint());
                for(int i = 0; i < cutPoints.size(); i++) {
                    Point cutP = cutPoints.get(i);
                    if(p.x >= cutP.x-POINTR*2 && cutP.x+POINTR*2 >= p.x) {
                        if(p.y >= cutP.y-POINTR*2 && cutP.y+POINTR*2 >= p.y) {
                            cutPoints.remove(i);
                            break;
                        }
                    }
                }
            }

            
        } 
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    
    /**
     * Converte una lista in un array.
     * @param list Lista da convertire.
     * @return Array risultante.
     */
    public Point[] convertToArray(List<Point> list) {
        Point[] array = new Point[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    /**
     * Ritorna il centro di un poligono.
     * @param points Punti del poligono.
     * @return Punto centrale.
     */
    public Point getPolygonCenter(Point[] points) {
        int maxX = 0;
        int maxY = 0;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            int x = points[i].x;
            int y = points[i].y;
            if(maxX < x) {
                maxX = x;
            }
            if(minX > x) {
                minX = x;
            }
            if(maxY < y) {
                maxY = y;
            }
            if(minY > y) {
                minY = y;
            }
        }
        return new Point((maxX-minX)/2+minX, (maxY-minY)/2+minY);
    }
    
    /**
     * Ruota una matrice di punti.
     * @param origPoints Punti da girare.
     * @param angle Di quanti gradi girare.
     * @param storeTo Dove mettere i punti girati.
     */
    public void rotatePointMatrix(Point[] origPoints, double angle, Point[] storeTo) {
        AffineTransform.getRotateInstance(Math.toRadians(angle), center.x, center.y)
                .transform(origPoints, 0, storeTo, 0, origPoints.length);

    }
    
    /**
     * Switcha la variabile booleana live.
     */
    public void switchLive() {
        this.enableLive = !this.enableLive;
        repaint();
    }
    
    /**
     * Switcha la variabile booleana darkmode.
     */
    public void switchDarkmode() {
        this.darkmodeEnabled = !this.darkmodeEnabled;
        repaint();
    }
    
    /**
     * Cancella tutti i punti.
     */
    public void deletePoints() {
        cutPoints = new ArrayList<>();
        repaint();
    }
    
    /**
     * Ritorna i punti del triangolo.
     * @return Punti del triangolo.
     */
    public Point[] getTrianglePoints() {
        return convertToArray(trianglePoints);
    }
    
    /**
     * Ritorna i punti di taglio.
     * @return Punti di taglio.
     */
    public Point[] getCutPoints() {
        return convertToArray(cutPoints);
    }   
    
    /**
     * Setta i punti del triangolo.
     * @param trianglePoints Punti del triangolo da settare.
     */
    public void setTrianglePoints(Point[] trianglePoints) {
        this.trianglePoints = toList(trianglePoints);
        repaint();
    }
    
    /**
     * Setta i punti di taglio.
     * @param cutPoints Punti di taglio da settare.
     */
    public void setCutPoints(Point[] cutPoints) {
        this.cutPoints = toList(cutPoints);
        repaint();
    }
    
    /**
     * COnverte un array di punti in una lista.
     * @param p Array di punti.
     * @return Lista risultante.
     */
    public List<Point> toList(Point[] p) {
        List<Point> result = new ArrayList<>();
        for(int i = 0; i < p.length; i++) {
            result.add(p[i]);
        }
        return result;
    }
    
    /**
     * Setta la modalità di taglio true = subtract, false = intersect
     */
    public void setMode(boolean b) {
        this.subtractEnabled = b;
        repaint();
    }
    
    /**
     * Switcha il far vedere i punti si o no.
     */
    public void switchShowPoints() {
        this.showPoints = !this.showPoints;
        repaint();
    }
    
}
