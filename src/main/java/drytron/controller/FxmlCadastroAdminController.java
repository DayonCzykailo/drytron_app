package drytron.controller;

import drytron.cep_api.ViaCEP;
import drytron.repository.FuncionariosRepository;
import drytron.model.Cargo;
import drytron.dto.Endereco;
import drytron.dto.Funcionarios;
import drytron.model.Uf;
import drytron.util.Dicionario;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import drytron.util.Util;
import drytron.util.ValidaDados;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

public class FxmlCadastroAdminController implements Initializable {

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
        if(!ValidaDados.validaAdmin(tfCep.getText(),tfEmail.getText() ,tfSenha.getText(),tfCpf.getText(), tfNome.getText(), tfTel.getText(),cbCargo.getValue())){
            return;
        }
        
        f.setNome(tfNome.getText());
        f.setCpf(tfCpf.getText().replace(".", "").replace("-", ""));
        f.setTelefone(tfTel.getText().replace("(", "").replace(")", "").replace("-", ""));
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

        if (Mensagens.mensagemConfirmar("CONFIRMAR CADASTRO", "TEM CERTEZA QUE OS DADOS ESTÃO CORRETOS?", Mensagens.SIM, Mensagens.NAO).equals(Mensagens.SIM)) {
            FuncionariosRepository fr = new FuncionariosRepository();
            fr.insere(f);
            FxmlFactory.fecharTelaSecundario();
        }

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
        } catch (Exception ex) {
            Mensagens.mensagemExcessao("ERRO", "Verifique seu CEP.", ex);
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
        FxmlFactory.fecharTelaSecundario();
    }

    @FXML
    private Tooltip toolTip;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.mascaraCPF(tfCpf);
        Mascaras.mascaraTelefone(tfTel);
        Mascaras.mascaraEmail(tfEmail);
        Mascaras.mascaraCEP(tfCep);

        cbCargo.setItems(FXCollections.observableArrayList(Cargo.values()));
        cbCargo.getItems().addAll();

        tfSenha.setOnKeyPressed((KeyEvent e) -> {
            if (rbMostrarSenha.isSelected()) {
                Util.mostrarSenha(toolTip, tfSenha);
            } else {
                Util.esconderSenha(toolTip);
            }
        });
    }
}
