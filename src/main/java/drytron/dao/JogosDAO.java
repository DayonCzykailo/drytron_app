package drytron.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import drytron.dto.Jogos;
import java.util.List;

public class JogosDAO {

 
    public Connection getConecxao(){//sem uso
    Connection con;
        try{
           Class.forName("org.sqlite.JDBC");
           con = DriverManager.getConnection("jdbc:sqlite:banco_de_dados\\banco_de_dados.db");
           return con;
        }catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"ERRO NA CONEC��O COM O BANCO SQLITE");
            return null;
        }
    }
  
        
    public ArrayList<Jogos> listaJogos(){//sem uso
        ArrayList<Jogos> listaJogos = new ArrayList<>();
        try {
            Connection con;
            con = getConecxao();
            String query = "SELECT * FROM jogos";
            Statement st;
            ResultSet rt;
            st = con.createStatement();
            rt = st.executeQuery(query);
            Jogos jogos;
            while(rt.next()){
                /*jogos = new Jogos(rt.getInt("id"), rt.getString("nome"),rt.getString("genero"),rt.getString("plataforma")
                ,rt.getString("lancamento"),rt.getString("desenvolvedor"),rt.getString("publicador"),rt.getString("idioma"),
                rt.getFloat("preco"),rt.getInt("estoque"));
              listaJogos.add(jogos);*/
            }
        } catch (Exception e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(null,"ERRO NO METODO LISTAJOGOS : ARRAYLIST<JOGOS> ");
        }
        return listaJogos;
    } 
    
    public void mostrarJogos(JTable jtPlanilha){//sem uso
    	try {
            ArrayList<Jogos> lista = listaJogos();
            DefaultTableModel model = (DefaultTableModel) jtPlanilha.getModel();
          
            Object[] row = new Object[10];
            for(int i=0;i<lista.size();i++){
                row[0] = lista.get(i).getId();
                row[1] = lista.get(i).getNome();
                row[2] = lista.get(i).getGenero();
                row[3] = lista.get(i).getPlataforma();
                row[4] = lista.get(i).getLancamento();
                row[5] = lista.get(i).getDesenvolvedor();
                row[6] = lista.get(i).getPublicador();
                row[7] = lista.get(i).getIdioma();
                row[8] = lista.get(i).getPreco();
                row[9] = lista.get(i).getEstoque();   
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERRO NO METODO MOSTARJOGOS : VOID ");
	}
      
    }
    
    public  ArrayList<Jogos> pesquisaPorNome(JTextField jtPesquisar){
        ArrayList<Jogos> jogosList = new ArrayList<>();  
       Connection con = getConecxao();
       String query = "SELECT * FROM jogos where nome like'%"+jtPesquisar.getText()+"%'";
       Statement st;
       ResultSet rt;
       
       try {
           st = con.createStatement();
           rt = st.executeQuery(query);
           Jogos jogos;
           while(rt.next()){
               /*jogos = new Jogos(rt.getInt("id"), rt.getString("nome"),rt.getString("genero"),rt.getString("plataforma")
               ,rt.getString("anoLan"),rt.getString("desenvolvedor"),rt.getString("publicador"),rt.getString("idioma"),
               rt.getFloat("preco"),rt.getInt("estoque"));
               jogosList.add(jogos);*/
           }
           
       } catch (Exception ex) {
           ex.printStackTrace();
           JOptionPane.showMessageDialog(null,"ERRO  NO METODO PESQUISAPORNOME : ARRAYLIST<JOGOS>");
       }
       return jogosList;
       
   } 
    
    public void salvar(JTextField jtNome,JTextField jtGenero,JTextField jtPlataforma,JTextField jtAnoLan
    		,JTextField jtDesenvolvedor,JTextField jtPublicador
    		,JTextField jtIdioma ,JTextField jtPreco, JTextField jtEstoque) {
    	Connection con;
        try {
             
            con = getConecxao();
             String query = "INSERT INTO jogos (nome,genero,plataforma,anoLan,"
                        + "desenvolvedor,publicador,idioma,preco,estoque) "
                        + "VALUES (?,?,?,?,?,?,?,?,?);";
      
            PreparedStatement pstmt = con.prepareStatement(query);
        	               
            pstmt.setString(1,jtNome.getText());
            pstmt.setString(2,jtGenero.getText());
            pstmt.setString(3,jtPlataforma.getText());
            pstmt.setString(4,jtAnoLan.getText());
            pstmt.setString(5,jtDesenvolvedor.getText());
            pstmt.setString(6,jtPublicador.getText());
            pstmt.setString(7,jtIdioma.getText());
            pstmt.setFloat(8,Float.valueOf(jtPreco.getText()));
            pstmt.setInt(9,Integer.valueOf(jtEstoque.getText()));
                            
    	    pstmt.executeUpdate();
    	    System.out.println("Inserção bem sucedida.\n");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ERRO NO METODO SALVAR : VOID ");
        }
    }
    
    public void deletar(JTextField jtId) {
    	String query = "DELETE FROM jogos WHERE id = ?";
        Connection con;
            try {       
                con = getConecxao();
                PreparedStatement pstmt = con.prepareStatement(query);                   
                pstmt.setInt(1,Integer.valueOf(jtId.getText()));                    
                pstmt.executeUpdate();
                System.out.println("Exclusão bem sucedida.\n");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"ERRO NO METODO DELETAR : VOID ");
            }
    }
    
    public void alterar(JTextField jtNome,JTextField jtGenero,JTextField jtPlataforma,JTextField jtAnoLan
    		,JTextField jtDesenvolvedor,JTextField jtPublicador
    		,JTextField jtIdioma ,JTextField jtPreco, JTextField jtEstoque, JTextField jtId) {
    	Connection con;
        String query = "UPDATE Jogos SET nome = ?,"
                     + "genero = ?,"
                     + "plataforma = ?,"
                     + "anoLan = ?,"
                     + "desenvolvedor = ?,"
                     + "publicador = ?,"
                     + "idioma = ?,"
                     + "preco = ?,"
                     + "estoque = ? "
	                + "WHERE id = ?;";
        con = getConecxao();      
        try {    
            PreparedStatement pstmt = con.prepareStatement(query);
	    pstmt.setString(1,jtNome.getText());
            pstmt.setString(2,jtGenero.getText());
            pstmt.setString(3,jtPlataforma.getText());
            pstmt.setString(4,jtAnoLan.getText());
            pstmt.setString(5,jtDesenvolvedor.getText());
            pstmt.setString(6,jtPublicador.getText());
            pstmt.setString(7,jtIdioma.getText());
            pstmt.setFloat(8,Float.valueOf(jtPreco.getText()));
            pstmt.setInt(9,Integer.valueOf(jtEstoque.getText()));   
            pstmt.setInt(10,Integer.valueOf(jtId.getText()));             
	    pstmt.executeUpdate();
            System.out.println("Update bem sucedido!");
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null,"ERRO NOM METODO ALTERAR : VOID ");
            System.out.println(e.getMessage());
	}
    }
}