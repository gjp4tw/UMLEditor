package Components;
import java.awt.Graphics;
import java.awt.Point;
public class DraggingLine {
    private Point head, tail;
    private boolean isDragging = false;
    public boolean isDragging(){
        return isDragging;
    }
    public void isDragging(boolean isDragging){
        this.isDragging = isDragging;
    }

    public void draw(Graphics g){
        g.drawLine(head.x, head.y, tail.x, tail.y);
    }
    public void setHead(Point p){
        head = p;
    }
    public Point getHead(){
        return head;
    }
    public void setTail(Point p){
        tail = p;
    }
    public Point getTail(){
        return tail;
    }
}
