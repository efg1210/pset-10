
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
    private JButton plus;
    
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
            //System.out.println(word.getWord());
        }
    }
    
    public void setWords(Word[] words) {
        this.words = words;
    }
    
    public void saveWords() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(System.getProperty("user.dir") + File.separator + "words.json")) {
            gson.toJson(words, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Word[] getWords() {
        return this.words;
    }
    
    private void getWordsFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(System.getProperty("user.dir") + File.separator + "words.json")) {
            setWords(gson.fromJson(reader, Word[].class));
            Utils.sortWords(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addWord(Word newWord) {
        //System.out.println("last in words (1): " + getWords()[getWords().length - 1].getWord());
        Word[] newWordsList = Arrays.copyOf(getWords(), getWords().length + 1);
        newWordsList[newWordsList.length - 1] = newWord;
        //System.out.println("last in words (2): " + newWordsList[newWordsList.length - 1].getWord());
        setWords(newWordsList);
        Utils.sortWords(getWords());
        //System.out.println("last in words (2): " + words[words.length - 1].getWord());
        saveWords();
        display.setSelection(newWord.getWord());
        display.setDisplayWords(getWords());
        display.makeToolbar();
        display.makeWindow();
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
                submit = display.getWindow().getSubmitBtn();
                submit.addActionListener(this);
                plus = display.getWindow().getPlusBtn();
                plus.addActionListener(this);
                break;
            case "Delete":
                System.out.println("Delete");
                System.out.println(display.deleteMethod());
                break;
            case "+":
                //System.out.println("+");
                display.getWindow().plusButtonPressed();
                break;
            case "Submit":
                //System.out.println("one");
                display.getWindow().makeWord();
                addWord(display.getWindow().getWinWord());
                //System.err.println("done.");
                break;
            default: System.out.println(clicked.getText());
        }
    }
}