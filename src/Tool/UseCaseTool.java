package Tool;

import Components.Objects.UseCase;
import Components.BaseObject;
import java.awt.event.MouseEvent;
public class UseCaseTool extends Tool{
    public void mousePressed(MouseEvent e) {
        BaseObject s = new UseCase(e.getX(), e.getY());
        canvas.removeSelected();
        canvas.addShape(s);
        canvas.selected = (BaseObject)s;
        s.select();
        canvas.repaint();
    }
}
