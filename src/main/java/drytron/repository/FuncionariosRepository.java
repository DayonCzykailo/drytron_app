package drytron.repository;

import drytron.dto.Endereco;
import drytron.dto.Funcionarios;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dayon
 */
public class FuncionariosRepository {

    private final EntityManager em;

    public FuncionariosRepository() {
        this.em = Persistence
                .createEntityManagerFactory("DrytronDataBase")
                .createEntityManager();
    }

    public Funcionarios pesquisaPeloId(Long id) {
        return em.find(Funcionarios.class, id);
    }

    public Endereco pesquisaEndereco(Long id) {
        return em.find(Funcionarios.class, id).getEndFun();
    }

    public List<Funcionarios> listaTodos() {
        List<Funcionarios> funcionarios = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT f FROM Funcionarios f");
            funcionarios = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("FuncionariosRepository: Ocorreu um problema no método listaTodos");
        }
        return funcionarios;
    }

    public List<Funcionarios> listaTodosAdminOrGerente() {
        List<Funcionarios> funcionarios = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT f FROM Funcionarios f where f.nivel =1 or f.nivel =3");
            funcionarios = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("FuncionariosRepository: Ocorreu um problema no método listaTodos");
        }
        return funcionarios;
    }

    public void listaPieChart(PieChart pcMain) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        List<Object[]> resultado = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT count(f.cargo),f.cargo FROM Funcionarios f group by f.cargo");
            resultado = query.getResultList();
            em.getTransaction().commit();
            PieChart.Data[] list = null;
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

    public List<Funcionarios> listaPorNome(String nome) {
        List<Funcionarios> funcionarios = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT f FROM Funcionarios f where f.nome like :nomeFuncionarios").setParameter("nomeFuncionarios", "%" + nome + "%");
            funcionarios = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("FuncionariosRepository: Ocorreu um problema no método listaTodos");
        }
        return funcionarios;
    }

    public List<Funcionarios> verificaPorNome(String nome) {
        List<Funcionarios> funcionarios = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT f FROM Funcionarios f where f.nome like :nomeFuncionarios").setParameter("nomeFuncionarios", nome);
            funcionarios = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("FuncionariosRepository: Ocorreu um problema no método listaTodos");
        }
        return funcionarios;
    }

    public void insere(Funcionarios funcionarios) {
        try {
            em.getTransaction().begin();
            funcionarios.getEndFun().setCep(funcionarios.getEndFun().getCep().replace("-", ""));
            em.persist(funcionarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("FuncionariosRepository: Ocorreu um problema no método insere");
        }
    }

    public void atualiza(Funcionarios funcionarios) {
        try {
            em.getTransaction().begin();
            funcionarios.getEndFun().setCep(funcionarios.getEndFun().getCep().replace("-", ""));
            em.merge(funcionarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("FuncionariosRepository: Ocorreu um problema no método atualiza");
        }
    }

    public void remove(Long id) {
        Funcionarios funcionarios = this.pesquisaPeloId(id);
        try {
            em.getTransaction().begin();
            em.remove(funcionarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("FuncionariosRepository: Ocorreu um problema no método remove");
        }
    }
}
