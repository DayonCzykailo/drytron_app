package drytron.controller;

import drytron.cep_api.ViaCEP;
import drytron.dao.EnderecoRepository;
import drytron.dao.JogosRepository;
import drytron.dto.Endereco;
import drytron.dto.Jogos;
import drytron.util.XlsxJogos;
import drytron.util.PdfJogos;
import drytron.util.Util;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FxmlEnderecoClientesController implements Initializable {

    @FXML
    private Button btnSair;

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

    private ArrayList<Endereco> list;
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
        
        endereco = Util.getEndereco();
        tfBairro.setText(Util.getEndereco().getBairro());
        tfCep.setText(Util.getEndereco().getCep());
        tfComplemento.setText(Util.getEndereco().getComplemento());
        tfLocalidadade.setText(Util.getEndereco().getLocalidade());
        tfLongradouro.setText(Util.getEndereco().getLogradouro());
        tfUf.setText(Util.getEndereco().getUf());
        
        Util.setEndereco(null);
    }

}
