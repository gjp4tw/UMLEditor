package Components.Objects;

import Components.BaseObject;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;
public class Classobj extends BaseObject{
    public Classobj(int x, int y){
        type = 0;
        setPosition(x, y);
        setSize(100,120);
        setPorts();
        HeadLines = new ArrayList<>();
        TailLines = new ArrayList<>();
        name = "Class";
    }

    public void setPorts(){
        p[0] = new Point(width/2,0);
        p[1] = new Point(width,height/2);
        p[2] = new Point(width/2,height);
        p[3] = new Point(0,height/2);
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawLine(x, y+40, x+width, y+40);
        g.drawLine(x, y+80, x+width, y+80);

        int stringWidth = g.getFontMetrics(font).stringWidth(name);
		g.setFont(font);	
        g.drawString(name, x + (int)((width- stringWidth)/2), y + 26);
        super.draw(g);
    }


}
