package drytron.controller;


import drytron.dao.JogosDAO;
import drytron.dto.Jogos;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author dayon
 */
public class FxmlMainController implements Initializable {
    @FXML
    private SplitPane spMain;

    @FXML
    private AnchorPane apBtn;

    @FXML
    private VBox vbBtn;

    @FXML
    private Button btnCad;

    @FXML
    private Button btnAlt;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnRel;

    @FXML
    private Button btnOp;

    @FXML
    private Button btnAju;

    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane apLogo;

    @FXML
    private AnchorPane apTable;

    @FXML
    private DatePicker calendario;

    @FXML
    private Button btnPes;

    @FXML
    private TextField txtPes;

    @FXML
    private TableView<Jogos> tbMain;

    @FXML
    private TableColumn<Jogos, Integer> tcId;

    @FXML
    private TableColumn<Jogos, String> tcNome;

    @FXML
    private TableColumn<Jogos, String> tcGenero;

    @FXML
    private TableColumn<Jogos, String> tcPlat;

    @FXML
    private TableColumn<Jogos, String> tcLanc;

    @FXML
    private TableColumn<Jogos, String> tcDese;

    @FXML
    private TableColumn<Jogos, String> tcPubl;

    @FXML
    private TableColumn<Jogos, String> tcIdio;

    @FXML
    private TableColumn<Jogos, Float> tcPrec;

    @FXML
    private TableColumn<Jogos, Integer> tcEsto;
    
    @FXML
    private Button btnCliente;
 
    private ArrayList<Jogos> list;
    private ObservableList <Jogos> obList;
    
    public void mostrarDadosJogos(){
        tcId.setCellValueFactory(new PropertyValueFactory<Jogos,Integer>("Id"));
        tcNome.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Nome"));
        tcGenero.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Genero"));
        tcPlat.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Plataforma"));
        tcLanc.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Lancamento"));
        tcDese.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Desenvolvedor"));
        tcPubl.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Publicador"));
        tcIdio.setCellValueFactory(new PropertyValueFactory<Jogos,String>("Idioma"));
        tcPrec.setCellValueFactory(new PropertyValueFactory<Jogos,Float>("Preco"));
        tcEsto.setCellValueFactory(new PropertyValueFactory<Jogos,Integer>("Estoque")); 
             
        JogosDAO jogo = new JogosDAO();
        list = jogo.listaJogos(); 
        
        obList = FXCollections.observableArrayList(list);
        tbMain.setItems(obList);
       
    }
    /*@FXML
    public void handleMenuItemTelaCliente() throws IOException {
        AnchorPane a =(AnchorPane) FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainCliente.fxml"));
        spMain.getChildrenUnmodifiable().setAll(a);
    }*
    //abrir tela principal
    //*Drytron d = new Drytron();
       Drytron.getStage().close();
       d.start(new Stage( ));*/
    
    private static Stage stage = new Stage();

    @FXML
    protected void btnClickClienteTelaAction(ActionEvent e) throws IOException{
        //Main.getStage().close();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainCliente.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void btnClickSairAction(ActionEvent e){
         try {
          //DrytronMain.getStage().close();
            System.out.println("Saiu");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarDadosJogos();
        } catch (Exception e) {
        }
        

       
    }
}
