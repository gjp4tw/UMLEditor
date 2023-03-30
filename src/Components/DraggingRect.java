package Components;

import java.awt.Graphics;
import java.awt.Point;
public class DraggingRect{
    private boolean isDragging = false;
    protected int width, height;
    protected int x, y;
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
    }
    public boolean isDragging(){
        return isDragging;
    }
    public void isDragging(boolean isDragging){
        this.isDragging = isDragging;
    }
    public void setBound(BaseObject s){
        if(s.x<x){
            width += x-s.x;
            x=s.x;
        }
        if(s.y<y){
            height += y-s.y;
            y = s.y;
        }
        if(s.x>x+width){
            width = s.x-x;
        }
        if(s.y>y+height){
            height = s.y-y;
        }
    }
    public void setBound(Point p1, Point p2){
        x = Math.min(p1.x, p2.x);
        y = Math.min(p1.y, p2.y);
        width = Math.max(p1.x, p2.x) - x;
        height = Math.max(p1.y, p2.y) - y;
    }

}
