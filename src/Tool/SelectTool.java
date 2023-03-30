package Tool;

import Components.BaseObject;
import java.awt.Point;
import java.awt.event.MouseEvent;
public class SelectTool extends Tool{
    private Point clickStart;
    public void mousePressed(MouseEvent e) {
        clickStart  = e.getPoint();
        canvas.removeSelected();
        canvas.selected = null;
        for(int shape = canvas.objects.size()-1; shape>=0;shape--){
            BaseObject currentShape = (BaseObject)canvas.objects.get(shape);
            if(currentShape.checkSelected(clickStart.x,clickStart.y)){
                canvas.selected = currentShape;
                canvas.moveObjtoTop(currentShape);
                currentShape.select();
                break;
            }
        }

        if(canvas.selected == null){//outside  rectangles
            canvas.draggingBox.isDragging(true);
            canvas.draggingBox.setBound(e.getPoint(), e.getPoint());
        }
        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {
            Point p = e.getPoint();
            if(canvas.draggingBox.isDragging()){
                canvas.draggingBox.setBound(clickStart, p);
                for(int i=0;i<canvas.objects.size();i++){
                    BaseObject currentShape = canvas.objects.get(i);
                    if(currentShape.checkSelected(canvas.draggingBox)){ 
                        currentShape.select();
                        if(!canvas.selectedList.contains(currentShape)){
                            canvas.selectedList.add(currentShape);
                        }
                    }
                    else{
                        if(canvas.selectedList.contains(currentShape)){
                            currentShape.unselect();
                            canvas.selectedList.remove(currentShape);
                        }
                    }
                }
                
            }
            else{ //move object
                canvas.selected.movePosition(p.x-clickStart.x, p.y - clickStart.y);
                clickStart = p;
            }
            canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if(canvas.draggingBox.isDragging()){
            //check if group many items
            if(canvas.selectedList.size()>0){
                if(canvas.selectedList.size() == 1){
                    canvas.selected = canvas.selectedList.get(0);
                    canvas.selectedList.clear();
                    canvas.moveObjtoTop(canvas.selected);
                }
            }
                canvas.draggingBox.isDragging(false);
        }
        canvas.repaint();
    }
}
