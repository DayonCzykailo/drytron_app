package drytron.controller;

import drytron.repository.JogosRepository;
import drytron.dto.Jogos;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class FxmlRelatorioJogosController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JogosRepository jr = new JogosRepository();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        List<Jogos> list = jr.listaTodos();
        lineChart.setTitle("Jogos por Estoque");
        lineChart.setLegendVisible(false);
       
        
        for (int i = 0; i < list.size(); i++) {
            series.getData().add(new XYChart.Data<>(list.get(i).getNome(), list.get(i).getEstoque()));
        }
        lineChart.getData().add(series);
    }
}
