import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Toolbar extends JPanel {
    
    private Word[] tbWords;
    private JList wordsList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton ascButton;
    private JButton descButton;
    private JTextField search;
    private GridBagConstraints gbc = new GridBagConstraints();
    
    public Toolbar(Word[] displayWords) {
        tbWords = displayWords;
    }
    
    public JButton getAddButton() {
        return addButton;
    }
    
    public JButton getDeleteButton() {
        return deleteButton;
    }
    
    public JButton getAscButton() {
        return ascButton;
    }
    
    public JButton getDescButton() {
        return descButton;
    }
    
    public JTextField getSearch() {
        return search;
    }
    
    public Word[] getTBWords() {
        return tbWords;
    }

    public JList getWordsList() {
        return wordsList;
    }
    
    public void setTBWords(Word[] tbWords) {
        this.tbWords = tbWords;
    }
    
    public void initComponents() {
        removeAll();
        revalidate();
        
        setLayout(new GridBagLayout());
        
        addButton = new JButton("Add");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);
        deleteButton = new JButton("Delete");
        gbc.gridx = 1;
        add(deleteButton, gbc);
        
        JLabel searchTitle = new JLabel("Search:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(searchTitle, gbc);
        
        search = new JTextField("");
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(search, gbc);
        
        ascButton = new JButton("Asc");
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(ascButton, gbc);
        descButton = new JButton("Desc");
        gbc.gridx = 1;
        add(descButton, gbc);
        
        makeWords();
        setVisible(true);
    }
    
    public void makeWords() {
        wordsList = new JList(Utils.parseWords(tbWords));
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(wordsList, gbc);
        setVisible(true);
    }
}