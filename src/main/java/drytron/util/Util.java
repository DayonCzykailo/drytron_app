package drytron.util;

import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.Jogos;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dayon
 */
public abstract class Util {
    
    static Jogos jogos = null;
    static Clientes clientes = null;
    static Endereco endereco = null;

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
    
    public static Jogos getJogos(){
        return jogos;
    }
    public static void setJogos(Jogos jogo){
        jogos = jogo;
    }
    
    static public String getDataAgora(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }
    
    
    static public String getDataHoraAgora(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
    }
  
}
