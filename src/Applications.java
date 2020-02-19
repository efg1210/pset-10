import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

public class Applications {
    public static void main(String[] args) {
//        Form form = new Form();
//        form.run(args);
//        HelloWorld hello = new HelloWorld();
//        hello.run(args);
        getWords();
    }
    
    public static void getWords() {
        Gson gson = new Gson();
        
        try (Reader reader = new FileReader(System.getProperty("user.dir") + File.separator + "words.json")) {

            // Convert JSON File to Java Object
            Word[] word = gson.fromJson(reader, Word[].class);
            
            // print staff 
            //System.out.println(word[0].getWord());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}