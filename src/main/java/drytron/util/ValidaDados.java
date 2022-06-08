package drytron.util;

import drytron.dto.Clientes;
import drytron.dto.Funcionarios;
import drytron.dto.Jogos;
import drytron.model.Cargo;
import drytron.model.GeneroJogos;
import drytron.model.PlataformaJogos;
import drytron.repository.ClientesRepository;
import drytron.repository.FuncionariosRepository;
import drytron.repository.JogosRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public abstract class ValidaDados {

    public static boolean validaJogos(String nome, GeneroJogos genero, PlataformaJogos plataforma, DatePicker lancamento,
            String desenvolvedor, String publicador, String idioma, String estoque, String preco) {

        ArrayList<Jogos> jogos = (ArrayList<Jogos>) new JogosRepository().verificaPorNome(nome);

        if (!jogos.isEmpty()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O NOME JÁ EXISTENTE, TENTE VARIAÇÕES.");
            return false;
        }
        if (nome.isEmpty() || nome.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UM NOME.");
            return false;
        }

        if (genero == null) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UM GÊNERO.");
            return false;
        }

        if (plataforma == null) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UMA PLATAFORMA.");
            return false;
        }

        if (lancamento == null || lancamento.getValue() == null) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UMA DATA DE LANÇAMENTO.");
            return false;
        }

        if (desenvolvedor.isEmpty() || desenvolvedor.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UM DESENVOLVEDOR.");
            return false;
        }

        if (publicador.isEmpty() || publicador.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UM PUBLICADOR.");
            return false;
        }

        if (idioma.isEmpty() || idioma.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "INSIRA UM IDIOMA.");
            return false;
        }

        if (!ValidaDados.validaNome(idioma)) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O IDIOMA NÃO PODE POSSUIR NUMEROS.");
            return false;
        }

        if (!validaEstoque(estoque) || estoque.isEmpty() || estoque.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O ESTOQUE É INVÁLIDO.");
            return false;
        }

        if (!validaPreco(preco) || preco.isEmpty() || preco.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O PREÇO É INVÁLIDO.");
            return false;
        }

        return true;
    }

    public static boolean validaAdmin(String cep, String email, String senha, String cpf, String nome, String tel, Cargo cargo) {
        ArrayList<Funcionarios> funcionarios = (ArrayList<Funcionarios>) new FuncionariosRepository().verificaPorNome(nome);

        if (!ValidaDados.validaNome(nome)) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O NOME NÃO PODE CONTER NUMEROS.");
            return false;
        }

        if (!funcionarios.isEmpty()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O NOME JÁ EXISTENTE, TENTE VARIAÇÕES.");
            return false;
        }

        if (!email.contains("@") || email.isEmpty() || email.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SUA EMAIL.");
            return false;
        }

        if (senha.isEmpty() || senha.isBlank() || senha.length() < 6) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SUA SENHA,\n ELA NÃO PODE SER MENOR QUE 6 DIGITOS.");
            return false;
        }

        if (!ValidaDados.validaCpf(cpf.replace(".", "").replace("-", ""))) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SE SEU CPF É VÁLIDO.");
            return false;
        }

        if (!tel.replace("(", "").replace(")", "").replace("-", "").isBlank()
                || !tel.replace("(", "").replace(")", "").replace("-", "").isEmpty()) {

            if (tel.replace("(", "").replace(")", "").replace("-", "").length() != 11) {
                Mensagens.mensagemAlerta("ALERTA!!!", "O TAMANHO DO TELEFONE É INVALIDO.");
                return false;
            }
        }
        if (cep.isEmpty() || cep.isBlank() || cep.length() < 9) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SEU CEP.");
            return false;
        }

        if (cargo == null) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O CARGO É INVALIDO.");
            return false;
        }

        return true;
    }

    public static boolean validaCliente(String cep, String email, String cpf, String nome, String tel) {
        ArrayList<Clientes> clientes = (ArrayList<Clientes>) new ClientesRepository().verificaPorNome(nome);

        if (!ValidaDados.validaNome(nome)) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O NOME NÃO PODE CONTER NUMEROS.");
            return false;
        }

        if (!clientes.isEmpty()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "O NOME JÁ EXISTENTE, TENTE VARIAÇÕES.");
            return false;
        }

        if (!email.contains("@") || email.isEmpty() || email.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SUA EMAIL.");
            return false;
        }

        if (!ValidaDados.validaCpf(cpf.replace(".", "").replace("-", ""))) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SE SEU CPF É VÁLIDO.");
            return false;
        }

        if (!tel.replace("(", "").replace(")", "").replace("-", "").isBlank()
                || !tel.replace("(", "").replace(")", "").replace("-", "").isEmpty()) {

            if (tel.replace("(", "").replace(")", "").replace("-", "").length() != 11) {
                Mensagens.mensagemAlerta("ALERTA!!!", "O TAMANHO DO TELEFONE É INVALIDO.");
                return false;
            }
        }
        if (cep.isEmpty() || cep.isBlank()) {
            Mensagens.mensagemAlerta("ALERTA!!!", " SEU CEP É NULO.");
            return false;
        }
        if (cep.length() < 7 || cep.length() > 9) {
            Mensagens.mensagemAlerta("ALERTA!!!", "VERIFIQUE SEU CEP.");
            return false;
        }
        return true;
    }

    public static double validaDesconto(TextField tf) {
        return Double.valueOf(tf.getText().trim() == null || tf.getText().trim().equals("") ? "0" : tf.getText());
    }

    public static boolean validaNome(String nome) {
        boolean validador = true;

        for (int i = 0; i < nome.length(); i++) {
            if (Character.isDigit(nome.charAt(i))) {
                validador = false;
            }
        }
        return validador;
    }

    public static boolean validaPreco(String preco) {
        try {
            Double validade = Double.parseDouble(preco);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validaEstoque(String estoque) {
        try {
            Long validade = Long.parseLong(estoque);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validaData(String dia) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            @SuppressWarnings("unused")
            LocalDate date = LocalDate.parse(dia, dateTimeFormatter);

            return true;
        } catch (DateTimeParseException e) {
            System.out.println("ERRO:" + e.getMessage());
            return false;
        }
    }

    public static boolean validaCpf(String cpf) {

        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }

    }

}
