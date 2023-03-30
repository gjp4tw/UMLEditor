package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar{
    private Dimension buttonSize;
    private Canvas canvas = Canvas.getCanvas();
    private JButton[] buttons;
    private int CurrentMode=0;
    public ToolBar(){
        setOrientation(VERTICAL);
        setFloatable(false);
        // setBorderPainted(false);
        setPreferredSize(new Dimension(80, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set the maximum size of each button to be the same
        buttonSize = new Dimension(80, 80);

        buttons = new JButton[6];
        // Create the buttons
        buttons[0] = createToolbarButton("src/resources/select.png", "Select", 0);
        buttons[1] = createToolbarButton("src/resources/association.png", "Association", 1);
        buttons[2] = createToolbarButton("src/resources/generalization.png", "Generalization", 2);
        buttons[3] = createToolbarButton("src/resources/composition.png", "Composition", 3);
        buttons[4] = createToolbarButton("src/resources/class.png", "Class", 4);
        buttons[5] = createToolbarButton("src/resources/use-case.png", "Use Case", 5);
        canvas.setMode(CurrentMode);
    }

    private JButton createToolbarButton(String imagePath, String tooltip, int mode) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(icon);
        button.setOpaque(true);
        if(mode ==0 )
            button.setBackground(Color.BLACK);
        else
            button.setBackground(Color.LIGHT_GRAY);
        button.setToolTipText(tooltip);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setMaximumSize(buttonSize);
        button.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                buttons[CurrentMode].setBackground(Color.LIGHT_GRAY);
                CurrentMode = Arrays.asList(buttons).indexOf(e.getSource());
                //current button set color
                buttons[CurrentMode].setBackground(Color.BLACK); 
                canvas.setMode(mode);
            }
          } );
        add(button);
        return button;
    }
}
