package drytron.dto;

/**
 *
 * @author dayon
 */
public class TableVendas {
    private String produto;
    private double desconto;
    private int quantidade;

    public TableVendas( String produto, double desconto, int quantidade) {
        this.produto = produto;
        this.desconto = desconto;
        this.quantidade = quantidade;
    }


    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
