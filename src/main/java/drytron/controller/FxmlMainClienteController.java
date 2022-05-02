package drytron.controller;


import drytron.dao.ClientesDAO;
import drytron.dao.JogosDAO;
import drytron.dto.Clientes;
import drytron.dto.Jogos;
import drytron.main.Drytron;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
public class FxmlMainClienteController implements Initializable {
    @FXML
    private SplitPane spMain;

    @FXML
    private AnchorPane apBtn;

    @FXML
    private VBox vbBtn;

    @FXML
    private Group gpBtn;

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
    private TableView<Clientes> tbMainCliente;

    @FXML
    private TableColumn<Clientes, Integer> tcIdCli;

    @FXML
    private TableColumn<Clientes, String> tcNomeCli;

    @FXML
    private TableColumn<Clientes, String> tcCpfCli;

    @FXML
    private TableColumn<Clientes, String> tcTelCli;

    @FXML
    private TableColumn<Clientes, String> tcEmailCli;

    @FXML
    private TableColumn<Clientes, String> tcEndCli;

    @FXML
    private TableColumn<Clientes, String> tcEstCli;
    
    private ArrayList<Clientes> list;
    private ObservableList <Clientes> obList;
    
    /*public void mostrarDadosClientes(){
        tcIdCli.setCellValueFactory(new PropertyValueFactory<>("IdCli"));
        tcNomeCli.setCellValueFactory(new PropertyValueFactory<>("NomeCli"));
        tcCpfCli.setCellValueFactory(new PropertyValueFactory<>("CpfCli"));
        tcTelCli.setCellValueFactory(new PropertyValueFactory<>("TelCli")); 
        tcEmailCli.setCellValueFactory(new PropertyValueFactory<>("EmaCli"));
        tcEndCli.setCellValueFactory(new PropertyValueFactory<>("EndCli"));
        tcEstCli.setCellValueFactory(new PropertyValueFactory<>("EstCli"));

        
             
        ClientesDAO cli = new ClientesDAO();
        list = cli.listaClientes(); 
        
        obList = FXCollections.observableArrayList(list);
        tbMainCliente.setItems(obList);
       
    }*/
    @FXML
    protected void btnClickJogosTelaAction(ActionEvent e){
       // Drytron.getStage().close();
        try{
        Drytron d = new Drytron();
        Drytron.getStage().close();
       d.start(new Stage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      

       
    } 
}
