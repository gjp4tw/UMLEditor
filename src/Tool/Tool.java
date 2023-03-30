package Tool;

import javax.swing.event.MouseInputAdapter;

import UI.Canvas;

import java.awt.event.MouseEvent;
public abstract class Tool extends MouseInputAdapter{
    protected Canvas canvas;
    Tool(){
        canvas = Canvas.getCanvas();
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
}
