package drytron.controller;

import drytron.dao.ClientesRepository;
import drytron.dto.Clientes;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FxmlLoginController implements Initializable {

    @FXML
    private ImageView FxmlLogin;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnEsqSenha;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfSenha;

    @FXML
    void btnClickSairAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnClickbtnEsqSenhaAction(ActionEvent event) {

    }
    private static Stage stage = new Stage();

    @FXML
    void btnClickbtnEntrarAction(ActionEvent event) {
        ClientesRepository cr = new ClientesRepository();
        List<Clientes> lista = cr.listaTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (((true) && (true))) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
                    stage.setTitle("Sistema de Vendas-Drytron");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");

                }

            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
