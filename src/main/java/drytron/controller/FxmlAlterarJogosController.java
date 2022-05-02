package drytron.controller;

import drytron.dao.JogosDAO;
import drytron.main.Drytron;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FxmlAlterarJogosController implements Initializable {
 @FXML
    private AnchorPane apMain;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfGen;

    @FXML
    private TextField tfPla;

    @FXML
    private TextField tfLan;

    @FXML
    private TextField tfDes;

    @FXML
    private TextField tfPub;

    @FXML
    private TextField tfIdi;

    @FXML
    private TextField tfPre;

    @FXML
    private TextField tfEst;

    @FXML
    private Button btnAlterar;

    @FXML
    private ImageView ivSalvar;

    @FXML
    private Button btnSair;

    @FXML
    private ImageView ivSair;

    @FXML
    private TextField tfAltId;

    @FXML
    private TextField tfAltNome;

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
       JogosDAO jogo = new JogosDAO();
        try {
           // jogo.alterar(tfNome, tfGen, tfPla, tfLan, tfDes, tfPub, tfIdi, tfPre, tfEst, tfAltId);           
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
       //FxmlMainController.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}