package drytron.controller;

import com.sun.mail.imap.ACL;
import drytron.dao.JogosRepository;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.GeneroJogos;
import drytron.dto.Jogos;
import drytron.dto.PlataformaJogos;
import drytron.dto.UfEnum;
import drytron.util.Dicionario;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author dayon
 */
public class FxmlCadastroClientesController implements Initializable {

    public FxmlCadastroClientesController() {

    }

    public FxmlCadastroClientesController(Jogos j) {
        tfNome.setText(j.getNome());
        /*tfDesenvolvedor.clear();
        tfPublicador.clear();
        tfEstoque.clear();
        tfPreco.clear();
        tfIdioma.clear();*/
    }

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private ComboBox<UfEnum> cbUf;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfComplemento;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfLocalidade;

    @FXML
    private TextField tfLongradouro;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTel;

    /*@FXML
    void btnClickCadastrarAction(ActionEvent event) {
        Clientes c = new Clientes();

        c.setNome(tfNome.getText());
        c.setCpf(tfCpf.getText());
        c.setTelefone(tfTel.getText());
        c.setEmail(tfEmail.getText());
        Endereco e = new Endereco();
        e.setCep(tfCep.getText());
        e.setComplemento(tfComplemento.getText());
        e.setLogradouro(tfLongradouro.getText());
        e.setBairro(tfBairro.getText());
        e.setLocalidade(tfLocalidade.getText());
        e.setUF(cbUf.getValue());

        JogosRepository jr = new JogosRepository();
        jr.insere(c);
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfNome.clear();
        tfDesenvolvedor.clear();
        tfPublicador.clear();
        tfEstoque.clear();
        tfPreco.clear();
        tfIdioma.clear();
        dpLancamento.setValue(null);
        cbGenero.setValue(null);
        cbPlataforma.setValue(null);
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbUf.setItems(FXCollections.observableArrayList(UfEnum.values()));

        cbUf.getItems().addAll();

    }

}
