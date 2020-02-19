import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import com.google.gson.Gson;

public class Applications {
    public static void main(String[] args) {
//        Form form = new Form();
//        form.run(args);
//        HelloWorld hello = new HelloWorld();
//        hello.run(args);
        app(args);
    }
    
    private static void app(String[] args) {
        Word[] words = getWords();
        
        Display display = new Display();
        display.run(args, words);
    }
    
    private static Word[] sortWords(Word[] words) {
        Arrays.sort(words, (a, b) -> a.getWord().compareTo(b.getWord()));
        return words;
    }
    
    private static Word[] getWords() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(System.getProperty("user.dir") + File.separator + "words.json")) {
            Word[] words = gson.fromJson(reader, Word[].class);
            sortWords(words);
            return words;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}