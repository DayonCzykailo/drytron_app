package drytron.controller;

import drytron.util.Mensagens;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FxmlAutenticacaoController implements Initializable {

    @FXML
    private PasswordField tfSenha;

    @FXML
    private TextField tfUsuario;


    @FXML
    void btnClickEntrarAction(ActionEvent event) {
        boolean logou = false;
        if (((Util.getUsuario().getNome().equals(tfUsuario.getText())) && (Util.getUsuario().getSenha().equals(tfSenha.getText())))) {
            try {
                FxmlFactory.fecharTelaSecundario();
                logou = true;
                FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource(Util.getTelaUrl())));

            } catch (IOException ex) {
                System.out.println("ERRO EM AUTENTICAR  :" + ex.getMessage());

            }
        }
        if (!logou) {
            Mensagens.mensagemInfo("LOGIN INVÁLIDO!!!", "VERIFIQUE SE SUA SENHA OU NOME DE USUARIO ESTÃO INCORRETOS");
        }
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfUsuario.setText("");
        tfSenha.setText("");
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlFactory.fecharTelaSecundario();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
