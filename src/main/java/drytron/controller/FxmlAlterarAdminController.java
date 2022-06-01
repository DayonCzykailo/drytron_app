package drytron.controller;

import drytron.dao.ClientesRepository;
import drytron.dao.FuncionariosRepository;
import drytron.dto.Cargo;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.Funcionarios;
import drytron.dto.Uf;
import drytron.util.Dicionario;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FxmlAlterarAdminController implements Initializable {

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private ChoiceBox<Cargo> cbCargo;

    @FXML
    private ChoiceBox<Uf> cbUf;

    @FXML
    private RadioButton rbMostrarSenha;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfComplemento;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfLocalidade;

    @FXML
    private TextField tfLongradouro;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfTel;

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
        Funcionarios f = new Funcionarios();

        f.setNome(tfNome.getText());
        f.setCpf(tfCpf.getText());
        f.setTelefone(tfTel.getText());
        f.setEmail(tfEmail.getText());

        Endereco e = new Endereco();
        e.setCep(tfCep.getText());
        e.setComplemento(tfComplemento.getText());
        e.setLogradouro(tfLongradouro.getText());
        e.setBairro(tfBairro.getText());
        e.setLocalidade(tfLocalidade.getText());
        e.setUf(Dicionario.getUF(cbUf.getValue()));

        f.setEndFun(e);

        f.setSenha(tfSenha.getText());
        f.setCargo(cbCargo.getValue());
        f.setNivel(Dicionario.getNivel(cbCargo.getValue()));

        FuncionariosRepository fr = new FuncionariosRepository();
        fr.atualiza(f);
    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfNome.clear();
        tfCpf.clear();
        tfTel.clear();
        tfEmail.clear();
        tfSenha.clear();
        cbCargo.setValue(Cargo.SEM_CARGO);
        tfCep.clear();
        tfComplemento.clear();
        tfLongradouro.clear();
        tfBairro.clear();
        tfLocalidade.clear();
        cbUf.setValue(Uf.UF);
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    @FXML
    void tfClickPesquisarIdAction(ActionEvent event) {
        FuncionariosRepository fr = new FuncionariosRepository();
        Funcionarios f = fr.pesquisaPeloId(Long.parseLong(tfId.getText()));

        if (f != null) {
            tfNome.setText(f.getNome());
            tfCpf.setText(f.getCpf());
            tfTel.setText(f.getTelefone());
            tfEmail.setText(f.getEmail());
            tfSenha.setText(f.getSenha());
            cbCargo.setValue(f.getCargo());

            tfCep.setText(f.getEndFun().getCep());
            tfComplemento.setText(f.getEndFun().getComplemento());
            tfLongradouro.setText(f.getEndFun().getLogradouro());
            tfBairro.setText(f.getEndFun().getBairro());
            tfLocalidade.setText(f.getEndFun().getLocalidade());
            cbUf.setValue(Dicionario.getUFEnum(f.getEndFun().getUf()));
            Util.setClientes(null);
        }
    }
    @FXML
    private Tooltip toolTip;
    @FXML
    private Pane pMain;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCargo.setItems(FXCollections.observableArrayList(Cargo.values()));
        cbCargo.getItems().addAll();

        tfSenha.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (rbMostrarSenha.isSelected()) {
                    Util.mostrarSenha(toolTip, tfSenha);
                } else {
                    Util.esconderSenha(toolTip);
                }
            }
        });
        pMain.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                if (Util.getFuncionarios()!= null) {
                    Funcionarios f = Util.getFuncionarios();
                    tfId.setText(String.valueOf(f.getId()));
                    tfNome.setText(f.getNome());
                    tfCpf.setText(f.getCpf());
                    tfTel.setText(f.getTelefone());
                    tfEmail.setText(f.getEmail());
                    tfSenha.setText(f.getSenha());
                    cbCargo.setValue(f.getCargo());

                    tfCep.setText(f.getEndFun().getCep());
                    tfComplemento.setText(f.getEndFun().getComplemento());
                    tfLongradouro.setText(f.getEndFun().getLogradouro());
                    tfBairro.setText(f.getEndFun().getBairro());
                    tfLocalidade.setText(f.getEndFun().getLocalidade());
                    cbUf.setValue(Dicionario.getUFEnum(f.getEndFun().getUf()));
                    Util.setClientes(null);
                }
                if (rbMostrarSenha.isSelected()) {
                    Util.mostrarSenha(toolTip, tfSenha);
                } else {
                    Util.esconderSenha(toolTip);
                }
            }
        });
    }

}
