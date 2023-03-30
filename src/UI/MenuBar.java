package UI;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;
import Components.BaseObject;
import Components.Objects.Classobj;
import Components.Objects.Group;
public class MenuBar extends JMenuBar{
    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem groupMenuItem;
    private JMenuItem ungroupMenuItem;
    private JMenuItem changeObjectName;
    private JMenu editMenu;
    protected Canvas canvas;
    public MenuBar(){
        canvas = Canvas.getCanvas();
        fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        
        editMenu = new JMenu("Edit");
        groupMenuItem = new JMenuItem("Group");
        ungroupMenuItem = new JMenuItem("Ungroup");
        changeObjectName = new JMenuItem("Change Object Name");
        groupMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(canvas.selectedList.size()>=2){
                    BaseObject c = new Group(canvas.selectedList);
                    for(int i=0;i<canvas.selectedList.size();i++){
                        canvas.objects.remove(canvas.selectedList.get(i));
                    }
                    canvas.removeSelected();
                    canvas.addShape(c);
                    canvas.selected = c;
                    c.select();
                    canvas.repaint();
                }
                return;
            }
        });
        ungroupMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(canvas.selected!=null && canvas.selected.type == 2){
                    ArrayList<BaseObject> g = ((Group)canvas.selected).getGroupObjects();
                    canvas.objects.remove(canvas.selected);
                    canvas.selected.unselect();
                    canvas.selected = null;
                    for(int i=0;i<g.size();i++){
                        canvas.objects.add(g.get(i));
                    }
                    canvas.repaint();
                }
                return;
            }
        });
        changeObjectName.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(canvas.selected!=null && canvas.selected.type != 2){
                    String org= canvas.selected.getName(),name = JOptionPane.showInputDialog(canvas,
                    "change object name", org);
                    if(name!=null && org!=name)
                    canvas.selected.setName(name);
                    canvas.repaint();
                }
                return;
            }
        });
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        editMenu.add(changeObjectName);
        add(fileMenu);
        add(editMenu);
    }
}
