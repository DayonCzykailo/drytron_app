package drytron.controller;

import com.sun.mail.imap.ACL;
import drytron.cep_api.ViaCEP;
import drytron.cep_api.ViaCEPException;
import drytron.dao.ClientesRepository;
import drytron.dao.EnderecoRepository;
import drytron.dao.JogosRepository;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.GeneroJogos;
import drytron.dto.Jogos;
import drytron.dto.PlataformaJogos;
import drytron.dto.UfEnum;
import drytron.util.Dicionario;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author dayon
 */
public class FxmlCadastroClientesController implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private ChoiceBox<UfEnum> cbUf;

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
            
            System.out.println(vc.getLocalidade());
        } catch (ViaCEPException ex) {
            Logger.getLogger(FxmlCadastroClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnClickCadastrarAction(ActionEvent event) {
        Clientes c = new Clientes();

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

        System.out.println(e.getLocalidade());
        ClientesRepository cr = new ClientesRepository();
        cr.insere(c);
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
        cbUf.setValue(UfEnum.UF);
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbUf.setItems(FXCollections.observableArrayList(UfEnum.values()));
        cbUf.getItems().addAll();

    }

}
