package Components.Lines;
import java.awt.Graphics;
import java.awt.Point;
import Components.BaseLine;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
public class GeneralizationLine extends BaseLine {
    public GeneralizationLine(Point h, Point t) {
        super(h, t);
        offset =20;
        calEndPoint();
    }

    public void draw(Graphics g){
        super.draw(g);
        Graphics2D g1 = (Graphics2D)g;
        Line2D arrowb = new Line2D.Double(offsetEndPoint.getX()-7,offsetEndPoint.getY(),offsetEndPoint.getX()+7,offsetEndPoint.getY());
        Line2D arrowl = new Line2D.Double(offsetEndPoint.getX()-7,offsetEndPoint.getY(),offsetEndPoint.getX(),offsetEndPoint.getY()+10);
        Line2D arrowr = new Line2D.Double(offsetEndPoint.getX()+7,offsetEndPoint.getY(),offsetEndPoint.getX(),offsetEndPoint.getY()+10);
        double angle = angleOfDegrees(offsetEndPoint.getX()-head.x, offsetEndPoint.getY()-head.y, 0.0, -1.0);
        AffineTransform at = 
        AffineTransform.getRotateInstance(
            Math.toRadians(-angle+180), offsetEndPoint.getX(), offsetEndPoint.getY());
        g1.draw(at.createTransformedShape(arrowb));
        g1.draw(at.createTransformedShape(arrowl));
        g1.draw(at.createTransformedShape(arrowr));


    }
}
