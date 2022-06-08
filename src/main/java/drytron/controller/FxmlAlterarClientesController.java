package drytron.controller;

import drytron.repository.ClientesRepository;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.model.Uf;
import drytron.util.Dicionario;
import drytron.util.Mascaras;
import drytron.util.Mensagens;
import drytron.util.Util;
import drytron.util.ValidaDados;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class FxmlAlterarClientesController implements Initializable {

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
    private TextField tfId;

    @FXML
    private TextField tfLocalidade;

    @FXML
    private TextField tfLongradouro;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTel;

    private String nome;

    @FXML
    void btnClickAlterarAction(ActionEvent event) {
        Clientes c = new Clientes();

        if (!ValidaDados.validaCliente(tfCep.getText(), tfEmail.getText(), tfCpf.getText(), nome.equals(tfNome.getText()) ? "" : tfNome.getText(), tfTel.getText())) {
            return;
        }
        
        c.setId(Long.valueOf(tfId.getText()));
        c.setNome(tfNome.getText());
        c.setCpf(tfCpf.getText());
        c.setTelefone(tfTel.getText());
        c.setEmail(tfEmail.getText());

        Endereco endCli = new Endereco();

        endCli.setCep(tfCep.getText());
        endCli.setComplemento(tfComplemento.getText());
        endCli.setLogradouro(tfLongradouro.getText());
        endCli.setBairro(tfBairro.getText());
        endCli.setLocalidade(tfLocalidade.getText());
        endCli.setUf(Dicionario.getUF(cbUf.getValue()));

        c.setEndCli(endCli);

        if (Mensagens.mensagemConfirmar("CONFIRMAR ALTERAR", "TEM CERTEZA QUE OS DADOS ESTÃO CORRETOS?", Mensagens.SIM, Mensagens.NAO).equals(Mensagens.SIM)) {
            ClientesRepository cr = new ClientesRepository();
            cr.atualiza(c);
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

    @FXML
    void tfClickPesquisarIdAction(ActionEvent event) {
        ClientesRepository cr = new ClientesRepository();
        Clientes c = cr.pesquisaPeloId(Long.parseLong(tfId.getText()));

        if (c != null) {
            nome = c.getNome();
            
            tfNome.setText(c.getNome());
            tfCpf.setText(c.getCpf());
            tfTel.setText(c.getTelefone());
            tfEmail.setText(c.getEmail());

            tfCep.setText(c.getEndCli().getCep());
            tfComplemento.setText(c.getEndCli().getComplemento());
            tfLongradouro.setText(c.getEndCli().getLogradouro());
            tfBairro.setText(c.getEndCli().getBairro());
            tfLocalidade.setText(c.getEndCli().getLocalidade());
            cbUf.setValue(Dicionario.getUFEnum(c.getEndCli().getUf()));
            Util.setClientes(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mascaras.mascaraCEP(tfCep);
        Mascaras.mascaraCPF(tfCpf);
        Mascaras.mascaraTelefone(tfTel);
        Mascaras.mascaraEmail(tfEmail);

        cbUf.setItems(FXCollections.observableArrayList(Uf.values()));
        cbUf.getItems().addAll();

        if (Util.getClientes() != null) {
            Clientes c = Util.getClientes();
            nome = c.getNome();
            
            tfId.setText(Long.toString(c.getId()));
            tfNome.setText(c.getNome());
            tfCpf.setText(c.getCpf());
            tfTel.setText(c.getTelefone());
            tfEmail.setText(c.getEmail());

            tfCep.setText(c.getEndCli().getCep());
            tfComplemento.setText(c.getEndCli().getComplemento());
            tfLongradouro.setText(c.getEndCli().getLogradouro());
            tfBairro.setText(c.getEndCli().getBairro());
            tfLocalidade.setText(c.getEndCli().getLocalidade());
            cbUf.setValue(Dicionario.getUFEnum(c.getEndCli().getUf()));

            Util.setClientes(null);
        }
    }

}
