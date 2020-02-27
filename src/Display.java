import java.awt.*;
import javax.swing.*;

public class Display extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private Word[] displayWords;
    private JScrollPane tbScrollPane;
    
    public Display(Word[] words) {
        this.displayWords = words;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setTitle("eDictionary");
        setLayout(new BorderLayout());
        
        makeToolbar();
        makeWindow();
        
        setSize(screenSize.width/2, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
    }
    
    private void makeWindow() {

    }
    
    private void makeToolbar() {
        Toolbar toolbar = new Toolbar(displayWords);
        Word winWord = toolbar.initComponents();
        tbScrollPane = new JScrollPane(toolbar);
        tbScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tbScrollPane, BorderLayout.WEST);
    }
}
