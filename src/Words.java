//
////package com.howtodoinjava.demo.jsonsimple;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
// 
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class Words {
//    
//    private final String pathWord = "../words.json";
//    
//    @SuppressWarnings("unchecked")
//    public static void main(String[] args) {
//        
//        HelloWorld.launch(args);
//
//        System.out.println("testing");
//        
//        JSONParser jsonParser = new JSONParser();
//        
//        try (FileReader reader = new FileReader("words.json")) {
//            
//            Object obj = jsonParser.parse(reader);
//            
//            JSONArray wordList = (JSONArray) obj;
//            System.out.println(wordList);
//            
//            wordList.forEach(word -> parseWordObject((JSONObject) word));
//            
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private static void parseWordObject(JSONObject words) {
//        JSONObject wordObject = (JSONObject) words.get("word");
//        
//        JSONArray definitions = (JSONArray) wordObject.get("definitions");
//        System.out.println("definitions: " + definitions);
//    }
//}