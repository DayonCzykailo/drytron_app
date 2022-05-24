package drytron.dao;

import drytron.dto.Funcionarios;
import java.util.List;
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
