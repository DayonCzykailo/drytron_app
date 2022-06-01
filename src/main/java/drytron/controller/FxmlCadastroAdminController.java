package drytron.controller;

import drytron.cep_api.ViaCEP;
import drytron.cep_api.ViaCEPException;
import drytron.dao.ClientesRepository;
import drytron.dao.FuncionariosRepository;
import drytron.dao.JogosRepository;
import drytron.dto.Cargo;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.Funcionarios;
import drytron.dto.Jogos;
import drytron.dto.PlataformaJogos;
import drytron.dto.Uf;
import drytron.util.Dicionario;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxmlCadastroAdminController implements Initializable {

    @FXML
    private Button btnCadastrar;

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
    private TextField tfSenha;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfLocalidade;

    @FXML
    private TextField tfLongradouro;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTel;

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
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
        fr.insere(f);
    }

    @FXML
    void btnClickCepAction(ActionEvent event) {
        ViaCEP vc = new ViaCEP();
        try {
            vc.buscar(tfCep.getText());

            tfBairro.setText(vc.getBairro());
            tfCep.setText(vc.getCep());
            tfComplemento.setText(vc.getComplemento());
            tfLocalidade.setText(vc.getLocalidade());
            tfLongradouro.setText(vc.getLogradouro());
            cbUf.setValue(Dicionario.getUFEnum(vc.getUf()));

            System.out.println(vc.getLocalidade());
        } catch (ViaCEPException ex) {
        }
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
        FxmlMainAdminController.getStage().close();
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
                if (rbMostrarSenha.isSelected()) {
                    Util.mostrarSenha(toolTip, tfSenha);
                } else {
                    Util.esconderSenha(toolTip);
                }
            }
        });

    }
}
