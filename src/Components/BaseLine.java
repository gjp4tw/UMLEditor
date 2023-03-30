package Components;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
public abstract class BaseLine {
    public Point head, tail;
    protected int offset=0;
    protected Point2D offsetEndPoint;
    public BaseLine(Point h, Point t){
        head = new Point(h);
        tail = new Point(t);
        // offsetEndPoint = new Point2D.Double(t.x,t.y);
    }
    protected double angleOfDegrees(Double x0, Double y0, Double x1, Double y1) {
        double angle2 = Math.atan2(y1,x1);
        double angle1 = Math.atan2(y0,x0);
    
       return (Math.toDegrees(angle2 - angle1) + 360) % 360;
    }
    protected void calEndPoint(){
        double l = Math.sqrt(Math.pow(head.x-tail.x,2)+Math.pow(head.y-tail.y,2))-offset;
        double angle = angleOfDegrees((tail.x-head.x)*1.0, (tail.y-head.y)*1.0, 0.0, -1.0);
        double r = Math.toRadians(-angle);
        double cos = Math.cos(r);
        double sin = Math.sin(r);
        // x = cos(0)-sin(l)+x1
        // y= sin(0)+cos(l)+y1
        double x =cos*0-sin*(-l)+head.x;
        double y = sin*0+cos*(-l)+head.y;
        offsetEndPoint =  new Point2D.Double(x,y);
    }
    public void draw(Graphics g){
        Graphics2D g1 = (Graphics2D)g;
        Line2D line = new Line2D.Double(head.x,head.y,offsetEndPoint.getX(),offsetEndPoint.getY());
        g1.draw(line);
    }
}
