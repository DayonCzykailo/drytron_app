package drytron.controller;

import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import drytron.util.XlsxJogos;
import drytron.util.PdfJogos;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
        try {
            XlsxJogos.GerarXlsx(tfDir.getText() == null || tfDir.getText() == "" ? tfDir.getText() : "D:\\Downloads");
            Desktop.getDesktop().open(new File(((tfDir.getText() == null || tfDir.getText() == "" ? tfDir.getText() : "D:\\Downloads") + "\\00_JogosRelatorio.xlsx")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnClickDirAction(ActionEvent event) {

    }

    @FXML
    void btnClickPdfAction(ActionEvent event) {
       try {
            PdfJogos.gerar(tfDir.getText() == null || tfDir.getText() == "" ? tfDir.getText() : "D:\\Downloads");
            Desktop.getDesktop().open(new File(((tfDir.getText() == null || tfDir.getText() == "" ? tfDir.getText() : "D:\\Downloads") + "\\JogosRelatorio.pdf")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
