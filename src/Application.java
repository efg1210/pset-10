
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.JButton;

import com.google.gson.*;

public class Application implements ActionListener {
    
    private Word[] words;
    private Display display;
    
    private JButton appAddButton;
    private JButton appDeleteButton;
    private JButton submit;
    
    public Application() {
        getWordsFile();
        display = new Display(words);
        appAddButton = display.getAddButton();
        appAddButton.addActionListener(this);
        appDeleteButton = display.getDeleteButton();
        appDeleteButton.addActionListener(this);
    }
    
    public void printAll() {
        for (Word word: words) {
            System.out.println(word.getWord());
        }
    }
    
    public void saveWords() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(System.getProperty("user.dir") + File.separator + "words.json")) {
            gson.toJson(words, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Word[] getWordsObj() {
        return this.words;
    }
    
    private void getWordsFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(System.getProperty("user.dir") + File.separator + "words.json")) {
            this.words = gson.fromJson(reader, Word[].class);
            Utils.sortWords(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addWord(Word newWord) {
        System.out.println("last in words (1): " + words[words.length]);
        words = Arrays.copyOf(words, words.length + 1);
        words[words.length] = newWord;
        System.out.println("last in words (2): " + words[words.length]);
    }
    
    private void runDisplay() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                display.setVisible(true);
            }
        });
    }
    
    public static void main(String[] args) {
        Application app = new Application();
        app.runDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton clicked = (JButton) e.getSource();
        switch (clicked.getText()) {
            case "Add":
                display.addMethod();
                break;
            case "Delete":
                //pop.show();
                System.out.println("Delete");
                break;
            case "Submit":
                System.out.println("Submit");
                display.getWindow().makeWord();
                System.out.println("Submit 2");
                addWord(display.getWindow().getWinWord());
                break;
            default:
        }
    }
}