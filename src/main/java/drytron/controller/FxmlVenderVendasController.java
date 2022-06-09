package drytron.controller;

import drytron.repository.ClientesRepository;
import drytron.repository.JogosRepository;
import drytron.repository.VendasRepository;
import drytron.dto.Jogos;
import drytron.model.TableVendas;
import drytron.dto.Vendas;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import drytron.util.Util;
import drytron.util.ValidaDados;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

public class FxmlVenderVendasController implements Initializable {

    @FXML
    private Spinner<Integer> spQuantidade;

    @FXML
    private TextField tfDesconto;

    @FXML
    private TextField tfProduto;

    @FXML
    private TextField tfComprador;

    @FXML
    private TableView<TableVendas> tvVendas;

    @FXML
    private TableColumn<TableVendas, String> colDesconto;

    @FXML
    private TableColumn<TableVendas, String> colProdutos;

    @FXML
    private TableColumn<TableVendas, Integer> colQuantidade;

    private ArrayList<TableVendas> list = new ArrayList<>();

    @FXML
    void btnClickAddAction(ActionEvent event) {
        addDados(new TableVendas(tfProduto.getText(), ValidaDados.validaDesconto(tfDesconto), spQuantidade.getValue()));
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfProduto.clear();
        tfDesconto.clear();
        tfComprador.clear();
    }

    @FXML
    void btnClickRemoveAction(ActionEvent event) {
        list.remove((int) tvVendas.getSelectionModel().getSelectedIndex());
        colProdutos.setCellValueFactory(new PropertyValueFactory<>("Produto"));
        colDesconto.setCellValueFactory(new PropertyValueFactory<>("Desconto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));

        tvVendas.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlFactory.fecharTelaSecundario();
    }

    @FXML
    void btnClickVenderAction(ActionEvent event) {
        try {
            if (Mensagens.mensagemConfirmar("CONFIRMAR A VENDA", "TEM CERTEZA QUE OS DADOS ESTÃO CORRETOS?", Mensagens.SIM, Mensagens.NAO).equals(Mensagens.SIM)) {
                for (int i = 0; i < list.size(); i++) {
                    Vendas v = new Vendas();

                    v.setVendedor(Util.getUsuario());

                    v.setComprador(new ClientesRepository().listaPorNome(tfComprador.getText()).get(0));

                    JogosRepository jr = new JogosRepository();

                    v.setQuantidade(list.get(i).getQuantidade());

                    v.setData(LocalDateTime.now());

                    Jogos jogo = jr.listaPorNome(list.get(i).getProduto()).get(0);
                    v.setProduto(jogo);

                    v.setValorFinal((((list.get(i).getDesconto() == 0.0 ? 100 : list.get(i).getDesconto()) / 100) * jogo.getPreco()) * list.get(i).getQuantidade());
                    v.setPercDesconto((float) list.get(i).getDesconto());

                    new VendasRepository().atualiza(v);
                    new VendasRepository().diminuirEstoque(list.get(i).getQuantidade(), list.get(i).getProduto());
                }
            }
        } catch (Exception e) {
            Mensagens.mensagemAlerta("ALERTA!!", "VERIFIQUE SE EXISTE O PRODUTO OU COMPRADOR.");
        }

    }

    private void addDados(TableVendas tv) {
        boolean achou = false;
        for (int i = 0; i < list.size(); i++) {
            if (tfProduto.getText().equals(list.get(i).getProduto()) && ValidaDados.validaDesconto(tfDesconto) == list.get(i).getDesconto()) {
                achou = true;
                for (int index = 0; i < tvVendas.getItems().size(); index++) {
                    if (tvVendas.getItems().get(index).getProduto().equals(tfProduto.getText()) && tvVendas.getItems().get(index).getDesconto() == ValidaDados.validaDesconto(tfDesconto)) {
                        TableVendas tvUpdate = new TableVendas(tvVendas.getItems().get(index).getProduto(), tvVendas.getItems().get(index).getDesconto(), tvVendas.getItems().get(index).getQuantidade() + spQuantidade.getValue());
                        list.set(index, tvUpdate);
                        break;
                    }
                }

            }
        }

        if (!achou) {
            list.add(tv);
        }

        colProdutos.setCellValueFactory(new PropertyValueFactory<>("Produto"));
        colDesconto.setCellValueFactory(new PropertyValueFactory<>("Desconto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));

        tvVendas.setItems(FXCollections.observableArrayList(list));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.mascaraNumero(tfDesconto);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        spQuantidade.setValueFactory(valueFactory);

        TextFields.bindAutoCompletion(tfComprador, new ClientesRepository().listaTodosNomes());
        TextFields.bindAutoCompletion(tfProduto, new VendasRepository().listaTodosProdutos());

    }

}
