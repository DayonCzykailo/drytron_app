package drytron.controller;

import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import drytron.util.Exibicao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FxmlExibicaoJogosController implements Initializable {

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfDir;

    @FXML
    void btnClickXlsxAction(ActionEvent event) {
        Exibicao.GerarXlsx(tfDir.getText());
    }

    @FXML
    void btnClickDirAction(ActionEvent event) {

    }

    @FXML
    void btnClickPdfAction(ActionEvent event) {
        Exibicao.GerarPdf(tfDir.getText());
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
