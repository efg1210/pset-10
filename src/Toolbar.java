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
        //GridBagConstraints gbc = new GridBagConstraints();
        
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
//        search.getDocument().addDocumentListener(new DocumentListener() {
//            public void changedUpdate(DocumentEvent e) {
//                search();
//            }
//            public void removeUpdate(DocumentEvent e) {
//                search();
//            }
//            public void insertUpdate(DocumentEvent e) {
//                search();
//            }
//
//            public void search() {
//                String searchTerm = search.getText();
//                ArrayList<Word> filteredWords = new ArrayList<Word>();
//                ArrayList<Integer> sort = new ArrayList<Integer>();
//                ArrayList<Integer> sorted = new ArrayList<Integer>();
//                
//                for (Word word: tbWords) {
//                    if (word.getWord().contains(searchTerm)) {
//                        filteredWords.add(word);
//                        sort.add(word.getWord().indexOf(searchTerm));
//                        sorted.add(word.getWord().indexOf(searchTerm));
//                    }
//                }
//                Collections.sort(sorted);
//                
//                ArrayList<Word> sortedWords = new ArrayList<Word>();
//                while(filteredWords.size() > 0) {
//                    for (int i = 0; i < sort.size(); i++) {
//                        if (sorted.get(0) == sort.get(i)) {
//                            sortedWords.add(filteredWords.get(i));
//                            sorted.remove(0);
//                            sort.remove(i);
//                            filteredWords.remove(i);
//                        }
//                    }
//                }
//                Word[] outputWords = new Word[sortedWords.size()];
//                for (int i = 0; i < outputWords.length; i++) {
//                    outputWords[i] = sortedWords.get(i);
//                }
//                setTBWords(outputWords);
//                remove(wordsList);
//                makeWords();
//                revalidate();
//                //initComponents();
//            }
//        });
        
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