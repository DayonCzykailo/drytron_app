package drytron.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vendas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Funcionarios vendedor;
    @OneToOne(cascade = CascadeType.ALL)
    private Clientes comprador;
    @OneToOne(cascade = CascadeType.ALL)
    private Jogos produto;

    private int quantidade;
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCompra;
    private float percDesconto;
    private double valorFinal;
    private char ativo;

    public Vendas() {
        ativo = 'S';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }



    public Funcionarios getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionarios vendedor) {
        this.vendedor = vendedor;
    }

    public Clientes getComprador() {
        return comprador;
    }

    public void setComprador(Clientes comprador) {
        this.comprador = comprador;
    }

    public Jogos getProduto() {
        return produto;
    }

    public void setProduto(Jogos produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getData() {
        return dataCompra;
    }

    public void setData(LocalDateTime data) {
        this.dataCompra = data;
    }

    public float getPercDesconto() {
        return percDesconto;
    }

    public void setPercDesconto(float percDesconto) {
        this.percDesconto = percDesconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

}
