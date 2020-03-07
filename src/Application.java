
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
    
    private void addWord(Word newWord) {
        Word[] newWordsList = Arrays.copyOf(getWords(), getWords().length + 1);
        newWordsList[newWordsList.length - 1] = newWord;
        setWords(newWordsList);
        Utils.sortWords(getWords());
        saveWords();
        display.setSelection(newWord.getWord());
        display.setDisplayWords(getWords());
        display.remove(display.getTBScrollPane());
        display.remove(display.getWinScrollPane());
        display.makeToolbar();
        display.makeWindow();
    }
    
    private void deleteWord() {
        String removed = display.getSelection();
        String[] onlyWords = Utils.parseWords(getWords());
        int index = Utils.indexOf(removed, onlyWords);
        Word[] wordsCopy = Arrays.copyOf(getWords(), getWords().length);
        Word temp = wordsCopy[index];        
        if (index == wordsCopy.length - 1) {
            wordsCopy = Arrays.copyOf(getWords(), getWords().length - 1);
        } else {
            for (int i = index + 1; i < wordsCopy.length; i++) {
                wordsCopy[i - 1] = wordsCopy[i];
            }
            wordsCopy[wordsCopy.length - 1] = temp;
            wordsCopy = Arrays.copyOf(wordsCopy, wordsCopy.length - 1);
        }
        setWords(wordsCopy);
        saveWords();
        display.setSelection(null);
        display.setDisplayWords(getWords());
        display.remove(display.getTBScrollPane());
        display.remove(display.getWinScrollPane());
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
                if (Arrays.asList(Utils.parseWords(getWords())).contains(display.getSelection())) {
                    if (display.deleteMethod()) {
                        deleteWord();
                    }
                }
                break;
            case "+":
                display.getWindow().plusButtonPressed();
                break;
            case "Submit":
                display.getWindow().makeWord();
                addWord(display.getWindow().getWinWord());
                break;
            default: System.out.println(clicked.getText());
        }
    }
}