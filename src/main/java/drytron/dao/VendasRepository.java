package drytron.dao;

import drytron.dto.Clientes;
import drytron.dto.Funcionarios;
import drytron.dto.Jogos;
import drytron.dto.Vendas;
import drytron.util.Mensagens;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dayon
 */
public class VendasRepository {

    private EntityManager em;

    public VendasRepository() {
        this.em = Persistence
                .createEntityManagerFactory("DrytronDataBase")
                .createEntityManager();
    }

    public Vendas pesquisaPeloId(Long id) {
        return em.find(Vendas.class, id);
    }

    public Funcionarios pesquisaFuncionarios(Long id) {
        return em.find(Vendas.class, id).getVendedor();
    }

    public Jogos pesquisaProduto(Long id) {
        return em.find(Vendas.class, id).getProduto();
    }

    public Clientes pesquisaClientes(Long id) {
        return em.find(Vendas.class, id).getComprador();
    }

    public List<Vendas> listaTodos() {
        List<Vendas> vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v FROM Vendas v");
            vendas = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos");
        }
        return vendas;
    }

    public List<Object[]> listaTodosScatterChart() {
        List<Object[]> vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT SUM(v.valorFinal), v.vendedor.nome FROM Vendas v group by v.vendedor.nome");
            vendas = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos");
        }
        return vendas;
    }

    public List<Vendas> listaTodosPorAtivo(char ativo) {
        List<Vendas> vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v FROM Vendas v where v.ativo = :ativo").setParameter("ativo", ativo);
            vendas = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos");
        }
        return vendas;
    }

    public List<String> listaTodosProdutosPesquisa() {
        List<String> produtos = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v.produto.nome FROM Vendas v");
            produtos = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos");
        }
        return produtos;
    }

    public List<String> listaTodosProdutos() {
        List<String> produtos = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT j.nome FROM Jogos j");
            produtos = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos");
        }
        return produtos;
    }

    public List<Vendas> listaPorProdutoPesquisa(String produto) {
        List<Vendas> vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v FROM Vendas v where v.produto.nome like :produto").setParameter("produto", "%" + produto + "%");
            vendas = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos por Produto");
        }
        return vendas;
    }

    public Funcionarios listaPorVendedor(String vendedor) {
        Funcionarios vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v.vendedor FROM Vendas v where v.vendedor.nome like :vendedor").setParameter("vendedor", vendedor);
            vendas = (Funcionarios) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos por Vendedor");
        }
        return vendas;
    }

    public Clientes listaPorComprador(String comprador) {
        Clientes vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v.comprador FROM Vendas v where v.comprador.nome like :comprador").setParameter("comprador", comprador);
            vendas = (Clientes) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos por Comprador");
        }
        return vendas;
    }

    public Jogos listaPorProduto(String produto) {
        Jogos vendas = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT v.produto FROM Vendas v where v.produto.nome like :produto").setParameter("produto", produto);
            vendas = (Jogos) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos por Produto");
        }
        return vendas;
    }

    public List<Vendas> listaPorData(String data) {
        List<Vendas> vendas = null;
        try {
            data = data + "T23:59:59";
            Query query = em.createQuery("SELECT v FROM Vendas v where v.dataCompra <= :data").setParameter("data", LocalDateTime.parse(data));
            vendas = query.getResultList();
        } catch (Exception e) {
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos por Data : " + e.getMessage());
        }
        return vendas;
    }

    public List<Vendas> listaPorData() {
        List<Vendas> vendas = null;
        try {
            Query query = em.createQuery("SELECT v FROM Vendas v where v.dataCompra <= :data order by  v.dataCompra asc").setParameter("data", LocalDateTime.now());
            vendas = query.getResultList();
        } catch (Exception e) {
            System.out.println("VendasRepository: Ocorreu um problema no método listaTodos por Data : " + e.getMessage());
        }
        return vendas;
    }

    public void insere(Vendas vendas) {
        try {
            if (vendas.getComprador() == null) {
                Mensagens.mensagemAlerta("COMPRADOR INVÁLIDO", "ESCOLHA UM CLIENTE VÁLIDO");
            } else if (vendas.getProduto() == null) {
                Mensagens.mensagemAlerta("PRODUTO INVÁLIDO", "ESCOLHA UM JOGO VÁLIDO");
            }            
            em.getTransaction().begin();
            em.persist(vendas);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
            System.out.println("VendasRepository: Ocorreu um problema no método insere :" + e.getMessage());
        }
    }

    public void atualiza(Vendas vendas) {
        try {
            em.getTransaction().begin();
            em.merge(vendas);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("VendasRepository: Ocorreu um problema no método atualiza");
        }
    }

    public void remove(Long id) {
        Vendas vendas = this.pesquisaPeloId(id);
         if (vendas == null || id == null || id == 0) {
            Mensagens.mensagemAlerta("ID Inválido!", "Insira um ID válido, novamente.");
        }
        try {
            em.getTransaction().begin();
            em.remove(vendas);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("VendasRepository: Ocorreu um problema no método remove");
        }
    }
}
