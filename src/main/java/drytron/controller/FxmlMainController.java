package drytron.controller;

import drytron.dao.JogosDAO;
import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import drytron.main.Drytron;
import drytron.util.Dicionario;
import drytron.util.Util;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author dayon
 */
public class FxmlMainController implements Initializable {

    @FXML
    private Button btnClientes;

    @FXML
    private MenuButton btnFiltros;

    @FXML
    private TableColumn<Jogos, Double> colPreco;

    @FXML
    private TableColumn<Jogos, String> colDesenvolvedor;

    @FXML
    private TableColumn<Jogos, Integer> colEstoque;

    @FXML
    private TableColumn<Jogos, String> colGenero;

    @FXML
    private TableColumn<Jogos, Long> colId;

    @FXML
    private TableColumn<Jogos, String> colIdioma;

    @FXML
    private TableColumn<Jogos, String> colLancamento;

    @FXML
    private TableColumn<Jogos, String> colNome;

    @FXML
    private TableColumn<Jogos, String> colPlataforma;

    @FXML
    private TableColumn<Jogos, String> colPublicador;

    @FXML
    private TableView<Jogos> tableMain;

    @FXML
    private TextField tfPesquisar;

    @FXML
    private Label lbnData;

    @FXML
    private Button btnAtualizar;

    private ArrayList<Jogos> list;
    private ObservableList<Jogos> obList;
    private static Stage stage = new Stage();

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FxmlMainController.stage = stage;
    }

    @FXML
    void btnClickAtualizarAction(ActionEvent event) {
        mostrarDadosJogos();
    }

    public void mostrarDadosJogos() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        colPlataforma.setCellValueFactory(new PropertyValueFactory<>("Plataforma"));
        colLancamento.setCellValueFactory(new PropertyValueFactory<>("Lancamento"));
        colDesenvolvedor.setCellValueFactory(new PropertyValueFactory<>("Desenvolvedor"));
        colPublicador.setCellValueFactory(new PropertyValueFactory<>("Publicador"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("Idioma"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("Estoque"));

        JogosRepository jogo = new JogosRepository();
        list = new ArrayList<Jogos>(jogo.listaTodos());
        if (list == null) {
            System.out.println("LISTA VAZIA JOGOS");
        } else {
            System.out.println("Não vazia");
        }
        for (Jogos j : list) {
            System.out.println(j.getNome());
        }
        obList = FXCollections.observableArrayList(list);
        tableMain.setItems(obList);

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
    @FXML
    void btnClickVendasTelaAction(ActionEvent event) {

    }

    @FXML
    void btnClickRelatorioAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlRelatorioJogos.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarJogos.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlCadastrarJogos.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlDeletarJogos.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlExibicaoJogos.fxml"));//todo
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
        colGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        colPlataforma.setCellValueFactory(new PropertyValueFactory<>("Plataforma"));
        colLancamento.setCellValueFactory(new PropertyValueFactory<>("Lancamento"));
        colDesenvolvedor.setCellValueFactory(new PropertyValueFactory<>("Desenvolvedor"));
        colPublicador.setCellValueFactory(new PropertyValueFactory<>("Publicador"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("Idioma"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("Estoque"));

        JogosRepository jogo = new JogosRepository();
        list = new ArrayList<Jogos>(jogo.listaPorNome(tfPesquisar.getText()));
        if (list == null) {
            System.out.println("LISTA VAZIA JOGOS");
        } else {
            System.out.println("Não vazia");
        }
        for (Jogos j : list) {
            System.out.println(j.getNome());
        }
        obList = FXCollections.observableArrayList(list);
        tableMain.setItems(obList);
    }

    @FXML
    protected void btnClickClienteTelaAction(ActionEvent e) throws IOException {
        //Main.getStage().close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainClientes.fxml"));
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
         FxmlMainClientesController.getStage().close();
        mostrarDadosJogos();
        lbnData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("d/MM/uuuu")));

        tableMain.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2) {
                    Jogos j = new Jogos();

                    j.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                    j.setNome(tableMain.getSelectionModel().getSelectedItem().getNome());
                    j.setGenero(tableMain.getSelectionModel().getSelectedItem().getGenero());
                    j.setPlataforma(tableMain.getSelectionModel().getSelectedItem().getPlataforma());
                    j.setLancamento(tableMain.getSelectionModel().getSelectedItem().getLancamento());
                    j.setDesenvolvedor(tableMain.getSelectionModel().getSelectedItem().getDesenvolvedor());
                    j.setPublicador(tableMain.getSelectionModel().getSelectedItem().getPublicador());
                    j.setIdioma(tableMain.getSelectionModel().getSelectedItem().getIdioma());
                    j.setEstoque(tableMain.getSelectionModel().getSelectedItem().getEstoque());
                    j.setPreco(tableMain.getSelectionModel().getSelectedItem().getPreco());
                    Parent root = null;
                    try {
                        Util.setJogos(j);
                        stage = new Stage();

                        root = FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarJogos.fxml"));
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
                        Jogos j = new Jogos();
                        j.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                        Util.setJogos(j);
                        stage = new Stage();

                        root = FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlDeletarJogos.fxml"));
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


        /* tableMain.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                mostrarDadosJogos();
            }
        });*/
    }
}
