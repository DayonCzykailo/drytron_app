package drytron.controller;

import drytron.util.Mensagens;
import drytron.util.PdfAdmin;
import drytron.util.XlsxAdmin;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FxmlExibicaoAdminController implements Initializable {
    @FXML
    private TextField tfDir;

    @FXML
    void btnClickDirAction(ActionEvent event) {
        if(!new File(tfDir.getText()).exists()){
            Mensagens.mensagemAlerta("Diretório não encontrado.", "Por favor digite um dirtório válido.");
        }
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
        FxmlFactory.fecharTelaSecundario();
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
