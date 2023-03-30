import javax.swing.*;


import UI.Canvas;
import UI.MenuBar;
import UI.ToolBar;
import java.awt.*;

public class UMLEditor extends JFrame {
    private MenuBar menuBar;
    private ToolBar toolBar;
    private Canvas canvas;

    public UMLEditor() {
        // Set up the frame
        setTitle("UML Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create the menu bar
        menuBar = new MenuBar();
        setJMenuBar(menuBar);

        // Create the canvas
        canvas = Canvas.getCanvas();

        // Create the tool bar
        toolBar = new ToolBar();

        add(toolBar, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);


        setLocationRelativeTo(null);
        // Show the frame
        setVisible(true);
    }


    public static void main(String[] args) {
        new UMLEditor();
    }
}