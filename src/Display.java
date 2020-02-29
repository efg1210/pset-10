import java.awt.*;
import javax.swing.*;

public class Display extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private Word[] displayWords;
    private JScrollPane tbScrollPane;
    private JScrollPane winScrollPane;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public Display(Word[] words) {
        this.displayWords = words;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setTitle("eDictionary");
        setLayout(new BorderLayout());
        
        makeToolbar();
        makeWindow();
        
        setSize(screenSize.width/2, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
    }
    
    private void makeWindow() {
        Window window = new Window(/*displayWords[1]*/null);
        window.initComponents();
        winScrollPane = new JScrollPane(window);
        winScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(winScrollPane, BorderLayout.CENTER); 
    }
    
    private void makeToolbar() {
        Toolbar toolbar = new Toolbar(displayWords);
        Word winWord = toolbar.initComponents();
        //System.out.println("winWord: " + winWord);
        tbScrollPane = new JScrollPane(toolbar);
        tbScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tbScrollPane.setSize(screenSize.width/3, screenSize.height);
        add(tbScrollPane, BorderLayout.WEST);
    }
}
