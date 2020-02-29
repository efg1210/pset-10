import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class Toolbar extends JPanel implements ActionListener {
    
    private Word[] tbWords;
    private String selection;
    
    public Toolbar(Word[] displayWords) {
        tbWords = displayWords;
    }
    
    public String initComponents() {
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        gbc.gridx = 1;
        add(deleteButton, gbc);
        
        JTextField search = new JTextField("Search");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(search, gbc);
        
        JButton ascButton = new JButton("Asc");
        ascButton.addActionListener(this);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(ascButton, gbc);
        JButton descButton = new JButton("Desc");
        descButton.addActionListener(this);
        gbc.gridx = 1;
        add(descButton, gbc);
        
        return makeWords(gbc);
    }
    
    public String makeWords(GridBagConstraints gbc) {
        
        final Container parent = this;
        
        //JList can sort itself
        JList wordsList = new JList(Utils.parseWords(tbWords));
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        
        Word[] word = new Word[1];
        word[0] = null;
        wordsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    //parent.dispatchEvent(e);
                    //System.out.println("test: " + ((JList) e.getSource()).getText());
                    selection = wordsList.getSelectedValue().toString();
                    System.out.println("in: " + selection);
                }
            }
        });
        
        System.out.println("out: " + selection);
        //System.out.println("e: " + parent.e);

        add(wordsList, gbc);
        return "hi";
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        System.out.println(clicked.getText());
        switch (clicked.getText()) {
            case "Add": //app.addWord(words); break;
            case "Asc": 
                //app.sortWords();
                //initComponents();
                break;
            case "Desc": 
                //app.sortWordsDesc();
                //initComponents();
                break;
            default:
        }
    }
}