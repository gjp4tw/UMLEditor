package Components.Lines;
import java.awt.Graphics;
import java.awt.Point;
import Components.BaseLine;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
public class CompositionLine extends BaseLine{
    public CompositionLine(Point h, Point t) {
        super(h, t);
        offset =25;
        calEndPoint();
    }

    public void draw(Graphics g){
        super.draw(g);
        
        Graphics2D g1 = (Graphics2D)g;
        Line2D arrowtl = new Line2D.Double(offsetEndPoint.getX(),offsetEndPoint.getY(),offsetEndPoint.getX()-5,offsetEndPoint.getY()+10);
        Line2D arrowtr = new Line2D.Double(offsetEndPoint.getX(),offsetEndPoint.getY(),offsetEndPoint.getX()+5,offsetEndPoint.getY()+10);
        Line2D arrowbl = new Line2D.Double(offsetEndPoint.getX()-5,offsetEndPoint.getY()+7,offsetEndPoint.getX(),offsetEndPoint.getY()+20);
        Line2D arrowbr = new Line2D.Double(offsetEndPoint.getX()+5,offsetEndPoint.getY()+7,offsetEndPoint.getX(),offsetEndPoint.getY()+20);
        double angle = angleOfDegrees(offsetEndPoint.getX()-head.x, offsetEndPoint.getY()-head.y, 0.0, -1.0);
        AffineTransform at = 
        AffineTransform.getRotateInstance(
            Math.toRadians(-angle+180), offsetEndPoint.getX(), offsetEndPoint.getY());
        g1.draw(at.createTransformedShape(arrowtl));
        g1.draw(at.createTransformedShape(arrowtr));
        g1.draw(at.createTransformedShape(arrowbl));
        g1.draw(at.createTransformedShape(arrowbr));
    }
}
