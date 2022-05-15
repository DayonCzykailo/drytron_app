package drytron.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author dayon
 */
@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCli = 0;
    @Column(nullable = false, length = 50)
    private String nomeCli = "";
    @Column(nullable = false, length = 11)
    private String cpfCli = "";
    @Column(nullable = false, length = 50)
    private String telCli = "";
    @Column(nullable = false, length = 50, name="")
    private String emaCli = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endCli;
    @Column(nullable = false)
    private char ativo;

    public Clientes() {
        ativo = 'S';
    }

    public Clientes(int idCli, String nomeCli, String cpfCli,
            String telCli, String emaCli, Endereco endCli) {
        this.endCli = endCli;
        this.idCli = idCli;
        this.nomeCli = nomeCli;
        this.cpfCli = cpfCli;
        this.telCli = telCli;
        this.emaCli = emaCli;
        this.endCli = endCli;
        ativo = 'S';
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    public long getIdCli() {
        return idCli;
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

    public Endereco getEndCli() {
        return endCli;
    }

    public void setEndCli(Endereco endCli) {
        this.endCli = endCli;
    }

}
