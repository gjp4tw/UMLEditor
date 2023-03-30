package Components.Objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.Point;

import Components.BaseObject;

public class Group extends BaseObject{
    private ArrayList<BaseObject> GroupObjects;
    public Group(ArrayList<BaseObject> b){
        type = 2;
        GroupObjects = new ArrayList<>();
        int minx = (int)1e9, miny= (int)1e9, maxx= (int)-1e9, maxy= (int)-1e9;
        for(int i=0;i<b.size();i++){
            GroupObjects.add(b.get(i));
            Point pos = b.get(i).getPosition();
            int[] n = b.get(i).getSize();
            minx = Math.min(minx, pos.x);
            miny = Math.min(miny, pos.y);
            maxx = Math.max(maxx, pos.x+n[0]);
            maxy = Math.max(maxy, pos.y+n[1]);
        }
        x = minx;
        y=miny;
        width = maxx-minx;
        height = maxy-miny;
    }

    public void draw(Graphics g){
        Iterator<BaseObject> it = GroupObjects.iterator();
        while(it.hasNext()){
            BaseObject n = it.next();
            n.draw(g);
        }
    }

    public void drawLines(Graphics g){
        Iterator<BaseObject> it = GroupObjects.iterator();
        while(it.hasNext()){
            BaseObject n = it.next();
            n.drawLines(g);
        }
    }
    public void movePosition(int x, int y){
        super.movePosition(x, y);
        for(int i=0;i<GroupObjects.size();i++){
            GroupObjects.get(i).movePosition(x, y);
        }
    }

    public boolean checkSelected(int x, int y){
        for(int i=GroupObjects.size()-1;i>=0;i--){
            if(GroupObjects.get(i).checkSelected(x,y)){
                return true;
            }
        }
        return false;
    }

    public Object[] getPart(Point p){
        for(int i=GroupObjects.size()-1;i>=0;i--){
            if(GroupObjects.get(i).checkSelected(p.x, p.y)){
                return GroupObjects.get(i).getPart(p);
            }
        }
        return  new Object[]{this,-1};
    }

    public void select(){
        is_selected = true;
        for(int i=0;i<GroupObjects.size();i++){
            GroupObjects.get(i).select();
        }
    }

    public void unselect(){
        is_selected = false;
        for(int i=0;i<GroupObjects.size();i++){
            GroupObjects.get(i).unselect();
        }
    }

    public ArrayList<BaseObject> getGroupObjects(){
        return GroupObjects;
    }
}
