package drytron.controller;

import drytron.repository.FuncionariosRepository;
import drytron.model.Cargo;
import drytron.dto.Funcionarios;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FxmlLoginController implements Initializable {

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
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlEsqueciMinhaSenha.fxml")));
        } catch (IOException ex) {
            System.out.println("DEU RUIM EM btnClickbtnEntrarAction no EsqSenha :" + ex.getMessage());
        }

    }

    @FXML
    void btnClickbtnEntrarAction(ActionEvent event) {
        FuncionariosRepository cr = new FuncionariosRepository();
        List<Funcionarios> lista = cr.listaTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (((lista.get(i).getNome().equals(tfLogin.getText())) && (lista.get(i).getSenha().equals(tfSenha.getText())))) {
                Util.setUsuario(lista.get(i));
                if (lista.get(i).getCargo().equals(Cargo.ADMINISTRADOR) || lista.get(i).getCargo().equals(Cargo.GERENTE)) {
                    try {
                        FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainAdmin.fxml")));
                    } catch (IOException ex) {
                        System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");
                    }
                } else {
                    try {
                        FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml")), getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
                    } catch (IOException ex) {
                        System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
