import java.io.*;
import java.util.*;
import com.google.gson.Gson;

public class Word {
    private String word;
    private Definition[] definitions;
    private String[] synonyms;
    private String[] antonyms;
    
    public Word() {
        
    }
    
    public String getWord() {
        return word;
    }
    
    public String getSyn() {
        return synonyms;
    }
    
    public String getAnt() {
        return antonyms;
    }
    
    public Definition[] getDefs() {
        return definitions;
    }
}