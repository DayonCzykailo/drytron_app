package drytron.util;

import drytron.dto.GeneroJogos;
import drytron.dto.PlataformaJogos;
import drytron.dto.UfEnum;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author dayon
 */
public abstract class Dicionario {
    
    static public String getDinheiro(String valor){
       return getDinheiro(Double.valueOf(valor));
    }
    static public String getDinheiro(float valor){
       return getDinheiro(Double.valueOf(valor));
    }
    static public String getDinheiro(double valor){
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        return nf.format(valor);
    }  
    /*static public String getUF(UfEnum ue){
        switch (ue) {
            case UF:
                return "";
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                 case val:
                return ;
                
                
                
            default: 
        }
    }

    static public String getGenero(GeneroJogos gj) {
        return switch (gj) {
            case ACAO -> "Ação";
            case AVENTURA -> "Aventura";
            case FPS -> "FPS";
            case RPG -> "RPG";
            case FANTASIA -> "Fantasia";
            case LUTA -> "Luta";
            case SOBREVIVENCIA -> "Sobrevivência";
            case RITIMICO -> "Ritímico";
            case SIMULACAO -> "Simulação";
            case PUZZLE -> "Puzzle";
            case ESPORTES -> "Esportes";
            case ESTRATEGIA -> "Estratégia";
            case CORRIDA -> "Corrida";
            case EXPLORACAO -> "Exploração";
            default -> "";
        };
    }
    
    static public String getPlataforma(PlataformaJogos pj){
        return switch (pj) {
            case XBOX360 -> "XBOX 360";
            case XBOXONE -> "XBOX ONE";
            case PS3 -> "PS3";
            case PS4 -> "PS4";
            case PS5 -> "PS5";
            case XBOXSERIESX -> "XBOX SERIES X";
            case PC -> "Computador";
            case ANDROID -> "ANDROID";
            case IOS -> "IOS";
            case SWITCH -> "Switch";
            case NINTENDO3DS -> "3DS";
            case NINTENDODS -> "DS";
            default -> "";
        };
    }
    static public GeneroJogos getGeneroEnum(String gj) {
        return switch (gj) {
            case "Ação" -> GeneroJogos.ACAO;
            case "Aventura" -> GeneroJogos.AVENTURA;
            case "FPS" -> GeneroJogos.FPS;
            case "RPG" -> GeneroJogos.RPG;
            case  "Fantasia" -> GeneroJogos.FANTASIA;
            case "Luta" -> GeneroJogos.LUTA;
            case "Sobrevivência" -> GeneroJogos.SOBREVIVENCIA;
            case "Ritímico" -> GeneroJogos.RITIMICO;
            case "Simulação" -> GeneroJogos.SIMULACAO;
            case "Puzzle" -> GeneroJogos.PUZZLE;
            case "Esportes" -> GeneroJogos.ESPORTES;
            case "Estratégia" ->GeneroJogos.ESTRATEGIA;
            case "Corrida" -> GeneroJogos.CORRIDA;
            case "Exploração" -> GeneroJogos.EXPLORACAO;
            default -> null;
        };
    }
    
    static public PlataformaJogos getPlataformaEnum(String pj){
        return switch (pj) {
            case "XBOX 360" -> PlataformaJogos.XBOX360;
            case "XBOX ONE" -> PlataformaJogos.XBOXONE;
            case "PS3" -> PlataformaJogos.PS3;
            case  "PS4" -> PlataformaJogos.PS4;
            case "PS5" -> PlataformaJogos.PS5;
            case "XBOX SERIES X" -> PlataformaJogos.XBOXSERIESX;
            case "Computador" -> PlataformaJogos.PC;
            case "ANDROID" -> PlataformaJogos.ANDROID;
            case "IOS" -> PlataformaJogos.IOS;
            case "Switch" -> PlataformaJogos.SWITCH;
            case  "3DS" -> PlataformaJogos.NINTENDO3DS;
            case  "DS" -> PlataformaJogos.NINTENDODS;
            default -> null;
        };
    }*/
    
}
