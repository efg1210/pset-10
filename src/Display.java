import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Display extends JFrame implements ListSelectionListener {
    private static final long serialVersionUID = 1L;
    
    private Word[] displayWords;
    private JScrollPane tbScrollPane;
    private JScrollPane winScrollPane;
    private String selection = null;
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
        System.out.println("selection: " + selection);
        Window window = new Window(selection, displayWords);
        //initComponents();
        winScrollPane = new JScrollPane(window);
        winScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        winScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(winScrollPane, BorderLayout.CENTER); 
    }
    
    private void makeToolbar() {
        Toolbar toolbar = new Toolbar(displayWords);
        /*String winWord = */toolbar.initComponents();
        //System.out.println("winWord: " + winWord);
        
        JList wordsList = toolbar.getJList();
        wordsList.addListSelectionListener(this);
        
        tbScrollPane = new JScrollPane(toolbar);
        tbScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tbScrollPane.setSize(screenSize.width/3, screenSize.height);
        add(tbScrollPane, BorderLayout.WEST);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selection = ((JList) e.getSource()).getSelectedValue().toString();
            makeToolbar();
            makeWindow();
        }
    }
}
