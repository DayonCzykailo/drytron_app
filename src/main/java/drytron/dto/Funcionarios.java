package drytron.dto;

import java.io.Serializable;
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
public class Funcionarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 0;
    @Column(nullable = false, length = 50)
    private String nome = "";  
    @Column(nullable = false, length = 11)
    private String cpf = "";
    @Column(nullable = false, length = 50)
    private String telefone = "";
    @Column(nullable = false, length = 50)
    private String email = "";
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endFun;
    @Column(nullable = false)
    private char ativo;
    
    @Column(nullable = false, length = 50)
    private String senha = "";

    @Column(nullable = false)
    private int nivel;
    @Column(nullable = false, length = 50)
    private Cargo cargo;
    
  
    public Funcionarios() {
    this.ativo = 'S';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

   
}
