package drytron.controller;

import drytron.dao.FuncionariosRepository;
import drytron.dto.Funcionarios;
import drytron.main.Drytron;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FxmlAutenticacaoController implements Initializable {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private TextField tfUsuario;

    private static Stage stage = new Stage();

    @FXML
    void btnClickEntrarAction(ActionEvent event) {

        if (((Util.getUsuario().getNome().equals(tfUsuario.getText())) && (Util.getUsuario().getSenha().equals(tfSenha.getText())))) {
            Parent root = null;
            try {
                FxmlMainAdminController.getStage().close();
                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource(Util.getTelaUrl()));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException ex) {
                System.out.println("ERRO EM AUTENTICAR  :" + ex.getMessage());
                if (root == null) {
                    System.out.println("Nao Acho a tela ");
                }
            }
        }
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfUsuario.setText("");
        tfSenha.setText("");
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
