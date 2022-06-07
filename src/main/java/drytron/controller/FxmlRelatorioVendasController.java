package drytron.controller;

import drytron.repository.VendasRepository;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class FxmlRelatorioVendasController implements Initializable {

    @FXML
    private ScatterChart<?, ?> scMain;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series = new XYChart.Series();
        
        VendasRepository vr = new VendasRepository();
        List<Object[]> list = vr.listaTodosScatterChart();
        
        for (Object[] obj : list) {
            series.getData().add(new XYChart.Data(String.valueOf(obj[1]), (double) obj[0]));
        }
        
        scMain.getData().add(series);
    }
}
