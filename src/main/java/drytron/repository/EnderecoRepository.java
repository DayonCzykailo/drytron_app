package drytron.repository;

import drytron.dto.Endereco;
import drytron.util.Mensagens;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dayon
 */
public class EnderecoRepository {

    private EntityManager em;

    public EnderecoRepository() {
        this.em = Persistence
                .createEntityManagerFactory("DrytronDataBase")
                .createEntityManager();
    }

    public Endereco pesquisaPeloId(Long id) {
        return em.find(Endereco.class, id);
    }

    public List<Endereco> listaTodos() {
        List<Endereco> endereco = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT e FROM Endereco e");
            endereco = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("EnderecoRepository: Ocorreu um problema no método listaTodos");
        }
        return endereco;
    }

    public void insere(Endereco endereco) {
        try {
            endereco.setCep(endereco.getCep().replace("-", ""));
            em.getTransaction().begin();
            em.persist(endereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("EnderecoRepository: Ocorreu um problema no método insere");
        }
    }

    public void atualiza(Endereco endereco) {
        try {
            em.getTransaction().begin();
            endereco.setCep(endereco.getCep().replace("-", ""));
            em.merge(endereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("EnderecoRepository: Ocorreu um problema no método atualiza");
        }
    }

    public void remove(Long id) {
        Endereco endereco = this.pesquisaPeloId(id);
        if (endereco == null || id == null || id == 0) {
            Mensagens.mensagemAlerta("ID Inválido!", "Insira um ID válido, novamente.");
        }
        try {
            em.getTransaction().begin();
            em.remove(endereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("EnderecoRepository: Ocorreu um problema no método remove");
        }
    }
}
