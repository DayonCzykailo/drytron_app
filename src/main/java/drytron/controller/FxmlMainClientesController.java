package drytron.controller;

import drytron.dao.ClientesRepository;
import drytron.dao.JogosRepository;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.Jogos;
import drytron.main.Drytron;
import drytron.util.Util;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author dayon
 */
public class FxmlMainClientesController implements Initializable {

    @FXML
    private Button btnAjuda;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnExibicao;

    @FXML
    private Button btnMostrarMain;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVendas;

    @FXML
    private Label lbnData;

    @FXML
    private TableView<Clientes> tableMain;

    @FXML
    private TableColumn<Clientes, String> colCpf;

    @FXML
    private TableColumn<Clientes, String> colEmail;

    @FXML
    private TableColumn<Clientes, String> colEndereco;

    @FXML
    private TableColumn<Clientes, Long> colId;

    @FXML
    private TableColumn<Clientes, String> colNome;

    @FXML
    private TableColumn<Clientes, String> colTelefone;

    @FXML
    private TextField tfPesquisar;

    private ArrayList<Clientes> list;
    private ObservableList<Clientes> obList;
    private static Stage stage = new Stage();

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FxmlMainClientesController.stage = stage;
    }

    @FXML
    void btnClickMostrarMaisAction(ActionEvent event) {
        Parent root;
        try {
            ClientesRepository cliente = new ClientesRepository();           
            Util.setEndereco( cliente.pesquisaEnderecoClientes(tableMain.getSelectionModel().getSelectedItem().getId()));
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlEnderecoClientes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Relatório");
        }
    }

    @FXML
    void btnClickAtualizarAction(ActionEvent event) {
        mostrarDadosJogos();
    }

    public void mostrarDadosJogos() {

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        ClientesRepository cliente = new ClientesRepository();
        list = new ArrayList<>(cliente.listaTodos());

        if (list == null) {
            System.out.println("LISTA VAZIA JOGOS");
        } else {
            System.out.println("Não vazia");
        }

        for (Clientes c : list) {
            System.out.println(c.getNome());
            System.out.println(c.getTelefone());
        }

        obList = FXCollections.observableArrayList(list);
        tableMain.setItems(obList);

    }

    @FXML
    void btnClickVendasTelaAction(ActionEvent event) {

    }

    @FXML
    void btnClickRelatorioAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlRelatorioClientes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Relatório");
        }
    }

    @FXML
    void btnClickAjudaAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlAjuda.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO AJUDA");
        }

    }

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarClientes.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("ERRO NO BOTÃO ALTERAR");
            if (root == null) {
                System.out.println("Nao Acho a tela");
            }
        }

    }

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlCadastrarClientes.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO CADASTRAR");
            if (root == null) {
                System.out.println("Nao Acho a tela");
            }
        }

    }

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlDeletarClientes.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO DELETAR");
        }

    }

    @FXML
    void btnClickExibicaoAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlExibicaoClientes.fxml"));//todo
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Opcoes");
        }
    }

    @FXML
    void btnClickPesquisarAction(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        ClientesRepository cliente = new ClientesRepository();
        list = new ArrayList<>(cliente.listaPorNome(tfPesquisar.getText()));
        if (list == null) {
            System.out.println("LISTA VAZIA JOGOS");
        } else {
            System.out.println("Não vazia");
        }
        for (Clientes c : list) {
            System.out.println(c.getNome());
        }
        obList = FXCollections.observableArrayList(list);
        tableMain.setItems(obList);
    }

    @FXML
    protected void btnClickProdutosTelaAction(ActionEvent e) throws IOException {
        //Main.getStage().close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void btnClickSairAction(ActionEvent e) {
        try {
            Drytron.getStage().close();
            System.out.println("Saiu");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDadosJogos();
        lbnData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("d/MM/uuuu")));

        tableMain.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2) {
                    Clientes c = new Clientes();

                    c.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                    c.setNome(tableMain.getSelectionModel().getSelectedItem().getNome());
                    c.setCpf(tableMain.getSelectionModel().getSelectedItem().getCpf());
                    c.setTelefone(tableMain.getSelectionModel().getSelectedItem().getTelefone());
                    c.setEmail(tableMain.getSelectionModel().getSelectedItem().getEmail());
                    Parent root = null;
                    try {
                        Util.setClientes(c);
                        stage = new Stage();

                        root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarClientes.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println("ERRO EM ALTERAR CLICK");
                        System.out.println(ex.getMessage());
                        if (root == null) {
                            System.out.println("Nao Acho a tela ");
                        }
                    }
                } else if (t.getButton() == MouseButton.SECONDARY) {
                    Parent root = null;
                    try {
                        Clientes c = new Clientes();
                        c.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                        Util.setClientes(c);
                        stage = new Stage();

                        root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlDeletarClientes.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println("ERRO EM ALTERAR CLICK");
                        System.out.println(ex.getMessage());
                        if (root == null) {
                            System.out.println("Nao Acho a tela ");
                        }
                    }

                }
            }
        }
        );
    }
}
