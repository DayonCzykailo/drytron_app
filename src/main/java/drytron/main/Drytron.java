/*
 IGNORAR 

---->>>>>          cd /d D:\Download\NetBeansProject\drytron_app

---->>>>> dayon@LAPTOP-UIKP5HD1 MINGW64 ~
$ cd /D/Download/NetBeansProject/drytron_app
 */
package drytron.main;

import java.io.IOException;
import static java.lang.System.exit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author patyu
 */
public class Drytron extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());

            stage.setTitle("Sistema de Vendas-Drytron");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            System.out.println("DEU CERTO");
            setStage(stage);
    }     

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             launch(args);
        
    }

}
