package drytron.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dayon
 */
public class FxmlFactory {

    static private Stage stageSecundario = new Stage();
    static private Stage stagePrincipal = new Stage();
    static private Scene scenePrincipal;
    static private Scene sceneSecundario;
    private static final Stage stageTelaPrinc = new Stage();
    private static final Stage stageTelaSec = new Stage();

    public static void acessarTelaPrincipal(Parent root) {
        acessarTelaPrincipal(root, "");
    }

    public static void acessarTelaPrincipal(Parent root, String url) {
        try {
            scenePrincipal = new Scene(root);
            stagePrincipal = stageTelaPrinc;
            if (!url.equals("")) {
                scenePrincipal.getStylesheets().add(url);
            }
            stagePrincipal.setTitle("Sistema de Vendas-Drytron");
            stagePrincipal.setScene(scenePrincipal);
            stagePrincipal.setResizable(false);
            stagePrincipal.show();
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
            if (root == null) {
                System.out.println("Nao Acho a tela");
            }
        }
    }

    public static void acessarTelaSecundario(Parent root) {
        try {

            sceneSecundario = new Scene(root);
            stageSecundario = stageTelaSec;
            stageSecundario.setTitle("Sistema de Vendas-Drytron");
            stageSecundario.setScene(sceneSecundario);
            stageSecundario.setResizable(false);
            stageSecundario.show();
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
            if (root == null) {
                System.out.println("Nao Acho a tela");
            }
        }
    }

    public static void fecharTelaPrincipal() {
        stagePrincipal.close();
    }

    public static void fecharTelaSecundario() {
        stageSecundario.close();
    }

    public static Stage getStageSecundario() {
        return stageSecundario;
    }
    
    public static Stage getStagePrincipal() {
        return stagePrincipal;
    }
}
