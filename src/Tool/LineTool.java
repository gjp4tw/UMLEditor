package Tool;

import Components.BaseLine;
import Components.BaseObject;
import Components.Lines.AssociationLine;
import Components.Lines.CompositionLine;
import Components.Lines.GeneralizationLine;

import java.awt.Point;
import java.awt.event.MouseEvent;
public class LineTool extends Tool{
    private Object[] headObj,tailObj;
    private int type;
    public LineTool(int t){
        type = t;
    }
    private BaseLine makeNewLine(Point h, Point t){
        if(type==0){
            return new AssociationLine(h, t);
        }
        if(type ==1){
            return new GeneralizationLine(h, t);
        }   
        return new CompositionLine(h, t);
    }
    public void mousePressed(MouseEvent e) {
        Point clickPos  = e.getPoint();
        for(int shape = canvas.objects.size()-1; shape>=0;shape--){
            BaseObject currentShape = (BaseObject)canvas.objects.get(shape);
            if(currentShape.checkSelected(clickPos.x,clickPos.y)){
                Point p;
                Object[] o = currentShape.getPart(clickPos);
                if((int)o[1]==-1)continue;
                headObj = o;
                // index=currentShape.getPart(clickPos);
                p=((BaseObject)o[0]).getPorts((int)o[1]);
                canvas.draggingLine.setHead(p);
                canvas.draggingLine.setTail(p);
                canvas.draggingLine.isDragging(true);
                break;
            }
        }
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {
            Point p = e.getPoint();
            if(canvas.draggingLine.isDragging()){
                canvas.draggingLine.setTail(p);
            }
            canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if(canvas.draggingLine.isDragging()){
            //check inside
            Point clickPos  = e.getPoint();
            for(int shape = canvas.objects.size()-1; shape>=0;shape--){
                BaseObject currentShape = (BaseObject)canvas.objects.get(shape);
                if(currentShape.checkSelected(clickPos.x,clickPos.y)){
                    Point p;
                    Object[] o = currentShape.getPart(clickPos);
                    if((int)o[1]==-1)continue;
                    // index=currentShape.getPart(clickPos);
                    p=((BaseObject)o[0]).getPorts((int)o[1]);
                    tailObj = o;
                    if(tailObj[0] == headObj[0]){
                        break;
                    }
                    BaseLine l = makeNewLine(canvas.draggingLine.getHead(),p);
                    ((BaseObject)headObj[0]).HeadLines.add(l);
                    ((BaseObject)tailObj[0]).TailLines.add(l);
                    break;
                }
            }
            // else{
            canvas.draggingLine.isDragging(false);
            // }
        }
        canvas.repaint();
    }
}
