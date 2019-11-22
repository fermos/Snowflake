
import java.awt.Color;
import java.awt.Graphics;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mosef
 */



public class SnowflakePanel extends JPanel implements MouseListener{
    private final int POINTR = 5;
    private List<Point> trianglePoints = new ArrayList<>();
    private Area triangleArea = new Area();
    private List<Point> cutPoints = new ArrayList<>();
    private Point lastMousePosition = new Point(0, 0);
    private boolean enableLive = false;
    private boolean darkmodeEnabled = false;
    
    private Point center;
    
    public SnowflakePanel() {
        this.addMouseListener(this);
        trianglePoints.add(new Point(200, 300));
        trianglePoints.add(new Point(200, 500));
        trianglePoints.add(new Point(500, 500));
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        if(darkmodeEnabled) {
            g.setColor(Color.DARK_GRAY);           
        }else{
            g.setColor(Color.WHITE);
        }

        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.RED);
        
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
        triangleArea.subtract(a);
        Polygon cuttedTriangle = convertToPolygon(triangleArea);
        for(int i = 0; i < trianglePoints.size(); i++) {
            System.out.println(trianglePoints.get(i).x + ", " + trianglePoints.get(i).y);
        }
        g.fillPolygon(cuttedTriangle);
        g.setColor(Color.BLUE);
        
        
        //*************** FIOCCO DI NEVE ***************************************************
        
        Area responsiveTriangleArea = new Area(getPolygon(trianglePoints));
        a = new Area(getPolygon(cutPoints));
        responsiveTriangleArea.subtract(a);
        Polygon responsiveCuttedTriangle = convertToPolygon(responsiveTriangleArea);
        this.center = new Point(this.getWidth()/2, this.getHeight()/2);
        Point[] pol1 = convertToArray(responsiveCuttedTriangle);
        Point[] pol2 = convertToArray(responsiveCuttedTriangle);
        Point[] pol3 = convertToArray(responsiveCuttedTriangle);
        Point[] pol4= convertToArray(responsiveCuttedTriangle);
        Point[] pol5= convertToArray(responsiveCuttedTriangle);
        Point[] pol6= convertToArray(responsiveCuttedTriangle);
        Point[] pol7= convertToArray(responsiveCuttedTriangle);
        Point[] pol8= convertToArray(responsiveCuttedTriangle);
        Point[] pol9= convertToArray(responsiveCuttedTriangle);
        Point[] pol10= convertToArray(responsiveCuttedTriangle);
        Point[] pol11= convertToArray(responsiveCuttedTriangle);

        rotatePointMatrix(convertToArray(responsiveCuttedTriangle), 60, pol1, convertToArray(responsiveCuttedTriangle).length);
        rotatePointMatrix(convertToArray(responsiveCuttedTriangle), 120, pol2, convertToArray(responsiveCuttedTriangle).length);
        rotatePointMatrix(convertToArray(responsiveCuttedTriangle), 180, pol3, convertToArray(responsiveCuttedTriangle).length);
        rotatePointMatrix(convertToArray(responsiveCuttedTriangle), 240, pol4, convertToArray(responsiveCuttedTriangle).length);
        rotatePointMatrix(convertToArray(responsiveCuttedTriangle), 300, pol5, convertToArray(responsiveCuttedTriangle).length);

        Point[] prova = convertToArray(responsiveCuttedTriangle);
        
