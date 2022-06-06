package drytron.controller;

import drytron.dao.FuncionariosRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FxmlRelatorioAdminController implements Initializable {

    @FXML
    private PieChart pcMain;

    @FXML
    private Label percentLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FuncionariosRepository cr = new FuncionariosRepository();

        cr.listaPieChart(pcMain);

        pcMain.setStartAngle(90);

        for (PieChart.Data data : pcMain.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                percentLbl.setTranslateX(e.getSceneX() - percentLbl.getLayoutX());
                percentLbl.setTranslateY(e.getSceneY() - percentLbl.getLayoutY());
                percentLbl.setText("Quantidade: " + String.valueOf((int) data.getPieValue()));
            });
        }
    }

}
