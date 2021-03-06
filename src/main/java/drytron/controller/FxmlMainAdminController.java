package drytron.controller;

import drytron.repository.FuncionariosRepository;
import drytron.dto.Funcionarios;
import drytron.main.Drytron;
import drytron.model.Cargo;
import drytron.model.TableMainFuncionario;
import drytron.util.Dicionario;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class FxmlMainAdminController implements Initializable {

    @FXML
    private TableColumn<Funcionarios, String> colCargo;

    @FXML
    private TableColumn<Funcionarios, String> colCpf;

    @FXML
    private TableColumn<Funcionarios, String> colEmail;

    @FXML
    private TableColumn<Funcionarios, Long> colId;

    @FXML
    private TableColumn<Funcionarios, String> colNome;

    @FXML
    private TableColumn<Funcionarios, String> colTelefone;

    @FXML
    private Label lbnData;

    @FXML
    private TableView<TableMainFuncionario> tableMain;

    @FXML
    private TextField tfPesquisar;

    @FXML
    private Label lblCargo;

    @FXML
    private Label lblUsuario;

    private ObservableList<TableMainFuncionario> obList;

    private void mostrarDados(ArrayList<Funcionarios> lista) {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));

        ArrayList<TableMainFuncionario> listaTabela = new ArrayList<>();

        for (Funcionarios f : lista) {
            TableMainFuncionario tmf = new TableMainFuncionario();
            tmf.setId(f.getId());
            tmf.setNome(f.getNome());
            tmf.setCpf(Mascaras.formataCpf(f.getCpf()));
            tmf.setTelefone(Mascaras.formataTelefone(f.getTelefone()));
            tmf.setEmail(f.getEmail());
            tmf.setCargo(Dicionario.getCargo(f.getCargo()));

            listaTabela.add(tmf);
        }

        obList = FXCollections.observableArrayList(listaTabela);
        tableMain.setItems(obList);

    }

    @FXML
    void btnClickAjudaAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/ViewFxmlAdmin.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOT??O AJUDA");
        }
    }

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
        telaAutenticacao("/drytron/fxml/FxmlAlterarAdmin.fxml");
    }

    @FXML
    void btnClickAtualizarAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new FuncionariosRepository().listaTodos()));
    }

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        telaAutenticacao("/drytron/fxml/FxmlCadastrarAdmin.fxml");
    }

    @FXML
    void btnClickDeletarAction(ActionEvent event) {
        telaAutenticacao("/drytron/fxml/FxmlDeletarAdmin.fxml");
    }

    @FXML
    void btnClickExibicaoAction(ActionEvent event) {
        telaAutenticacao("/drytron/fxml/FxmlExibicaoAdmin.fxml");
    }

    @FXML
    void btnClickMostrarMaisAction(ActionEvent event) {
        try {
            FuncionariosRepository fr = new FuncionariosRepository();
            Util.setEndereco(fr.pesquisaEndereco(tableMain.getSelectionModel().getSelectedItem().getId()));
            System.out.println("End  " + Util.getEndereco().getLocalidade());
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlEnderecoAdmin.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOT??O MostrarMais  :" + ex.getMessage());
        }
    }

    @FXML
    void btnClickPesquisarAction(ActionEvent event) {
        mostrarDados(new ArrayList<>(new FuncionariosRepository().listaPorNome(tfPesquisar.getText())));
    }

    @FXML
    void btnClickRelatorioAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlRelatorioAdmin.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO NO BOT??O Relat??rio");
        }
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        try {
            FxmlFactory.fecharTelaSecundario();
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlLogin.fxml")), getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
        } catch (IOException ex) {
            Mensagens.mensagemErro("ERRO!!!", "Erro em Sair do Modulo Erro: " + ex.getMessage());
        }
    }

    public void telaAutenticacao(String tela) {
        Util.setTelaUrl("");
        Util.setTelaUrl(tela);
        try {
            FxmlFactory.acessarTelaSecundario(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlAutenticacao.fxml")));
        } catch (IOException ex) {
            System.out.println("ERRO EM ALTENTICAR CLICK :" + ex.getMessage());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblCargo.setText(Dicionario.getCargo(Util.getUsuario().getCargo()));
        lblUsuario.setText(Util.getUsuario().getNome());

       mostrarDados(new ArrayList<>(new FuncionariosRepository().listaTodos()));

        lbnData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));

        tableMain.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                Util.setFuncionarios(new FuncionariosRepository().pesquisaPeloId(tableMain.getSelectionModel().getSelectedItem().getId()));
                telaAutenticacao("/drytron/fxml/FxmlAlterarAdmin.fxml");
            } else if (t.getButton() == MouseButton.SECONDARY) {
                Util.setFuncionarios(new FuncionariosRepository().pesquisaPeloId(tableMain.getSelectionModel().getSelectedItem().getId()));
                telaAutenticacao("/drytron/fxml/FxmlDeletarAdmin.fxml");
            }
        });
    }

}
