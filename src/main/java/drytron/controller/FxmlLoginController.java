package drytron.controller;

import drytron.dto.Endereco;
import drytron.repository.FuncionariosRepository;
import drytron.model.Cargo;
import drytron.dto.Funcionarios;
import drytron.util.Dicionario;
import drytron.util.Mensagens;
import drytron.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FxmlLoginController implements Initializable {

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfSenha;

    @FXML
    void btnClickSairAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnClickbtnEsqSenhaAction(ActionEvent event) {
        try {
            FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlEsqueciMinhaSenha.fxml")));
        } catch (IOException ex) {
            System.out.println("DEU RUIM EM btnClickbtnEntrarAction no EsqSenha :" + ex.getMessage());
        }

    }
    private boolean verificarFuncionarios(){
        ArrayList<Funcionarios> lista = (ArrayList<Funcionarios>) new FuncionariosRepository().listaTodosAdminOrGerente();
        if(lista.isEmpty()){
            return false;
        }
        return true;
    }
    private void gerarPrimeiroAcesso(){
        try {
           if(!verificarFuncionarios()){
           Funcionarios f = new Funcionarios();
           
        f.setNome("master");
        f.setCpf("00000000");
        f.setTelefone("");
        f.setEmail("");

        Endereco e = new Endereco();
        e.setCep("");
        e.setComplemento("");
        e.setLogradouro("");
        e.setBairro("");
        e.setLocalidade("");
        e.setUf("");

        f.setEndFun(e);

        f.setSenha("master");
        f.setCargo(Cargo.ADMINISTRADOR);
        f.setNivel(Dicionario.getNivel(Cargo.ADMINISTRADOR));
           
           
           new FuncionariosRepository().insere(f);
        } 
        } catch (Exception e) {
            Mensagens.mensagemExcessao("ERRO CRÍTICO", "VERIFIQUE O SUPORTE", e);
        }
        
    }

    @FXML
    void btnClickbtnEntrarAction(ActionEvent event) {
        FuncionariosRepository cr = new FuncionariosRepository();
        List<Funcionarios> lista = cr.listaTodos();
        boolean logou = false;
        for (int i = 0; i < lista.size(); i++) {
            if (((lista.get(i).getNome().equals(tfLogin.getText())) && (lista.get(i).getSenha().equals(tfSenha.getText())))) {
                Util.setUsuario(lista.get(i));
                if (lista.get(i).getCargo().equals(Cargo.ADMINISTRADOR) || lista.get(i).getCargo().equals(Cargo.GERENTE)) {
                    try {
                        logou = true;
                        FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainAdmin.fxml")));
                    } catch (IOException ex) {
                        System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");
                    }
                } else {
                    try {
                        logou = true;
                        FxmlFactory.acessarTelaPrincipal(FXMLLoader.load(getClass().getResource("/drytron/fxml/FxmlMainProdutos.fxml")), getClass().getResource("/drytron/css/cssfxmlmain.css").toExternalForm());
                    } catch (IOException ex) {
                        System.out.println("DEU RUIM EM btnClickbtnEntrarAction no FXMLLOGIN");
                    }
                }
            }
        }
        if (!logou) {
            Mensagens.mensagemInfo("LOGIN INVÁLIDO!!!", "VERIFIQUE SE SUA SENHA OU NOME DE USUARIO ESTÃO INCORRETOS");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gerarPrimeiroAcesso();
    }
}
