package drytron.controller;

import drytron.dao.FuncionariosRepository;
import drytron.dto.Cargo;
import drytron.dto.Funcionarios;
import drytron.email.Email;
import drytron.main.Drytron;
import drytron.util.Mensagens;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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

public class FxmlEsqueciMinhaSenhaController implements Initializable {

    private static Stage stage = new Stage();
    String uuid;
    FuncionariosRepository cr = new FuncionariosRepository();
    List<Funcionarios> lista = cr.listaTodos();

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfEmail;

    @FXML
    void btnClickSairAction(ActionEvent event) {
        Parent root;
        try {
            Drytron.getStage().close();
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlLogin.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
            stage.setTitle("Sistema de Vendas-Drytron");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
           
        } catch (IOException e) {
            System.out.println("ERRO em iniciar o programa. (" + e.getMessage() + ")");
        }
    }

    @FXML
    void btnClickbtnVerificarAction(ActionEvent event) {
        if (tfCodigo.getText().equals(uuid)) {
            if (Util.getUsuario().getCargo().equals(Cargo.ADMINISTRADOR) || Util.getUsuario().getCargo().equals(Cargo.GERENTE)) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainAdmin.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/drytron/css/cssfxmlmainAdmin.css").toExternalForm());
                    stage.setTitle("Sistema de Vendas-Drytron");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    Drytron.getStage().close();
                } catch (IOException ex) {
                    System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");
                }
            } else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
                    stage.setTitle("Sistema de Vendas-Drytron");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    Drytron.getStage().close();
                } catch (IOException ex) {
                    System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");
                }
            }
        } else {
            Mensagens.mensagemAlerta("CÓDIGO INSERIDO INVÁLIDO", "O CÓDIGO DE RECUPERAÇÃO INSERIDO ESTÁ INCORRETO");
        }
    }

    @FXML
    void tfEmailAction(ActionEvent event) {
        for (int i = 0; i < lista.size(); i++) {
            if ((lista.get(i).getEmail().equals(tfEmail.getText()))) {
                Util.setUsuario(lista.get(i));
                Email.mandar(tfEmail.getText(), uuid);
                return;
            }
        }

        Mensagens.mensagemAlerta("E-MAIL INVÁLIDO", "INSIRA O E-MAIL DO FUNCIONARIO EM QUESTÃO");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uuid = UUID.randomUUID().toString();

    }
}