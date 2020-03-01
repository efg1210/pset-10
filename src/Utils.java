import java.util.Arrays;

public class Utils {
    public static String[] parseWords(Word[] words) {
        String[] onlyWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            onlyWords[i] = words[i].getWord();
        }
        return onlyWords;
    }
    
    public static Word[] sortWords(Word[] words) {
        Arrays.sort(words, (a, b) -> a.getWord().compareTo(b.getWord()));
        return words;
    }
    
    public static Word[] sortWordsDesc(Word[] words) {
        sortWords(words);        
        for (int i = 0; i < words.length / 2; i++) {
            Word temp = words[i];
            words[i] = words[words.length - i - 1];
            words[words.length - i - 1] = temp;
        }
        return words;
    }
}
