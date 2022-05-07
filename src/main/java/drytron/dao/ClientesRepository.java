package drytron.dao;

import drytron.dto.Clientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    public void insere(Clientes clientes) {
        try {
            em.getTransaction().begin();
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
}
