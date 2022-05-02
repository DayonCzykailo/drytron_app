package drytron.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dayon
 */
public class FxmlCadastroJogosController implements Initializable  {
    
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
    private ImageView ivSalvar;

    @FXML
    private ImageView ivSair;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
