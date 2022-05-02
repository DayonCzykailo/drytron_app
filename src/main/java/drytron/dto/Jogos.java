
package drytron.dto;

public class Jogos {

   private int id=0;
   private String nome = "";
   private String genero = "";
   private String plataforma = "";
   private String lancamento = "" ;
   private String desenvolvedor = "";
   private String publicador = "";
   private String idioma = "";
   private Float preco = 0f;
   private int estoque = 0;
   
    public Jogos(){}

    public Jogos(int id,String nome, String genero, 
        String plataforma,String lancamento,
        String desenvolvedor, String publicador,
        String idioma,Float preco,int estoque ) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.plataforma = plataforma;
        this.lancamento = lancamento;
        this.desenvolvedor = desenvolvedor;
        this.publicador = publicador;
        this.idioma = idioma;
        this.preco = preco;
        this.estoque = estoque;
    } 
//Setter JOGOS
    public void setjogo(int id,String nome, String genero, 
        String plataforma,String lancamento,String desenvolvedor, String publicador,
        String idioma,Float preco,int estoque ){
        
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.plataforma = plataforma;
        this.lancamento = lancamento;
        this.desenvolvedor = desenvolvedor;
        this.publicador = publicador;
        this.idioma = idioma;
        this.preco = preco;
        this.estoque = estoque;  
    }
//Getters
    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getGenero(){
        return genero;
    }
    public String getPlataforma(){
        return plataforma;
    }
    public String getLancamento(){
        return lancamento;
    }
    public String getDesenvolvedor(){
        return desenvolvedor;
    }
    public String getPublicador(){
        return publicador;
    }
    public String getIdioma(){
        return idioma;
    }
    public float getPreco(){
        return preco;
    }
    public int getEstoque(){
        return estoque;
     }
}            

