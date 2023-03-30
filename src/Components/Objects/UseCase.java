package Components.Objects;

import Components.BaseObject;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.*;
public class UseCase extends BaseObject{
    public UseCase(int x, int y){
        type = 1;
        setPosition(x, y);
        setSize(120, 80);
        setPorts();
        HeadLines = new ArrayList<>();
        TailLines = new ArrayList<>();
        name = "Usecase";
    }

    public void setPorts(){
        p[0] = new Point(width/2,0);
        p[1] = new Point(width,height/2);
        p[2] = new Point(width/2,height);
        p[3] = new Point(0,height/2);
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);

        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
        int stringWidth = g.getFontMetrics(font).stringWidth(name);
		g.setFont(font);	
        g.drawString(name, x + (int)((width- stringWidth)/2), y + 45);

        super.draw(g);
    }
}
