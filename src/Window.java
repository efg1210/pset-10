
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class Window extends JPanel {

    private Word winWord;
    
    public Window(Word tbWord) {
        winWord = tbWord;
    }
    
    public void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel word = new JLabel();
        word.setText(winWord.getWord().toUpperCase());
        word.setFont(new Font(getFont().getName(), getFont().getStyle(), 50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(word, gbc);
        
        showAnts(gbc, showSyns(gbc, showDefs(gbc)));
    }
    
    private void showAnts(GridBagConstraints gbc, int totalLength) {
        JLabel title = new JLabel();
        title.setText("Antonym");
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = totalLength + 5;
        add(title, gbc);
                
        String[] ants = winWord.getAnt();
        for (int i = 0; i < ants.length; i++) {
            JLabel ant = new JLabel(ants[i]);
            ant.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = i + totalLength + 6;
            add(ant, gbc);
        }
    }
    
    private int showSyns(GridBagConstraints gbc, int defLength) {
        JLabel title = new JLabel();
        title.setText("Synonyms");
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = defLength;
        add(title, gbc);
        
        String[] syns = winWord.getSyn();
        for (int i = 0; i < syns.length; i++) {
            JLabel syn = new JLabel(syns[i]);
            syn.setFont(new Font(getFont().getName(), getFont().getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = i + defLength + 5;
            add(syn, gbc);
        }
        return syns.length + 1 + defLength;
    }
    
    private int showDefs(GridBagConstraints gbc) {
        JLabel title = new JLabel();
        title.setText("Definitions");
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(title, gbc);
        
        Definition[] defs = winWord.getDefinitions();
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
