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
public class Funcionarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFun = 0;
    @Column(nullable = false, length = 50)
    private String nomeFun = "";
    
    @Column(nullable = false, length = 50)
    private String usuario = "";
    @Column(nullable = false, length = 50)
    private String senha = "";
    
    @Column(nullable = false, length = 11)
    private String cpfFun = "";
    @Column(nullable = false, length = 50)
    private String telFun = "";
    @Column(nullable = false, length = 50)
    private String emaFun = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endFun;
    @Column(nullable = false)
    private char ativo;

    public Funcionarios() {
    this.ativo = 'S';
    }
    
    public long getIdFun() {
        return idFun;
    }

    public void setIdFun(long idFun) {
        this.idFun = idFun;
    }

    public String getNomeFun() {
        return nomeFun;
    }

    public void setNomeFun(String nomeFun) {
        this.nomeFun = nomeFun;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpfFun() {
        return cpfFun;
    }

    public void setCpfFun(String cpfFun) {
        this.cpfFun = cpfFun;
    }

    public String getTelFun() {
        return telFun;
    }

    public void setTelFun(String telFun) {
        this.telFun = telFun;
    }

    public String getEmaFun() {
        return emaFun;
    }

    public void setEmaFun(String emaFun) {
        this.emaFun = emaFun;
    }

    public Endereco getEndFun() {
        return endFun;
    }

    public void setEndFun(Endereco endFun) {
        this.endFun = endFun;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
    
    
    
}
