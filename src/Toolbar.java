import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener {
    
    private Word[] words;
    
    public Toolbar(Word[] words) {
        this.words = words;
        initComponents();
    }
    
    private void initComponents() {
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(deleteButton, gbc);
        
        for (int i = 0; i < words.length; i++) {
            JButton button = new JButton();
            button.setText(words[i].getWord());
            button.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = (i + 1);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 2;
            add(button, gbc);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        System.out.println(clicked.getText());
    }
}