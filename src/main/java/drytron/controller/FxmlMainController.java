package drytron.controller;

import drytron.repository.JogosRepository;
import drytron.dto.Jogos;
import drytron.model.TableMainJogos;
import drytron.model.TableMainVendas;
import drytron.util.Mensagens;
import drytron.util.Util;
import drytron.util.ValidaDados;
import java.io.IOException;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
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
public class FxmlMainController implements Initializable {

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
    private TableView<TableMainJogos> tableMain;

    @FXML
    private TextField tfPesquisar;

    @FXML
    private Label lbnData;

    private ArrayList<Jogos> list;
    private ObservableList<TableMainJogos> obList;

    @FXML
    void btnClickAtualizarAction(ActionEvent event) {
        mostrarDadosJogos(new ArrayList<>(new JogosRepository().listaTodos()));
    }

    public void mostrarDadosJogos(ArrayList lista) {
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

        list = lista;
        ArrayList<TableMainJogos> listaTabela = new ArrayList<>();
        
        for (Jogos j : list) {
            TableMainJogos tmj = new TableMainJogos();
            tmj.setId(j.getId());
            tmj.setNome(j.getNome());
            tmj.setGenero(j.getGenero());
            tmj.setPlataforma(j.getPlataforma());
            tmj.setLancamento(j.getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            tmj.setDesenvolvedor(j.getDesenvolvedor());
            tmj.setPublicador(j.getPublicador());
            tmj.setIdioma(j.getIdioma());
            tmj.setPreco("R$ " + new DecimalFormat("0.00").format(j.getPreco()));
            tmj.setEstoque("" + j.getEstoque());
            tmj.setAtivo(j.getAtivo());
            
            listaTabela.add(tmj);
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
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlRelatorioJogos.fxml")));
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
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarJogos.fxml")));
        } catch (IOException ex) {
            ex.getMessage();
            System.out.println("ERRO NO BOTÃO ALTERAR");
        }

    }

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlCadastrarJogos.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO CADASTRAR  " + ex.getMessage());
        }

    }

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlDeletarJogos.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO DELETAR");
        }

    }

    @FXML
    void btnClickExibicaoAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlExibicaoJogos.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOTÃO Opcoes");
        }
    }

    @FXML
    void btnClickPesquisarAction(ActionEvent event) {
        mostrarDadosJogos( new ArrayList<>(new JogosRepository().listaPorNome(tfPesquisar.getText())));
    }

    @FXML
    protected void btnClickClienteTelaAction(ActionEvent e) throws IOException {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainClientes.fxml")));
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
        mostrarDadosJogos(new ArrayList<>(new JogosRepository().listaTodos()));
        lbnData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));

        tableMain.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                Jogos j = new Jogos();
                
                System.out.println("-"+tableMain.getSelectionModel().getSelectedItem().getLancamento()+"-");
                
                j.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                j.setNome(tableMain.getSelectionModel().getSelectedItem().getNome());
                j.setGenero(tableMain.getSelectionModel().getSelectedItem().getGenero());
                j.setPlataforma(tableMain.getSelectionModel().getSelectedItem().getPlataforma());
                j.setLancamento(LocalDate.parse(LocalDate.parse(tableMain.getSelectionModel().getSelectedItem().getLancamento(),DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT)).format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))));
                j.setDesenvolvedor(tableMain.getSelectionModel().getSelectedItem().getDesenvolvedor());
                j.setPublicador(tableMain.getSelectionModel().getSelectedItem().getPublicador());
                j.setIdioma(tableMain.getSelectionModel().getSelectedItem().getIdioma());
                j.setEstoque(Integer.parseInt(tableMain.getSelectionModel().getSelectedItem().getEstoque()));
                j.setPreco(Float.valueOf(tableMain.getSelectionModel().getSelectedItem().getPreco().replace("R$", "").replace(",", ".")));
                
                System.out.println("-->"+j.getLancamento());
                try {
                    Util.setJogos(j);
                    FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAlterarJogos.fxml")));

                } catch (IOException ex) {
                    System.out.println("ERRO EM ALTERAR CLICK");
                    Mensagens.mensagemErro("ERRO!!!", " Erro: " + ex.getMessage());
                }
            } else if (t.getButton() == MouseButton.SECONDARY) {
                try {
                    Jogos j = new Jogos();
                    j.setId(tableMain.getSelectionModel().getSelectedItem().getId());
                    Util.setJogos(j);

                    FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlDeletarJogos.fxml")));

                } catch (IOException ex) {
                    System.out.println("ERRO EM ALTERAR CLICK");
                    Mensagens.mensagemErro("ERRO!!!", " Erro: " + ex.getMessage());
                }

            }
        });
    }
}
