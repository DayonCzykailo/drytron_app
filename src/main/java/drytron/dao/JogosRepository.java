package drytron.dao;

import drytron.dto.Jogos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dayon
 */
public class JogosRepository {

    private EntityManager em;

    public JogosRepository() {
        this.em = Persistence
                .createEntityManagerFactory("DrytronDataBase")
                .createEntityManager();
    }

    public Jogos pesquisaPeloId(Long id) {
        return em.find(Jogos.class, id);
    }

    public List<Jogos> listaTodos() {
        List<Jogos> jogos = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT j FROM Jogos j");
            jogos = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("JogosRepository: Ocorreu um problema no método listaTodos");
        }
        return jogos;
    }

    public void insere(Jogos jogos) {
        try {
            em.getTransaction().begin();
            em.persist(jogos);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("JogosRepository: Ocorreu um problema no método insere");
        }
    }

    public void atualiza(Jogos jogos) {
        try {
            em.getTransaction().begin();
            em.merge(jogos);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("JogosRepository: Ocorreu um problema no método atualiza");
        }
    }

    public void remove(Long id) {
        Jogos jogos = this.pesquisaPeloId(id);
        try {
            em.getTransaction().begin();
            em.remove(jogos);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("JogosRepository: Ocorreu um problema no método remove");
        }
    }
}
