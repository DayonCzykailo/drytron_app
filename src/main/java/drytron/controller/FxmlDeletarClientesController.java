package drytron.controller;

import drytron.repository.ClientesRepository;
import drytron.dto.Clientes;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author patyu
 */
public class FxmlDeletarClientesController implements Initializable {

    @FXML
    private TextField tfId;

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        ClientesRepository cr = new ClientesRepository();
        cr.remove(Long.parseLong(tfId.getText()));
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
        if (Util.getClientes()!= null) {
            Clientes c = Util.getClientes();
            tfId.setText(Long.toString(c.getId()));
            Util.setClientes(null);
        }
    }

}
