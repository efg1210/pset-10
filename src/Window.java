
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JPanel {

    private Word winWord;
    private GridBagConstraints gbc;
    private int defFieldCount = 0;
    
    private JTextField addWord;
    private ArrayList<JTextField> defs = new ArrayList<JTextField>();
    private ArrayList<JComboBox> POSs = new ArrayList<JComboBox>();
    private JLabel synsTitle;
    private JTextField synsWord;
    private JLabel synsInstructions;
    private JLabel antsTitle;
    private JTextField antsWord;
    private JLabel antsInstructions;
    private JButton submit;
    private JButton plusButton;
    
    private String savedSyns;
    private String savedAnts;
    
    public Window(String tbWord, Word[] tbWords) {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        winWord = null;
        if (Arrays.asList(Utils.parseWords(tbWords)).contains(tbWord)) {
            winWord = getWordFromString(tbWord, tbWords);
            showWord();
        } else if (tbWord == null) {
            showDefault();
        } else if (tbWord.equals("Add")) {
            showAdd();
        } else {
            showDefault();
        }
    }
    
    public Window() {
        winWord = null;
    }
    
    public JButton getSubmitBtn() {
        return submit;
    }
    
    public JButton getPlusBtn() {
        return plusButton;
    }
    
    public Word getWinWord() {
        return winWord;
    }
    
    private void showAddSyn() {
        synsTitle = new JLabel();
        synsTitle.setText("Synonyms");
        synsTitle.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.gridy = 5 + defFieldCount;
        add(synsTitle, gbc);
        
        synsInstructions = new JLabel();
        synsInstructions.setText("Seperate with a comma and a space");
        synsInstructions.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridy = 6 + defFieldCount;
        add(synsInstructions, gbc);
        
        if (savedSyns == null) {
            synsWord = new JTextField("");
        } else {
            synsWord = new JTextField(savedSyns);
        }
        synsWord.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridy = 7 + defFieldCount;
        add(synsWord, gbc);
    }
    
    private void showAddAnt() {
        antsTitle = new JLabel();
        antsTitle.setText("Antonyms");
        antsTitle.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.gridy = 8 + defFieldCount;
        add(antsTitle, gbc);
        
        antsInstructions = new JLabel();
        antsInstructions.setText("Seperate with a comma and a space");
        antsInstructions.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridy = 9 + defFieldCount;
        add(antsInstructions, gbc);
        
        if (savedAnts == null) {
            antsWord = new JTextField("");
        } else {
            antsWord = new JTextField(savedAnts);
        }
        antsWord.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridy = 10 + defFieldCount;
        add(antsWord, gbc);
    }
    
    
    private Word showAdd() {
        savedSyns = null;
        savedAnts = null;
        JLabel title = new JLabel();
        title.setText("ADD");
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        add(title, gbc);
        
        JLabel wordTitle = new JLabel();
        wordTitle.setText("Word");
        wordTitle.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.gridy = 2;
        add(wordTitle, gbc);
        
        addWord = new JTextField("");
        addWord.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridy = 3;
        add(addWord, gbc);
        
        showAddDef();
        showAddSyn();
        showAddAnt();
        showAddSubmit();
        
        return winWord;
    }
    
    private void showAddSubmit() {        
        submit = new JButton("Submit");
        gbc.gridy = 11 + defFieldCount;
        add(submit, gbc);
    }
    
    private void showAddDef() {
        JLabel defTitle = new JLabel();
        defTitle.setText("Definitions");
        defTitle.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(defTitle, gbc);
                   
        plusButton = new JButton("+");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(plusButton, gbc);
        
        addDefFeild();
    }
    
    private void addDefFeild() {
        defFieldCount++;
        JTextField addDef = new JTextField("");
        addDef.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridx = 0;
        gbc.gridy = 4 + defFieldCount;
        add(addDef, gbc);
        defs.add(addDef);
        
        defFieldCount++;
        String[] partsOfSpeech = {"noun", "verb", "adjective", "adverb", "pronoun", "preposition", "conjunction", "interjection", "determiner"};
        JComboBox addPOS = new JComboBox(partsOfSpeech);
        gbc.gridy = 4 + defFieldCount;
        add(addPOS, gbc);
        POSs.add(addPOS);
    }
    
    private Word getWordFromString(String stringWord, Word[] tbWords) {
        for (Word word: tbWords) {
            if (word.getWord().equals(stringWord)) {
                return word;
            }
        }
        return null;
    }
    
    private void showDefault() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel welcome = new JLabel();
        welcome.setText("WELCOME");
        welcome.setFont(new Font(getFont().getName(), getFont().getStyle(), 50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(welcome, gbc);
        
        JLabel message = new JLabel("Welcome to the graphical dictionary. You can look up words, add words, delete words, and sort words. Please rate us 5 stars on Application Store\u2122.");
        message.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridy = 1;
        add(message, gbc);
    }
    
    private void showWord() {
        JLabel word = new JLabel();
        word.setText(winWord.getWord().toUpperCase());
        word.setFont(new Font(getFont().getName(), getFont().getStyle(), 50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(word, gbc);
        showAnts(showSyns(showDefs()));
        setVisible(true);
    }
    
    private void showAnts(int totalLength) {
        JLabel title = new JLabel();
        String[] ants = winWord.getAnt();
        if (ants.length > 1) {
            title.setText("Antonyms");
        } else if (ants.length == 1 && !ants[0].equals("")) {
            title.setText("Antonym");
        }
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = totalLength + 5;
        if (ants.length > 0) {
            add(title, gbc);
        }
        for (int i = 0; i < ants.length; i++) {
            JLabel ant = new JLabel(ants[i]);
            ant.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = i + totalLength + 6;
            add(ant, gbc);
        }
    }
    
    private int showSyns(int defLength) {
        JLabel title = new JLabel();
        String[] syns = winWord.getSyn();
        if (syns.length > 1) {
            title.setText("Synonyms");
        } else if (syns.length == 1 && !syns[0].equals("")) {
            title.setText("Synonym");
        }
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = defLength;
        if (syns.length > 0) {
            add(title, gbc);
        } else {
            return defLength;
        }
        
        for (int i = 0; i < syns.length; i++) {
            JLabel syn = new JLabel(syns[i]);
            syn.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = i + defLength + 5;
            add(syn, gbc);
        }
        return syns.length + 1 + defLength;
    }
    
    private int showDefs() {
        JLabel title = new JLabel();
        Definition[] defs = winWord.getDefinitions();
        if (defs.length == 1) {
            title.setText("Definition");
        } else {
            title.setText("Definitions");
        }
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(title, gbc);
        
        for (int i = 0; i < defs.length * 2; i++) {
            if (defs[i / 2] != null) {
                String textPOS = ((i / 2) + 1) + ". " + winWord.getWord() + " (" + defs[i / 2].getPart() + ")";
                String textDef = "          " + defs[i / 2].getDefinition();
                
                JLabel pos = new JLabel(textPOS);
                pos.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
                gbc.gridx = 0;
                gbc.gridy = i + 2;
                add(pos, gbc);
                
                i++;
                JLabel def = new JLabel(textDef);
                def.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
                gbc.gridx = 0;
                gbc.gridy = i + 2;
                add(def, gbc);
            }
        }
        return ((defs.length * 2) + 2);
    }

    private void deleteAddStuff() {
        remove(synsTitle);
        remove(synsInstructions);
        savedSyns = synsWord.getText();
        remove(synsWord);
        remove(antsTitle);
        remove(antsInstructions);
        savedAnts = antsWord.getText();
        remove(antsWord);
        remove(submit);
    }
    
    public boolean makeWord(Word[] words) {        
        String word = addWord.getText().toLowerCase();
        if (word != null && !word.equals("") && !Arrays.asList(Utils.parseWords(words)).contains(word)) {
            String[] partsOfSpeech = new String[POSs.size()];
            String[] definitions = new String[defs.size()];
            for (int i = 0; i < POSs.size(); i++) {
                partsOfSpeech[i] = (String) POSs.get(i).getSelectedItem();
            }
            for (int i = 0; i < defs.size(); i++) {
                definitions[i] = (String) defs.get(i).getText();
            }
            
            String[] synonyms = null;
            String[] antonym = null;
            if (!synsWord.getText().equals("Seperate with a comma and a space")) {
                synonyms = synsWord.getText().split(", ");
            }
            if (!antsWord.getText().equals("Seperate with a comma and a space")) {
                antonym = antsWord.getText().split(", ");
            }
            if (synonyms == null) {
                synonyms = new String[]{""};
            }
            if (antonym == null) {
                antonym = new String[]{""};
            }            
            winWord = new Word(word, definitions, partsOfSpeech, synonyms, antonym);
            return true;
        } else {
            return false;
        }
    }
    
    public void plusButtonPressed() {
        deleteAddStuff();
        addDefFeild();
        showAddSyn();
        showAddAnt();
        showAddSubmit();
        revalidate();
    }
}
