package drytron.dao;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.dto.Jogos;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dayon
 */
public class ClientesRepository {

    private EntityManager em;

    public ClientesRepository() {
        this.em = Persistence
                .createEntityManagerFactory("DrytronDataBase")
                .createEntityManager();
    }

    public Clientes pesquisaPeloId(Long id) {
        return em.find(Clientes.class, id);
    }

    public Endereco pesquisaEnderecoClientes(Long id) {
        return em.find(Clientes.class, id).getEndCli();
    }

    public List<Clientes> listaTodos() {
        List<Clientes> clientes = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Clientes c");
            clientes = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("ClientesRepository: Ocorreu um problema no método listaTodos");
        }
        return clientes;
    }

    public void listaPieChart(PieChart pcMain) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();;
        List<Object[]> resultado = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT count(e.uf),e.uf FROM Clientes c inner join Endereco e on c.endCli.id = e.id group by e.uf");
            resultado = query.getResultList();
            em.getTransaction().commit();
            Data[] list = null;
            for (Object[] obj : resultado) {             
                  pieChartData.add(new PieChart.Data(java.lang.String.valueOf(obj[1]), (Long) obj[0]));
               
            }
            

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("ClientesRepository: Ocorreu um problema no método listaPieChart");
        }
        pcMain.setData(pieChartData);
        System.out.println(resultado.toString());
    }

    public List<Clientes> listaPorNome(String nome) {
        List<Clientes> clientes = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c FROM Clientes c where c.nome like :nomeClientes").setParameter("nomeClientes", "%" + nome + "%");
            clientes = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ClientesRepository: Ocorreu um problema no método listaTodos");
        }
        return clientes;
    }

    public void insere(Clientes clientes) {
        try {
            em.getTransaction().begin();
            clientes.getEndCli().setCep(clientes.getEndCli().getCep().replace("-", ""));
            em.persist(clientes);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("ClientesRepository: Ocorreu um problema no método insere");
        }
    }

    public void atualiza(Clientes clientes) {
        try {
            em.getTransaction().begin();
            clientes.getEndCli().setCep(clientes.getEndCli().getCep().replace("-", ""));
            em.merge(clientes);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ClientesRepository: Ocorreu um problema no método atualiza");
        }
    }

    public void remove(Long id) {
        Clientes clientes = this.pesquisaPeloId(id);
        try {
            em.getTransaction().begin();
            em.remove(clientes);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ClientesRepository: Ocorreu um problema no método remove");
        }
    }

    //public void 
}
