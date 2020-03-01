
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JPanel {

    private Word winWord;
    private GridBagConstraints gbc;
    
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
    
    private Word showAdd() {
        JLabel title = new JLabel();
        title.setText("ADD");
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);
        
        JLabel wordTitle = new JLabel();
        wordTitle.setText("Word");
        wordTitle.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(wordTitle, gbc);
        
        JTextField addWord = new JTextField("New word");
        addWord.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(addWord, gbc);
        
        showAddDef();
        
        return winWord;
    }
    
    private int showAddDef() {
        JLabel defTitle = new JLabel();
        defTitle.setText("Definitions");
        defTitle.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(defTitle, gbc);
                   
        JTextField defCounter = new JTextField("Number of definitions (press enter)");
        
        defCounter.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(defCounter, gbc);
        
        JTextField addDef = new JTextField("New definition");
        addDef.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(addDef, gbc);
        JTextField addPOS = new JTextField("New part of speech");
        addPOS.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(addPOS, gbc);
        
        return 6;
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
        } else if (ants.length == 1) {
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
        } else if (syns.length == 1) {
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
}
