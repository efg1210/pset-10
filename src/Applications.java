
import java.io.*;
import java.util.*;
import com.google.gson.*;

public class Applications {
    public static void main(String[] args) {
//        Form form = new Form();
//        form.run(args);
//        HelloWorld hello = new HelloWorld();
//        hello.run(args);
        
        HelloWorldSwing hello = new HelloWorldSwing();
        hello.run();
        
//        Applications app = new Applications();
//        app.app(args);
    }
    
    private void app(String[] args) {
        Word[] words = getWords();
        
//        Display display = new Display();
//        display.runDisplay(args, words);
        
        //saveWords(words);
    }
    
    public Word[] sortWords(Word[] words) {
        Arrays.sort(words, (a, b) -> a.getWord().compareTo(b.getWord()));
        return words;
    }
    
    public void saveWords(Word[] words) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(System.getProperty("user.dir") + File.separator + "words.json")) {
            gson.toJson(words, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Word[] getWords() {
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