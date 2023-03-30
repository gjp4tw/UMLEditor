package Components;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;
public abstract class BaseObject{
    protected Point[] p = new Point[4];
    public ArrayList<BaseLine> HeadLines, TailLines;
    private int depth;
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    protected String name;
    public int type;
    protected int width, height;
    protected int x, y;
    public  void draw(Graphics g){
        if(is_selected){
            drawPoints(g);
        }
    }

    public void drawLines(Graphics g){
        if(type!=2){
            for(int i=0;i<HeadLines.size();i++){
                BaseLine l = HeadLines.get(i);
                l.draw(g);
            }
        }
    }
    public void drawPoints(Graphics g){
        g.setColor(Color.BLACK);
        for(int i=0;i<4;i++){
            int x= this.x+p[i].x;
            int y= this.y+p[i].y;
            int w = 10;
            int h = 10;
            g.fillRect(x-w/2, y-h/2, w, h);
        }
    }
    public void setName(String s){
        name = s;
    }
    public String getName(){
        return name;
    }
    protected  void setPorts(){}
    public Point getPorts(int index){
        return new Point(p[index].x+x, p[index].y+y);
    }
    protected boolean is_selected = false;
    public boolean checkSelected(int x, int y){
        if(x>=this.x && x<=this.x+width && y>=this.y && y<=this.y+height){
            return true;
        }
        return false;
    }
    public boolean checkSelected(DraggingRect r){
        if(r.x<x && r.y<y && r.x+r.width>x+width &&r.y+r.height > y + height){
            return true;
        }
        return false;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void movePosition(int x, int y){
        this.x+=x;
        this.y+=y;
        if(HeadLines!=null){
            for(int i=0;i<HeadLines.size();i++){
                HeadLines.get(i).head.x+=x;
                HeadLines.get(i).head.y+=y;
                HeadLines.get(i).calEndPoint();
            }
        }
        if(TailLines!=null){
            for(int i=0;i<TailLines.size();i++){
                TailLines.get(i).tail.x+=x;
                TailLines.get(i).tail.y+=y;
                TailLines.get(i).calEndPoint();
            }
        }  
    }
    public Point getPosition() {
        return new Point(x, y);
    }
    public void setSize(int w, int h){
        width = w;
        height = h;
    }
    public int[] getSize(){
        return new int[] {width,height};
    }
    public void select(){
        is_selected = true;
    }
    public void unselect(){
        is_selected =false;
    }
    private boolean pointintriangle(Point s, Point a, Point b, Point c){
        int as_x = s.x - a.x;
        int as_y = s.y - a.y;
        boolean s_ab = (b.x - a.x) * as_y - (b.y - a.y) * as_x > 0;
        if ((c.x - a.x) * as_y - (c.y - a.y) * as_x > 0 == s_ab) 
            return false;
        if ((c.x - b.x) * (s.y - b.y) - (c.y - b.y)*(s.x - b.x) > 0 != s_ab) 
            return false;
        return true;
    }
    public Object[] getPart(Point p){
        Point tl,tr,bl,br,c;
        tl = new Point(x,y);
        tr = new Point(x+width, y);
        bl = new Point(x, y+ height);
        br = new Point(x+width, y+height);
        c = new Point((int)(x+width/2), (int)(y+width/2));
        // pointintriangle(p)
        if(pointintriangle(p, tl, tr, c)){
            return new Object[]{this,0};
        }
        else if(pointintriangle(p, tr, br, c)){
            return  new Object[]{this,1};
        }
        else if(pointintriangle(p, br, bl, c)){
            return  new Object[]{this,2};
        }
        else{
            return  new Object[]{this,3};
        }
    }
}