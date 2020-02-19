import java.io.*;
import java.util.*;
import com.google.gson.Gson;

public class Word {
    private String word;
    private Definition[] definitions;
    private String[] synonyms;
    private String[] antonyms;
    
    public Word(String word, Definition[] definitions, String[] synonyms, String[] antonyms) {
        this.word = word;
        this.definitions = definitions;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }
    
    public Word(String word, String definition, String partOfSpeech, String[] synonyms, String[] antonyms) {
        this.word = word;
        this.definitions = new Definition[1];
        definitions[0] = new Definition(definition, partOfSpeech);
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }
    
    public String getWord() {
        return word;
    }
    
    public String[] getSyn() {
        return synonyms;
    }
    
    public String[] getAnt() {
        return antonyms;
    }
    
    public Definition[] getDefinitions() {
        return definitions;
    }
}