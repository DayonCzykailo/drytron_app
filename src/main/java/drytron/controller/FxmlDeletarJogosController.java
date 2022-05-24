package drytron.controller;

import drytron.dao.JogosDAO;
import drytron.dao.JogosRepository;
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

/**
 * FXML Controller class
 *
 * @author patyu
 */
public class FxmlDeletarJogosController implements Initializable {
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
        JogosRepository jr = new JogosRepository();
        jr.remove(Integer.parseInt(tfId.getText()));
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfId.clear();
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
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
