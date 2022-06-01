package drytron.controller;

import drytron.dao.ClientesRepository;
import drytron.dao.FuncionariosRepository;
import drytron.dao.JogosRepository;
import drytron.dao.VendasRepository;
import drytron.dto.Clientes;
import drytron.dto.Funcionarios;
import drytron.dto.Jogos;
import drytron.dto.TableVendas;
import drytron.dto.Vendas;
import drytron.util.Util;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

public class FxmlVenderVendasController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVender;

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
        addDados(new TableVendas(tfProduto.getText(), validaDado(tfDesconto), spQuantidade.getValue()));
    }

    private double validaDado(TextField tf) {
        return Double.valueOf(tf.getText().trim() == null || tf.getText().trim().equals("") ? "0" : tf.getText());
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

    }

    @FXML
    void btnClickVenderAction(ActionEvent event) {
        for (int i = 0; i < list.size(); i++) {
            Vendas v = new Vendas();

            v.setVendedor(Util.getUsuario());


            v.setComprador(new ClientesRepository().listaPorNome(tfComprador.getText()).get(0));

            JogosRepository jr = new JogosRepository();

            v.setQuantidade(list.get(i).getQuantidade());
            
            v.setData(LocalDateTime.now());

            Jogos jogo = jr.listaPorNome(tfProduto.getText()).get(0);
            v.setProduto(jogo);

            v.setValorFinal((((list.get(i).getDesconto() == 0.0 ? 100 : list.get(i).getDesconto())/100) * jogo.getPreco()) * list.get(i).getQuantidade());
            v.setPercDesconto((float) list.get(i).getDesconto());

            new VendasRepository().atualiza(v);
        }
    }

    private void addDados(TableVendas tv) {
        boolean achou = false;
        for (int i = 0; i < list.size(); i++) {
            if (tfProduto.getText().equals(list.get(i).getProduto()) && validaDado(tfDesconto) == list.get(i).getDesconto()) {
                achou = true;
                System.out.println("IGUAL");
                for (int index = 0; i < tvVendas.getItems().size(); index++) {
                    if (tvVendas.getItems().get(index).getProduto().equals(tfProduto.getText()) && tvVendas.getItems().get(index).getDesconto() == validaDado(tfDesconto)) {
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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        spQuantidade.setValueFactory(valueFactory);

        TextFields.bindAutoCompletion(tfComprador, new ClientesRepository().listaTodosNomes());
        TextFields.bindAutoCompletion(tfProduto, new VendasRepository().listaTodosProdutos());

    }

}
