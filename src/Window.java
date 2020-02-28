
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
        word.setText("Word");
        word.setFont(new Font(getFont().getName(), getFont().getStyle(), 50));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(word, gbc);
        
        showDefs(gbc);
        showSyns(gbc);
        
        for (int i = 0; i < 2; i++) {
            JLabel syn = new JLabel("synonyms");
            gbc.gridx = 0;
            gbc.gridy = 22+i;
            add(syn, gbc);
        }
    }
    
    private void showSyns(GridBagConstraints gbc) {
        
    }
    
    private void showDefs(GridBagConstraints gbc) {
        JLabel title = new JLabel();
        title.setText("Definitions");
        title.setFont(new Font(getFont().getName(), getFont().getStyle(), 30));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(title, gbc);
        
        Definition[] defs = winWord.getDefinitions();
        for (int i = 0; i < defs.length * 2; i++) {
            if (defs[i/2] != null) {
                String textPOS = ((i/2)+1) + ". " + winWord.getWord() + " (" + defs[i/2].getPart() + ")";
                String textDef = defs[i/2].getDefinition();
                
                JLabel pos = new JLabel(textPOS);
                gbc.gridx = 0;
                gbc.gridy = i+2;
                add(pos, gbc);
                
                i++;
                JLabel def = new JLabel(textDef);
                gbc.gridx = 1;
                gbc.gridy = i+2;
                add(def, gbc);
            }
        }
    }
}
