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
    private Endereco endCli;
    @Column(nullable = false)
    private char ativo;

    public Clientes() {
        ativo = 'S';
    }

    public Clientes(long idCli, String nomeCli, String cpfCli,
            String telCli, String emaCli, Endereco endCli) {
        this.endCli = endCli;
        this.id = idCli;
        this.nome = nomeCli;
        this.cpf = cpfCli;
        this.telefone = telCli;
        this.email = emaCli;
        this.endCli = endCli;
        ativo = 'S';
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String tel) {
        this.telefone = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndCli() {
        return endCli;
    }

    public void setEndCli(Endereco endCli) {
        this.endCli = endCli;
    }

}
