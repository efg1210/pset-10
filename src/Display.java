
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display extends Application {

    //private Word[] words;
    
    @Override
    public void start(Stage primaryStage) {
        Applications app = new Applications();
        Word[] words = app.getWords();
        
        primaryStage.setTitle("eDictionary");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
                
        for (int i = 0; i < words.length-1; i++) {
            Text scenetitle = new Text();
            scenetitle.setText(words[i].getWord());
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
            grid.add(scenetitle, 0, i, 2, 1);
            //System.out.println(words[i].getWord());
        }
        
        ScrollBar s = new ScrollBar();
        s.setMin(0);  
        s.setMax(200);  
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        grid.add(s, 2, 0);
        
        Scene scene = new Scene(grid, 500, 600);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
//    public static void main(String[] args) { 
//        launch(args);
//  }
}