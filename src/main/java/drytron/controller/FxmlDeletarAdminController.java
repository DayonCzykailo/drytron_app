package drytron.controller;

import drytron.repository.FuncionariosRepository;
import drytron.dto.Funcionarios;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FxmlDeletarAdminController  implements Initializable {

    @FXML
    private TextField tfId;

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        FuncionariosRepository cr = new FuncionariosRepository();
        cr.remove(Long.parseLong(tfId.getText()));
        FxmlFactory.fecharTelaSecundario();
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfId.clear();
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlFactory.fecharTelaSecundario();
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
