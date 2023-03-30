package UI;
import javax.swing.*;

import Components.BaseObject;
import Components.DraggingLine;
import Components.DraggingRect;
import Tool.LineTool;
import Tool.ClassTool;
import Tool.SelectTool;
import Tool.Tool;
import Tool.UseCaseTool;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


import UI.Canvas;
public class Canvas extends JPanel{
    public ArrayList<BaseObject> objects,selectedList ;
    public int mode = 0;
    public DraggingRect draggingBox = new DraggingRect();
    public DraggingLine draggingLine = new DraggingLine();
    Tool tl;
    public BaseObject selected=null;
    private static Canvas canvas = null;
    // private static Line[] lines;
    private Canvas(){
        setOpaque(false);
        objects = new ArrayList<>();
        selectedList= new ArrayList<>();
    }


    public static Canvas getCanvas(){
        if(canvas == null)canvas = new Canvas();
        return canvas;
    }
    public void addShape(BaseObject s){
        int type = s.type;
        if(type<3){
            objects.add((BaseObject)s);
        }
        else{

        }
    }

    public void removeSelected(){
        if(selected!=null){
            selected.unselect();
        }
        for(int i=0;i<canvas.selectedList.size();i++){
            canvas.selectedList.get(i).unselect();
        }
        canvas.selectedList.clear();
    }

    public void setMode(int mode){
        this.mode = mode;
        if(tl != null){
            removeMouseListener(tl);
            removeMouseMotionListener(tl);
        }
        switch(this.mode){
            case 0:
                tl = new SelectTool();
                break;
            case 1:
                tl = new LineTool(0);
                break;
            case 2:
                tl = new LineTool(1);
                break;
            case 3:
                tl = new LineTool(2);
                break;
            case 4:
                tl = new ClassTool();
                break;
            case 5:
                tl = new UseCaseTool();
                break;
                
        }
        addMouseListener(tl);
        addMouseMotionListener(tl);
    }
    public void moveObjtoTop(BaseObject b){
        if(objects.contains(b)){
            objects.add(b);
            objects.remove(b);
        }
    }
    public void paintComponent(Graphics g){
        Iterator<BaseObject> it = objects.iterator();
        while(it.hasNext()){
            it.next().draw(g);
        }
        it = objects.iterator();
        while(it.hasNext()){
            it.next().drawLines(g);
        }
        if(draggingBox.isDragging()){
            draggingBox.draw(g);
        }
        if(draggingLine.isDragging()){
            draggingLine.draw(g);
        }
    }

}

