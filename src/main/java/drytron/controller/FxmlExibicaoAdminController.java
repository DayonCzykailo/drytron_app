package drytron.controller;

import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import drytron.util.PdfAdmin;
import drytron.util.PdfClientes;
import drytron.util.XlsxJogos;
import drytron.util.PdfJogos;
import drytron.util.XlsxAdmin;
import drytron.util.XlsxClientes;
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FxmlExibicaoAdminController implements Initializable {

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSair;

    @FXML
    private TextField tfDir;

    @FXML
    void btnClickDirAction(ActionEvent event) {

    }

    @FXML
    void btnClickPdfAction(ActionEvent event) {
        try {
            PdfAdmin.gerar(tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads");
            Desktop.getDesktop().open(new File(((tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads") + "\\AdminRelatorio.pdf")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlMainController.getStage().close();
    }

    @FXML
    void btnClickXlsxAction(ActionEvent event) {
        try {
            XlsxAdmin.GerarXlsx(tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads");
            Desktop.getDesktop().open(new File(((tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads") + "\\AdminRelatorio.xlsx")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
