import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Display extends JFrame implements ListSelectionListener, ActionListener {
    private static final long serialVersionUID = 1L;
    
    private Word[] displayWords;
    private JScrollPane tbScrollPane;
    private JScrollPane winScrollPane;
    private String selection = null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private Toolbar toolbar;
    private Window window;
    private JList wordsList;
    private JButton displayAddButton;
    private JButton displayDeleteButton;
    
    public Display(Word[] words) {
        this.displayWords = words;
        initComponents();
    }
    
    public JButton getAddButton() {
        return displayAddButton;
    }
    
    public JButton getDeleteButton() {
        return displayDeleteButton;
    }
    
    private void setDisplayWords(Word[] words) {
        this.displayWords = words;
    }
    
    public Window getWindow() {
        return window;
    }
    
    public void setSelection(String newSelection) {
        selection = newSelection;
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setTitle("eDictionary");
        setLayout(new BorderLayout());
        
        makeToolbar();
        makeWindow();
        
//        PopupFactory pf = new PopupFactory();
//        pop pop = pf.getPopup(f, p2, 180, 100);
        
        setSize(screenSize.width/2, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }
    
    private void makeWindow() {
        window = new Window(selection, displayWords);
        winScrollPane = new JScrollPane(window);
        winScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        winScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);        
        add(winScrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
    
    private void makeToolbar() {
        toolbar = new Toolbar(displayWords);
        toolbar.initComponents();
        
        wordsList = toolbar.getJList();
        wordsList.addListSelectionListener(this);
        
        displayAddButton = toolbar.getAddButton();
        displayDeleteButton = toolbar.getDeleteButton();
        JButton ascButton = toolbar.getAscButton();
        JButton descButton = toolbar.getDescButton();
        ascButton.addActionListener(this);
        descButton.addActionListener(this);
                
        tbScrollPane = new JScrollPane(toolbar);
        tbScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tbScrollPane.setSize(screenSize.width/3, screenSize.height);
        
        add(tbScrollPane, BorderLayout.WEST);
        setVisible(true);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            setSelection(((JList) e.getSource()).getSelectedValue().toString());
            remove(winScrollPane);
            makeWindow();
        }
    }
    
    public void addMethod() {
        remove(winScrollPane);
        setSelection("Add");
        makeWindow();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        wordsList = toolbar.getJList();
        wordsList.addListSelectionListener(this);        
        JButton clicked = (JButton) e.getSource();
        switch (clicked.getText()) {
            case "Asc":
                setDisplayWords(Utils.sortWords(displayWords));
                remove(tbScrollPane);
                makeToolbar();
                break;
            case "Desc":
                setDisplayWords(Utils.sortWordsDesc(displayWords));
                remove(tbScrollPane);
                makeToolbar();
                break;
            case "Add":
                addMethod();
            case "Delete":
                //pop.show();
            default:
        }
    }
}
