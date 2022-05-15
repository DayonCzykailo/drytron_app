package drytron.controller;

import drytron.dao.JogosRepository;
import drytron.dto.GeneroJogos;
import drytron.dto.Jogos;
import drytron.dto.PlataformaJogos;
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
public class FxmlCadastroJogosController implements Initializable {

    public FxmlCadastroJogosController() {

    }

    public FxmlCadastroJogosController(Jogos j) {
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
    private ChoiceBox<GeneroJogos> cbGenero;

    @FXML
    private ChoiceBox<PlataformaJogos> cbPlataforma;

    @FXML
    private DatePicker dpLancamento;

    @FXML
    private TextField tfDesenvolvedor;

    @FXML
    private TextField tfEstoque;

    @FXML
    private TextField tfIdioma;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfPublicador;

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        Jogos j = new Jogos();

        j.setNome(tfNome.getText());
        j.setGenero(Dicionario.getGenero(cbGenero.getValue()));
        j.setPlataforma(Dicionario.getPlataforma(cbPlataforma.getValue()));
        j.setLancamento(dpLancamento.getValue());
        j.setDesenvolvedor(tfDesenvolvedor.getText());
        j.setPublicador(tfPublicador.getText());
        j.setIdioma(tfIdioma.getText());
        j.setEstoque(Integer.parseInt(tfEstoque.getText()));
        j.setPreco(Float.parseFloat(tfPreco.getText()));

        JogosRepository jr = new JogosRepository();
        jr.insere(j);
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

        cbGenero.setItems(FXCollections.observableArrayList(GeneroJogos.values()));
        cbPlataforma.setItems(FXCollections.observableArrayList(PlataformaJogos.values()));

        cbGenero.getItems().addAll();
        cbPlataforma.getItems().addAll();

    }

}
