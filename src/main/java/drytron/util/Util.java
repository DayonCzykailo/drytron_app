package drytron.util;

import drytron.controller.FxmlMainAdminController;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.Funcionarios;
import drytron.dto.Jogos;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 *
 * @author dayon
 */
public abstract class Util {

    static Jogos jogos = null;
    static Clientes clientes = null;
    static Endereco endereco = null;
    static Funcionarios funcionarios = null;
    static Funcionarios usuario;
    static String telaUrl;
    
    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Util.stage = stage;
    }

    public static Funcionarios getUsuario() {
        return usuario;
    }

    public static void setUsuario(Funcionarios usuario) {
        Util.usuario = usuario;
    }

    public static String getTelaUrl() {
        return telaUrl;
    }

    public static void setTelaUrl(String telaUrl) {
        Util.telaUrl = telaUrl;
    }


    public static Funcionarios getFuncionarios() {
        return funcionarios;
    }

    public static void setFuncionarios(Funcionarios funcionarios) {
        Util.funcionarios = funcionarios;
    }

    public static Endereco getEndereco() {
        return endereco;
    }

    public static void setEndereco(Endereco endereco) {
        Util.endereco = endereco;
    }

    public static Clientes getClientes() {
        return clientes;
    }

    public static void setClientes(Clientes clientes) {
        Util.clientes = clientes;
    }

    public static Jogos getJogos() {
        return jogos;
    }

    public static void setJogos(Jogos jogo) {
        jogos = jogo;
    }

    static public String getDataAgora() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }

    static public String getDataHoraAgora() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
    }

    public static void mostrarSenha(Tooltip toolTip, TextField tfSenha) {
        Point2D p = tfSenha.localToScene(tfSenha.getBoundsInLocal().getMaxX(), tfSenha.getBoundsInLocal().getMaxY());
        toolTip.setText(tfSenha.getText());
        toolTip.show(tfSenha,
                p.getX() + FxmlMainAdminController.getStage().getScene().getX() + FxmlMainAdminController.getStage().getX(),
                p.getY() + FxmlMainAdminController.getStage().getScene().getY() + FxmlMainAdminController.getStage().getY());
    }

    public static void esconderSenha(Tooltip toolTip) {
        toolTip.setText("");
        toolTip.hide();
    }

}