        Point[] risultatoProva = new Point[prova.length];
        for(int i = 0; i < prova.length; i++) {
            int pX = prova[i].x;
            int pY = (center.y - prova[i].y) + center.y;
            System.out.println(center.y + "- " + prova[i].y + "= " + pY);
            risultatoProva[i] = new Point(pX, pY);
        }
        rotatePointMatrix(risultatoProva, 0, pol6, risultatoProva.length);
        rotatePointMatrix(risultatoProva, 60, pol7, risultatoProva.length);
        rotatePointMatrix(risultatoProva, 120, pol8, risultatoProva.length);
        rotatePointMatrix(risultatoProva, 180, pol9, risultatoProva.length);
        rotatePointMatrix(risultatoProva, 240, pol10, risultatoProva.length);
        rotatePointMatrix(risultatoProva, 300, pol11, risultatoProva.length);
        if(enableLive) {
            g.setColor(Color.CYAN);
            g.fillPolygon(getPolygon(pol1));
            g.fillPolygon(getPolygon(pol2));
            g.fillPolygon(getPolygon(pol3));
            g.fillPolygon(getPolygon(pol4));
            g.fillPolygon(getPolygon(pol5));
            g.setColor(Color.BLUE);
            g.fillPolygon(getPolygon(pol6));
            g.fillPolygon(getPolygon(pol7));
            g.fillPolygon(getPolygon(pol8));
            g.fillPolygon(getPolygon(pol9));
            g.fillPolygon(getPolygon(pol10));       
            g.fillPolygon(getPolygon(pol11));            
        }
        // *************** CUT POINTS ************************
        
        g.setColor(Color.GREEN);
        if(cutPoints.size() > 1) {
            if(!enableLive) {
               cutPolygon = getResponsivePolygon(cutPoints);
                g.drawPolygon(cutPolygon);        
            }
        }
        for(int i = 0; i < cutPoints.size(); i++) {
            g.fillOval(getResponsiveW(cutPoints.get(i).x)-POINTR, getResponsiveH(cutPoints.get(i).y)-POINTR, POINTR*2, POINTR*2);
        }
 
    }
    
    public Point[] convertToArray(Polygon pol) {
        Point[] points = new Point[pol.npoints];
        for(int i = 0; i < pol.npoints; i++) {
            points[i] = new Point(pol.xpoints[i], pol.ypoints[i]);
        }
        return points;
    }
    
    public int getResponsiveW(int a) {
        return this.getWidth()*a/1000;
    }
    
    public int getResponsiveH(int a) {
        return this.getHeight()*a/1000;
    }
    
    public Point toResponsivePoint(Point p) {
        Point a = new Point(p.x*1000/this.getWidth(), p.y*1000/this.getHeight());
        return a;
    }
    
    public Polygon getResponsivePolygon(List<Point> points) {
        Polygon pol = new Polygon();
        for(int i = 0; i < points.size(); i++) {
            pol.addPoint(getResponsiveW(points.get(i).x), getResponsiveH(points.get(i).y));
        }
        return pol;
    }
    
    public Polygon getResponsivePolygon(Point[] points) {
        Polygon pol = new Polygon();
        for (int i = 0; i < points.length; i++) {
            pol.addPoint(getResponsiveW(points[i].x), getResponsiveH(points[i].y));
        }
        return pol;
    }
    
    public Polygon getPolygon(List<Point> points) {
        Polygon pol = new Polygon();
        for(int i = 0; i < points.size(); i++) {
            pol.addPoint((points.get(i).x), (points.get(i).y));
        }
        return pol;
    }
    
    public Polygon getPolygon(Point[] points) {
        Polygon pol = new Polygon();
        for(int i = 0; i < points.length; i++) {
            pol.addPoint((points[i].x), (points[i].y));
        }
        return pol;
    }
    
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
            cutPoints.add(toResponsivePoint(arg0.getPoint()));
            lastMousePosition = toResponsivePoint(arg0.getPoint());
            repaint();
        }    
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
    
    public Point[] convertToArray(List<Point> list) {
        Point[] array = new Point[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

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
           
    public void rotatePointMatrix(Point[] origPoints, double angle, Point[] storeTo, int length) {

        /* We ge the original points of the polygon we wish to rotate
         *  and rotate them with affine transform to the given angle. 
         *  After the opeariont is complete the points are stored to the 
         *  array given to the method.
         */
        AffineTransform.getRotateInstance(Math.toRadians(angle), center.x, center.y)
                .transform(origPoints, 0, storeTo, 0, length);

    }
    
    public void switchLive() {
        this.enableLive = !this.enableLive;
        repaint();
    }
    
    public void switchDarkmode() {
        this.darkmodeEnabled = !this.darkmodeEnabled;
        repaint();
    }
    
    public void deletePoints() {
        cutPoints = new ArrayList<>();
        repaint();
    }
    
}
