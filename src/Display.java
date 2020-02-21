import java.awt.*;
import javax.swing.*;

public class Display extends JFrame {
    
    private Application app;
    
    public Display() {
        app = new Application();
        initComponents(app.getWords());
    }
    
    private void initComponents(Word[] words) {
        setTitle("eDictionary");
        setLayout(new BorderLayout());
        
        Toolbar toolbar = new Toolbar(words);
                
        JScrollPane scrollPane = new JScrollPane(toolbar);    
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.WEST);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
    }
 
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }
}
