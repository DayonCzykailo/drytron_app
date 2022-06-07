package drytron.controller;

import drytron.repository.VendasRepository;
import drytron.model.TableMainVendas;
import drytron.dto.Vendas;
import drytron.util.Mensagens;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

public class FxmlMainVendasController implements Initializable {

    @FXML
    private TableColumn<TableMainVendas, String> colCliente;

    @FXML
    private TableColumn<TableMainVendas, String> colData;

    @FXML
    private TableColumn<TableMainVendas, String> colDesconto;

    @FXML
    private TableColumn<TableMainVendas, Long> colId;

    @FXML
    private TableColumn<TableMainVendas, String> colProduto;

    @FXML
    private TableColumn<TableMainVendas, String> colValorFinal;

    @FXML
    private TableColumn<TableMainVendas, String> colVendedor;

    @FXML
    private TableColumn<TableMainVendas, Long> colQuantidade;

    @FXML
    private Label lbnData;

    @FXML
    private TableView<TableMainVendas> tableMain;

    @FXML
    private DatePicker dpPesquisarData;

    @FXML
    private TextField tfPesquisarProduto;

    @FXML
    void miClickMaisRecenteAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new VendasRepository().listaPorData()));
    }

    @FXML
    void miClickDevolucaoAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new VendasRepository().listaTodosPorAtivo('D')));
    }

    @FXML
    void btnClickDevolucaoAction(ActionEvent event) {
        if (tableMain.getSelectionModel().getSelectedIndex() == -1) {
            Mensagens.mensagemInfo("Dica", "Selecione uma linha");
        } else {
            Vendas v = new VendasRepository().pesquisaPeloId(tableMain.getSelectionModel().getSelectedItem().getId());
            v.setAtivo('D');

            new VendasRepository().atualiza(v);
        }
    }

    @FXML
    void btnClickAjudaAction(ActionEvent event) {
        //gerarTela("/drytron/fxml/Fxml.fxml");
    }

    @FXML
    void btnClickAtualizarAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new VendasRepository().listaTodos()));
    }

    @FXML
    void btnClickClienteTelaAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainClientes.fxml")));
        } catch (IOException ex) {
        }
    }

    @FXML
    void btnClickExibicaoAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlExibicaoVendas.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(FxmlMainVendasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void btnClickPesquisarProdutoAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new VendasRepository().listaPorProdutoPesquisa(tfPesquisarProduto.getText())));
    }

    @FXML
    void btnClickPesquisarDataAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new VendasRepository().listaPorData(dpPesquisarData.getValue().toString())));
    }

    @FXML
    void btnClickProdutosTelaAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml")));
        } catch (IOException ex) {
            Mensagens.mensagemErro("ERRO!!!", " Erro: " + ex.getMessage());
        }
    }

    @FXML
    void btnClickRelatorioAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlRelatorioVendas.fxml")));
        } catch (IOException ex) {
            Mensagens.mensagemErro("ERRO!!!", " Erro: " + ex.getMessage());
        }
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlLogin.fxml")), getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
        } catch (IOException ex) {
            Mensagens.mensagemErro("ERRO!!!", "Erro em Sair do Modulo Erro: " + ex.getMessage());
        }
    }

    @FXML
    void btnClickVenderAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlVenderVendas.fxml")));
        } catch (IOException ex) {
            Mensagens.mensagemErro("ERRO!!!", " Erro: " + ex.getMessage());
        }
    }

    private ArrayList<Vendas> list;
    private ObservableList<TableMainVendas> obList;


    private void mostrarDados(ArrayList<Vendas> lista) {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("comprador"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataCompra"));
        colDesconto.setCellValueFactory(new PropertyValueFactory<>("percDesconto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colValorFinal.setCellValueFactory(new PropertyValueFactory<>("valorFinal"));
        colVendedor.setCellValueFactory(new PropertyValueFactory<>("vendedor"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        list = lista;
        ArrayList<TableMainVendas> listaTabela = new ArrayList<>();

        for (Vendas v : list) {
            String valorFormatado;
            TableMainVendas tmv = new TableMainVendas();
            tmv.setId(v.getId());
            tmv.setComprador(v.getComprador().getNome());
            tmv.setVendedor(v.getVendedor().getNome());
            tmv.setProduto(v.getProduto().getNome());
            tmv.setQuantidade(Long.valueOf(v.getQuantidade()));
            tmv.setDataCompra(v.getData() == null ? "" : v.getData().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")));
            valorFormatado = new DecimalFormat("0.00").format(v.getPercDesconto());
            tmv.setPercDesconto(valorFormatado + "%");
            valorFormatado = new DecimalFormat("0.00").format(v.getValorFinal());
            tmv.setValorFinal("R$ " + valorFormatado);
            tmv.setAtivo(v.getAtivo());

            listaTabela.add(tmv);
        }

        obList = FXCollections.observableArrayList(listaTabela);
        tableMain.setItems(obList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDados(new ArrayList<>(new VendasRepository().listaTodos()));
        VendasRepository vendas = new VendasRepository();

        lbnData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));

        TextFields.bindAutoCompletion(tfPesquisarProduto, vendas.listaTodosProdutosPesquisa());

    }

}
