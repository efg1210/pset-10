
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(word, gbc);
        
        for (int i = 0; i < 20; i++) {
            String textPOS = "partOfSpeech";
            String textDef = "definition";
            
            JLabel pos = new JLabel(textPOS);
            gbc.gridx = 0;
            gbc.gridy = i+1;
            add(pos, gbc);
            
            i++;
            JLabel def = new JLabel(textDef);
            gbc.gridx = 1;
            gbc.gridy = i+1;
            add(def, gbc);
        }
        for (int i = 0; i < 2; i++) {
            JLabel syn = new JLabel("synonyms");
            gbc.gridx = 0;
            gbc.gridy = 22+i;
            add(syn, gbc);
        }
    }
}
