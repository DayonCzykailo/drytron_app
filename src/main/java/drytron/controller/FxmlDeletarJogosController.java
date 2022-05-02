package drytron.controller;

import drytron.dao.JogosDAO;
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
    private AnchorPane apMain;

    @FXML
    private TextField tfNome;

    @FXML
    private Button btnDelNome;

    @FXML
    private TextField tfId;

    @FXML
    private Button btnDelId;

    @FXML
    private Button btnDelSair;
    
    @FXML
    protected void btnClickExcluirIdJogosAction(ActionEvent e){
        JogosDAO jogos = new JogosDAO();
        //jogos.deletarComId(tfId);
         System.out.println("\n\n btnClickExcluirIdJogosAction \n\n");

    }
    @FXML
    protected void btnClickExcluirNomeJogosAction(ActionEvent e){
       JogosDAO jogos = new JogosDAO();
        
      // jogos.deletarComNome(tfNome);
    System.out.println("\n\n btnClickExcluirJogosJogosAction \n\n");

    }
    /**
     * Initializes the controller class.
     */
     @FXML
    protected void btnClickSairAction(ActionEvent e){
        try {           
           // FxmlMainController.getStage().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}