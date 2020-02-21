import java.awt.*;
import javax.swing.*;

public class HelloWorldSwing extends javax.swing.JFrame {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    
    private Applications app;
    private Word[] words;
    
    /** Creates new form CelsiusConverterGUI */
    public HelloWorldSwing() {
        app = new Applications();
        words = app.getWords();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("eDictionary");
                
        for (int i = 0; i < words.length; i++) {
            JButton button = new JButton();
            button.setText(words[i].getWord());
            button.setBounds(100, ((50*i) + 10), 100, 40);  
            add(button);
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLayout(null);
    }
 
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelloWorldSwing().setVisible(true);
;
            }
        });
    }
}
