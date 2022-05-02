package drytron.dao;

/**
 *
 * @author dayon
 */
import drytron.dto.Clientes;
import drytron.dto.Jogos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientesDAO {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DrytronDataBase");

    public static EntityManager em = entityManagerFactory.createEntityManager();

    public void inserir(Jogos jogo) {

        try {
            

            em.getTransaction().begin();
            em.persist(jogo);
            em.getTransaction().commit();
            em.close();
            entityManagerFactory.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*public ArrayList<Clientes> listaClientes() {
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        try {
            Connection con;
            con = getConecxao();
            String query = "SELECT * FROM clientes";
            Statement st;
            ResultSet rt;
            st = con.createStatement();
            rt = st.executeQuery(query);
            Clientes clientes;
            while (rt.next()) {
                clientes = new Clientes(rt.getInt("idCli"), rt.getString("nomeCli"), rt.getString("cpfCli"), rt.getString("telCli"),
                         rt.getString("emaCli"), rt.getString("endCli"), rt.getString("estCli"));
                listaClientes.add(clientes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO NO METODO LISTACLIENTES : ARRAYLIST<CLIENTES> ");
        }
        return listaClientes;
    }

    public void mostrarclientes(JTable jtPlanilha) {//sem uso
        try {
            ArrayList<Clientes> lista = listaClientes();
            DefaultTableModel model = (DefaultTableModel) jtPlanilha.getModel();
            Object[] row = new Object[10];
            for (int i = 0; i < lista.size(); i++) {
                row[0] = lista.get(i).getIdCli();
                row[1] = lista.get(i).getNomeCli();
                row[2] = lista.get(i).getCpfCli();
                row[3] = lista.get(i).getTelCli();
                row[4] = lista.get(i).getEmaCli();
                row[5] = lista.get(i).getEndCli();
                row[6] = lista.get(i).getEstCli();
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO NO METODO MOSTRARCLIENTES : VOID ");
        }

    }*/

}
