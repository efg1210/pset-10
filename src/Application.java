
import java.io.*;
import java.util.*;
import com.google.gson.*;

public class Application {
    
    private Word[] words;
    private Display display;
    
    public Application() {
        getWordsFile();
        display = new Display(words);
    }
    
    public void sortWords() {
        Arrays.sort(this.words, (a, b) -> a.getWord().compareTo(b.getWord()));
    }
    
    public Word[] sortWordsDesc() {
        sortWords();        
        for (int i = 0; i < words.length / 2; i++) {
            Word temp = words[i];
            words[i] = words[words.length - i - 1];
            words[words.length - i - 1] = temp;
        }
        return words;
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
            sortWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Word[] addWord() {
        return words;
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
}