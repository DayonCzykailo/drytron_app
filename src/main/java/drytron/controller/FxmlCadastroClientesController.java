package drytron.controller;

import drytron.cep_api.ViaCEP;
import drytron.repository.ClientesRepository;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.model.Uf;
import drytron.util.Dicionario;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import drytron.util.ValidaDados;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author dayon
 */
public class FxmlCadastroClientesController implements Initializable {

    @FXML
    private ChoiceBox<Uf> cbUf;

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
    private TextField tfLocalidade;

    @FXML
    private TextField tfLongradouro;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTel;

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

        } catch (Exception e) {
            Mensagens.mensagemExcessao("ERRO", "Verifique seu CEP.", e);
        }
    }

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        Clientes c = new Clientes();
        if (!ValidaDados.validaCliente(tfCep.getText(), tfEmail.getText(), tfCpf.getText(), tfNome.getText(), tfTel.getText())) {
            return;
        }
        c.setNome(tfNome.getText());
        c.setCpf(tfCpf.getText());
        c.setTelefone(tfTel.getText());
        c.setEmail(tfEmail.getText());

        Endereco e = new Endereco();

        e.setCep(tfCep.getText());
        e.setComplemento(tfComplemento.getText());
        e.setLogradouro(tfLongradouro.getText());
        e.setBairro(tfBairro.getText());
        e.setLocalidade(tfLocalidade.getText());
        e.setUf(Dicionario.getUF(cbUf.getValue()));

        c.setEndCli(e);

        if (Mensagens.mensagemConfirmar("CONFIRMAR CADASTRO", "TEM CERTEZA QUE OS DADOS EST??O CORRETOS?", Mensagens.SIM, Mensagens.NAO).equals(Mensagens.SIM)) {
            ClientesRepository cr = new ClientesRepository();
            cr.insere(c);
            FxmlFactory.fecharTelaSecundario();
        }

    }

    @FXML
    void btnClickLimparAction(ActionEvent event) {
        tfNome.clear();
        tfCpf.clear();
        tfTel.clear();
        tfEmail.clear();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.mascaraCEP(tfCep);
        Mascaras.mascaraCPF(tfCpf);
        Mascaras.mascaraTelefone(tfTel);
        Mascaras.mascaraEmail(tfEmail);

        cbUf.setItems(FXCollections.observableArrayList(Uf.values()));
        cbUf.getItems().addAll();

    }

}
