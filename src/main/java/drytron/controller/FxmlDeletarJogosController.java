package drytron.controller;

import drytron.repository.JogosRepository;
import drytron.dto.Jogos;
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
 * @author Dayon
 */
public class FxmlDeletarJogosController implements Initializable {


    @FXML
    private TextField tfId;

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        JogosRepository jr = new JogosRepository();
        jr.remove(Integer.parseInt(tfId.getText()));
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Util.getJogos() != null) {
            Jogos j = Util.getJogos();
            tfId.setText(Long.toString(j.getId()));
            Util.setJogos(null);
        }
    }

}
