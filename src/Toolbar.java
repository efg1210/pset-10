import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener {
    
    private Word[] tbWords;
    
    public Toolbar(Word[] displayWords) {
        tbWords = displayWords;
    }
    
    public Word initComponents() {
        
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
    
    public Word makeWords(GridBagConstraints gbc) {
        //JList can sort itself
        JList jList = new JList(tbWords);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 || e.getClickCount() == 2) {
                   String selection = (String) jList.getSelectedValue();
                   for (Word word: tbWords) {
                       if(word.getWord().equals(selection)) {
                           return word;
                       }
                   }
                 }
            }
        };
        jList.addMouseListener(mouseListener);
        
        add(jList, gbc);
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
        default: System.out.println("i don't actually know if this is possible right now");
        }
    }
}