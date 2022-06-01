package drytron.controller;

import drytron.dao.ClientesRepository;
import drytron.dao.FuncionariosRepository;
import drytron.dao.JogosDAO;
import drytron.dao.JogosRepository;
import drytron.dto.Clientes;
import drytron.dto.Funcionarios;
import drytron.dto.Jogos;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FxmlDeletarAdminController  implements Initializable {

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfId;

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        FuncionariosRepository cr = new FuncionariosRepository();
        cr.remove(Long.parseLong(tfId.getText()));
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfId.clear();
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    @FXML
    void tfClickPesquisarIdAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Util.getFuncionarios()!= null) {
            Funcionarios c = Util.getFuncionarios();
            tfId.setText(Long.toString(c.getId()));
            Util.setFuncionarios(null);
        }
    }

}
