/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drytron.dto;

/**
 *
 * @author patyu
 */

public class Clientes {
    private int idCli = 0;
    private String nomeCli = "";
    private String cpfCli = "";
    private String telCli = "";
    private String emaCli = "";
    private String endCli = "";
    private String estCli = "";
    
    public Clientes(){}

    public Clientes(int idCli,String nomeCli, String cpfCli, 
        String telCli,String emaCli,String endCli, String estCli){
    
       this.idCli = idCli;
       this.nomeCli = nomeCli;
       this.cpfCli = cpfCli;
       this.telCli = telCli;
       this.emaCli = emaCli;
       this.endCli = endCli;
       this.estCli = estCli;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public String getCpfCli() {
        return cpfCli;
    }

    public void setCpfCli(String cpfCli) {
        this.cpfCli = cpfCli;
    }

    public String getTelCli() {
        return telCli;
    }

    public void setTelCli(String telCli) {
        this.telCli = telCli;
    }

    public String getEmaCli() {
        return emaCli;
    }

    public void setEmaCli(String emaCli) {
        this.emaCli = emaCli;
    }

    public String getEndCli() {
        return endCli;
    }

    public void setEndCli(String endCli) {
        this.endCli = endCli;
    }

    public String getEstCli() {
        return estCli;
    }

    public void setEstCli(String estCli) {
        this.estCli = estCli;
    }
    
    
    
}
