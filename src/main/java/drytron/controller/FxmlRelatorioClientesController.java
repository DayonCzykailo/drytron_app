package drytron.controller;

import drytron.dao.ClientesRepository;
import drytron.dao.JogosRepository;
import drytron.dto.Jogos;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FxmlRelatorioClientesController implements Initializable {

    @FXML
    private PieChart pcMain;

    @FXML
    private Label percentLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientesRepository cr = new ClientesRepository();

        cr.listaPieChart(pcMain);

        pcMain.setStartAngle(90);

        for (PieChart.Data data : pcMain.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    percentLbl.setTranslateX(e.getSceneX() - percentLbl.getLayoutX());
                    percentLbl.setTranslateY(e.getSceneY() - percentLbl.getLayoutY());
                    percentLbl.setText("Quantidade: " + String.valueOf((int) data.getPieValue()));
                }
            });
        }
    }

}
