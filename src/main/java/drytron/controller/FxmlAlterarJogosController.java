package drytron.controller;

import drytron.repository.JogosRepository;
import drytron.model.GeneroJogos;
import drytron.dto.Jogos;
import drytron.model.PlataformaJogos;
import drytron.repository.ClientesRepository;
import drytron.util.Dicionario;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FxmlAlterarJogosController implements Initializable {

    @FXML
    private Button btnAlterar;

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
    private TextField tfID;

    @FXML
    private TextField tfIdioma;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfPublicador;

    @FXML
    void tfClickPesquisarIdAction(ActionEvent event) {
        JogosRepository jr = new JogosRepository();
        Jogos j = jr.pesquisaPeloId(Integer.parseInt(tfID.getText()));

        if (j != null) {
            tfNome.setText(j.getNome());
            tfDesenvolvedor.setText(j.getDesenvolvedor());
            tfPublicador.setText(j.getPublicador());
            tfEstoque.setText(Integer.toString(j.getEstoque()));
            tfPreco.setText(Float.toString(j.getPreco()));
            tfIdioma.setText(j.getIdioma());
            cbGenero.setValue(Dicionario.getGeneroEnum(j.getGenero()));
            cbPlataforma.setValue(Dicionario.getPlataformaEnum(j.getPlataforma()));
            dpLancamento.setValue(j.getLancamento());
            Util.setJogos(null);
        }

    }

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
        Jogos j = new Jogos();

        j.setId(Integer.parseInt(tfID.getText()));
        j.setNome(tfNome.getText());
        j.setGenero(Dicionario.getGenero(cbGenero.getValue()));
        j.setPlataforma(Dicionario.getPlataforma(cbPlataforma.getValue()));
        j.setLancamento(dpLancamento.getValue());
        j.setDesenvolvedor(tfDesenvolvedor.getText());
        j.setPublicador(tfPublicador.getText());
        j.setIdioma(tfIdioma.getText());
        j.setEstoque(Integer.parseInt(tfEstoque.getText()));
        j.setPreco(Float.parseFloat(tfPreco.getText()));

        if (Mensagens.mensagemConfirmar("CONFIRMAR ALTERAÇÃO", "TEM CERTEZA QUE OS DADOS ESTÃO CORRETOS?", Mensagens.SIM, Mensagens.NAO).equals(Mensagens.SIM)) {
            JogosRepository jr = new JogosRepository();
            jr.alterar(j);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.mascaraNumeroInteiro(tfID);
        Mascaras.mascaraNumeroInteiro(tfEstoque);
        Mascaras.mascaraNumero(tfPreco);
        Mascaras.mascaraData(dpLancamento);

        cbGenero.setItems(FXCollections.observableArrayList(GeneroJogos.values()));
        cbPlataforma.setItems(FXCollections.observableArrayList(PlataformaJogos.values()));

        cbGenero.getItems().addAll();
        cbPlataforma.getItems().addAll();

        if (Util.getJogos() != null) {
            Jogos j = Util.getJogos();

            tfID.setText(Long.toString(j.getId()));
            tfNome.setText(j.getNome());
            tfDesenvolvedor.setText(j.getDesenvolvedor());
            tfPublicador.setText(j.getPublicador());
            tfEstoque.setText(Integer.toString(j.getEstoque()));
            tfPreco.setText(Float.toString(j.getPreco()));
            tfIdioma.setText(j.getIdioma());
            cbGenero.setValue(Dicionario.getGeneroEnum(j.getGenero()));
            cbPlataforma.setValue(Dicionario.getPlataformaEnum(j.getPlataforma()));
            dpLancamento.setValue(j.getLancamento());
            Util.setJogos(null);
        }

    }

}
