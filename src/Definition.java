
public class Definition {
    private String definition;
    private String partOfSpeech;
    
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