package Tool;
import Components.Objects.Classobj;
import Components.BaseObject;
import java.awt.event.MouseEvent;
public class ClassTool extends Tool{
    public void mousePressed(MouseEvent e) {
        BaseObject c = new Classobj(e.getX(), e.getY());
        canvas.removeSelected();
        canvas.addShape(c);
        canvas.selected = (BaseObject)c;
        c.select();
        canvas.repaint();
    }
}
