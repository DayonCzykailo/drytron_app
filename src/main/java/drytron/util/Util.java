package drytron.util;

import drytron.dto.Jogos;

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
    
    
}
