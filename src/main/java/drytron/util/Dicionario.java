package drytron.util;

import drytron.model.Cargo;
import drytron.model.GeneroJogos;
import drytron.model.PlataformaJogos;
import drytron.model.Uf;
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

    static public int getNivel(Cargo cargo) {
        switch (cargo) {
            case SEM_CARGO:
                return 0;
            case ADMINISTRADOR:
                return 1;
            case FUNCIONARIO:
                return 2;
            case GERENTE:
                return 3;
            default:
                return 0;
        }
    }

    static public String getCargo(Cargo cargo) {
        switch (cargo) {
            case SEM_CARGO:
                return "SEM CARGO";
            case ADMINISTRADOR:
                return "ADMINISTRADOR";
            case FUNCIONARIO:
                return "FUNCIONARIO";
            case GERENTE:
                return "GERENTE";
            default:
                return "SEM_CARGO";
        }
    }

    static public Cargo getCargoEnum(String cargo) {
        switch (cargo) {
            case "SEM_CARGO":
                return Cargo.SEM_CARGO;
            case "ADMINISTRADOR":
                return Cargo.ADMINISTRADOR;
            case "FUNCIONARIO":
                return Cargo.FUNCIONARIO;
            case "GERENTE":
                return Cargo.GERENTE;
            default:
                return Cargo.SEM_CARGO;
        }
    }

    static public String getUF(Uf ue) {
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

    static public Uf getUFEnum(String ue) {
        switch (ue) {
            case "UF":
                return Uf.UF;
            case "AC":
                return Uf.AC;
            case "AL":
                return Uf.AL;
            case "AM":
                return Uf.AM;
            case "AP":
                return Uf.AP;
            case "BA":
                return Uf.BA;
            case "CE":
                return Uf.CE;
            case "DF":
                return Uf.DF;
            case "ES":
                return Uf.ES;
            case "GO":
                return Uf.GO;
            case "MA":
                return Uf.MA;
            case "MG":
                return Uf.MG;
            case "MS":
                return Uf.MS;
            case "MT":
                return Uf.MT;
            case "PA":
                return Uf.PA;
            case "PB":
                return Uf.PB;
            case "PE":
                return Uf.PE;
            case "PI":
                return Uf.PI;
            case "PR":
                return Uf.PR;
            case "RJ":
                return Uf.RJ;
            case "RN":
                return Uf.RN;
            case "RO":
                return Uf.RO;
            case "RR":
                return Uf.RR;
            case "RS":
                return Uf.RS;
            case "SC":
                return Uf.SC;
            case "SE":
                return Uf.SE;
            case "SP":
                return Uf.SP;
            case "TO":
                return Uf.TO;
            default:
                return null;
        }
    }

    static public String getGenero(GeneroJogos gj) {
        switch (gj) {
            case ACAO:
                return "A????o";
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
                return "Sobreviv??ncia";
            case RITIMICO:
                return "Rit??mico";
            case SIMULACAO:
                return "Simula????o";
            case PUZZLE:
                return "Puzzle";
            case ESPORTES:
                return "Esportes";
            case ESTRATEGIA:
                return "Estrat??gia";
            case CORRIDA:
                return "Corrida";
            case EXPLORACAO:
                return "Explora????o";
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
            case "A????o":
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
            case "Sobreviv??ncia":
                return GeneroJogos.SOBREVIVENCIA;
            case "Rit??mico":
                return GeneroJogos.RITIMICO;
            case "Simula????o":
                return GeneroJogos.SIMULACAO;
            case "Puzzle":
                return GeneroJogos.PUZZLE;
            case "Esportes":
                return GeneroJogos.ESPORTES;
            case "Estrat??gia":
                return GeneroJogos.ESTRATEGIA;
            case "Corrida":
                return GeneroJogos.CORRIDA;
            case "Explora????o":
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
