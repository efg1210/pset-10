
import javax.swing.*;

public class Window extends JPanel {

    private Word winWord;
    
    public Window(Word tbWord) {
        winWord = tbWord;
    }
    
    private void initComponents() {
        JTextField word = new JTextField();
        word.setText("Word");
        for (int i = 0; i < 10; i++) {
            String textPOS = "partOfSpeech";
            String textDef = "definition";
            JTextField pos = new JTextField(textPOS);
            JTextField def = new JTextField(textDef);
            add(pos);
            add(def);
        }
        for (int i = 0; i <2; i++) {
            JTextField syn = new JTextField("synonyms");
        }
    }
}
