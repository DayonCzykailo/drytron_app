package drytron.dto;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

/**
 *
 * @author dayon
 */
public class TableMainVendas {
    
    private Long id;
    private String vendedor;
    private String comprador;
    private String produto;
    private Long quantidade;
    private String dataCompra;
    private String percDesconto;
    private String valorFinal;
    private char ativo;

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getPercDesconto() {
        return percDesconto;
    }

    public void setPercDesconto(String percDesconto) {
        this.percDesconto = percDesconto;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

   

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    
}
