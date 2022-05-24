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

    static public String getDinheiro(String valor) {
        return getDinheiro(Double.valueOf(valor));
    }

    static public String getDinheiro(float valor) {
        return getDinheiro(Double.valueOf(valor));
    }

    static public String getDinheiro(double valor) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }

    static public String getUF(UfEnum ue) {
        switch (ue) {
            case UF:
                return "UF";
            case AC:
                return "AC";
            case AL:
                return "AL";
            case AM:
                return "AM";
            case AP:
                return "AP";
            case BA:
                return "BA";
            case CE:
                return "CE";
            case DF:
                return "DF";
            case ES:
                return "ES";
            case GO:
                return "GO";
            case MA:
                return "MA";
            case MG:
                return "MG";
            case MS:
                return "MS";
            case MT:
                return "MT";
            case PA:
                return "PA";
            case PB:
                return "PB";
            case PE:
                return "PE";
            case PI:
                return "PI";
            case PR:
                return "PR";
            case RJ:
                return "RJ";
            case RN:
                return "RN";
            case RO:
                return "RO";
            case RR:
                return "RR";
            case RS:
                return "RS";
            case SC:
                return "SC";
            case SE:
                return "SE";
            case SP:
                return "SP";
            case TO:
                return "TO";
            default:
                return "";
        }
    }
    
    static public UfEnum getUFEnum(String ue) {
        switch (ue) {
            case "UF":
                return UfEnum.UF;
            case "AC":
                return UfEnum.AC;
            case  "AL":
                return UfEnum.AL;
            case "AM":
                return UfEnum.AM;
            case "AP":
                return UfEnum.AP;
            case "BA":
                return UfEnum.BA;
            case "CE":
                return UfEnum.CE;
            case "DF":
                return UfEnum.DF;
            case "ES":
                return UfEnum.ES;
            case "GO":
                return UfEnum.GO;
            case "MA":
                return UfEnum.MA;
            case "MG":
                return UfEnum.MG;
            case  "MS":
                return UfEnum.MS;
            case  "MT":
                return UfEnum.MT;
            case "PA":
                return UfEnum.PA;
            case "PB":
                return UfEnum.PB;
            case "PE":
                return UfEnum.PE;
            case  "PI":
                return UfEnum.PI;
            case "PR":
                return UfEnum.PR;
            case "RJ":
                return UfEnum.RJ;
            case "RN":
                return UfEnum.RN;
            case "RO":
                return UfEnum.RO;
            case "RR":
                return UfEnum.RR;
            case "RS":
                return UfEnum.RS;
            case "SC":
                return UfEnum.SC;
            case "SE":
                return UfEnum.SE;
            case  "SP":
                return UfEnum.SP;
            case "TO":
                return UfEnum.TO;
            default:
                return null;
        }
    }

    static public String getGenero(GeneroJogos gj) {
        switch (gj) {
            case ACAO:
                return "Ação";
            case AVENTURA:
                return "Aventura";
            case FPS:
                return "FPS";
            case RPG:
                return "RPG";
            case FANTASIA:
                return "Fantasia";
            case LUTA:
                return "Luta";
            case SOBREVIVENCIA:
                return "Sobrevivência";
            case RITIMICO:
                return "Ritímico";
            case SIMULACAO:
                return "Simulação";
            case PUZZLE:
                return "Puzzle";
            case ESPORTES:
                return "Esportes";
            case ESTRATEGIA:
                return "Estratégia";
            case CORRIDA:
                return "Corrida";
            case EXPLORACAO:
                return "Exploração";
            default:
                return "";
        }
    }

    static public String getPlataforma(PlataformaJogos pj) {
        switch (pj) {
            case XBOX360:
                return "XBOX 360";
            case XBOXONE:
                return "XBOX ONE";
            case PS3:
                return "PS3";
            case PS4:
                return "PS4";
            case PS5:
                return "PS5";
            case XBOXSERIESX:
                return "XBOX SERIES X";
            case PC:
                return "Computador";
            case ANDROID:
                return "ANDROID";
            case IOS:
                return "IOS";
            case SWITCH:
                return "Switch";
            case NINTENDO3DS:
                return "3DS";
            case NINTENDODS:
                return "DS";
            default:
                return "";
        }
    }

    static public GeneroJogos getGeneroEnum(String gj) {
        switch (gj) {
            case "Ação":
                return GeneroJogos.ACAO;
            case "Aventura":
                return GeneroJogos.AVENTURA;
            case "FPS":
                return GeneroJogos.FPS;
            case "RPG":
                return GeneroJogos.RPG;
            case "Fantasia":
                return GeneroJogos.FANTASIA;
            case "Luta":
                return GeneroJogos.LUTA;
            case "Sobrevivência":
                return GeneroJogos.SOBREVIVENCIA;
            case "Ritímico":
                return GeneroJogos.RITIMICO;
            case "Simulação":
                return GeneroJogos.SIMULACAO;
            case "Puzzle":
                return GeneroJogos.PUZZLE;
            case "Esportes":
                return GeneroJogos.ESPORTES;
            case "Estratégia":
                return GeneroJogos.ESTRATEGIA;
            case "Corrida":
                return GeneroJogos.CORRIDA;
            case "Exploração":
                return GeneroJogos.EXPLORACAO;
            default:
                return null;
        }
    }

    static public PlataformaJogos getPlataformaEnum(String pj) {
        switch (pj) {
            case "XBOX 360":
                return PlataformaJogos.XBOX360;
            case "XBOX ONE":
                return PlataformaJogos.XBOXONE;
            case "PS3":
                return PlataformaJogos.PS3;
            case "PS4":
                return PlataformaJogos.PS4;
            case "PS5":
                return PlataformaJogos.PS5;
            case "XBOX SERIES X":
                return PlataformaJogos.XBOXSERIESX;
            case "Computador":
                return PlataformaJogos.PC;
            case "ANDROID":
                return PlataformaJogos.ANDROID;
            case "IOS":
                return PlataformaJogos.IOS;
            case "Switch":
                return PlataformaJogos.SWITCH;
            case "3DS":
                return PlataformaJogos.NINTENDO3DS;
            case "DS":
                return PlataformaJogos.NINTENDODS;
            default:
                return null;
        }
    }

}
