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
    private JList wordsList;
    
    public Toolbar(Word[] displayWords) {
        tbWords = displayWords;
    }
    
    public JList getJList() {
        return wordsList;
    }
    
    public void initComponents() {
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
        
        makeWords(gbc);
        
        //return selection;
    }
    
    public void makeWords(GridBagConstraints gbc) {
        //JList can sort itself
        wordsList = new JList(Utils.parseWords(tbWords));
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
                
        //wordsList.addListSelectionListener(this);
        
        add(wordsList, gbc);
    }
    
//    public void valueChanged(ListSelectionEvent e) {
//        if (!e.getValueIsAdjusting()) {
//            selection = ((JList) e.getSource()).getSelectedValue().toString();
//            //initComponents();
//        }
//    }
    
    public void actionPerformed(ActionEvent e) {
//        final Container parent = this;
//        parent.dispatchEvent(e);
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