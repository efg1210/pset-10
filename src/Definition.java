
public class Definition {
    String definition;
    String partOfSpeech;
    
    public Definition(String definition, String partOfSpeech) {
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
    }
    
    public String getDefinition() {
        return definition;
    }
    
    public String getPart() {
        return partOfSpeech;
    }
}