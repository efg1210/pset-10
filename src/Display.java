import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Display extends JFrame implements ListSelectionListener, ActionListener, DocumentListener {
    private static final long serialVersionUID = 1L;
    
    private Word[] displayWords;
    private JScrollPane tbScrollPane;
    private JScrollPane winScrollPane;
    private String selection = null;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
    
    public Window getWindow() {
        return window;
    }
    
    public String getSelection() {
        return selection;
    }
    
    public Word[] getDisplayWords() {
        return displayWords;
    }
    
    public void setDisplayWords(Word[] words) {
        this.displayWords = words;
    }
    
    public void setSelection(String newSelection) {
        selection = newSelection;
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setTitle("eDictionary");
        setLayout(new BorderLayout());
        
        makeToolbar(displayWords);
        makeWindow();
        
        setSize(screenSize.width/2, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }
    
    public void resetWordsList() {
        wordsList = toolbar.getWordsList();
        wordsList.addListSelectionListener(this);
    }
    
    public void makeWindow() {
        window = new Window(selection, displayWords);
        winScrollPane = new JScrollPane(window);
        winScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        winScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);        
        add(winScrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public void makeToolbar(Word[] toolbarWords) {
        toolbar = new Toolbar(toolbarWords);
        toolbar.initComponents();
        
        displayAddButton = toolbar.getAddButton();
        displayDeleteButton = toolbar.getDeleteButton();
        JButton ascButton = toolbar.getAscButton();
        JButton descButton = toolbar.getDescButton();
        ascButton.addActionListener(this);
        descButton.addActionListener(this);
        
        toolbar.getSearch().getDocument().addDocumentListener(this);
        
        resetWordsList();
                
        tbScrollPane = new JScrollPane(toolbar);
        tbScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tbScrollPane.setSize(screenSize.width/3, screenSize.height);
        
        add(tbScrollPane, BorderLayout.WEST);
        setVisible(true);
    }

    private void search() {
        String searchTerm = toolbar.getSearch().getText();
        ArrayList<Word> filteredWords = new ArrayList<Word>();
        ArrayList<Integer> sort = new ArrayList<Integer>();
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        
        for (Word word: getDisplayWords()) {
            if (word.getWord().contains(searchTerm)) {
                filteredWords.add(word);
                sort.add(word.getWord().indexOf(searchTerm));
                sorted.add(word.getWord().indexOf(searchTerm));
            }
        }
        Collections.sort(sorted);
        
        ArrayList<Word> sortedWords = new ArrayList<Word>();
        while(filteredWords.size() > 0) {
            for (int i = 0; i < sort.size(); i++) {
                if (sorted.get(0) == sort.get(i)) {
                    sortedWords.add(filteredWords.get(i));
                    sorted.remove(0);
                    sort.remove(i);
                    filteredWords.remove(i);
                }
            }
        }
        Word[] outputWords = new Word[sortedWords.size()];
        for (int i = 0; i < outputWords.length; i++) {
            outputWords[i] = sortedWords.get(i);
        }
        toolbar.setTBWords(outputWords);
        toolbar.remove(toolbar.getWordsList());
        toolbar.makeWords();
        
        resetWordsList();
        
        toolbar.revalidate();
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            setSelection(((JList) e.getSource()).getSelectedValue().toString());
            remove(winScrollPane);
            makeWindow();
        }
    }
    
    public boolean deleteMethod() {
        int result = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete the word \"" + selection + "\"?",
            "",
            JOptionPane.YES_NO_OPTION);
        return (result == 0) ? true : false;
    }
    
    public void addMethod() {
        remove(winScrollPane);
        setSelection("Add");
        makeWindow();
    }
    
    public JScrollPane getTBScrollPane() {
        return tbScrollPane;
    }
    
    public JScrollPane getWinScrollPane() {
        return winScrollPane;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        resetWordsList();        
        JButton clicked = (JButton) e.getSource();
        switch (clicked.getText()) {
            case "Asc":
                setDisplayWords(Utils.sortWords(displayWords));
                remove(tbScrollPane);
                makeToolbar(displayWords);
                break;
            case "Desc":
                setDisplayWords(Utils.sortWordsDesc(displayWords));
                remove(tbScrollPane);
                makeToolbar(displayWords);
                break;
//            case "Add":
//                addMethod();
//            case "Delete":
//                //pop.show();
            default:
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        search();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        search();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        search();
    }
}
