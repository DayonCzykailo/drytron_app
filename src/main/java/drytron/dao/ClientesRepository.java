package drytron.dao;

import drytron.dto.Clientes;
import drytron.dto.Endereco;
import drytron.util.Mensagens;
import drytron.util.ValidaDados;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
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

    public Endereco pesquisaEndereco(Long id) {
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

    public List<Clientes> listaTodosNomes() {
        List<Clientes> clientes = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT c.nome FROM Clientes c");
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
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
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
    }

    public List<Clientes> listaPorNome(String nome) {
        List<Clientes> clientes = null;
        try {
            Query query = em.createQuery("FROM Clientes c where c.nome like :nomeClientes").setParameter("nomeClientes", "%" + nome + "%");
            clientes = query.getResultList();
        } catch (Exception e) {
            System.out.println("ClientesRepository: Ocorreu um problema no método listaTodos");
        }
        return clientes;
    }

    public void insere(Clientes clientes) {
        try {
            if (ValidaDados.validaCpf(clientes.getCpf())) {
                Mensagens.mensagemAlerta("CPF INVALIDO!", "Insira um CPF valido, novamente.");
                return;
            } else if (clientes.getEndCli().getCep().isBlank() || clientes.getEndCli().getCep().isEmpty()) {
                Mensagens.mensagemAlerta("CEP VAZIO!", "Insira um CEP valido, novamente.");
            }
            clientes.setCpf(clientes.getCpf().replace(".", "").replace("-", ""));
            if (clientes.getCpf().length() != 11) {
                Mensagens.mensagemAlerta("O TAMANHO DO CPF ESTÁ INCORRETO!", "Insira um CPF valido, novamente.");
            }
            clientes.setTelefone(clientes.getTelefone().replace(")", "").replace("(", "").replace("-", ""));
            clientes.getEndCli().setCep(clientes.getEndCli().getCep().replace("-", ""));

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
            if (ValidaDados.validaCpf(clientes.getCpf())) {
                Mensagens.mensagemAlerta("CPF INVALIDO!", "Insira um CPF valido, novamente.");
                return;
            } else if (clientes.getEndCli().getCep().isBlank() || clientes.getEndCli().getCep().isEmpty()) {
                Mensagens.mensagemAlerta("CEP VAZIO!", "Insira um CEP valido, novamente.");
            }
            clientes.setCpf(clientes.getCpf().replace(".", "").replace("-", ""));
            if (clientes.getCpf().length() != 11) {
                Mensagens.mensagemAlerta("O TAMANHO DO CPF ESTÁ INCORRETO!", "Insira um CPF valido, novamente.");
            }
            clientes.setTelefone(clientes.getTelefone().replace(")", "").replace("(", "").replace("-", ""));
            clientes.getEndCli().setCep(clientes.getEndCli().getCep().replace("-", ""));
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
        if (clientes == null || id == null || id == 0) {
            Mensagens.mensagemAlerta("ID Inválido!", "Insira um ID válido, novamente.");
        }
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
