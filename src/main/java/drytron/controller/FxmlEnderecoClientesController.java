package drytron.controller;

import drytron.cep_api.ViaCEP;
import drytron.dao.EnderecoRepository;
import drytron.dto.Endereco;
import drytron.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FxmlEnderecoClientesController implements Initializable {


    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfComplemento;

    @FXML
    private TextField tfLocalidadade;

    @FXML
    private TextField tfLongradouro;

    @FXML
    private TextField tfUf;

    Endereco endereco;

    private void enderecoAction() {
        try {
            if (!endereco.getCep().equals(tfCep.getText()) || !endereco.getComplemento().equals(tfComplemento.getText())) {
                Endereco endMerge = endereco;
                endMerge.setCep(tfCep.getText());
                ViaCEP vc = new ViaCEP();
                vc.buscar(tfCep.getText());

                if (tfCep.getText() != null) {

                    endMerge.setBairro(vc.getBairro());
                    endMerge.setComplemento(tfComplemento.getText());
                    endMerge.setLocalidade(vc.getLocalidade());
                    endMerge.setLogradouro(vc.getLogradouro());
                    endMerge.setUf(vc.getUf());

                    EnderecoRepository merge = new EnderecoRepository();
                    merge.atualiza(endMerge);

                    tfBairro.setText(endMerge.getBairro());
                    tfCep.setText(endMerge.getCep());
                    tfComplemento.setText(endMerge.getComplemento());
                    tfLocalidadade.setText(endMerge.getLocalidade());
                    tfLongradouro.setText(endMerge.getLogradouro());
                    tfUf.setText(endMerge.getUf());
                }
            }
        } catch (Exception e) {
        }

    }

    @FXML
    void btnClickCepAction(ActionEvent event) {
        enderecoAction();
    }

    @FXML
    void btnClickComplementoAction(ActionEvent event) {
        enderecoAction();
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainClientesController.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Util.getEndereco() != null) {
            endereco = Util.getEndereco();
            tfBairro.setText(Util.getEndereco().getBairro());
            tfCep.setText(Util.getEndereco().getCep());
            tfComplemento.setText(Util.getEndereco().getComplemento());
            tfLocalidadade.setText(Util.getEndereco().getLocalidade());
            tfLongradouro.setText(Util.getEndereco().getLogradouro());
            tfUf.setText(Util.getEndereco().getUf());
        }

        Util.setEndereco(null);
    }

}
