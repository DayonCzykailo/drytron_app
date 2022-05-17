package drytron.util;

import drytron.dto.Jogos;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dayon
 */
public class Util {
    
    static Jogos jogos = null;
    
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
