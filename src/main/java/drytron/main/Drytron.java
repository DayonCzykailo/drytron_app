/*
 IGNORAR 

---:>>>>          cd /d D:\Download\NetBeansProject\drytron_app




---:>>>> dayon@LAPTOP-UIKP5HD1 MINGW64 ~
 cd /D/NetBeansProjects/drytron_app/

*/
package drytron.main;

import drytron.controller.FxmlFactory;
import java.io.IOException;
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

    @Override
    public void start(Stage stage) throws IOException {
        try {
            //root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlCadastrarAdmin.fxml"));

            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlLogin.fxml")),getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
            
        } catch (IOException e) {
            System.out.println("ERRO em iniciar o programa. (" + e.getMessage() + ")");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
