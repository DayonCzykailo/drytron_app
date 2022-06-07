package drytron.controller;

import drytron.repository.JogosRepository;
import drytron.model.GeneroJogos;
import drytron.dto.Jogos;
import drytron.model.PlataformaJogos;
import drytron.util.Dicionario;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author dayon
 */
public class FxmlCadastroJogosController implements Initializable {


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
        
        if (Mensagens.mensagemConfirmar("CONFIRMAR CADASTRO", "TEM CERTEZA QUE OS DADOS ESTÃO CORRETOS?", Mensagens.SIM, Mensagens.NAO).equals(Mensagens.SIM)) {
            JogosRepository jr = new JogosRepository();
            jr.insere(j);
            FxmlFactory.fecharTelaSecundario();
        }
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
        FxmlFactory.fecharTelaSecundario();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.mascaraNumeroInteiro(tfEstoque);
        Mascaras.mascaraNumero(tfPreco);

        cbGenero.setItems(FXCollections.observableArrayList(GeneroJogos.values()));
        cbPlataforma.setItems(FXCollections.observableArrayList(PlataformaJogos.values()));

        cbGenero.getItems().addAll();
        cbPlataforma.getItems().addAll();

    }

}
