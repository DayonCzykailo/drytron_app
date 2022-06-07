package drytron.controller;

import drytron.repository.ClientesRepository;
import drytron.dto.Clientes;
import drytron.model.TableMainClientes;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author dayon
 */
public class FxmlMainClientesController implements Initializable {

    @FXML
    private Label lbnData;

    @FXML
    private TableView<TableMainClientes> tableMain;

    @FXML
    private TableColumn<Clientes, String> colCpf;

    @FXML
    private TableColumn<Clientes, String> colEmail;

    @FXML
    private TableColumn<Clientes, Long> colId;

    @FXML
    private TableColumn<Clientes, String> colNome;

    @FXML
    private TableColumn<Clientes, String> colTelefone;

    @FXML
    private TextField tfPesquisar;

    private ArrayList<Clientes> list;
    private ObservableList<TableMainClientes> obList;

    @FXML
    void btnClickMostrarMaisAction(ActionEvent event) {

        if (tableMain.getSelectionModel().getSelectedIndex() != -1) {
            Util.setEndereco(new ClientesRepository().pesquisaEndereco(tableMain.getSelectionModel().getSelectedItem().getId()));
            try {
                FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlEnderecoClientes.fxml")));
            } catch (IOException ex) {
                System.out.println("ERRO NO BOTÃO MostrarMais  :" + ex.getMessage());
            }
        } else {
            Mensagens.mensagemInfo("Dica", "Click em um item da tabela, primeiro.");
        }

    }

    @FXML
    void btnClickAtualizarAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new ClientesRepository().listaTodos()));
    }

    private void mostrarDados(ArrayList<Clientes> lista) {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        list = lista;
        ArrayList<TableMainClientes> listaTabela = new ArrayList<>();

        for (Clientes c : lista) {
            TableMainClientes tmc = new TableMainClientes();
            tmc.setId(c.getId());
            tmc.setNome(c.getNome());
            tmc.setCpf(Mascaras.formataCpf(c.getCpf()));
            tmc.setTelefone("(" + c.getTelefone().substring(0, 2) + ")" + c.getTelefone().substring(2, 6) + "-" + c.getTelefone().substring(6, 10));
            tmc.setEmail(c.getEmail());
            tmc.setAtivo(c.getAtivo());

            listaTabela.add(tmc);
        }

        obList = FXCollections.observableArrayList(listaTabela);
        tableMain.setItems(obList);

    }

   

    @FXML
    void btnClickVendasTelaAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainVendas.fxml")));

        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Tela Vendas");
        }
    }

    @FXML
    void btnClickRelatorioAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlRelatorioClientes.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Relatório");
        }
    }

    @FXML
    void btnClickAjudaAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlAjuda.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO AJUDA");
        }
    }

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarClientes.fxml")));
        } catch (IOException ex) {
            ex.getMessage();
            System.out.println("ERRO NO BOTÃO ALTERAR");

        }

    }

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlCadastrarClientes.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO CADASTRAR");
        }

    }

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlDeletarClientes.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO DELETAR: " + ex.getMessage());
        }
    }

    @FXML
    void btnClickExibicaoAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlExibicaoClientes.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Opcoes");
        }
    }

    @FXML
    void btnClickPesquisarAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new ClientesRepository().listaPorNome(tfPesquisar.getText())));

    }

    @FXML
    protected void btnClickProdutosTelaAction(ActionEvent e) throws IOException {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml")), getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    protected void btnClickSairAction(ActionEvent e) {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlLogin.fxml")), getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
        } catch (IOException ex) {
            Mensagens.mensagemErro("ERRO!!!", "Erro em Sair do Modulo Erro: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDados(new ArrayList<>(new ClientesRepository().listaTodos()));
        lbnData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));

        tableMain.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {

                Util.setClientes(new ClientesRepository().pesquisaPeloId(tableMain.getSelectionModel().getSelectedItem().getId()));

                try {
                    FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarClientes.fxml")));
                } catch (IOException ex) {
                    System.out.println("ERRO EM ALTERAR CLICK :" + ex.getMessage());
                }
            } else if (t.getButton() == MouseButton.SECONDARY) {
                try {
                    Clientes c = new Clientes();
                    c.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                    Util.setClientes(c);

                    FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlDeletarClientes.fxml")));
                } catch (IOException ex) {
                    System.out.println("ERRO EM ALTERAR CLICK : " + ex.getMessage());
                }

            }
        });
    }
}
