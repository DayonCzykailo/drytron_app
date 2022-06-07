package drytron.controller;

import drytron.repository.JogosRepository;
import drytron.dto.Jogos;
import drytron.util.Mensagens;
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
    private TextField tfDir;

    @FXML
    void btnClickXlsxAction(ActionEvent event) {
        try {
            XlsxJogos.GerarXlsx(tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads");
            Desktop.getDesktop().open(new File(((tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads") + "\\00_JogosRelatorio.xlsx")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnClickDirAction(ActionEvent event) {
        if (!new File(tfDir.getText()).exists()) {
            Mensagens.mensagemAlerta("Diretório não encontrado.", "Por favor digite um dirtório válido.");
        }
    }

    @FXML
    void btnClickPdfAction(ActionEvent event) {
        try {
            PdfJogos.gerar(tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads");
            Desktop.getDesktop().open(new File(((tfDir.getText() == null || "".equals(tfDir.getText()) ? tfDir.getText() : "D:\\Downloads") + "\\JogosRelatorio.pdf")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnClickSairAction(ActionEvent event) {
        FxmlFactory.fecharTelaSecundario();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
