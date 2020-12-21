package medica_clinica;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Rivaldo
 */

public class Medica_clinica extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLDocumentController c=new FXMLDocumentController();
        c.start(primaryStage);
    }
    
}